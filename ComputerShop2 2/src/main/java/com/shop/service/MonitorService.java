package com.shop.service;

import com.shop.entities.*;
import java.util.List;

public interface MonitorService {
    List<Monitor> getAll();
    
    List<MonitorManufacturer> findAllManufacturers();

    List<Monitor> findByManufacturer(Integer manufacturer);
    
    List<Integer> findAllInches();
    
    List<Monitor> findByInches(Integer inches);

    public MonitorManufacturer findByManufacturerId(Integer manufacturer);

    public void saveOrUpdate(Monitor monitor);
}
