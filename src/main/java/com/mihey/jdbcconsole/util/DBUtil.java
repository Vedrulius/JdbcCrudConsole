package com.mihey.jdbcconsole.util;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBUtil {

    private static String url;
    private static String username;
    private static String password;
    private static Connection connection;
    final private static MysqlDataSource dataSource = new MysqlDataSource();

    private static void setProperties() {

        try (InputStream resourceAsStream = DBUtil.class.getResourceAsStream("/application.properties")) {
            Properties properties = new Properties();
            if (resourceAsStream == null) {
                System.out.println("Unable to find applications.properties, set default parameters.");
                System.out.println("-----------------------------------------------------------------------");
                url = "jdbc:mysql://localhost/PostService?serverTimezone=Europe/Moscow";
                username = "admin";
                password = "admin";
            } else {
                try {
                    properties.load(resourceAsStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                url = properties.getProperty("datasource.url");
                username = properties.getProperty("datasource.username");
                password = properties.getProperty("datasource.password");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setConnection() {
        setProperties();
        dataSource.setUrl(url);
        try {
            connection = dataSource.getConnection(username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

