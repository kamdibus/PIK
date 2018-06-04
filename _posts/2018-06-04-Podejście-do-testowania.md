---
layout: post
title: "Podejście do testowania"
---

###### Rafał Koguciuk
#### Wstęp

Coraz częściej w kontekście tworzenia oprogramowania mówi się między innymi o 
testwowaniu. Świadomość twórców o tym, że testowanie staje się jednym z najważniejszych
aspektów wytwarzania oprogramowania, stale się zwiększa.

Każdy programista nieustannie styka się z błędami tworzonego przez siebie oprogramowania 
i dobrze wie, że nawet najprostrzy błąd może przyprawiać o bóle głowy. Jednak zdarzają się 
gorsze rzeczy, błędy mogą prowadzić do bardziej katastrofalnych skutków. W latach 80, wskutek 
błędu w maszynie do radioterapii Therac-25 zmarło 5 osób, gdyż dostali oni zbyt dużą dawkę 
promieniowania. W roku 1991 podczas wojny w Zatoce Perskiej iracka rakieta zabiła 28 
amerykańskich żołnierzy, zawiódł system obronny Patriot. W wyniku błędów oprogramowania, 
sekunda wyliczana przez system obronny nie była równa rzeczywistej sekundzie. Skutkowało 
to tym, że czas wyliczany przez system obronny spóźniał się o 0,34 sekundy. Podczas ataku 
irackiej rakiety system źle wyliczał jej pozycje i uznał to za fałszywy alarm, wtedy to 
rakieta uderzyła w amerykańską bazę.

Jak widać, źle przetestowane oprogramownie może prowadzić do prawdziwych katastrof. 
Żeby tego uniknąć coraz częściej w IT mówi się o testowaniu aplikacji. Zatem jakie są 
rzeczywiste powody, dla których piszemy oprogramowanie? Między innymi dlatego, żeby:
- zminimalizować prawdopodobieństwo wystąpienia błędów działania systemów
- zwiększyć jakość i czytelność oprogramowania
- otrzymać szybką informację, że coś poszło nie tak
- zapewnić lepszą architekturę systemu
- sprawdzać, czy kolejne zmiany w kodzie nie popsuły kodu, który już został napisany.

#### Rodzaje testów

Aplikację możemy testować na różnym poziomie jej integracji, dlatego wyróżniamy 3 
główne rodzaje testów:
- jednostkowe (tzw. testy unitowe) - łatwe do pisania i modyfikacji, 
testują małą funkcjonalność aplikacji, mały fragment kodu, wykonywane bardzo szybko;
- integracyjne - tworzone by wykrywać błędy w interfejsach i interakcji 
między modułami, testują część systemu, wykonują się dłużej niż jednostkowe;
- akceptacyjne - testują cały system, sprawdzają funkcjonalności w sposób 
całościowy, wykonują się najdłużej.

![Piramida_testow](https://github.com/kamdibus/PIK/blob/gh-pages/img/piramida.png?raw=true "Piramida testów"){: .center-image }

Ze wględu na różnice między testami stworzono coś takiego jak piramida testów i testowy 
lodzik. Piramida mówi nam, których testów powinno być najwięcej oraz które testy powinny 
być dominujące w naszym systemie. Lodzik testowy, z drugiej strony, pokazuje jaki powinien 
być czas i koszt wykonywania testów naszego systemu.

![Lodzik_testowy](https://github.com/kamdibus/PIK/blob/gh-pages/img/lodzik.png?raw=true "Testowy lodzik"){: .center-image }

#### TDD (Test Driven Development)
Jest to jedna z technika tworzenia oprogramowania, której głównym zamysłem jest to, 
żeby tworzenie oprogramowania zaczynać od pisania testów funkcjonalności, którą chcemy 
zaimplementować, by później napisać kod wystarczający by testy przechodziły, a na 
końcu poddać ten kod refaktoryzacji. Trzy główne prawa TDD brzmią następująco:
1. Nie wolno Ci napisać żadnego kodu produkcyjnego dopóki nie masz nieprzechodzącego testu.
2. Nie wolno Ci napisać w teście nic ponad to, co starcza żeby nie przechodził.
3. Nie wolno Ci napisać żadnego kodu poza tym, który jest wystarczający by aktualny 
nieprzechodzący test przechodził.

![TDD_cycle](https://github.com/kamdibus/PIK/blob/gh-pages/img/tdd.png?raw=true "Cykl TDD"){: .center-image }

Dlaczego w ogóle stosujemy tę technikę? Przede wszystkim dlatego, że pozwala na pisanie 
lepszego kodu, a także sprawniejsze nim zarządzanie. Łatwiej jest wprowadzać zmiany i 
wymusza na programiście skupienie się na jakości i funkcjonalności tworzonego oprogramowania. 
Pozwala na dynamiczną pracę i inkrementalne rozwijanie systemu o kolejne małe funkcjonalności,
które potem będą tworzyć coraz to większe.

Jak TDD zostało wykorzystane w naszym projekcie? Przy implementacji prostych klas łatwo 
jest wykorzsytać tę technikę, gdyż nie wymaga ona dużego wkładu początkowego. Stworzona 
przez nas aplikacja opiera się na strukturze hexagonalnej, a implementacja fasady dla 
jednego z modułów wymagała implementacji dużo więcej innych składowych, by móc zadziałać. 
Przykładowo funkcjonalność dodawania urządzenia przez fasadę DeviceFacade za to odpowiedzialną 
wymagała implementacji klas DeviceConfigurator, DeviceCreator, repozytorium DeviceRepository i
wiele innych mniejszych składników. Przy próbie wykorzystania tej techniki okazało się, że 
napisanie wystarczającego kodu, by test przechodził, wymagało bardzo dużo pracy. Nasz młody i 
niedoświadczony zespół mógł przy okazji tego projektu bardzo dużo się nauczyć.

#### Wykorzystane techniki

Oprócz wykorzystania biblioteki JUnit, podstawowej i najbardziej powszechnej 
biblioteki do testów jednostkowych, do pracy nad projektem wykorzystaliśmy jeszcze 
parę innych ciekawych technik, które wspomagały nasz proces testowania aplikacji. 
Jedną z nich było wykorzystanie hashMapy by przetestować fasady modułów nie 
wykorzsytując do tego baz danych tylko operując obiektami w pamięci. HashMapa 
jest to struktury danych, która pozwala na mapowanie pomiędzy kluczami a obiektami. 
Zastąpienie wykorzystania baz danych podczas testów poprzez implementacje repozytoriów 
w pamięci wykorzystując przy tym hashMapę przyniosło nam parę ciekawych korzyści:
- zmniejszyliśmy czas wykonywania testów - jest to chyba największa zaleta, 
gdyż testy jednostkowe powinny wykonywać się w ułamku sekundy. Uruchamiając 
testy nie musieliśmy czekać, aż system obsługi baz danych będzie gotowy do pracy
- nie operowaliśmy na prawdziwych encjach baz danych - nie modyfikowaliśmy 
zawartości baz danych podczas wykonywania testów.

Wykorzystaliśmy dodatkowo bibliotekę Mockito, która służy do wygodnego mockowania obiektów, 
czyli innymi słowy udawania. Dzięki temu możemy przetestować pewne funkcjonalności modułu 
dostarczając mu udawane zależności. Testując jednostkowo funkcjonalności jednego modułu, 
który posiada zależności do innego modułu, nie chcemy testować obu na raz. Mockowanie pozwala 
nam zmieniać zachowanie udawanych mocków pomiędzy testami, co sprawia, że są one bardzo 
elastycznym i przydatnym narzędziem do testów. 
Chcąc przetestować funkcjonalność modułu odpwowiedzialnego za obsługę urządzeń i zmiennych 
przypisanych do nich mogliśmy zamockować zależności do modułu wartości tych zmiennych. Przy 
usuwaniu urządzenia czy zmiennej, naturalnym było, że należy usunąć także wartości zmiennych, 
lecz odpowiedzialny za to był inny moduł aplikacji. Dlatego testując moduł urządzeń mogliśmy 
zamockować zależności do drugiego modułu tak, żeby móc przetestować działanie modułu, bez 
wykorzystania drugiego. Mockowanie również okazało się przydatne przy pisaniu testów 
integracyjnych REST kontrolerów. Mocki obiektów pozwoliły na to, żeby nie skupiać się na 
działaniu poszczególnych klas, do testowania których służą testy jednostkowe, tylko 
przetestować większe funkcjonalności modułów. 

#### Codecov

Do sprawdzenia pokrycia kodu naszej aplikacji użyliśmy narzędzia, które nazywa się 
Codecov. Dzięki niemu możemy monitorować, które części aplikacji są przetestowane a 
które nie. Poniżej przedstawiono, jak zmieniało się procentowe pokrycie w czasie rozwijania projektu.

![Codecov_picture](https://github.com/kamdibus/PIK/blob/gh-pages/img/pokrycie.png?raw=true "Pokrycie kodu"){: .center-image }

###### Źródła:

[chlebik_webpage](https://chlebik.wordpress.com/)

[kodolamacz_webpage](https://kodolamacz.pl/blog/)

[image source1](https://studia.elka.pw.edu.pl/big/18L/PIK.A/priv//testowanie_automatyczne.pdf)

[TDD_source_pragmatists](https://studia.elka.pw.edu.pl/file/18L/PIK.A/priv/tdd.pdf)
