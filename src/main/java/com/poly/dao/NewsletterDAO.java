package com.poly.dao;

import com.poly.model.Newsletter;

import java.util.List;

public interface NewsletterDAO {
    int countAccount();
    List<Newsletter> findAll();
    void create(Newsletter newsletter);
    boolean existsByEmail(String email);
    void update(Newsletter n);
    void deleteByEmail(String email);
}
