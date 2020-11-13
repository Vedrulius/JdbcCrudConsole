package repository;

import java.util.List;

public interface UserRepository<User,Integer> {

//    void save(User user);
    void save(String name, String surname, String region);
    User getById(Integer id);
    List<User> getAll();
}
