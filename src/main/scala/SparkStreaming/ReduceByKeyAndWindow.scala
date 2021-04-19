package SparkStreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @author hhj
 * @create 2021-04-07 10:01
 */
object ReduceByKeyAndWindow {
  def main(args: Array[String]): Unit = {
    val sc=new SparkConf().setMaster("local[*]")
      .setAppName("WordCountSocket")
    val ssc=new StreamingContext(sc,batchDuration = Seconds(3))
    val lines=ssc.socketTextStream("master",9001)
    ssc.checkpoint("cp")
    val wordStream=lines.transform(rdd=>{
      rdd.flatMap(_.split(" "))
        .map((_,1))
    })

    val windowWord=wordStream.reduceByKeyAndWindow(
      (x,y)=>{x+y},
      (x,y)=>{x - y},
      Seconds(6),slideDuration = Seconds(3)
    ).print()


    ssc.start()
    ssc.awaitTermination()
  }

}
