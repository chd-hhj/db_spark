import org.apache.spark.{SparkConf, SparkContext}

/**
 * 设置并行度
 * @author hhj
 * @create 2021-03-11 10:18
 */


object MoreParalismscala {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf()
    conf.setAppName("MoreParalismscala")
    //conf.setMaster("local")
    //conf.set("spark.default.parallelism","5")
    val sc=new  SparkContext(conf)
    val rdd1=sc.parallelize(Array("asd"))
      rdd1.map((_,1))
       .reduceByKey(_+_)
       .foreach(println(_))
    sc.stop()

  }
}
