package jdbcTest;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author hhj
 * @create 2021-04-20 19:05
 */
public class jdbcDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/spark";
        DriverManager.getConnection(url,"root","root");
    }
}
