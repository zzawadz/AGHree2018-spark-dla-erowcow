val PATH = "/home/zzawadz/Repos/Open/AGHree2018-spark-dla-erowcow/"

val data = spark.read.parquet(PATH + "data/diamond")

//***** A'la dplyr *****

data.filter(col("cut").isin("Premium", "Ideal"))

// vararg - interface
val cuts = List("Premium", "Ideal")
data.filter(col("cut").isin(cuts:_*)).count

data.filter(col("cut").isin(cuts:_*) and col("carat") > 0.5).show
data.withColumn("pricePln", col("price") * 2.74)

// Mutate w przypadku gruopwanych danych
// W R byłoby to:
// data %>% group_by(cut, clarity, carat) %>% mutate(avgPriveBy = mean(price))

import org.apache.spark.sql.expressions.Window
val by = Window.partitionBy("cut", "clarity", "carat")
data.withColumn("avgPriveBy", avg(col("price")).over(by))

// Do poczytania:
// Windows:
// http://spark.apache.org/docs/2.0.2/api/scala/index.html#org.apache.spark.sql.expressions.Window
// https://jaceklaskowski.gitbooks.io/mastering-spark-sql/spark-sql-functions-windows.html
// Dostępne funkcje:
// https://spark.apache.org/docs/1.6.2/api/java/org/apache/spark/sql/functions.html

// SQL
// Dlaczego SQL jest ważny:
// https://blog.timescale.com/why-sql-beating-nosql-what-this-means-for-future-of-data-time-series-database-348b777b847a
data.createOrReplaceTempView("diamonds")
spark.sql("SELECT * FROM diamonds WHERE cut = 'Ideal' AND price BETWEEN 500 AND 1000")

val miniList = List(("Ideal", "IF", 1.1), ("Ideal", "VS1", 1.2), ("Premium", "IF", 0.9)) // To jest lokalne
val miniTable = spark.sparkContext.parallelize(miniList).toDF("miniCut", "miniClarity", "Factor") // To jest
miniTable.createOrReplaceTempView("miniTable")

val query = """
SELECT miniTable.Factor, diamonds.cut, diamonds.clarity, diamonds.price 
FROM diamonds
INNER JOIN miniTable ON diamonds.cut = miniTable.miniCut AND diamonds.clarity = miniTable.miniClarity
"""
spark.sql(query).show

data.join(miniTable, data("clarity") === miniTable("miniClarity") and data("cut") === miniTable("miniCut"), "inner").show
data.
  join(miniTable, data("clarity") === miniTable("miniClarity") and data("cut") === miniTable("miniCut"), "inner").
  select("Factor", "cut", "clarity", "price").show
  
val dtFinal = spark.sql(query)
dtFinal.write.parquet(PATH + "data/dfFinalRandom")
dtFinal.write.partitionBy("cut", "clarity").parquet(PATH + "data/dfFinalPartitioned")

dtFinal.rdd.mapPartitions(x => Array(x.size).iterator).collect
dtFinal.repartition(10, col("cut")).rdd.mapPartitions(x => Array(x.size).iterator).collect
dtFinal.repartition(10, col("cut"), col("clarity")).rdd.mapPartitions(x => Array(x.size).iterator).collect

val xt = for(x <- 1 to 10000) yield ("a", x % 2, x % 10)
val dxt = spark.sparkContext.parallelize(xt).toDF("a", "b", "c") // To jest
dxt.repartition(10, col("a")).rdd.mapPartitions(x => Array(x.size).iterator).collect
dxt.repartition(10, col("b")).rdd.mapPartitions(x => Array(x.size).iterator).collect
dxt.repartition(10, col("c")).rdd.mapPartitions(x => Array(x.size).iterator).collect
dxt.repartition(10, col("c"), col("b")).rdd.mapPartitions(x => Array(x.size).iterator).collect
