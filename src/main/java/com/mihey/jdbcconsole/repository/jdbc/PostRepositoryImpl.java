package com.mihey.jdbcconsole.repository.jdbc;

import com.mihey.jdbcconsole.model.Post;
import com.mihey.jdbcconsole.repository.PostRepository;
import com.mihey.jdbcconsole.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PostRepositoryImpl implements PostRepository {

    private final Connection connection = DBUtil.getConnection();

    @Override
    public Post save(Post post) {
        String createPost = "INSERT IGNORE INTO Posts(Content, UserId) VALUES ('" +
                post.getContent() + "','" + post.getUserId() + "');";
        String postId = "SELECT id FROM Posts WHERE UserId='" + post.getUserId() + "';";

        int id = 0;

        try {
            connection.createStatement().executeUpdate(createPost);
            ResultSet resultSet = connection.createStatement().executeQuery(postId);
            resultSet.next();
            id = resultSet.getInt("id");
        } catch (SQLException e) {
            System.out.println("something wrong");
            e.printStackTrace();
        }
        post.setId(id);
        return post;
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        String selectAll = "SELECT * FROM Posts;";
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(selectAll);
            while (resultSet.next()) {
                posts.add(new Post(resultSet.getInt("id"), resultSet.getInt("UserId"),
                        resultSet.getString("Content"),
                        resultSet.getTimestamp("Created"), resultSet.getTimestamp("Updated")));
            }
        } catch (SQLException e) {
            System.out.println("something wrong");
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public Post getById(Integer id) {
        int postId = 0;
        int userId = 0;
        String content = "";
        Timestamp created = new Timestamp(System.currentTimeMillis());
        Timestamp updated = new Timestamp(System.currentTimeMillis());
        String getPosts = "SELECT * FROM Posts WHERE id=" + id + ";";
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(getPosts);
            while (resultSet.next()) {
                postId = resultSet.getInt("id");
                userId = resultSet.getInt("UserId");
                content = resultSet.getString("Content");
                created = resultSet.getTimestamp("Created");
                updated = resultSet.getTimestamp("Updated");
            }
        } catch (SQLException e) {
            System.out.println("something wrong");
            e.printStackTrace();
            return null;
        }
        return new Post(postId, userId, content, created, updated);
    }

    @Override
    public Post update(Post post) {
        String update = "UPDATE Posts SET Content = '" +
                post.getContent() + "', Updated=now() WHERE id=" + post.getId() + ";";
        String updated = "SELECT userId,Updated FROM Posts WHERE id=" + post.getId() + ";";
        try {
            connection.createStatement().executeUpdate(update);
            ResultSet resultSet=connection.createStatement().executeQuery(updated);
            if (resultSet.next()) {
            post.setUserId(resultSet.getInt("userId"));
            post.setUpdated(resultSet.getTimestamp("Updated"));}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public void deleteById(Integer id) {
        String delete = "DELETE FROM Posts WHERE id=" + id + ";";
        try {
            connection.createStatement().executeUpdate(delete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Post> getPostsByUserId(Integer id) {
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
