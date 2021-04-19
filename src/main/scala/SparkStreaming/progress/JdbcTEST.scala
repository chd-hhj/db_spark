package SparkStreaming.progress

import scala.collection.mutable.ListBuffer

/**
 * @author hhj
 * @create 2021-04-08 9:25
 */
object JdbcTEST {
  def main(args: Array[String]): Unit = {
    val conc=jdbcUtil.getConnection
    val pstat=conc.prepareStatement("select * from black_list")
    val rs=pstat.executeQuery()
    val blacklist=ListBuffer[String]()
    while (rs.next()){
      println(rs.getString(1))
    }

  }

}
