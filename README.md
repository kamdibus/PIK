[![Build Status](https://travis-ci.org/kamdibus/PIK.svg?branch=master)](https://travis-ci.org/kamdibus/PIK)

[![codecov](https://codecov.io/gh/kamdibus/PIK/branch/development/graph/badge.svg)](https://codecov.io/gh/kamdibus/PIK/branch/development)

### Architektura
W wykonywanym projekcie wydzielono 3 moduły devices, values oraz moduł producera. 

Moduł devices odpowiedzialny jest za zarządanie urządzeniami oraz ich zmiennymi.

Moduł values odpowiedzialny jest za zarządzanie przesyłanymi wartościami zmiennych.

Moduł producera odpowiedzialny jest za przyjmowaniem przesyłanych wartości zmiennych oraz zapisywaniem 
ich do bufora.

Całość podzielono także na 9 modułów mavenowych aby ułatwić zarządzanie zaleznościami:
+ app
+ consumer
+ devices-domain
+ devices-persitence-jpa
+ devices-rest
+ producer
+ values-domain
+ values-persistence-jpa
+ values-rest

Więcej informacji o architekturze znajduje się [w tym artykule](https://kamdibus.github.io/PIK/2018/05/30/Hexagonal-Architecture.html)
a informacje na temat zastosowania Apache Kafka znajdują się [pod tym adresem](https://kamdibus.github.io/PIK/2018/06/03/Apache-Kafka.html)


### Uruchomienie
#### Backend (dev)
Wersja developement,
Baza H2 in memory
````
mvn package
java -jar app/target/app-<version>.jar
````

#### Backend (z kafką heroku)
Wersja produkcyjna Heroku z kafką,
Baza PostgreSQL
````
mvn -Pprod package
java -jar app/target/app-<version>.jar --spring.profiles.active=prod
````

#### Backend (bez kafki heroku)
Wersja produkcyjna Heroku,
Baza PostgreSQL
````
mvn -Pheroku package
java -jar app/target/app-<version>.jar --spring.profiles.active=heroku
````
		
#### Frontend
````
cd src/frontend
npm start
````

### Deployment
Powyższa aplikacja została zdeployowana do usługi Heroku. Znajduje się pod [tym adresem](http://wtxgs.herokuapp.com)