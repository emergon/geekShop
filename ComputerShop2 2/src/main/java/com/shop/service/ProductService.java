/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.service;

import com.shop.entities.Product;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author frodi
 */
public interface ProductService {
    
    public Product createOrUpdate(Product p);

    List<Product> getAll();
    
    public List<Product> getNewest();

    public List<Product> findSales();
    
     public List getDetails();
  
     public Product findById(Integer id);

    
    
//    public void deleteCustomer(int id);
//
//    public Customer findCustomerById(Integer id);
//
//    public List<Customer> findCustomersByName(String searchName);

    public List<Product> getByCategory(Integer category);

    public List<Product> getByPrice(BigDecimal initialPrice, BigDecimal finalPrice, Integer category);

    public void delete(Product product);

    public Boolean hasSameName (Product product);
   
    public Boolean hasSameCode (Product product);
   
   
     public Boolean hasSameNameExceptThisOne (Product product);
   
    public Boolean hasSameCodeExceptThisOne (Product product);


}
