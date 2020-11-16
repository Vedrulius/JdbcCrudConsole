package com.mihey.jdbcconsole.controller;

import com.mihey.jdbcconsole.model.Post;
import com.mihey.jdbcconsole.repository.PostRepository;
import com.mihey.jdbcconsole.repository.jdbc.PostRepositoryImpl;

public class PostController {

    private final PostRepository<Post, Integer> postRepository = new PostRepositoryImpl();

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
