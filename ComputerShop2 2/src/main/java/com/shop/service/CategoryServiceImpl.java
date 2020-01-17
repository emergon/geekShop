package com.shop.service;

import com.shop.dao.CategoryDao;
import com.shop.entities.Category;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao catDao;

    @Override
    public List<Category> getCategories() {

        List<Category> list = catDao.fereCategories();
        return list;
    }

    @Override
    public Category findById(Integer id) {
        return catDao.findById(id);
    }

    @Override
    public Integer findCategoryByName(String jspName) {
        return catDao.findCategoryByName(jspName);
    }
}
