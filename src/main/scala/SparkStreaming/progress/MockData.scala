package SparkStreaming.progress

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}

import java.util.Properties
import scala.collection.mutable.ListBuffer
import scala.util.Random

/**
 * @author hhj
 * @create 2021-04-07 15:06
 */
object MockData {
  def main(args: Array[String]): Unit = {
    /**
     * 模拟的数据
     *
     * 格式 ：timestamp area city userid adid
     * 某个时间点 某个地区 某个城市 某个用户 某个广告
     */
      //生产数据
    val prop = new Properties()
    prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "master:9092")
    prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
      "org.apache.kafka.common.serialization.StringSerializer")
    prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
      "org.apache.kafka.common.serialization.StringSerializer")
    val producer=new KafkaProducer[String, String](prop)
    while(true){
      mockData().foreach(
        data=>{
          println(data)
          producer.send(new ProducerRecord("spark",data))
        }
      )
      Thread.sleep(300000)
    }

  }



  def mockData(): ListBuffer[String] ={
    val list=ListBuffer[String]()
    val arrList=ListBuffer[String]("华东","华北","华南")
    val cityList=ListBuffer[String]("北京","上海","深圳")
    for(i <- 1 to 30){
      val area=arrList(new Random().nextInt(3))+1
      val city=cityList(new Random().nextInt(3))+1
      val userId=new Random().nextInt(6)+1
      val adId=new Random().nextInt(6)+1
      list.append(s"${System.currentTimeMillis()} ${area} ${city} ${userId} ${adId}")
    }
    list
  }



}
