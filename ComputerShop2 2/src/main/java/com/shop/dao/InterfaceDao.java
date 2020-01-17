package com.shop.dao;


import com.shop.entities.Category;
import com.shop.entities.Product;
import java.util.List;

public interface InterfaceDao<T> {

    List<T> findAll();

    public boolean createOrUpdate(T t);
    
    

//    public List<T> findNewest();
//    
//    public List<T> findSales();

//    public List getDetails();
     
    
//    List<T> getByPrice(int initialPrice, int finalPrice, int category);
    
    
    
    public void delete(T t);
//
//    public Customer findById(Integer id);
//
//    public List<Customer> findCustomersByName(String searchName);
}
