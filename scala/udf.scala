// UDF - user defined functions
import org.apache.spark.sql.functions.udf
import scala.collection.mutable.WrappedArray

val data = spark.sparkContext.
      parallelize(List(("A", 11, List(1,2,3)), ("A", 15, List(2,3,16)), ("B", 16, List(42,42,42)))).
      toDF("X", "Y", "Z")
      
data.schema

val toMean = udf( (x : WrappedArray[Int]) => { x.sum.toDouble / x.size })
data.withColumn("meanZ", toMean(col("Z"))).show

val minusY = udf( (x : WrappedArray[Int], y : Int) => { x.map(a => a - y) })
data.withColumn("ZminusY", minusY(col("Z"), col("Y"))).show


val textMap = Map("A" -> (200, "Scala"), "B" -> (100, "R")).withDefaultValue((0, "?"))
textMap("A")
textMap("B")
textMap("C")
textMap("D")
val getInfoFromTextMao = udf( (x : String) => { textMap(x) })
val dt2 = data.withColumn("MapTxt", getInfoFromTextMao(col("X")))
dt2.show
dt2.schema
val dtFinal = dt2
  .select(
    col("MapTxt"), 
    col("MapTxt").getField("_1").as("Value"), 
    col("MapTxt").getField("_2").as("Name")
  )
dtFinal.show
