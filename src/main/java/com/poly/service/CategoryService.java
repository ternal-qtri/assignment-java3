package com.poly.service;

import com.poly.model.Category;

import java.util.List;

public interface CategoryService {
    int numberOfCategories();
    List<Category> getAll();
    void create(Category category);
    void delete(String id);
    void update(Category c);
}
