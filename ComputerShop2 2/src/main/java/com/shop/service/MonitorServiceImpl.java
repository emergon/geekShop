package com.shop.service;

import com.shop.dao.MonitorDao;
import com.shop.entities.Monitor;
import com.shop.entities.MonitorManufacturer;
import com.shop.entities.Product;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MonitorServiceImpl implements MonitorService {
    
    @Autowired
    MonitorDao mdao;
    
    @Override
    public List<Monitor> getAll() {
        return mdao.findAll();
    }
    
    @Override
    public List<MonitorManufacturer> findAllManufacturers() {
        return mdao.findAllManufacturers();
    }
    
    @Override
    public List<Monitor> findByManufacturer(Integer manufacturer) {
        return mdao.findByManufacturer(manufacturer);
    }
    
    @Override
    public List<Integer> findAllInches() {
        return mdao.findAllInches();
    }
    
    @Override
    public List<Monitor> findByInches(Integer inches) {
        return mdao.findByInches(inches);
    }
    
    @Override
    public MonitorManufacturer findByManufacturerId(Integer manufacturer) {
        return mdao.findByManufacturerId(manufacturer);
    }
    
    @Override
    public void saveOrUpdate(Monitor monitor) {
        mdao.createOrUpdate(monitor);
    }
}
