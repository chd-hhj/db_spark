package SparkStreaming

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.receiver.Receiver
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @author hhj
 * @create 2021-04-06 19:50
 */
object Source {
  def main(args: Array[String]): Unit = {
    val sc=new SparkConf().setMaster("local[*]")
      .setAppName("WordCountSocket")
    val ssc=new StreamingContext(sc,batchDuration = Seconds(3))
    ssc.receiverStream(new MyReceiver)
      .print()


    ssc.start()
    ssc.awaitTermination()
  }

}


class MyReceiver extends Receiver[String](StorageLevel.MEMORY_ONLY){
  private var flag = true
  override def onStart(): Unit = {
    new Thread(){
      override def run(): Unit = {
        while(flag){
          store("1")
          Thread.sleep(1000)
        }
      }
    }.start()
  }

  override def onStop(): Unit = {
    flag=false
  }
}
