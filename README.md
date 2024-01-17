**# DAI_labo_HTTP
Develop a complete infrastructure with static and dynamic Web servers, running on docker-compose

## Step 1: Static Web site


## Step 2: Docker compose


## Étape 3 : serveur HTTP api 
Nous avons développé une api CRUD permettant gérer un dresseur de Pokémon.

Elle a été développée en Java et utilise le framework Javalin pour l'envoi des requêtes HTML.

### UML

![UML_DAI.png](\images\UML_DAI.png)

### Description de l'API

| Méthode | URL              | Descirpition                                                                                                |
|---------|------------------|-------------------------------------------------------------------------------------------------------------|
| GET     | /api/trainers| Donne la liste de tous les dresseurs.                                                                       |
| GET     | /api/trainers/{id}| Donne les informations du dresseur correspondant à l'id fourni.                                             |
| POST    | /api/trainers/| Crée un dresseur avec les informations fournies dans le corps de la requête.                                |
| PUT     | /api/trainers/{id}| Modifie le dresseur correspondant à l'id fourni avec les informations fournies dans le corps de la requête. |
| DELETE  | /api/trainers/{id}| Supprime le dresseur de la liste.                                                                           |

Les informations sont transmises au format JSON.

### Exemples
Les tests pour les exemples ont été mené avec Bruno.

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
L'api a été dockerisée et ajoutée à la configuration docker compose de l'étape précédente. 

```dockerfile
FROM eclipse-temurin:latest
COPY target/HTTP_API_Server-1.0-SNAPSHOT-jar-with-dependencies.jar /app/app.jar

EXPOSE 7002
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
```
Dans notre Dockerfile :
- nous définissons l'image de base eclipse-temurin
- nous copions le fichier JAR exécutable de l'application dans le répertoire /app de l'image
- nous exposons le port 7002 sur le conteneur Docker, indiquant que l'application à l'intérieur du conteneur écoute sur ce port
- nous définissons que la commande "java -jar /app/app.jar" soit exécutée lorsque le conteneur est démarré.

```text
 api:
  build: ./../HTTP_API_Server
  ports:
  - "7002:7002"
```
Nous avons ajouté le service "api" ci-dessus à la configuration Docker Compose.

Pour lancer l'api avec Docker, il faut : 

- exécuter la commande suivante depuis \HTTP_API_Server : 
```text
mvn clean package
```
Ceci va créer le fichier exécutable .jar de notre API avec toutes les dépendances requises.

- exécuter les commandes suivantes depuis \HTTP_API_Server :
```text
docker build -t myapi .

docker run -p 7002:7002 myapi
```
Ceci permet de construire l'image Docker et de la démarrer.

- ou avec Docker Compose à l'aide des commandes suivantes depuis /DockerCompose :
```text
docker compose build

docker compose up
```
Ceci permet de construire l'image Docker de notre api, mais aussi de nos sites et de la démarrer.

## Step 4: Reverse proxy avec Traefik


```yaml
version: '3.8'

services:
  traefik:
    image: traefik
    command:
      - "--api.insecure=true"
      - "--providers.docker=true"
      - "--providers.docker.exposedbydefault=false"
    ports:
      - "80:80"
      - "8080:8080"
    volumes:
      - /var/run/docker.sock:/var/run/docker.sock

  static_server_a:
    image: mynginx
    build: ../StaticWebServer
    expose:
      - "80"
    volumes:
      - ./../DockerCompose/site-a:/usr/share/nginx/html
    labels:
      - "traefik.enable=true"
      - "traefik.http.routers.static_server_a.rule=Host(`site-a.localhost`)"

  static_server_b:
    image: mynginx
    expose:
      - "80"
    volumes:
      - ./../DockerCompose/site-b:/usr/share/nginx/html
    labels:
      - "traefik.enable=true"
      - "traefik.http.routers.static_server_b.rule=Host(`site-b.localhost`)"



  api_server:
    build: ./../HTTP_API_Server
    expose:
      - "7002"
    labels:
      - "traefik.enable=true"
      - "traefik.http.routers.api_server.rule=Host(`localhost`) && PathPrefix(`/api`)"
```

Nous avons ajouté un reverse proxy à la configuration Docker Compose précédente et nous l'avons adaptée. 

L'ajout du reverse proxy avec l'image traefik a nécessité quelques adaptations pour le reste. 
- Nos serveurs statiques (static_server_a et static_server_b) ainsi que dynamique (api_server) ont vu leur champ "ports" modifié en "expose" pour indiquer les ports qui seront accessibles depuis d'autres services dans le réseau Docker interne.
- Des champs labels ont également été ajouté afin d'activer Traefik pour chaque service et définir des règles de routage spécifiques.

Il est désormais possible :
- d'accéder au site a à l'adresse http://site-a.localhost/
- d'accéder au site b à l'adresse http://site-b.localhost/
- d'accéder au Dashboard traefik à l'adresse http://localhost:8080/Dashboard
- d'accéder d'envoyer de requête à notre api à l'adresse http://localhost/api

## Step 5: Scalability and load balancing

## Step 6: Load balancing with round-robin and sticky sessions

## Step 7: Securing Traefik with HTTPS**

## Étape facultative 1 : Interface utilisateur de gestion

```yaml  
  portainer:
    image: portainer/portainer-ce
    ports:
      - "9443:9443"
      - "9000:9000"
    volumes:
      - "/var/run/docker.sock:/var/run/docker.sock"
    restart: unless-stopped
```
Nous avons ajouté un service portainer avec une image portainer-ce à notre configuration Docker Compose. 
Portainer est une interface utilisateur de gestion Docker qui permet de visualiser et de gérer les conteneurs, 
les images, les réseaux et d'autres ressources Docker de manière graphique. Voici une explication des éléments ajoutés :
- le port 9443 est mappé pour la connexion HTTPS 
- le port 9000 est mappé pour la connexion HTTP
- le socket Docker (/var/run/docker.sock) est monté à l'intérieur du conteneur Portainer. Cela permet à Portainer d'interagir directement avec le démon Docker de l'hôte, facilitant ainsi la gestion des conteneurs
- le redémarrage du service "portainer" est configuré pour qu'il soit automatique sauf s'il est explicitement arrêté (unless-stopped). Cela garantit que le service Portainer sera relancé automatiquement en cas de défaillance ou de redémarrage du système hôte.

### Utilisation de Portainer 
En allant à l'adresse http://localhost:9000 ou https://localhost:9000 et une fois connecté, il est possible par exemple de dresser la liste des conteneurs en cours d'exécution et de les démarrer/arrêter.
Exemple de comment démarrer/arrêter les conteneurs sur Portainer.
![img1_portainer.PNG](images\img1_portainer.PNG)
![img3_portainer.PNG](images\img2_portainer.PNG)
![img2_portainer.PNG](images\img3_portainer.PNG)
![img4_portainer.PNG](images\img4_portainer.PNG)