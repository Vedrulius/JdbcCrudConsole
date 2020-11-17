package com.mihey.jdbcconsole.controller;

import com.mihey.jdbcconsole.model.Post;
import com.mihey.jdbcconsole.repository.PostRepository;
import com.mihey.jdbcconsole.repository.jdbc.PostRepositoryImpl;

import java.util.List;

public class PostController {

    private final PostRepository postRepository = new PostRepositoryImpl();

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post getPostsById(int id) {
        return postRepository.getById(id);
    }

    public Post editPost(Post post) {
        return postRepository.update(post);
    }

    public void deletePostById(int id) {
        postRepository.deleteById(id);
    }

    public List<Post> showAllPosts() {
        return postRepository.getAll();
    }

    public List<Post> getPostsByUserId(Integer id) {
        return postRepository.getPostsByUserId(id);
    }

}

