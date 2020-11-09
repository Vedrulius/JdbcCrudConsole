package util;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlDB {

    Connection connection = null;
    Statement statement = null;
    String url = "jdbc:mysql://localhost/PostService?serverTimezone=Europe/Moscow";

    public void DBConnection() {
        System.out.println("Connecting database...");
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl(url);
        try {
            connection = dataSource.getConnection("admin", "admin");
            System.out.println("Database connected!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void CloseDBConnection() {
        try {
            if (connection != null) {
                System.out.println("close connection");
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

