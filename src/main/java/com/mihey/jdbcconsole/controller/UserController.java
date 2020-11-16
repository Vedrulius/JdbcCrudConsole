package com.mihey.jdbcconsole.controller;

import com.mihey.jdbcconsole.model.User;
import com.mihey.jdbcconsole.repository.UserRepository;
import com.mihey.jdbcconsole.repository.jdbc.UserRepositoryImpl;

public class UserController {

    private final UserRepository<User, Integer> userRepository = new UserRepositoryImpl();

//    return UserId for created user
    public int saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
