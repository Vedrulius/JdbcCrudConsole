package com.mihey.jdbcconsole.repository;

import com.mihey.jdbcconsole.model.User;
import com.mihey.jdbcconsole.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryImpl implements UserRepository<User, Integer> {

    DBUtil dbUtil = new DBUtil();

    @Override
    public int save(User user) {
        dbUtil.setConnection();
        String saveUser = "INSERT IGNORE INTO User(FirstName, LastName, Region) VALUES ('" +
                user.getFirstName() + "','" + user.getLastName() + "','" + user.getRegion().getName() + "');";
        dbUtil.executeStatement(saveUser);
        String reg = "INSERT IGNORE INTO Region(name) VALUES ('" + user.getRegion().getName() + "');";
        dbUtil.executeStatement(reg);
        String userId = "SELECT id FROM User WHERE FirstName='" + user.getFirstName() + "' AND LastName='" +
                user.getLastName() + "' AND region='" + user.getRegion().getName() + "';";

        ResultSet resultSet = dbUtil.retrieveData(userId);
        int id = 0;
        try {
            resultSet.next();
            id = resultSet.getInt("id");
        } catch (SQLException e) {
            System.out.println("something wrong");
            e.printStackTrace();
        }
        dbUtil.closeConnection();
        return id;
    }

    @Override
    public void deleteById(Integer id) {
        dbUtil.setConnection();
        String delete = "DELETE FROM User WHERE id=" + id + ";";
        dbUtil.executeStatement(delete);
        dbUtil.closeConnection();
    }

}
