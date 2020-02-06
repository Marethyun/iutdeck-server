# Description du système de multijoueur

Il y a trois acteurs dans le système de multijoueur:
- **Le service d'authentification** (abrégé **A**), qui est en fait un service central officiel. Ce service gère l'authentification, le répertoire de serveurs, et par extension la base de données.
- **Le serveur** (abrégé **S**), ou plutôt les serveurs, car il peut y en avoir plusieurs et même des serveurs non-officiels. C'est le serveur de jeu qui gère les calculs et les intéractions entre les joueurs. Il possèdent chacun un ou plusieurs salons grâce auxquels les joueurs se rejoignent pour lancer une partie.
- **Le client** (le jeu, abrégé **C**). Il gère uniquement le rendu visuel et l'interface utilisateur en interprêtant les messages des serveurs.

La communication entre les différents acteurs est faite en JSON à travers le réseau et le protocole TCP (et par dessus SSL pour le chiffrement). Ces messages sont référencés comme des évènements dans le contexte d'une partie lancée. Un message a un nom et des propriétés.

Un message (peu importe le contexte) est transmis en JSON sous les formes:
```json
{
    "nom_message" : {
        "propriété1" : "valeur1",
        "propriété2" : "valeur2"
    }
}
```
Ou
```json
{
    "name" : "nom_message",
    "properties" : {
        "propriété1" : "valeur1",
        "propriété2" : "valeur2"
    }
}
```
Lorsqu'on a besoin d'y faire référence indirectement (publication des historiques de partie, association avec un joueur, etc..)

Les temps (temps de début, dates de créations, etc.) sont toujours exprimées en timestamp UNIX pour faciliter la transmission.

## Messages suivant le modèle requête/réponse

Lorsque l'interpellé répond à l'interpellant, il termine la connexion.

> `ok()`

Message sans paramètres, notifie l'autre partie que l'on a bien traité une requête.
Ce message n'est pas utilisé pour répondre à des évènements de jeu.

> `error(code, message)`

Ce message est commun à tous les acteurs et permet de notifier d'une erreur. Le code associé permet d'interprêter facilement la cause de l'erreur.

> `auth(username, password)` **C** vers **A**

Demande l'authentification du joueur, en transmettant son nom d'utilisateur et son mot de passe.

**A** peut répondre par un message d'erreur `error` lorsque l'authentification a échoué. 

> `user_token(token)` **A** vers **C**

Suite à un message `auth` et au traitement avec succès de l'authentification, **A** répond avec un jeton d'utilisateur. Ce jeton est un UUID représentant la session du joueur auprès du service d'authentification. Le joueur devra le renvoyer pour modifier son compte, demander à se connecter à une partie ou se déconnecter.

**C** doit garder précieusement ce jeton, ou il devra se réauthentifier.

> `servers_list(user_token)` **C** vers **A**

Le client demande une liste de serveurs en fournissant son jeton d'utilisateur.

> `servers(list)` **A** vers **C**

**A** répond avec la liste de serveurs, qui est un tableau d'objets JSON de la forme:

```json
{
    "name" : "un super serveur!",
    "address" : "chihuahua.iut-deck.fr",
    "port" : 4257,
    "official" : true
}
```

> `poke(name, address, port)` **S** vers **A**

Un serveur de jeu notifie sa présence à **A**. Cela permet à tout serveur non-officiel de faire partie de la liste envoyée aux joueurs.

Un serveur de jeu doit rappeler régulièrement sa présence au service central **A**, car **A** se débarrasse des serveurs qui ne se sont pas signalés depuis un certain temps.

Après la réception de ce message, **A** répond avec un message `ok` pour signifier sa compréhension.

> `push_history(application_token, player1_id, player2_id, winner_id?, time_started, time_ended, events)` **S** vers **A**

Envoi d'une partie à **A** lorsqu'elle est terminée, l'ajoutant ainsi à l'historique des parties jouées.

Ce message n'est autorisé qu'aux serveurs officiels, qui possèdent un jeton d'application (`application_token`). Ce jeton est un UUID stocké en permanence sur les serveurs de jeu officiels, ayant une correspondance dans la base de données officielle de **A**.

`winner_id` peut être nul, lorsqu'il n'y a pas de gagnant.

`events` est un tableau d'objets JSON contenant les évènements du jeu.
Dans ce contexte, évènement est représenté par la deuxième forme utilisée pour représenter un message, ils sont associés à leur timestamp d'apparition et à des informations contextuelles.
Exemple d'un évènement illustrant la reddition du joueur n°123456789:

```json
{
    "name" : "surrender",
    "player" : 123456789,
    "time_fired" : 1000198800,
    "properties" : {}
}
```

> `prepare_game(user_token)` **C** vers **A**

**C** demande à **A** de préparer son authentification à une partie en s'authentifiant à l'aide de son jeton d'utilisateur.

> `game_token(token)` **A** vers **C**

**A** répond à un message `prepare_game` avec un un jeton de partie si la préparation s'est faite avec succès.

C'est ce jeton de partie que **C** doit envoyer à **S** lorsqu'il veut rejoindre un salon.

Ce jeton est à usage unique.

> `check_game(game_token)` **S** vers **A**

Lancé par un serveur de jeu lorsqu'il veut vérifier l'authentification d'un joueur lors de sa connexion, grâce à son jeton de partie (`game_token`).

Si la vérification de la partie a réussi, **A** détruit le jeton définitivement

> `user(user_id, pseudonym)` **A** vers **S**

Message envoyé par **A** à un serveur de jeu **S** suite à un message `check_game` dont le traitement a réussi.

Ainsi, **S** aura vérifié l'identité du joueur et aura récupéré son identifiant et son pseudonyme.

> `info()` vers **S**

Demande d'informations concernant **S**.

> `server_info(name, online, rooms)` depuis **S**

**S** répond à une requête `info` avec son nom, si il est configuré pour vérifier les connexions (paramètre booléen `online`), et la liste de ses salons.
Cette liste est un tableau JSON d'objets représentant des salons.
Exemple de salon:

```json
{
    "id" : 4,
    "slots_available" : 1
}
```

L'identifiant des salons est important car il permet aux joueurs de les différencier si un serveur en possède plusieurs. Cet identifiant est un entier court non signé déterminé arbitrairement par le serveur.

## Messages de partie

### Messages d'initialisation

Ces messages initialisent la connexion entre **C** et **S** pour une partie. Lorsque leur traitement est réussi, la connexion reste active.

> `join_room(room_id, game_token)` **C** vers **S**

Le client demande à rejoindre un salon en donnant son jeton de partie, que **S** vérifie auprès de **A** utilisant le message `check_game`.

Si la connexion au salon est réussie, **S** répond avec un message `ok`.

> `join_room_offline(room_id, pseudonym)` **C** vers **S**

La même chose que `join_room` lorsque **S** est en mode 'hors-ligne' et ne vérifie pas l'identité du joueur auprès de **A**.

### Messages de création de la partie

Ces messages sont lancés dans un contexte de jeu, du moment où le client **C** rejoint un salon auprès du serveur **S**.

> `opponent_event(event)` **S** vers **C**

Évènement en encapsulant un autre, permet de notifier un évènement concernant l'adversaire.
Par exemple, notifier que c'est désormais le tour de l'adversaire:

```json
{
    "opponent_event" : {
        "name" : "your_turn",
        "properties" : {}
    }
}
```

> `quit_room()` **C** <> **S**

**C** décide de quitter le salon, ou **S** l'expulse.
La connexion se termine après l'échange.

> `ready(ready)` **C** <> **S**

Lorsque **C** est dans un salon, change son état (prêt à lancer le chargement de la partie ou pas).

> `game_starting()` **S** vers **C**

Notifie que la partie démarre

> `initialize(game_objects)`

Envoie la liste des objets de jeu sous la forme d'un tableau JSON. Un objet de jeu (gameobject) est représenté de cette manière: (exemple d'une carte)

```json
{
    "identifier" : 1234,
    "parent" : 5678,
    "type" : "CARD",
    "properties" : {
        "card_id" : 9101,
        "info" : 6,
        "defense" : 3
    }
}
```

Le client **C** concerné peut ainsi construire l'arbre des objets et les afficher en conséquence.

(voir les informations concernant la structure du client et aux game objects)

> `all_set()` **C** <> **S**

Lorsque les deux **C** connectés ont terminé de charger les éléments de jeu et que la partie peut être lancée, ils envoient un évènement `all_set` à **S** pour le notifier qu'ils ont tout reçu et que la partie peut commencer.

> `game_started()`

Notifie que la partie a commencé.

> `your_turn()` **S** vers **C**

Évènement notifiant au joueur concerné que c'est à son tour de jouer.

> `cast(object_id, target_id)` **C** <> **S**

Notifie qu'un élément est casté sur un autre.

(voir mécaniques de cast, TODO à compléter)

> `state(card_id, state)` **C** <> **S**

Notifie que la carte spécifiée change d'état (carte ou défense).

> `surrender()` ou `ff()` **C** <> **S**

Notifie que le joueur concerné se rend.

> `object_change(object_id, properties)` **S** vers **C**

Notifie aux clients que les propriétés d'un objet de jeu changent.
Par exemples: l'info d'une carte augmente, la mana d'un joueur diminue, les stats du prof changent, etc.

> `game_end(reason_code)` **S** vers **C**

Notifie aux clients que la partie se termine.
Cet évènement est accompagné d'un code faisant référence à la raison de la fin de partie: une reddition, un problème de réseau, un joueur a gagné, etc..

> `object(object_id, type, parent, properties)` **S** vers **C**

Notifie de l'insertion d'un nouvel objet côté client.
Il ne s'agit pas forcément de la "création" d'un nouvel objet: cet évènement permet d'ajouter un objet dans la structure du jeu.