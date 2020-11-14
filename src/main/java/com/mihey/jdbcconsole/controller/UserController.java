package com.mihey.jdbcconsole.controller;

import com.mihey.jdbcconsole.model.User;
import com.mihey.jdbcconsole.repository.UserRepository;
import com.mihey.jdbcconsole.repository.UserRepositoryImpl;

public class UserController {

    private final UserRepository<User, Integer> userRepository = new UserRepositoryImpl();

    public int saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
