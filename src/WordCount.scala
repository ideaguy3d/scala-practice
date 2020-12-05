package main

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

object WordCount {
  def main(args: Array[String]) = {
    val conf = new SparkConf().setAppName("Word Counter")
    val sc = new SparkContext(conf)
    val textFile = sc.textFile("file:///spark/readme.md")
    val tokenizedFileData = textFile.flatMap(line => line.split(" "))
    val countPrep = tokenizedFileData.map(word => (word, 1))
    val counts = countPrep.reduceByKey((accumVal, newVal) => accumVal + newVal)
    val sortedCounts = counts.sortBy(kvPair=>kvPair._2, false)
    sortedCounts.saveAsTextFile("file:///jdata/WordCountApp")
  }
}
