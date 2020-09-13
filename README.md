# Chat application

## Features

This project uses 

* Chat application, send message to chat and it will be delivered to the respective queue.
* List of messages and participants in a chat
* spring boot health `/actuator/health` that displays app status.
* spring boot info `/actuator/info` that displays app info including version.
* Small js client to test web socket integration, currently using only two participants. (was not made to be evaluated)

## Frontend

The frontend serves only for testing purposes and has no validations.
The user must write the name of the participants and the message.
Subscribes both sender and destination queues.

## Database

This application uses an in-memory database (h2) that can be accessed from the `/h2-console` with the url and credentials in application.properties.

## Tests

The tests use a mockist approach.  
I was not exhaustive in testing but tested what was worth testing.  
Test were not only made to ChatController with webSockets.

## Run and deploy

### To run application
`docker-compose up -d`

### If you don't want to run the application as a container
`./mvnw clean install`  
`./mvnw spring-boot:run`

## Improvements

* Add real Frontend.
* Add login support and users micro service instead of a string representing the uswer.
* Dtos and model support more than 2 participants (list).
* Add ActiveMQ.
* Add DLQ.
