---
layout: post
title: "Sample post"
---

### Architektura heksagonalna
###### Przemysław WIlczyński
#### Wstęp
Celem niniejszego artykułu jest pokazanie głównej idei budowania aplikacji z wykorzystaniem architektury heksagonalnej (zwanej również architekturą portów i adapterów), przedstawieniem jej zalet oraz próby jej wykorzystania.
#### Cel wprowadzenia architektury
Koncept architektury heksagonalnej został wprowadzony przez Alistair Cockburn w 2005. Głównym celem było umożliwienie, aby aplikacja mogła być wykorzystywana w takim samym stopniu przez użytkowników, inne programy, automatyczne testy oraz umożliwienie uruchomienia aplikacji w środowisku odizolowanym (developerskim, testowym) bez konieczności zależenia od zewnętrznych serwisów, baz danych itp.
Z  założenia zastosowanie architektury heksagonalnej pozwala na modularność aplikacji oraz łatwą zmianę i dodawanie nowych komponentów, np. podmiana warstwy persystencji, dodanie nowego interfejsu aplikacji jak na przykład interface RESTowy, konsolowy lub zamiana istniejących zewnętrznych zależności na mocki, aby móc w pełni przetestować aplikację.
#### Struktura
Główna idea polega na utworzeniu różnych modułów aplikacji (heksagonów) będących corem logicznym aplikacji, tzn zawierających logikę biznesową niezależną od zewnętrzych frameworków, warstwy persystencji itp. 
Pojedynczy moduł posiada porty i adaptery (api i jego różne implementacje). Pojedynczy moduł może posiadać wiele portów do różnych celów, na przykład do komunikacji z użytkownikiem, bazą danych, innymi modułami i aplikacjami. Dodatkowo każdy port może mieć wiele adapterów, czyli implementacji danego api jak na przykład implementacje warstwy persystencji dla różnych baz

![alt text](../img/hex.png "Struktura modułu")

#### Case study
W wykonywanym projekcie wydzielono 2 moduły devices i values. Kaźdy z modułów posiada port do warstwy persystencji (Repository), oraz port do komunikacji z użytkownikiem (Facade). Z portem Repository mogą łączyć się 2 Adaptery zawierające implementację pozwalającą na łączenie się z relacyjną bazą danych lub też testową implementacją InMemory. Za komunikację z użytkownikiem odpowada RESTowa końcówka. 
Dodatkowo w module Values znajduje się port do komunikacji pomiędzy modułami oraz jego implementacja dla modułu Devices. 

//TODO: diagram
###### Źródła:
[alistair cockburn webpage](http://alistair.cockburn.us/Hexagonal+architecture)\
[dossier-andreas.net webpage](http://www.dossier-andreas.net/software_architecture/ports_and_adapters.html)\
[image source](http://geekswithblogs.net/cyoung/archive/2014/12/20/hexagonal-architecturendashthe-great-reconciler.aspx)