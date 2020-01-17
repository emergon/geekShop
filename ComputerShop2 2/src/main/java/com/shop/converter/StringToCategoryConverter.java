package com.shop.converter;

import com.shop.entities.Category;
import com.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToCategoryConverter implements Converter<Object, Category> {

    @Autowired
    CategoryService categoryService;

    @Override
    public Category convert(Object source) {
        Integer id = Integer.parseInt((String) source);
        Category category = categoryService.findById(id);
        return category;
    }

}
