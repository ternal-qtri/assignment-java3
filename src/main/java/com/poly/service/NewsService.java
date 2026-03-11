package com.poly.service;

import com.poly.model.News;
import com.poly.model.User;

import java.util.List;

public interface NewsService {
    List<News> getHomeNews();
    News getNewsById(String id);
    List<News> getTop5View();
    List<News> getTop5Latest();
    int numberOfNews();
    List<News> getAll();
    void create(News news);
    void delete(String newsId, User currentUser);
    void update(News news, User currentUser);
    List<News> getByAuthor(int authorId);
    List<News> getRelatedNews(String categoryId, String excludeId);
}
