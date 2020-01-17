package com.shop.service;

import com.shop.dao.MouseDao;
import com.shop.entities.Mouse;
import com.shop.entities.MouseManufacturer;
import com.shop.entities.MouseType;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MouseServiceImpl implements MouseService {
    
    @Autowired
    MouseDao mdao;
    
    @Override
    public List<Mouse> getAll() {
        return mdao.findAll();
    }
    
    @Override
    public List<MouseManufacturer> findAllManufacturers() {
        return mdao.findAllManufacturers();
    }
    
    @Override
    public List<Mouse> findByManufacturer(Integer manufacturer) {
        return mdao.findByManufacturer(manufacturer);
    }
    
    @Override
    public List<MouseType> findAllTypes() {
        return mdao.findAllTypes();
    }
    
    @Override
    public List<Mouse> findByType(Integer type) {
        return mdao.findByType(type);
    }
    
    @Override
    public MouseManufacturer findByManufacturerId(Integer manufacturer) {
        return mdao.findByManufacturerId(manufacturer);
    }
    
    @Override
    public MouseType findByTypeId(Integer type) {
        return mdao.findByTypeId(type);
    }
    
    @Override
    public void saveOrUpdate(Mouse mouse) {
        mdao.createOrUpdate(mouse);
    }
    
}
