package com.poly.service;

import java.util.List;

import com.poly.model.Newsletter;

public interface NewsletterService {
    int numberOfAccounts();
    List<Newsletter> getAll();
    void create(Newsletter newsletter);
    void update(Newsletter newsletter);
    void deleteByEmail(String email);
}
