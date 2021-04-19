package sql

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
 * @author hhj
 * @create 2021-03-11 16:08
 */
object DataFramesql {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf()
    conf.setMaster("local")
    val sparkSession=SparkSession.builder()
      .config(conf)
      .getOrCreate()
    val tdf=sparkSession.read.json("D:\\SparkSQLtest")
    tdf.createOrReplaceTempView("trajectory")
    sparkSession.sql("select SegmentSequence from trajectory")
      .show()
    sparkSession.close()
  }

}
