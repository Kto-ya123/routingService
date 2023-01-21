## Routing Service

The service is responsible for finding the shortest path from one country to another.

## Description of the path finding algorithm

To store the graph itself, an adjacency list structure was chosen.
The algorithm for finding the shortest path used in this project is based on the classic breadth-first search (BFS).


## Built With
- [Java 11](https://docs.oracle.com/javase/11/)
- [Spring Boot Framework](https://spring.io/projects/spring-boot) (2.7.8)

## Setup
### Local environment pre-requirements
- Install Java 11
- Install Maven 3.6+

## Getting Started
### Initial steps

1. Go to folder with project and build it:
```shell script
mvn clean package
```
2. Run application:
```shell script
mvn spring-boot:run -Dspring-boot.run.profiles=local
```
3. Send test request:
```
curl --location --request GET 'http://localhost:8080/routing/ESP/POL'
```
