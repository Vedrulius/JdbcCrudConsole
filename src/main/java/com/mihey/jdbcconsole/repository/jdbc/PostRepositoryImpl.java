package com.mihey.jdbcconsole.repository.jdbc;

import com.mihey.jdbcconsole.model.Post;
import com.mihey.jdbcconsole.repository.PostRepository;
import com.mihey.jdbcconsole.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PostRepositoryImpl implements PostRepository {

    DBUtil dbUtil = new DBUtil();

    @Override
    public Post save(Post post) {
        dbUtil.setConnection();
        String createPost = "INSERT IGNORE INTO Post(Content, UserId) VALUES ('" +
                post.getContent() + "','" + post.getUserId() + "');";

        dbUtil.executeStatement(createPost);

        dbUtil.closeConnection();
        return null;
    }

    @Override
    public List<Post> getAll() {
        return null;
    }

    @Override
    public Post getById(Integer id) {
        try {
            dbUtil.setConnection();
            String getPosts = "SELECT id,Content FROM Post WHERE UserId=" + id + ";";
            ResultSet resultSet = dbUtil.retrieveData(getPosts);
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + ": " +
                        resultSet.getString("Content"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbUtil.closeConnection();
        return null;
    }

    @Override
    public Post update(Post post) {
        dbUtil.setConnection();
        String update = "UPDATE Post SET Content = '" +
                post + "', Updated=now() WHERE id=" + post.getId() + ";";
        dbUtil.executeStatement(update);
        dbUtil.closeConnection();
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        dbUtil.setConnection();
        String delete = "DELETE FROM Post WHERE id=" + id + ";";
        dbUtil.executeStatement(delete);
        dbUtil.closeConnection();
    }


    public void showAll() {
        int rowId = 1;
        try {
            dbUtil.setConnection();
            String getPosts = "select FirstName, LastName, Content, Created from Post " +
                    "inner join User on User.id=Post.UserId;";
            String format = "%-20s%s%n";
            ResultSet resultSet = dbUtil.retrieveData(getPosts);
            while (resultSet.next()) {
                System.out.printf(format, rowId + ": " + resultSet.getString("FirstName") + "  " +
                        resultSet.getString("LastName"),
                        resultSet.getString("Content"));
                rowId++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbUtil.closeConnection();
    }


    public void showAllById(Integer id) {
        int rowId = 1;
        try {
            dbUtil.setConnection();
            String getPosts = "SELECT Content FROM Post WHERE UserId=" + id + ";";
            ResultSet resultSet = dbUtil.retrieveData(getPosts);
            while (resultSet.next()) {
                System.out.println(rowId + ": " + resultSet.getString("Content"));
                rowId++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbUtil.closeConnection();
    }
}
