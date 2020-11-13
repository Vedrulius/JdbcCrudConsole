package repository;

import model.User;

import java.util.List;

public interface UserRepository<User, Integer> {

    int save(User user);
    void deleteById(Integer id);
}
