## Overview

This project serves as a guide for developers new to Java and Spring Boot who need to establish communication between
microservices via Kafka events. To expedite the setup of the development environment, Docker Compose is provided to
provision the necessary infrastructure, including Kafka and Zookeeper. Please note that the proposed infrastructure is
suitable for development purposes and not for production operation.

## Prerequisites

- Maven 3+
- Java 17
- Docker 4.28.0

## Preparing Environment

From the project's folder, execute:

- `docker-compose up` to initialize Kafka and Zookeeper
- `mvn package` to build the applications

## Booting Applications

- Initializing the `producer`

````bash
$ cd producer
$ mvn spring-boot:run

````

**Note:** The `producer` will be accepting request at `http://localhost:8080/message`

- Initializing the `consumer`

````bash
$ cd consumer
$ mvn spring-boot:run

````

[Consumer] Received message:example {"name": "yourame", "surname": "yourSurname", "message": "Message for producing"}

## Testing

With both `consumer` and `producer` applications up and running, let's test their integration through Kafka:

````bash
$ curl -d "{"name": "yourame", "surname": "yourSurname", "message": "Message for producing"}" \
-H "Content-Type: application/json" \
-X POST http://127.0.0.1:8080/messages

````

If the above request works, we should se[Producer] ACK from ProducerListener: {"name": "yourame", "surname": "
yourSurname", "message": "Message for producing"} offset:  0 e a log in the `producer` application's console that looks
like the following:

````

````

## Cleaning Up

After playing around, we need to clean up the mess.. So from the project's folder, do:
<ol>
<li>Stop the containers using the following command:</li>
  <code>docker-compose down</code>
<li>Delete all containers using the following command:</li>
  <code>docker rm -f $(docker ps -a -q)</code>
</ol> 