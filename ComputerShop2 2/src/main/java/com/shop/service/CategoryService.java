/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.service;

import com.shop.entities.Category;
import java.util.List;

/**
 *
 * @author mike
 */
public interface CategoryService {
    
    List<Category> getCategories ();
    
    Category findById(Integer id);

    public Integer findCategoryByName(String jspName);
}
