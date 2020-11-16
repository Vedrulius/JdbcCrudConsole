package com.mihey.jdbcconsole.util;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBUtil {

    private static DBUtil dbUtil;

    private static String url/*="jdbc:mysql://localhost/PostService?serverTimezone=Europe/Moscow"*/;
    private static String username/* = "admin"*/;
    private static String password/* = "admin"*/;
    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;
    final private static MysqlDataSource dataSource = new MysqlDataSource();

    private DBUtil() {
    }

    public static DBUtil getDbUtil() {
        if (dbUtil == null) {
            dbUtil = new DBUtil();
        }
        return dbUtil;
    }

    private static void setProperties() {
        File file = new File("/src/main/resources/application.properties");
        if (!file.exists()) {
            System.out.println("Unable to find applications.properties, set default parameters.");
            System.out.println("-----------------------------------------------------------------------");
            url = "jdbc:mysql://localhost/PostService?serverTimezone=Europe/Moscow";
            username="admin";
            password="admin";
        } else {
            try (FileReader input = new FileReader(file)) {
                Properties properties = new Properties();
            properties.load(input);
            url = properties.getProperty("datasource.url");
            username = properties.getProperty("datasource.username");
            password = properties.getProperty("datasource.password");}
        catch (IOException e) {
            e.printStackTrace();
        }}
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

    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void executeStatement(String sqlStatement) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet retrieveData(String sqlStatement) {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}

