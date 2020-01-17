package com.shop.service;

import com.shop.dao.KeyboardDao;
import com.shop.entities.Keyboard;
import com.shop.entities.KeyboardManufacturer;
import com.shop.entities.KeyboardType;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class KeyboardServiceImpl implements KeyboardService {
    
    @Autowired
    KeyboardDao kdao;
    
    @Override
    public List<Keyboard> getAll() {
        return kdao.findAll();
    }
    
    @Override
    public List<KeyboardManufacturer> findAllManufacturers() {
        return kdao.findAllManufacturers();
    }
    
    @Override
    public List<Keyboard> findByManufacturer(Integer manufacturer) {
        return kdao.findByManufacturer(manufacturer);
    }
    
    @Override
    public List<KeyboardType> findAllTypes() {
        return kdao.findAllTypes();
    }
    
    @Override
    public List<Keyboard> findByType(Integer type) {
        return kdao.findByType(type);
    }
    
    @Override
    public KeyboardManufacturer findByManufacturerId(Integer manufacturer) {
        return kdao.findByManufacturerId(manufacturer);
    }
    
    @Override
    public KeyboardType findByTypeId(Integer type) {
        return kdao.findByTypeId(type);
    }
    
    @Override
    public void saveOrUpdate(Keyboard keyboard) {
        kdao.createOrUpdate(keyboard);
    }
    
}
