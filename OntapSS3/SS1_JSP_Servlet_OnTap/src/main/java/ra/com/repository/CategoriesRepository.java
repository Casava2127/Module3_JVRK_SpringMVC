package ra.com.repository;

import ra.com.entity.Categories;

import java.util.List;

public interface CategoriesRepository {
    List<Categories> findAll();
    Categories findById(int id);
    boolean save(Categories categories);
    boolean update(Categories categories);
    boolean delete(int catalogId);
}
