package repository;

import model.User;

import java.util.List;

public interface UserRepository extends PostServiceRepository<User, Integer> {

    int save(User user);
}
