package com.mihey.jdbcconsole.repository.jdbc;

import com.mihey.jdbcconsole.model.Post;
import com.mihey.jdbcconsole.model.Region;
import com.mihey.jdbcconsole.model.Role;
import com.mihey.jdbcconsole.model.User;
import com.mihey.jdbcconsole.repository.UserRepository;
import com.mihey.jdbcconsole.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final Connection connection = DBUtil.getConnection();

    @Override
    public List<User> getAll() {

        List<User> users = new ArrayList<>();
        String selectAll = "SELECT * FROM Users INNER JOIN Regions ON Users.regionId=Regions.id";
        int userId = 0;
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(selectAll);
            while (resultSet.next()) {
                userId = resultSet.getInt("id");
                users.add(new User(userId, resultSet.getString("FirstName"),
                        resultSet.getString("LastName"), getPostsByUserId(userId),
                        new Region(resultSet.getInt("regionId"),
                                resultSet.getString("name")), Role.USER));
            }
        } catch (SQLException e) {
            System.out.println("something wrong");
            e.printStackTrace();
            return null;
        }

        return users;
    }

    @Override
    public User getById(Integer id) {
        List<Post> posts = new ArrayList<>();
        int userId = 0;
        int regionId = 0;
        String name = "";
        String surname = "";
        int postId;
        String regionName = "";
        String content = "";
        Timestamp created;
        Timestamp updated;
        String selectAll = "SELECT * FROM ((Posts INNER JOIN Users" +
                " ON Users.id=Posts.userId) INNER JOIN Regions" +
                " ON Users.regionId=Regions.id) Where Users.id" + id + ";";
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(selectAll);
            if (resultSet.next()) {
                userId = resultSet.getInt("Users.id");
                postId = resultSet.getInt("Posts.id");
                name = resultSet.getString("FirstName");
                surname = resultSet.getString("LastName");
                regionId = resultSet.getInt("regionId");
                regionName = resultSet.getString("name");
                content = resultSet.getString("Content");
                created = resultSet.getTimestamp("Created");
                updated = resultSet.getTimestamp("Updated");
                posts.add(new Post(postId, userId, content, created, updated));
            }
            while (resultSet.next()) {
                postId = resultSet.getInt("Posts.id");
                content = resultSet.getString("Content");
                created = resultSet.getTimestamp("Created");
                updated = resultSet.getTimestamp("Updated");
                posts.add(new Post(postId, userId, content, created, updated));
            }
        } catch (SQLException e) {
            System.out.println("something wrong");
            e.printStackTrace();
            return null;
        }
        return new User(userId, name, surname, posts, new Region(regionId, regionName), Role.USER);
    }

    @Override
    public User update(User user) {
        String update = "UPDATE Users SET FirstName = '" +
                user.getFirstName() + "', LastName = '" + user.getLastName() +
                "' regionId = '" + user.getRegion().getId() + "' WHERE id=" + user.getId() + ";";
        try {
            connection.createStatement().executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getById(user.getId());
    }

    @Override
    public User save(User user) {
        String saveUser = "INSERT IGNORE INTO Users(FirstName, LastName, regionId) VALUES ('" +
                user.getFirstName() + "','" + user.getLastName() + "','" + user.getRegion().getId() + "');";

        String userId = "SELECT id FROM Users WHERE FirstName='" + user.getFirstName() + "' AND LastName='" +
                user.getLastName() + "' AND regionId='" + user.getRegion().getId() + "';";

        int id = 0;
        try {
            connection.createStatement().executeUpdate(saveUser);
            ResultSet resultSet = connection.createStatement().executeQuery(userId);
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("something wrong");
            e.printStackTrace();
            return null;
        }
        user.setId(id);
        return user;
    }

    @Override
    public void deleteById(Integer id) {
        String delete = "DELETE FROM Users WHERE id=" + id + ";";
        try {
            connection.createStatement().executeUpdate(delete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Post> getPostsByUserId(Integer id) {
        List<Post> posts = new ArrayList<>();
        String select = "SELECT id,Content,Created,Updated FROM " +
                "Posts WHERE userId=" + id + ";";
        int postId;
        String content;
        Timestamp created;
        Timestamp updated;

        try {
            ResultSet resultSet = connection.createStatement().executeQuery(select);
            while (resultSet.next()) {
                postId = resultSet.getInt("id");
                content = resultSet.getString("Content");
                created = resultSet.getTimestamp("Created");
                updated = resultSet.getTimestamp("Updated");
                posts.add(new Post(postId, id, content, created, updated));
            }
        } catch (SQLException e) {
            System.out.println("something wrong");
            e.printStackTrace();
        }
        return posts;
    }
}
