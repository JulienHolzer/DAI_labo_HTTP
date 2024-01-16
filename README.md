**# DAI_labo_HTTP
Develop a complete infrastructure with static and dynamic Web servers, running on docker-compose

## Step 1: Static Web site


## Step 2: Docker compose


## Étape 3 : serveur HTTP api 
Nous avons développé une api CRUD permettant gérer un dresseur de Pokémon.

| Méthode | URL              | Descirpition                                                                                                |
|---------|------------------|-------------------------------------------------------------------------------------------------------------|
| GET     | /api/trainers| Donne la liste de tous les dresseurs.                                                                       |
| GET     | /api/trainers/{id}| Donne les informations du dresseur correspondant à l'id fourni.                                             |
| POST    | /api/trainers/| Crée un dresseur avec les informations fournies dans le corps de la requête.                                |
| PUT     | /api/trainers/{id}| Modifie le dresseur correspondant à l'id fourni avec les informations fournies dans le corps de la requête. |
| DELETE  | /api/trainers/{id}| Supprime le dresseur de la liste.                                                                           |

Les informations sont transmises au format JSON.

### Exemples

#### GET /api/trainers
résultat :
```json
{
  "1": {
    "name": "Demont",
    "prenom": "Kilian",
    "birthdate": "2002-02-10",
    "team": [
      {
        "pokedexnumber": 150,
        "name": "Mewtwo",
        "weight": 122,
        "size": 2,
        "type1": "PSYCHIC",
        "type2": null
      },
      {
        "pokedexnumber": 131,
        "name": "Lokhlass",
        "weight": 220,
        "size": 2.5,
        "type1": "WATER",
        "type2": "ICE"
      }
    ]
  },
  "2": {
    "name": "Holzer",
    "prenom": "Julien",
    "birthdate": "2001-03-10",
    "team": [
      {
        "pokedexnumber": 150,
        "name": "Mewtwo",
        "weight": 122,
        "size": 2,
        "type1": "PSYCHIC",
        "type2": null
      },
      {
        "pokedexnumber": 131,
        "name": "Lokhlass",
        "weight": 220,
        "size": 2.5,
        "type1": "WATER",
        "type2": "ICE"
      }
    ]
  }
}
```

#### GET /api/trainers/1
résultat :
```json
{
  "name": "Demont",
  "prenom": "Kilian",
  "birthdate": "2002-02-10",
  "team": [
    {
      "pokedexnumber": 150,
      "name": "Mewtwo",
      "weight": 122,
      "size": 2,
      "type1": "PSYCHIC",
      "type2": null
    },
    {
      "pokedexnumber": 131,
      "name": "Lokhlass",
      "weight": 220,
      "size": 2.5,
      "type1": "WATER",
      "type2": "ICE"
    }
  ]
}
```

#### POST /api/trainers/
corps de la requête: 


résultat :
Le dresseur est ajouté à la liste.


## Step 4: Reverse proxy with Traefik

## Step 5: Scalability and load balancing

## Step 6: Load balancing with round-robin and sticky sessions

## Step 7: Securing Traefik with HTTPS**
