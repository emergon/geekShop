package com.shop.service;

import com.shop.entities.*;
import java.util.List;

public interface MouseService {
    List<Mouse> getAll();
    
    public List<MouseManufacturer> findAllManufacturers();

    public List<Mouse> findByManufacturer(Integer manufacturer);
    
    public List<MouseType> findAllTypes();
    
    public List<Mouse> findByType(Integer type);

    public MouseManufacturer findByManufacturerId(Integer manufacturer);

    public MouseType findByTypeId(Integer type);

    public void saveOrUpdate(Mouse mouse);
}
