package SparkStreaming.progress

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.parquet.schema.Types.ListBuilder
import org.apache.spark.SparkConf
import org.apache.spark.streaming.Durations.seconds
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}

import java.text.SimpleDateFormat
import java.util.Date
import scala.collection.mutable.ListBuffer


/**
 * @author hhj
 * @create 2021-04-07 15:48
 */
object Consumer_kafka {
  def main(args: Array[String]): Unit = {
    val sc=new SparkConf().setMaster("local[*]").setAppName("Consumer_kafka")
    val ssc=new StreamingContext(sc,seconds(3))


    val kafkaPara: Map[String, Object] = Map[String, Object](
      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG ->
        "master:9092",
      ConsumerConfig.GROUP_ID_CONFIG -> "spark",
      "key.deserializer" ->
        "org.apache.kafka.common.serialization.StringDeserializer",
      "value.deserializer" ->
        "org.apache.kafka.common.serialization.StringDeserializer"
    )

    val kafkaData = KafkaUtils.createDirectStream[String, String](
      ssc,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe[String, String](Set("spark"), kafkaPara)
    )
    val adClickData=kafkaData.map(
      data=>{
        val data2=data.value().split(" ")
        AdClickData(data2(0),data2(1),data2(2),data2(3),data2(4))
      }
    )
    val ds=adClickData.transform(
      rdd=>{
        //从mysql周期性取得黑名单 jdbc
        val conc=jdbcUtil.getConnection
        val pstat=conc.prepareStatement("select * from black_list")
        val rs=pstat.executeQuery()
        val blacklist=ListBuffer[String]()
        while (rs.next()){
          blacklist.append(rs.getString(1))
        }
        pstat.close()
        conc.close()
        val filterId=rdd.filter(
          data=>{
            !blacklist.contains(data.user)
          }
        )
        filterId.map(
          data=>{
            val sdf=new SimpleDateFormat("yyyy-MM-dd")
            val day=sdf.format(new Date(data.ts.toLong))
            val user=data.user
            val ad=data.ad
            ((day,user,ad),1)
        }).reduceByKey(_+_)

      }
    )
    ds.foreachRDD(
      rdd=> {
        rdd.foreach(
          data => {
            data match {
              case ((day,user,ad),count)=>{
                if(count>=30){

                }
              }
            }
            if (data._2 >= 30) {
              val conc = jdbcUtil.getConnection
              val pstat = conc.prepareStatement("insert into black_list (userid) values (?)")
              pstat.setString(1, data._1._2)
              pstat.executeUpdate()
              pstat.close()
              conc.close()

            }
            else {
            }


          })
      }


    )
    ssc.start()
    ssc.awaitTermination()


  }

}


case class AdClickData(ts:String,area:String,city:String,user:String,ad:String)
