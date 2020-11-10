import util.DBUtil;

public class Starter {
    public static void main(String[] args) {
        DBUtil mySql = new DBUtil();
        mySql.DBConnection();
        mySql.CloseDBConnection();

    }
}
