import util.MySqlDB;

public class Starter {
    public static void main(String[] args) {
        MySqlDB mySql = new MySqlDB();
        mySql.DBConnection();
        mySql.CloseDBConnection();

    }
}
