library(sparklyr)
library(dplyr)
library(ggplot2)

config <- spark_config()
config$sparklyr.cores.local <- 2
sc <- spark_connect(master = "local", config = config)

irSpark <- copy_to(sc, iris)
dmSpark <- copy_to(sc, diamonds)

if(!file.exists("data")) dir.create("data")
spark_write_parquet(irSpark, partition_by = "Species", path = "data/iris")
spark_write_parquet(dmSpark, partition_by = c("color", "cut"), path = "data/diamond")
