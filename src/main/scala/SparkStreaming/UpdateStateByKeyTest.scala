package SparkStreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @author hhj
 * @create 2021-04-06 20:21
 */
object UpdateStateByKeyTest {
  def main(args: Array[String]): Unit = {
    val sc=new SparkConf().setMaster("local[*]").setAppName("WordCountSocket")
    val ssc=new StreamingContext(sc,batchDuration = Seconds(3))
    ssc.checkpoint("cp")
    val datastream=ssc.socketTextStream("master",9001)
    val wordstream=datastream.flatMap(_.split(" ")).map((_,1))
    val curWordCount=wordstream.updateStateByKey(
      (seq:Seq[Int],opt:Option[Int])=>{
        val curNum=opt.getOrElse(0)
        val newNum=seq.sum
        Option(curNum+newNum)
      }
    )
    curWordCount.print()
    ssc.start()
    ssc.awaitTermination()
  }

}
