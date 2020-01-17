
package com.shop.dao;

import com.shop.entities.Category;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDaoImpl implements CategoryDao {
    
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Category> fereCategories() {
        Query q = getSession().createNamedQuery("Category.findAll");
        List<Category> list = q.getResultList();
        return list;
    }

    @Override
    public Category findById(Integer id) {
        return (Category) getSession().get(Category.class, id);
    }    

    @Override
    public Integer findCategoryByName(String jspName) {
        Query q = getSession().createNamedQuery("Category.findByName");
        q.setParameter("pcategory", jspName);
        List<Integer> list = q.getResultList();
        int intId =list.get(0);
        return intId;
    }
    
}
