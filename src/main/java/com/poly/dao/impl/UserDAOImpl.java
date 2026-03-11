package com.poly.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.poly.dao.UserDAO;
import com.poly.model.User;
import com.poly.model.enums.Role;
import com.poly.utils.DBConnect;

public class UserDAOImpl implements UserDAO {

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        String sql = """
            SELECT * FROM users ORDER BY created_at DESC
        """;

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
    public int countUsers() {
        String sql = """
            SELECT COUNT(*) AS total FROM users
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
    public int countAdmins() {
        String sql = """
            SELECT COUNT(*) AS total FROM users WHERE role = 1
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
    public int countReporters() {
        String sql = """
            SELECT COUNT(*) AS total FROM users WHERE role = 0
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
    public User findById(String id) {
        String sql = """
            SELECT * FROM users WHERE id = ?
        """;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(id));

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
    public User findByUsername(String username) {
        String sql = """
            SELECT * FROM users WHERE username = ?
        """;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);

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
    public boolean existsByEmail(String email) {
        String sql = "SELECT 1 FROM users WHERE email = ?";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existsByUsername(String username) {
        String sql = "SELECT 1 FROM users WHERE username = ?";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(User u) {
        String sql = """
            INSERT INTO users (username, password, fullname, email, phone, birthday, gender, avatar, role, status)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getFullname());
            ps.setString(4, u.getEmail());
            ps.setString(5, u.getPhone());
            if (u.getBirthday() != null) {
                ps.setDate(6, new java.sql.Date(u.getBirthday().getTime()));
            } else {
                ps.setNull(6, java.sql.Types.DATE);
            }
            ps.setBoolean(7, u.isGender());
            ps.setString(8, u.getAvatar());
            ps.setBoolean(9, u.getRole().toDb());
            ps.setBoolean(10, u.isStatus());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User u) {
        String sql = """
            UPDATE users SET
                username = ?,
                password = ?,
                fullname = ?,
                email = ?,
                phone = ?,
                birthday = ?,
                gender = ?,
                avatar = ?,
                role = ?,
                status = ?
            WHERE id = ?
        """;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getFullname());
            ps.setString(4, u.getEmail());
            ps.setString(5, u.getPhone());

            if (u.getBirthday() != null) {
                ps.setDate(6, new java.sql.Date(u.getBirthday().getTime()));
            } else {
                ps.setNull(6, java.sql.Types.DATE);
            }

            ps.setBoolean(7, u.isGender());
            ps.setString(8, u.getAvatar());
            ps.setBoolean(9, u.getRole().toDb());
            ps.setBoolean(10, u.isStatus());
            ps.setInt(11, u.getId());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean hasNews(int userId) {
        String sql = "SELECT 1 FROM news WHERE author_id = ?";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = """
            DELETE FROM users WHERE id = ?
        """;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findAuthorByNewsId(String newsId) {
        String sql = """
            SELECT u.id, u.fullname, u.avatar
            FROM news n
            JOIN users u ON n.author_id = u.id
            WHERE n.id = ?
        """;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newsId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        null,
                        null,
                        rs.getString("fullname"),
                        null,
                        null,
                        null,
                        false,
                        rs.getString("avatar"),
                        null,
                        true,
                        null
                );
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private User mapRow(ResultSet rs) throws Exception {
        User u = new User();

        u.setId(rs.getInt("id"));
        u.setUsername(rs.getString("username"));
        u.setPassword(rs.getString("password"));

        u.setFullname(rs.getString("fullname"));
        u.setEmail(rs.getString("email"));
        u.setPhone(rs.getString("phone"));

        u.setBirthday(rs.getDate("birthday"));
        u.setGender(rs.getBoolean("gender"));
        u.setAvatar(rs.getString("avatar"));

        u.setRole(Role.fromDb(rs.getBoolean("role")));
        u.setStatus(rs.getBoolean("status"));

        u.setCreatedAt(rs.getTimestamp("created_at"));

        return u;
    }
}
