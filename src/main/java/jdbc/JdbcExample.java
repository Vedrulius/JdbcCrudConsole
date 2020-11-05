package jdbc;

import org.sqlite.SQLiteDataSource;

import java.sql.*;


public class JdbcExample {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:/home/mihey/Sqlite/westeros.db";

        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        try (Connection con = dataSource.getConnection()) {
            try (Statement statement = con.createStatement()) {
             /*   try (ResultSet greatHouses = statement.executeQuery("SELECT * FROM HOUSES")) {
                    while (greatHouses.next()) {
                        // Retrieve column values
                        int id = greatHouses.getInt("id");
                        String name = greatHouses.getString("name");
                        String words = greatHouses.getString("words");

                        System.out.printf("House %d%n", id);
                        System.out.printf("\tName: %s%n", name);
                        System.out.printf("\tWords: %s%n", words);
                    }
                }*/
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS HOUSES(" +
                        "id INTEGER PRIMARY KEY," +
                        "name TEXT NOT NULL," +
                        "words TEXT NOT NULL);");

                /*int i = statement.executeUpdate("INSERT INTO HOUSES VALUES" +
                        "(1, 'Targarien of King''s Landing', 'Fire and blood')," +
                        "(2,'Stark of Winterfell','Summer is comming')," +
                        "(3,'Lannister of Casterly Rock','Hear me Roar!');");*/
                int u = statement.executeUpdate("UPDATE HOUSES " +
                        "SET words = 'Winter is comming' " +
                        "WHERE id=2;");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
