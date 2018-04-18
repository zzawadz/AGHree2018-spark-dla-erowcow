## Wymagania:

- Podstawowa znajomość R.
- Zainstalowana Java.
- Odpalenie poniższego kodu w R (to jest ważne! Instalacja sparka wymaga pobrania ok 200mb):

```
library(sparklyr)
sparklyr::spark_install()
spark_install_dir() # Tutaj pojawia się ścieżka w której spark został zainstalowane
```

Następnie by przetestować wszystko należy odpalić konsolę systemową (`cmd`), idziemy do katalogu w którym jest zainstalowany `Spark`, wchodzimy do katalogu `bin` (pełna ścieżka u mnie - `/home/zzawadz/spark/spark-2.2.0-bin-hadoop2.7/bin`) i uruchamiamy:

```
./spark-shell
```

Jeżeli zobaczymy mniej więcej coś takiego:

```
Spark session available as 'spark'.
Welcome to
      ____              __
     / __/__  ___ _____/ /__
    _\ \/ _ \/ _ `/ __/  '_/
   /___/ .__/\_,_/_/ /_/\_\   version 2.2.0
      /_/

Using Scala version 2.11.8 (Java HotSpot(TM) 64-Bit Server VM, Java 1.8.0_161)
Type in expressions to have them evaluated.
Type :help for more information.

scala>
```

wtedy wszystko powinno być okej:)

Jeżeli nie - należy napisać do mnie maila.

## Opis warsztatów:

Świat Big Data rządzi się swoimi prawami. Wiele narzędzi, które z sukcesem spisują się w analizie małych i średnich zbiorów danych, zwyczajnie nie daje rady, gdy próbujemy przepuścić przez nie dziesiątki, czy nawet setki gigabajtów. W takim przypadku lepiej skorzystać z dedykowanych rozwiązań, które zostały specjalnie zaprojektowane, by radzić sobie z ogromem danych, a najlepiej wykorzystać Sparka, który w niejednych bojach został już sprawdzony.

Spark udostępnia 4 podstawowe interfejsy: Java, Python, R i Scala. Z punktu widzenia analityka korzystającego z R oczywiście najlepszym wyborem wydaje się R (mamy pakiet sparklyr od RStudio). Istnieje jednak pewne prawdopodobieństwo, że reszta zespołu nie będzie zaznajomiona z R, co może nieco utrudnić współpracę. Jednak w takim przypadku warto spróbować Scali, która jest znacznie częściej wykorzystywana w BigData. Oczywiście spotkanie z innym językiem programowania z początku bywa problematyczne, ale na szczęście Spark w Scali ma pewne części wspólne (na poziomie koncepcyjnym) z eRowym dplyrem i można się w nim dosyć szybko odnaleźć. I właśnie o tym będzie ten warsztat — jak wiedzę zdobytą w R, przenieść na grunt Sparka.

## O prowadzącym:

#### Zygmunt Zawadzki

Z zamiłowania statystyk i trochę programista. Od ponad siedmiu lat mocno związany z R – przez co nigdy nie nauczył się Excela... Z tego powodu nie może sobie wpisać w CV – „Znajomość pakietu Office” (jak sam twierdzi, jest to raczej powód do dumy…). Czas wolny poświęca na działanie w ramach eRki (Entuzjastów R Krakowska Alternatywa) promując R gdzie tylko się da. Ostatnio trochę czasu poświęca BigData, a szczególnie Sparkowi.