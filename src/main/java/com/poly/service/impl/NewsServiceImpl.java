package com.poly.service.impl;

import com.poly.dao.NewsDAO;
import com.poly.dao.impl.NewsDAOImpl;
import com.poly.model.News;
import com.poly.model.User;
import com.poly.model.enums.Role;
import com.poly.service.NewsService;

import java.util.List;

public class NewsServiceImpl implements NewsService {
    private final NewsDAO newsDAO = new NewsDAOImpl();

    @Override
    public List<News> getHomeNews() {
        return newsDAO.findHomeNews();
    }

    @Override
    public News getNewsById(String id) {
        return newsDAO.findbyId(id);
    }

    @Override
    public List<News> getTop5View() {
        return newsDAO.findTop5ByView();
    }

    @Override
    public List<News> getTop5Latest() {
        return newsDAO.findTop5Latest();
    }

    @Override
    public int numberOfNews() {
        return newsDAO.countNews();
    }

    @Override
    public List<News> getAll() {
        return newsDAO.findAll();
    }

    @Override
    public void create(News news) {
        if (news.getTitle() == null || news.getTitle().isBlank()) {
            throw new IllegalArgumentException("Tiêu đề không được để trống");
        }
        if (news.getContent() == null || news.getContent().isBlank()) {
            throw new IllegalArgumentException("Nội dung không được để trống");
        }
        if (news.getCategoryId() == null) {
            throw new IllegalArgumentException("Vui lòng chọn loại tin");
        }

        newsDAO.create(news);
    }

    @Override
    public void delete(String newsId, User currentUser) {
        News news = newsDAO.findbyId(newsId);
        if (news == null) {
            throw new IllegalArgumentException("Bài viết không tồn tại");
        }

        boolean isAdmin = currentUser.getRole() == Role.ADMIN;
        boolean isAuthor = news.getAuthorId() == currentUser.getId();

        if (!isAdmin && !isAuthor) {
            throw new IllegalArgumentException("Bạn không có quyền xoá bài viết này");
        }

        newsDAO.deleteById(newsId);
    }

    @Override
    public void update(News news, User currentUser) {
        News old = newsDAO.findbyId(news.getId());
        if (old == null) {
            throw new IllegalArgumentException("Bài viết không tồn tại");
        }

        boolean isAdmin = currentUser.getRole() == Role.ADMIN;
        boolean isAuthor = old.getAuthorId() == currentUser.getId();

        if (!isAdmin && !isAuthor) {
            throw new IllegalArgumentException("Bạn không có quyền sửa bài viết này");
        }

        newsDAO.update(news);
    }

    @Override
    public List<News> getByAuthor(int authorId) {
        if (authorId <= 0) {
            throw new IllegalArgumentException("Tác giả không hợp lệ");
        }
        return newsDAO.findByAuthor(authorId);
    }

    @Override
    public List<News> getRelatedNews(String categoryId, String excludeId) {
        return newsDAO.findRelatedByCategory(categoryId, excludeId);
    }

}
