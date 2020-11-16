package com.mihey.jdbcconsole.controller;

import com.mihey.jdbcconsole.model.Post;
import com.mihey.jdbcconsole.repository.PostRepository;
import com.mihey.jdbcconsole.repository.jdbc.PostRepositoryImpl;

public class PostController {

    private final PostRepository postRepository = new PostRepositoryImpl();

    public void createPost(Post post) {
        postRepository.save(post);
    }

    public void getPostsByUser(int id) {
        postRepository.getById(id);
    }

    public void editPost(Post post) {
        postRepository.update(post);
    }

    public void deletePostById(int id) {
        postRepository.deleteById(id);
    }

    public void showAllPosts() {
        postRepository.getAll();
    }

    public void showAllPostsByUser(int id) {
        postRepository.getById(id);
    }
}
