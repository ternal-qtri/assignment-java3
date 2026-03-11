package com.poly.service.impl;

import com.poly.dao.CategoryDAO;
import com.poly.dao.impl.CategoryDAOImpl;
import com.poly.model.Category;
import com.poly.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    CategoryDAO categoryDAO = new CategoryDAOImpl();

    @Override
    public int numberOfCategories() {
        return categoryDAO.countCategory();
    }

    @Override
    public List<Category> getAll() {
        return categoryDAO.findAll();
    }

    @Override
    public void create(Category category) {
        if (category.getId() == null || category.getId().isBlank()) {
            throw new IllegalArgumentException("Mã loại không được để trống");
        }
        if (category.getName() == null || category.getName().isBlank()) {
            throw new IllegalArgumentException("Tên loại không được để trống");
        }
        categoryDAO.create(category);
    }

    @Override
    public void delete(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Mã loại không hợp lệ");
        }
        categoryDAO.deleteById(id);
    }

    @Override
    public void update(Category c) {
        if (c.getId() == null || c.getId().isBlank()) {
            throw new IllegalArgumentException("Mã loại không hợp lệ");
        }
        if (c.getName() == null || c.getName().isBlank()) {
            throw new IllegalArgumentException("Tên loại không được để trống");
        }
        categoryDAO.update(c);
    }
}
