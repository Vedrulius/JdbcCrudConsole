package com.mihey.jdbcconsole.controller;

import com.mihey.jdbcconsole.model.Region;
import com.mihey.jdbcconsole.model.User;
import com.mihey.jdbcconsole.repository.UserRepository;
import com.mihey.jdbcconsole.repository.jdbc.UserRepositoryImpl;

import java.util.List;

public class UserController {

    private final UserRepository userRepository = new UserRepositoryImpl();

    public User getUserById(int id) {
        return userRepository.getById(id);
    }

    public User editUser(User user) {
        return userRepository.update(user);
    }


    public List<User> getAllUsers() {
        return userRepository.getAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
