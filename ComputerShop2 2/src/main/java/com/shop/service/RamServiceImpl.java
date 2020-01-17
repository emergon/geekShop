package com.shop.service;

import com.shop.dao.RamDao;
import com.shop.entities.Monitor;
import com.shop.entities.Ram;
import com.shop.entities.RamManufacturer;
import com.shop.entities.RamSize;
import com.shop.entities.RamType;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RamServiceImpl implements RamService {
    
    @Autowired
    RamDao rdao;
    
    @Override
    public List<Ram> getAll() {
        return rdao.findAll();
    }
    
    @Override
    public List<RamManufacturer> findAllManufacturers() {
        return rdao.findAllManufacturers();
    }
    
    @Override
    public List<Ram> findByManufacturer(Integer manufacturer) {
        return rdao.findByManufacturer(manufacturer);
    }
    
    @Override
    public List<RamType> findAllTypes() {
        return rdao.findAllTypes();
    }
    
    @Override
    public List<Ram> findByType(Integer type) {
        return rdao.findByType(type);
    }
    
    @Override
    public List<RamSize> findAllSizes() {
        return rdao.findAllSizes();
    }
    
    @Override
    public List<Ram> findBySize(Integer size) {
        return rdao.findBySize(size);
    }
    
    @Override
    public List<Integer> findAllFrequencies() {
        return rdao.findAllFrequencies();
    }
    
    @Override
    public List<Ram> findByFrequency(Integer frequency) {
        return rdao.findByFrequency(frequency);
    }
    
    @Override
    public List<BigDecimal> findAllVoltage() {
        return rdao.findAllVoltage();
    }
    
    @Override
    public List<Ram> findByVoltage(BigDecimal voltage) {
        return rdao.findByVoltage(voltage);
    }
    
    @Override
    public RamManufacturer findByManufacturerId(Integer manufacturer) {
        return rdao.findByManufacturerId(manufacturer);
    }
    
    @Override
    public RamSize findBySizeId(Integer size) {
        return rdao.findBySizeId(size);
    }
    
    @Override
    public RamType findByTypeId(Integer type) {
        return rdao.findByTypeId(type);
    }
    
    @Override
    public void saveOrUpdate(Ram ram) {
        rdao.createOrUpdate(ram);
    }
    
}
