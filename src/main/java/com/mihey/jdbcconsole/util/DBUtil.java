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
    private static Connection connection = null;
    private static Statement statement = null;
    private static PreparedStatement prepStatement = null;
    private static CallableStatement callStatement = null;
    private static ResultSet resultSet = null;
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
            if (statement != null) {
                statement.close();
            }
            if (prepStatement != null) {
                prepStatement.close();
            }
            if (callStatement != null) {
                callStatement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Statement getStatement(String sqlStatement) {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    public static PreparedStatement getPrepStatement(String sqlStatement) {
        try {
            prepStatement = connection.prepareStatement(sqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prepStatement;
    }

    public static CallableStatement getCallStatement(String sqlStatement) {
        try {
            callStatement = connection.prepareCall(sqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return callStatement;
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

