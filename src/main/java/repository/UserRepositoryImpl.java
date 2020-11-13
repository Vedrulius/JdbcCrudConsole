package repository;

import model.User;
import util.DBUtil;

import java.util.List;

public class UserRepositoryImpl implements UserRepository<User, Integer> {

    DBUtil dbUtil = new DBUtil();


    @Override
    public void save(String name, String surname, String region) {
        dbUtil.setConnection();
        String saveUser = "INSERT IGNORE INTO User(FirstName, LastName, Region) VALUES ('" +
                name + "','" + surname + "','" + region + "');";
        dbUtil.executeStatement(saveUser);
        String reg = "INSERT IGNORE INTO Region(name) VALUES ('" + region + "');";
        dbUtil.executeStatement(reg);

        dbUtil.closeConnection();
    }

    @Override
    public User getById(Integer id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
