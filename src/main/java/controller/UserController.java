package controller;

import model.User;
import repository.UserRepository;
import repository.UserRepositoryImpl;

public class UserController {
    private UserRepository<User, Integer> userRepository = new UserRepositoryImpl();

    public void saveUser(String name, String surname, String region) {
        userRepository.save(name, surname, region);
    }
}
