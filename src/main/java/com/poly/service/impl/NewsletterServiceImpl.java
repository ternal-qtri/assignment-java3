package com.poly.service.impl;

import com.poly.dao.NewsletterDAO;
import com.poly.dao.impl.NewsletterDAOImpl;
import com.poly.model.Newsletter;
import com.poly.service.NewsletterService;

import java.util.List;

public class NewsletterServiceImpl implements NewsletterService {
    private final NewsletterDAO newslettersDAO = new NewsletterDAOImpl();

    @Override
    public int numberOfAccounts() {
        return newslettersDAO.countAccount();
    }

    @Override
    public List<Newsletter> getAll() {
        return newslettersDAO.findAll();
    }

    @Override
    public void create(Newsletter n) {
        if (n.getEmail() == null || n.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email không được để trống");
        }

        try {
            newslettersDAO.create(n);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("Email đã tồn tại trong hệ thống");
        }
    }

    @Override
    public void update(Newsletter newsletter) {
        if (newsletter.getEmail() == null || newsletter.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email không hợp lệ");
        }
        newslettersDAO.update(newsletter);
    }

    @Override
    public void deleteByEmail(String email) {
        newslettersDAO.deleteByEmail(email);
    }
}
