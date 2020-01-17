package com.shop.dao;

import com.shop.entities.Category;
import com.shop.entities.Product;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao implements InterfaceDao<Product> {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Product> findAll() {
        Query q = getSession().createNamedQuery("Product.findAll");
        List<Product> list = q.getResultList();
        return list;
    }

    @Override
    public boolean createOrUpdate(Product p) {
        System.out.println("dao**************************   " + p);
        try {
            getSession().saveOrUpdate(p);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Product> findNewest() {
        Query q = getSession().createNamedQuery("Product.findLimit");
        List<Product> list = q.setMaxResults(5).getResultList();
        return list;
    }

    public List<Product> findSales() {
        Query q = getSession().createNamedQuery("Product.findSales");
        List<Product> list = q.setMaxResults(5).getResultList();
        return list;
    }

    public List<Category> getDetails() {
        Query q = getSession().createNamedQuery("Category.findAll");
        List<Category> list = q.getResultList();
        return list;
    }

    public List<Product> getByCategory(Integer category) {
        Query q = getSession().createQuery("SELECT p FROM Product p WHERE p.category.id= :category");
        q.setParameter("category", category);
        List<Product> list = q.getResultList();
        return list;
    }
//    public List<Product> test(Integer category){
//        Query q = getSession().createQuery("SELECT p FROM Product p WHERE");
//        
//        List<Product> list = q.getResultList();
//        return list;
//    }

//    @Override
    public List<Product> getByPrice(BigDecimal initialPrice, BigDecimal finalPrice, Integer category) {
        Query q = getSession().createQuery("SELECT p FROM Product p WHERE p.price BETWEEN :initialPrice AND :finalPrice AND p.category.id= :category");
        q.setParameter("initialPrice", initialPrice);
        q.setParameter("finalPrice", finalPrice);
        q.setParameter("category", category);
        List<Product> list = q.getResultList();
        return list;
    }

    //product/min/max/manufacturer/size/inches
//    @Override
//    public void delete(int id) {
//        Query q = getSession().createNamedQuery("Customer.deleteById");
//        q.setParameter("kwdikos", id);
//        int result = q.executeUpdate();
//    }
//
//    @Override
//    public Customer findById(Integer id) {
//        return (Customer) getSession().get(Customer.class, id);
//    }
//
//    @Override
//    public List<Customer> findCustomersByName(String searchName) {
//        Query q = getSession().createNamedQuery("Customer.findLikeName");
//        q.setParameter("name", "%" + searchName + "%");
//        List<Customer> list = q.getResultList();
//        return list;
//    }
//
    public Product findById(Integer id) {
        return (Product) getSession().get(Product.class, id);
    }

    @Override
    public void delete(Product t) {
//        getSession().delete(t);
        Query q = getSession().createNamedQuery("Product.deleteById"); //to onoma pou dosame sto namedquery stin klasi toy entity 
        q.setParameter("id", t.getId());
        int result = q.executeUpdate();
    }
    
    public Product hasSameName (Product product){
        Query q = getSession().createQuery("SELECT p FROM Product p WHERE p.name= :name");
        q.setParameter("name", product.getName());
        List<Product> list = q.getResultList();
        if (list.size()>0){
        return list.get(0);
        }
        return null;
    }
   
    public Product hasSameCode (Product product){
        Query q = getSession().createQuery("SELECT p FROM Product p WHERE p.pcode= :pcode");
        q.setParameter("pcode", product.getPcode());
        List<Product> list = q.getResultList();
        if (list.size()>0){
        return list.get(0);
        }
        return null;
    }
   
    public boolean hasSameNameExceptThisOne (Product product){
      
        Query q = getSession().createNativeQuery("SELECT * FROM product WHERE name= :name and id!= :id");
        q.setParameter("name", product.getName());
        q.setParameter("id", product.getId());
        List<Product> list = q.getResultList();
        if (list.size()>0){
        return true;
        }
        return false;
    }
   
    public boolean hasSameCodeExceptThisOne (Product product){
        Query q = getSession().createNativeQuery("SELECT * FROM product WHERE pcode= :pcode and id!= :id");
        q.setParameter("pcode", product.getPcode());
        q.setParameter("id", product.getId());
        List<Product> list = q.getResultList();
        if (list.size()>0){
        return true;
        }
        return false;
    }
    
    
}
