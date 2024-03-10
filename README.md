## Overview

This project is for peristing provided message data into PostgreSQL using Kafka producer consumer.
To expedite the setup of the development environment, Docker Compose is provided to
provision the necessary infrastructure, including Kafka, Zookeeper and PostgreSQL.

## Prerequisites

- Maven
- Java 17
- Docker

## Preparing Environment

From the project's folder, execute:

- `docker-compose up` to initialize Kafka, Zookeeper and PostgreSQL
- 
## Booting Applications

**Note:** The `producer` will be accepting request at `http://localhost:8080/message`

[Consumer] Received message:example {"name": "yourame", "surname": "yourSurname", "message": "Message for producing"}

## Cleaning Up

After playing around, we need to clean up the mess..

Stop the containers using the following command:
`docker-compose down`
Delete all containers using the following command:
`docker rm -f $(docker ps -a -q)`
