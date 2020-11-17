package com.mihey.jdbcconsole.repository;

import com.mihey.jdbcconsole.model.Post;

import java.util.List;

public interface PostRepository extends GenericRepository<Post, Integer> {
    public List<Post> getPostsByUserId(Integer id);
}
