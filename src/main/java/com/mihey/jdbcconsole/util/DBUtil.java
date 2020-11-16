package com.mihey.jdbcconsole.util;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;

public class DBUtil {

    private static DBUtil dbUtil;

    final static String URL = "jdbc:mysql://localhost/PostService?serverTimezone=Europe/Moscow";
    final static String USERNAME = "admin";
    final static String PASSWORD = "admin";
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



    public static void setConnection() {
        dataSource.setUrl(URL);
        try {
            connection = dataSource.getConnection(USERNAME, PASSWORD);
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

