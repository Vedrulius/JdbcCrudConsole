package repository;

import model.Post;
import util.DBUtil;

import java.util.List;

public class PostRepositoryImpl implements PostRepository {

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
    public Post getById(Integer id) {
        return null;
    }

    @Override
    public List<Post> getAll() {
        return null;
    }

    @Override
    public List<Post> getById() {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void updateById(Integer id) {

    }
}
