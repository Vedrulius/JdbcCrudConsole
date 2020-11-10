package util;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    MysqlDataSource dataSource = new MysqlDataSource();

    final String URL = "jdbc:mysql://localhost/PostService?serverTimezone=Europe/Moscow";

    public void DBConnection() {
        dataSource.setUrl(URL);
        try {
            connection = dataSource.getConnection("admin", "admin");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void CloseDBConnection() {
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

    public void ExecuteStatement(String SqlStatement) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(SqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet RetrieveData(String SqlStatement) {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}

