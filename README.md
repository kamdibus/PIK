### Uruchomienie
#### Backend (dev)
Wersja developement,
Baza H2 in memory
````
mvn package
java -jar app/target/app-<version>.jar
````

#### Backend (z kafką)
Wersja z kafką,
Baza H2 in memory
````
mvn -Pprod package
java -jar app/target/app-<version>.jar --spring.profiles.active=prod
````

#### Backend (heroku)
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
