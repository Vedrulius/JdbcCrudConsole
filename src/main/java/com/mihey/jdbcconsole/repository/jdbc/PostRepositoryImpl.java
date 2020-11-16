package com.mihey.jdbcconsole.repository.jdbc;

import com.mihey.jdbcconsole.model.Post;
import com.mihey.jdbcconsole.repository.PostRepository;
import com.mihey.jdbcconsole.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PostRepositoryImpl implements PostRepository {

    @Override
    public Post save(Post post) {
        String createPost = "INSERT IGNORE INTO Post(Content, UserId) VALUES ('" +
                post.getContent() + "','" + post.getUserId() + "');";
        DBUtil.executeStatement(createPost);
        String postId = "SELECT id FROM Post WHERE UserId='" + post.getUserId() + "';";

        ResultSet resultSet = DBUtil.retrieveData(postId);
        int id = 0;
        try {
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
        String selectAll = "SELECT * FROM Post;";
        ResultSet resultSet = DBUtil.retrieveData(selectAll);
        try {
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
        String getPosts = "SELECT * FROM Post WHERE id=" + id + ";";
        try {
            ResultSet resultSet = DBUtil.retrieveData(getPosts);
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
        }
        return new Post(postId,userId,content,created,updated);
    }

    @Override
    public Post update(Post post) {
        String update = "UPDATE Post SET Content = '" +
                post.getContent() + "', Updated=now() WHERE id=" + post.getId() + ";";
        DBUtil.executeStatement(update);

        return getById(post.getId());
    }

    @Override
    public void deleteById(Integer id) {
        String delete = "DELETE FROM Post WHERE id=" + id + ";";
        DBUtil.executeStatement(delete);
    }
}
