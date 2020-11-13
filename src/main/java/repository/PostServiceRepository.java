package repository;

import java.util.List;

public interface PostServiceRepository<T, Integer> {

    void create(T t);

    T getById(Integer id);

    List<T> getAll();

    List<T> getById();


    void deleteById(Integer id);

    void updateById(Integer id);
}
