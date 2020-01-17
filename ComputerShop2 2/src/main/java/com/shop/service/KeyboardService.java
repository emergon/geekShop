package com.shop.service;

import com.shop.entities.*;
import java.util.List;

public interface KeyboardService {
    List<Keyboard> getAll();
    
    public List<KeyboardManufacturer> findAllManufacturers();

    public List<Keyboard> findByManufacturer(Integer manufacturer);
    
    public List<KeyboardType> findAllTypes();
    
    public List<Keyboard> findByType(Integer type);

    public KeyboardManufacturer findByManufacturerId(Integer manufacturer);

    public KeyboardType findByTypeId(Integer type);

    public void saveOrUpdate(Keyboard keyboard);
}
