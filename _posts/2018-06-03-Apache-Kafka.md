---
layout: post
title: "Apache Kafka"
---
##### Kamil Biduś
# Kafka podstawy 
#### Kafka to rozproszona platforma do strumieniowania, o trzech głównych funkcjach:
* 	Publikowanie i subskrypcja do strumieni danych (kolejek wiadomości)
*	Odporne na awarie przechowywanie danych
*	Przetwarzanie danych
#### Rozróżnia się dwie podstawowe klasy zastosowań dla Kafki
*	Tworzenie strumieni danych w czasie rzeczywistym, które niezawodnie pobierają dane między systemami lub aplikacjami.
*	Tworzenie aplikacji do strumieniowego przesyłania w czasie rzeczywistym, które reagują na strumienie danych.
#### Podstawowe pojęcia to
*	_Klastery_ w których może działać Kafka to co najmniej jeden serwer.
*	Klastery przechowują strumienie _rekordów_ w kategoriach zwanych _tematami_.
*	Każdy rekord składa się z klucza, wartości i znacznika czasu.

![Schemat kafki](https://github.com/kamdibus/PIK/blob/gh-pages/img/kafka.png?raw=true "Struktura kafki")

#### Cztery podstawowe API
*	Producer API (zastosowane w projekcie)
*	Consumer API (zastosowane w projekcie)
*	Streams API
*	Connector API

### W projekcie
<div style="text-align: justify">Z założeń niefunkcjonalnych projektu wynika, że ilość danych dostarczanych do systemu może się znacznie zwiększać po podłączeniu nowych urządzeń. Aby zapewnić skalowalność systemu, dane najpierw wrzucane są do bufora Kafki, a następnie konsumenci zajmują się przekazywaniem ich do bazy danych. Takie podejście pozwala w razie potrzeby zredukować obłożenie bazy.
</div>