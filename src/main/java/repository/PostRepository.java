package repository;

import java.util.List;

public interface PostRepository<Post, Integer> {
    List<Post> getAllPost();
}
