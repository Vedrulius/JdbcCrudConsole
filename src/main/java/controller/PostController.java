package controller;

import model.Post;
import repository.PostRepository;
import repository.PostRepositoryImpl;

public class PostController {

    private PostRepository postRepository = new PostRepositoryImpl();

    public void createPost(Post post) {
        postRepository.create(post);
    }

    public void getPostsByUser(int id) {
        postRepository.getById(id);
    }

    public void editPost(int id, String post) {
        postRepository.updateById(id, post);
    }

    public void deletePostById(int id) {
        postRepository.deleteById(id);
    }

    public void showAllPosts() {
        postRepository.showAll();
    }

    public void showAllPostsByUser(int id) {
        postRepository.showAllById(id);
    }
}
