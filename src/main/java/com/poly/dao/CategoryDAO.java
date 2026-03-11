package com.poly.dao;

import com.poly.model.Category;

import java.util.List;

public interface CategoryDAO {
    int countCategory();
    List<Category> findAll();
    void create(Category category);
    boolean existsById(String id);
    boolean hasNews(String categoryId);
    void deleteById(String id);
    void update(Category category);
}
