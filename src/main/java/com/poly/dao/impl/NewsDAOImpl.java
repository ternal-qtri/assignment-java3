package com.poly.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.poly.dao.NewsDAO;
import com.poly.model.News;
import com.poly.utils.DBConnect;

public class NewsDAOImpl implements NewsDAO {

    @Override
    public List<News> findHomeNews() {
        String sql = """
            SELECT
                n.*,
                u.fullname AS author_name
            FROM news n
            JOIN users u ON n.author_id = u.id
            WHERE n.is_home = 1
            ORDER BY n.posted_date DESC
        """;

        List<News> list = new ArrayList<>();

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public News findbyId(String id) {
        String sql = """
            SELECT n.*, u.fullname AS author_name
            FROM news n
            JOIN users u ON n.author_id = u.id
            WHERE n.id = ?
        """;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<News> findTop5ByView() {
        String sql = """
            SELECT TOP 5
                n.*,
                u.fullname AS author_name
            FROM news n
            JOIN users u ON n.author_id = u.id
            ORDER BY n.view_count DESC
        """;

        List<News> list = new ArrayList<>();

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<News> findTop5Latest() {
        String sql = """
            SELECT TOP 5
                n.*,
                u.fullname AS author_name
            FROM news n
            JOIN users u ON n.author_id = u.id
            ORDER BY n.posted_date DESC
        """;

        List<News> list = new ArrayList<>();

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public int countNews() {
        String sql = """
            SELECT COUNT(*) AS total FROM news
        """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public List<News> findAll() {
        List<News> list = new ArrayList<>();

        String sql = """
            SELECT n.id, n.title, n.view_count, n.is_home, n.posted_date, c.name AS category_name, u.fullname AS author_name
            FROM news n
            LEFT JOIN categories c ON n.category_id = c.id
            LEFT JOIN users u ON n.author_id = u.id
            ORDER BY n.posted_date DESC
        """;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                News news = new News();
                news.setId(rs.getString("id"));
                news.setTitle(rs.getString("title"));
                news.setViewCount(rs.getInt("view_count"));
                news.setHome(rs.getBoolean("is_home"));
                news.setPostedDate(rs.getDate("posted_date"));
                news.setCategoryId(rs.getString("category_name"));
                news.setAuthorName(rs.getString("author_name"));

                list.add(news);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public void create(News news) {
        String sql = """
            INSERT INTO news
            (id, title, content, image, posted_date, author_id, view_count, category_id, is_home)
            VALUES (?, ?, ?, ?, ?, ?, 0, ?, 0)
        """;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, news.getId());
            ps.setString(2, news.getTitle());
            ps.setString(3, news.getContent());
            ps.setString(4, news.getImage());
            ps.setDate(5, new java.sql.Date(news.getPostedDate().getTime()));
            ps.setInt(6, news.getAuthorId());
            ps.setString(7, news.getCategoryId());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM news WHERE id = ?";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(News news) {
        String sql = """
            UPDATE news
            SET title = ?, content = ?, image = ?, category_id = ?, is_home = ?
            WHERE id = ?
        """;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, news.getTitle());
            ps.setString(2, news.getContent());
            ps.setString(3, news.getImage());
            ps.setString(4, news.getCategoryId());
            ps.setBoolean(5, news.isHome());
            ps.setString(6, news.getId());

            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<News> findByAuthor(int authorId) {
        List<News> list = new ArrayList<>();

        String sql = """
            SELECT n.id, n.title, n.image, n.posted_date, n.view_count, u.fullname AS author_name
            FROM news n
            JOIN users u ON n.author_id = u.id
            WHERE n.author_id = ?
            ORDER BY n.posted_date DESC
        """;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, authorId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                News n = new News();
                n.setId(rs.getString("id"));
                n.setTitle(rs.getString("title"));
                n.setImage(rs.getString("image"));
                n.setPostedDate(rs.getDate("posted_date"));
                n.setViewCount(rs.getInt("view_count"));
                n.setAuthorName(rs.getString("author_name"));

                list.add(n);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public List<News> findRelatedByCategory(String categoryId, String excludeId) {
        String sql = """
            SELECT TOP 3
                n.id, n.title, n.image, n.posted_date,
                u.fullname AS author_name
            FROM news n
            JOIN users u ON n.author_id = u.id
            WHERE n.category_id = ?
            AND n.id <> ?
            ORDER BY n.posted_date DESC
        """;

        List<News> list = new ArrayList<>();

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, categoryId);
            ps.setString(2, excludeId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                News n = new News();
                n.setId(rs.getString("id"));
                n.setTitle(rs.getString("title"));
                n.setImage(rs.getString("image"));
                n.setPostedDate(rs.getDate("posted_date"));
                n.setAuthorName(rs.getString("author_name"));
                list.add(n);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private News mapRow(ResultSet rs) throws Exception {
        News n = new News();
        n.setId(rs.getString("id"));
        n.setTitle(rs.getString("title"));
        n.setContent(rs.getString("content"));
        n.setImage(rs.getString("image"));
        n.setPostedDate(rs.getDate("posted_date"));
        n.setAuthorId(rs.getInt("author_id"));
        n.setAuthorName(rs.getString("author_name"));
        n.setViewCount(rs.getInt("view_count"));
        n.setCategoryId(rs.getString("category_id"));
        n.setHome(rs.getBoolean("is_home"));
        return n;
    }
}
