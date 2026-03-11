package com.poly.dao;

import com.poly.model.News;

import java.util.List;

public interface NewsDAO {
    List<News> findHomeNews();
    News findbyId(String id);
    List<News> findTop5ByView();
    List<News> findTop5Latest();
    int countNews();
    List<News> findAll();
    void create(News news);
    void deleteById(String id);
    void update(News news);
    List<News> findByAuthor(int authorId);
    List<News> findRelatedByCategory(String categoryId, String excludeId);
}
