package jdbc;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class MySQLConnection {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter login");
        String username = sc.nextLine();
        System.out.println("Enter password");
        String password = sc.nextLine();


        String url = "jdbc:mysql://localhost/PostService?serverTimezone=UTC";

        System.out.println("Connecting database...");
        MysqlDataSource dataSource=new MysqlDataSource();
        dataSource.setUrl(url);
        try(Connection con = dataSource.getConnection(username,password)) {
            System.out.println("Database connected!");
        } catch (SQLException e) {

            e.printStackTrace();
        }

//        try (Connection connection = DriverManager.getConnection(url, username, password)) {
//            System.out.println("Database connected!");
//        } catch (SQLException e) {
//            throw new IllegalStateException("Cannot connect the database!", e);
//        }
    }
}