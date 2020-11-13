package repository;

import model.Post;
import util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PostRepositoryImpl implements PostRepository<Post, Integer> {

    DBUtil dbUtil = new DBUtil();

    @Override
    public void create(Post post) {
        dbUtil.setConnection();
        String createPost = "INSERT IGNORE INTO Post(Content, UserId) VALUES ('" +
                post.getContent() + "','" + post.getUserId() + "');";

        dbUtil.executeStatement(createPost);

        dbUtil.closeConnection();
    }

    @Override
    public void getById(Integer id) {
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
    }

    @Override
    public void updateById(Integer id, String post) {
        dbUtil.setConnection();
        String update = "UPDATE Post SET Content = '" +
                post + "', Updated=now() WHERE id=" + id + ";";
        dbUtil.executeStatement(update);
        dbUtil.closeConnection();
    }

    @Override
    public void deleteById(Integer id) {
        dbUtil.setConnection();
        String delete = "DELETE FROM Post WHERE id=" + id + ";";
        dbUtil.executeStatement(delete);
        dbUtil.closeConnection();
    }

    @Override
    public void showAll() {
        int rowId = 1;
        try {
            dbUtil.setConnection();
            String getPosts = "SELECT Content FROM Post;";
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

    @Override
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
