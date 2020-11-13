package util;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;

public class DBUtil {

    Connection connection = null;
    Statement statement = null;
    PreparedStatement prep = null;
    ResultSet resultSet = null;

    MysqlDataSource dataSource = new MysqlDataSource();

    final String URL = "jdbc:mysql://localhost/PostService?serverTimezone=Europe/Moscow";

    public void setConnection() {
        dataSource.setUrl(URL);
        try {
            connection = dataSource.getConnection("admin", "admin");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
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

    public void executeStatement(String sqlStatement) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executePrepStatement(String sqlStatement) {
        try {
            prep = connection.prepareStatement(sqlStatement);
            statement.executeUpdate(sqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet retrieveData(String sqlStatement) {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}

