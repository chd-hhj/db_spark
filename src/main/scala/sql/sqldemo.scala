package sql

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
 * @author hhj
 * @create 2021-03-11 16:00
 */
object sqldemo {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf()
    conf.setMaster("local")
    val sparkSession=SparkSession.builder()
      .config(conf)
      .getOrCreate()
    val tdf=sparkSession.read.json("D:\\SparkSQLtest")
    tdf.show()
    sparkSession.close()

  }

}
