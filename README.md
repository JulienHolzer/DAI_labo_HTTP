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
    "firstname": "Kilian",
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
    "firstname": "Julien",
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
  "firstname": "Kilian",
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

#### POST /api/trainers
corps de la requête: 
```json
{
  "name": "Ketchum",
  "firstname": "Sacha",
  "birthdate": "1996-02-27",
  "team": [
    {
      "pokedexnumber": 25,
      "name": "Pikachu",
      "weight": 6.0,
      "size": 0.6,
      "type1": "ELECTRIC",
      "type2": null
    }
  ]
}
```

résultat :
Le dresseur est ajouté à la liste.

#### PUT /api/trainers/1
corps de la requête:
```json
{
  "name": "Azuria",
  "firstname": "Ondine",
  "birthdate": "1998-12-01",
  "team": [
    {
      "pokedexnumber": 120,
      "name": "Stari",
      "weight": 34.5,
      "size": 0.8,
      "type1": "WATER"
    }
  ]
}
```
résultat : 
modifie le dresseur d'id 1 en ondine et ses carctéristiques

#### DELETE /api/trainers/1
résultat :
supprime le dresseur d'id 1 et ses carctéristiques

### Dockeristation de l'api
L'api a été dockerisée et peut être lancée avec Docker. Pour ça, il faut : 

- lancer la commande suivante depuis \HTTP_API_Server : 
```text
mvn clean package
```
Ceci va créer le fichier exécutable .jar de notre API avec toutes les dépendances requises.

- lancer les commandes suivantes depuis \HTTP_API_Server :
```text
docker build -t myapi .

docker run -p 7002:7002 myapi
```
Ceci permet de construire l'image Docker et de la lancer.

- ou avec Docker Compose à l'aide des commandes suivantes depuis /DockerCompose :
```text
docker compose build

docker compose up
```
Ceci permet de construire l'image Docker de notre api, mais aussi de nos sites et de la lancer.



## Step 4: Reverse proxy with Traefik

## Step 5: Scalability and load balancing

## Step 6: Load balancing with round-robin and sticky sessions

## Step 7: Securing Traefik with HTTPS**
