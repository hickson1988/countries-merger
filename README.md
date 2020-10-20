# Quarkus Countries Merger

This is a sample app that exposes a rest endpoint, which consumes asynchronously services from the https://restcountries.eu API and merges their results before being returned.
The technologies used are Java 11, Quarkus, RestEasy and Mutiny for async processing
Furthermore, the endpoint is secured using OpenId Connect and Keycloak as authorization server.

## Running Keycloak authorization server
In order to run a keycloak container instance with a default user admin/admin execute the below command:
```shell script
docker run --name keycloak -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin -p 8180:8080 jboss/keycloak
```
When keycloak has started visit http://localhost:8180/auth and login as admin/admin.
Then import the realm located at .misc/my_realm.json

## Running the application in dev mode

You can run the application in dev mode using:
```shell script
./mvnw compile quarkus:dev
```

## Testing the application

You can test the application by using the exported postman collection CountriesMerger.postman_collection.json located in .misc folder.
The 'Keycloak' endpoint is used to retrieve a valid access token from keycloak. 
Copy the access_token from the response and place it as a bearer authorization token at the 'Countries' request
