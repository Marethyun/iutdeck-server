https://stackoverflow.com/questions/39304612/c-sharp-ssl-tls-with-socket-tcp

Il y a trois acteurs dans le système de multijoueur:
- **Le service d'authentification** (abrégé **A**), qui est en fait un service central officiel. Ce service gère l'authentification, le répertoire de serveurs, et par extension la base de données.
- **Le serveur** (abrégé **S**), ou plutôt les serveurs, car il peut y en avoir plusieurs et même des serveurs non-officiels. C'est le serveur de jeu qui gère les calculs et les intéractions entre les joueurs. Il possèdent chacun un ou plusieurs salons grâce auxquels les joueurs se rejoignent pour lancer une partie.
- **Le client** (le je, abrégé **C**). Il gère uniquement le rendu visuel et l'interface utilisateur en interprêtant les messages des serveurs.

La communication entre les différents acteurs est faite en JSON à travers le réseau et le protocole TCP (et par dessus SSL pour le chiffrement). Ces messages sont référencés comme des évènements dans le contexte d'une partie lancée.

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
[...]
{
    "message_name" : "nom_message",
    "properties" : {
        "propriété1" : "valeur1",
        "propriété2" : "valeur2"
    }
}
[...]
```

Lorsqu'on a besoin d'y faire référence indirectement (publication des historiques de partie, association avec un joueur, etc..)

Les évènements sont les suivants:

> `auth(username, password)` - **C vers A**

Demande l'authentification du joueur, en transmettant son nom d'utilisateur et son mot de passe.

> `auth_tokens(user_token, game_token)` - **A** -> **C**