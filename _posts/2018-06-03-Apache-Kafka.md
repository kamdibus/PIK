---
layout: post
title: "Apache Kafka"
---
##### Kamil Biduś
# Kafka podstawy 

### Kafka to rozproszona platforma do strumieniowania, o trzech głównych funkcjach:
* 	Publikowanie i subskrypcja do strumieni danych (kolejek wiadomości)
*	Odporne na awarie przechowywanie danych
*	Przetwarzanie danych

### Rozróżnia się dwie podstawowe klasy zastosowań dla Kafki
*	Tworzenie strumieni danych w czasie rzeczywistym, które niezawodnie pobierają dane między systemami lub aplikacjami.
*	Tworzenie aplikacji do strumieniowego przesyłania w czasie rzeczywistym, które reagują na strumienie danych.

### Podstawowe pojęcia to
*	_Klastery_ w których może działać Kafka to co najmniej jeden serwer.
*	Klastery przechowują strumienie _rekordów_ w kategoriach zwanych _tematami_.
*	Każdy rekord składa się z klucza, wartości i znacznika czasu.

![Schemat kafki](https://github.com/kamdibus/PIK/blob/gh-pages/img/kafka.png?raw=true "Struktura kafki"){: .center-image }

### Cztery podstawowe API
*	Producer API (zastosowane w projekcie)
*	Consumer API (zastosowane w projekcie)
*	Streams API
*	Connector API

### W projekcie
Z założeń niefunkcjonalnych projektu wynika, że ilość danych dostarczanych do systemu może się znacznie zwiększać po podłączeniu nowych urządzeń. Aby zapewnić skalowalność systemu, dane najpierw wrzucane są do bufora Kafki, a następnie konsumenci zajmują się przekazywaniem ich do bazy danych. Takie podejście pozwala w razie potrzeby zredukować obłożenie bazy.

#### Architektura
Aby zapewnić możliwość korzystania z Kafki z aplikacji zdeployowanej na heroku, bez używania dedykowanego, płatnego rozwiązania, Kafka została postawiona na zewnętrznym serwerze.
Na potrzeby projektu skonfigurawaliśmy jednego brokera z jedną partycją i jednym tematem _values_ z którego korzystają producent i konsument (javowe implementacje Producer i Consumer API).
Deployment jest dokonywany w odpowieniej fazie pipeline Jenkinsa poprzez skrypt
```
#! /bin/bash
set -e
app_name=$(find . -name "consumer*.jar")
echo "worker: java -jar $app_name" > Procfile
heroku deploy:jar $app_name -a wtxgsw
```
Powyższy kod przedstawia deplyment konsumenta (producent jest deployowany razem z resztą backendu). Jest on uruchamiany we własnym kontenerze jako proces typu _worker_ (nie ma potrzeby otwierania portu jak w procesach _web_). Do kontenera podłączona jest baza PostgreSQL.

