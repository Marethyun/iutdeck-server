# Serveur d'authentification (ou serveur de base de données):

## Requête d'authentification (utilisateur)

auth(user, password)

```json
{
    "auth" : {
        "user" : "tartenpion",
        "password" : "azerty"
    }
}
```

## Réponse, envoi de token d'authentification

auth_token(token)

Token de 32 caractères (minuscules, majuscules, chiffres, caractères spéciaux)

```json
{
    "auth_token" :  {
        "token" : "!-A1SN+5Y5X1tu4U&u|RKT56|$QIsus#"
    }
}
```

## Requête

## Réponse, envoi d'erreur

error(code, message)

Le code d'erreur est variable, et sert à interprêter l'erreur plus facilement.

```json
{
    "error" : {
        "code" : 27,
        "message" : "Invalid credentials"
    }
}
```

### Messages d'erreur possibles:

| code | message |
| ---- | ------- |
| 0    | Invalid credentials |
| 1    | Invalid token |


TODO WIP