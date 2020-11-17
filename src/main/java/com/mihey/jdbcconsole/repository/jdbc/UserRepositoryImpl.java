package com.mihey.jdbcconsole.repository.jdbc;

import com.mihey.jdbcconsole.model.Region;
import com.mihey.jdbcconsole.model.Role;
import com.mihey.jdbcconsole.model.User;
import com.mihey.jdbcconsole.repository.UserRepository;
import com.mihey.jdbcconsole.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String selectAll = "SELECT * FROM Users;";
        ResultSet resultSet = DBUtil.retrieveData(selectAll);
        try {
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt("id"), resultSet.getString("FirstName"),
                        resultSet.getString("LastName"), new ArrayList<>(),
                        new Region(resultSet.getString("region")), Role.USER));
            }
        } catch (SQLException e) {
            System.out.println("something wrong");
            e.printStackTrace();
        }

        return users;
    }


    @Override
    public User getById(Integer id) {

        int userId = 0;
        String name = "";
        String surname = "";
        String region = "";
        String selectAll = "SELECT * FROM Users WHERE id=" + id + ";";
        ResultSet resultSet = DBUtil.retrieveData(selectAll);
        try {
            while (resultSet.next()) {
                userId = resultSet.getInt("id");
                name = resultSet.getString("FirstName");
                surname = resultSet.getString("LastName");
                region = resultSet.getString("region");
            }
        } catch (SQLException e) {
            System.out.println("something wrong");
            e.printStackTrace();
        }
        return new User(userId, name, surname, new ArrayList<>(), new Region(region), Role.USER);
    }

    @Override
    public User update(User user) {
        String update = "UPDATE Users SET FirstName = '" +
                user.getFirstName() + "', LastName = '" + user.getLastName() +
                "' region = '" + user.getRegion().getName() + "' WHERE id=" + user.getId() + ";";
        DBUtil.executeStatement(update);

        return getById(user.getId());
    }

    @Override
    public User save(User user) {
        String saveUser = "INSERT IGNORE INTO Users(FirstName, LastName, Region) VALUES ('" +
                user.getFirstName() + "','" + user.getLastName() + "','" + user.getRegion().getName() + "');";
        DBUtil.executeStatement(saveUser);

        String userId = "SELECT id FROM Users WHERE FirstName='" + user.getFirstName() + "' AND LastName='" +
                user.getLastName() + "' AND region='" + user.getRegion().getName() + "';";

        ResultSet resultSet = DBUtil.retrieveData(userId);
        int id = 0;
        try {
            resultSet.next();
            id = resultSet.getInt("id");
        } catch (SQLException e) {
            System.out.println("something wrong");
            e.printStackTrace();
        }
        user.setId(id);
        return user;
    }

    @Override
    public void deleteById(Integer id) {
        String delete = "DELETE FROM Users WHERE id=" + id + ";";
        DBUtil.executeStatement(delete);
    }

}
