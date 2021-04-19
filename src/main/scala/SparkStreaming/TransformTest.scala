package SparkStreaming

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @author hhj
 * @create 2021-04-06 20:31
 * 测试transform
 */


object TransformTest {
  val sc=new SparkConf().setMaster("local[*]")
    .setAppName("WordCountSocket")
  val ssc=new StreamingContext(sc,batchDuration = Seconds(3))

  val lines=ssc.socketTextStream("master",9001)
  lines.transform(rddIn=>{
    rddIn.map(str=>str)
  })
}
