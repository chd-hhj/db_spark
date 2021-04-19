package SparkStreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @author hhj
 * @create 2021-04-06 18:55
 */
object WordCountSocket {
  def main(args: Array[String]): Unit = {
    val sc=new SparkConf().setMaster("local[*]")
      .setAppName("WordCountSocket")
    val ssc=new StreamingContext(sc,batchDuration = Seconds(3))

    val datastream=ssc.socketTextStream("master",9001)
    datastream.flatMap(_.split(" "))
      .map((_,1))
      .reduceByKey(_+_)
      .print()
    //启动采集器
    ssc.start()
    ssc.awaitTermination()
  }


}
