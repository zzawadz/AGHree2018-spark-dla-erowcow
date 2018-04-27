## Spark - najważniejsze informacje

Najważniejszą informacją jest to, że `Spark` ma naprawdę bardzo dobrą dokumentację i całą masę przykładów. Naprawdę warto ją przeczytać.

https://spark.apache.org/docs/latest/ - link do dokumentacji.

### Machine Learning:

- https://spark.apache.org/docs/latest/ml-guide.html - dokumentacja biblioteki do uczenia maszynowego w Sparku.
  - https://spark.apache.org/docs/latest/ml-pipeline.html - tworzenie pipeów - WAŻNE!.
  - https://spark.apache.org/docs/latest/ml-classification-regression.html - klasyfikacja i regresja.
  
- https://spark.apache.org/docs/latest/api/scala/index.html#package - dokumentacja obiektów (z tego miejsca można pobierać ścieżki do importu obiektów - ale w dokumentacji też są).
  - Dla przykładu - https://spark.apache.org/docs/latest/api/scala/index.html#org.apache.spark.ml.regression.LinearRegression - dokumentacja regresji logistycznej - zawiera spis wszystkich dostępnych metod.
  
### ml vs mllib 

Biblioteka do uczenia maszynowego `mllib` w Sparku posiada dwa interfejsy - jeden `mllib` i drugi `ml`. Co do zasady lepiej korzystać z `ml`, gdyż oparty jest na `DataFrame` i jest bardziej przyjazny użytkownikowi. `mllib` pochodzi jeszcze z czasów przed wprowadzeniem do Spark'a DataFrame i trudniej w nim wykonać większość operacji (np. zapisać obiekt w pliku itp).

### Operacje na oknach w DataFrame

Operacje użyte przy pomocy `Window` pozwalają uzyskać podobną funkcjonalność jak w przypadku:
`data %>% group_by(column) %>% mutate(avgx = mean(x))`:

- http://spark.apache.org/docs/2.0.2/api/scala/index.html#org.apache.spark.sql.expressions.Window
- https://jaceklaskowski.gitbooks.io/mastering-spark-sql/spark-sql-functions-windows.html


### Tworzenie aplikacji do uruchamiania na klastrze.

- https://spark.apache.org/docs/latest/quick-start.html

### Matriały do nauki Scali

- https://scala-lang.org/documentation/learn.html - zbiór z oficjalnej strony.
- https://booksites.artima.com/programming_in_scala_3ed - książka napisana przez autora języka. Jestem w trakcie i wydaje się być ciekawa.
- https://www.manning.com/books/akka-in-action - książka opisująca bibliotekę `akka` implementującą tzw. `actor model`. Generalnie znajomość tego tematu nie jest konieczna przy pracy w Scali, ale może być bardzo pomocna przy współpracy z deweloperami - łatwiej będzie znaleźć wspólny język przy współtworzeniu dużych aplikacji wykorzystujących dane.
- https://www.coursera.org/specializations/scala - specjalizacja na Courserze. Jeszcze nie skończyłem całej, także ciężko mi ocenić. Wydaje mi się dosyć nie równa - miejscami jest świetnie prowadzona, ale niektóre elementy nie pasują. Mimo wszystko polecam, szczególnie pierwszą część, wprowadzającą do programowania funkcjonalnego.

## SQL

Spark bardzo dobrze wspiera `SQL`:

```
spark.sql("SELECT * FROM table").show
```

z tego też powodu warto go poznać.

### Kursy na datacamp:

- https://www.datacamp.com/courses/intro-to-sql-for-data-science - podstawowe wprowadzenie do SQL.
- https://www.datacamp.com/courses/joining-data-in-postgresql - wersja trochę bardziej zaawansowane - co prawda w nazwie jest PostgreSQL - ale na tym etapie nie ma to znaczenia.

### Inne: 

- https://blog.timescale.com/why-sql-beating-nosql-what-this-means-for-future-of-data-time-series-database-348b777b847a - wpis na temat tego, dlaczego warto nauczyć się SQL. Ostatecznie, nawet gdy pojawia się jakaś nowa baza danych, jest duża szansa, że pojawi się możliwość korzystania z niej przy pomocy SQL.
