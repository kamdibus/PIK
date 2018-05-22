[![Build Status](https://travis-ci.org/kamdibus/PIK.svg?branch=master)](https://travis-ci.org/kamdibus/PIK)

[![codecov](https://codecov.io/gh/kamdibus/PIK/branch/development/graph/badge.svg)](https://codecov.io/gh/kamdibus/PIK/branch/development)

### Uruchomienie
#### Backend (dev)
````
mvn package
java -jar app/target/app-<version>.jar
````

#### Backend (heroku)
````
mvn -Pheroku package
java -jar app/target/app-<version>.jar --spring.profiles.active=heroku
````
		
#### Frontend
````
cd src/frontend
npm start
````
