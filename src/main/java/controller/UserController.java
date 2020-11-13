package controller;

import model.User;
import repository.UserRepository;
import repository.UserRepositoryImpl;

public class UserController {

    private UserRepository userRepository = new UserRepositoryImpl();

    public int saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
