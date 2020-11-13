package controller;

import model.Post;
import model.User;
import repository.PostRepository;
import repository.PostRepositoryImpl;

public class PostController {

    private PostRepository postRepository=new PostRepositoryImpl();

    public void createPost(Post post) {
         postRepository.create(post);
    }
}
