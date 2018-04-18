// Wczytanie danych z pliku parquet
val PATH = "/home/zzawadz/Repos/Open/AGHree2018-spark-dla-erowcow/"

val x = spark.read.parquet(PATH + "data/iris")

x.count // ilość wierszy
x.groupBy("Species").count.show

val x2 = x.groupBy("Species").agg(max(col("Sepal_Length")).alias("Max_Sepal_Length"), count(col("Species")).alias("N"))

// Przykład z regresją logistyczną
// https://spark.apache.org/docs/2.3.0/ml-guide.html - dokumentacja
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.feature.StringIndexer
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors

val assembler = new VectorAssembler().
      setInputCols(Array("Sepal_Length", "Sepal_Width", "Petal_Length", "Petal_Width")).
      setOutputCol("features")
      
val indexer = new StringIndexer().setInputCol("Species").setOutputCol("label").fit(x)
      
val xTrain = indexer.transform(assembler.transform(x))
val lr = new LogisticRegression().fit(xTrain)
val result = lr.transform(xTrain)
result.select("probability", "prediction").show(false, 100)
result.createOrReplaceTempView("result")

spark.sql("SELECT Species, FIRST(prediction) AS prediction FROM result GROUP BY Species").show

// Pipeline
import org.apache.spark.ml.{Pipeline, PipelineModel}
val assemblerRaw = new VectorAssembler().
      setInputCols(Array("Sepal_Length", "Sepal_Width", "Petal_Length", "Petal_Width")).
      setOutputCol("features")
val indexerRaw = new StringIndexer().setInputCol("Species").setOutputCol("label")
val logRegRaw = new LogisticRegression()
val pipeline = new Pipeline().setStages(Array(assemblerRaw, indexerRaw, logRegRaw))

val model = pipeline.fit(x)
model.write.overwrite().save(PATH + "models/pipeline")
