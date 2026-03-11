package com.poly.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.poly.dao.NewsletterDAO;
import com.poly.model.Newsletter;
import com.poly.utils.DBConnect;

public class NewsletterDAOImpl implements NewsletterDAO {

    @Override
    public int countAccount() {
        String sql = """
            SELECT COUNT(*) AS total FROM newsletters
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
    public List<Newsletter> findAll() {
        List<Newsletter> list = new ArrayList<>();

        String sql = """
            SELECT email, fullname, enabled
            FROM newsletters
            ORDER BY email
        """;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Newsletter n = new Newsletter();
                n.setEmail(rs.getString("email"));
                n.setFullname(rs.getString("fullname"));
                n.setEnabled(rs.getBoolean("enabled"));
                list.add(n);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public void create(Newsletter n) {
        String sql = """
            INSERT INTO newsletters (email, fullname, enabled)
            VALUES (?, ?, ?)
        """;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, n.getEmail());
            ps.setString(2, n.getFullname());
            ps.setBoolean(3, n.getEnabled());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        String sql = "SELECT 1 FROM newsletters WHERE email = ?";

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            return ps.executeQuery().next();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Newsletter n) {
        String sql = """
            UPDATE newsletters
            SET fullname = ?, enabled = ?
            WHERE email = ?
        """;

        try (Connection c = DBConnect.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, n.getFullname());
            ps.setBoolean(2, n.getEnabled());
            ps.setString(3, n.getEmail());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteByEmail(String email) {
        String sql = "DELETE FROM newsletters WHERE email = ?";

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
