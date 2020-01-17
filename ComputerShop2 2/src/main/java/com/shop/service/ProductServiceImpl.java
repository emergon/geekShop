package com.shop.service;

import com.shop.entities.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.shop.dao.ProductDao;
import com.shop.entities.Category;
import com.shop.entities.Tower;
import java.math.BigDecimal;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao cdao;

    @Override
    public List<Product> getAll() {
        return cdao.findAll();
    }

    @Override
    public Product createOrUpdate(Product p) {
        cdao.createOrUpdate(p);
        return p;
    }

    @Override
    public List<Product> getNewest() {
        List<Product> list = cdao.findNewest();
        return list;
    }

    @Override
    public List<Product> findSales() {
        return cdao.findSales();
    }

    @Override
    public List<Category> getDetails() {
        List<Category> list = cdao.getDetails();
        return list;
    }

    public List<Product> getByCategory(Integer category) {
        return cdao.getByCategory(category);
    }

//    @Override
//    public void deleteCustomer(int id) {
//        cdao.delete(id);
//    }
//
//    @Override
//    public Customer findCustomerById(Integer id) {
//        return cdao.findById(id);
//    }
//
//    @Override
//    public List<Customer> findCustomersByName(String searchName) {
//        List<Customer> list = cdao.findCustomersByName(searchName);
//        return list;
//    }
    @Override
    public List<Product> getByPrice(BigDecimal initialPrice, BigDecimal finalPrice, Integer category) {
        List<Product> list = cdao.getByPrice(initialPrice, finalPrice, category);
        return list;
    }

    @Override
    public Product findById(Integer id) {
        return cdao.findById(id);
    }

    @Override
    public void delete(Product product) {
        cdao.delete(product);
    }

    @Override
    public Boolean hasSameName(Product product) {
       Product productToCheck = cdao.hasSameName(product);
       if (productToCheck == null){
           return false;
       }else{
           return true;
       }
    }

 

    @Override
    public Boolean hasSameCode(Product product) {
     Product productToCheck = cdao.hasSameCode(product);
       if (productToCheck == null){
           return false;
       }else{
           return true;
       }
    }
    
     public Boolean hasSameNameExceptThisOne (Product product){
     boolean result = cdao.hasSameNameExceptThisOne(product);
     return result;       
      }
    
    public Boolean hasSameCodeExceptThisOne (Product product){
         boolean result = cdao.hasSameCodeExceptThisOne(product);
       return result;
    }
}
