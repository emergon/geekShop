
package com.shop.dao;


import com.shop.entities.Category;
import java.util.List;

public interface CategoryDao {
    
    List<Category> fereCategories ();

    public Category findById(Integer id);

    public Integer findCategoryByName(String jspName);
    
    
    
}
