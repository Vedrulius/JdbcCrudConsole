package com.mihey.jdbcconsole.repository;

public interface UserRepository<User, Integer> {

    int save(User user);
    void deleteById(Integer id);
}
