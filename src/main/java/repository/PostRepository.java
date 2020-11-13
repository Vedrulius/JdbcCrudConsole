package repository;

import model.Post;

import java.util.List;

public interface PostRepository<Post, Integer> {

    void create(Post post);

    void showAll();

    void showAllById(Integer id);

    void getById(Integer id);

    void deleteById(Integer id);

    void updateById(Integer id, String post);
}
