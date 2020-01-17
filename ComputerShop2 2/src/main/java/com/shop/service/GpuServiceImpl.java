package com.shop.service;

import com.shop.dao.GpuDao;
import com.shop.entities.Gpu;
import com.shop.entities.GpuChipset;
import com.shop.entities.GpuManufacturer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GpuServiceImpl implements GpuService {
    
    @Autowired
    GpuDao gdao;
    
    @Override
    public List<Gpu> getAll() {
        return gdao.findAll();
    }
    
    @Override
    public List<GpuManufacturer> findAllManufacturers() {
        return gdao.findAllManufacturers();
    }
    
    @Override
    public List<Gpu> findByManufacturer(Integer manufacturer) {
        return gdao.findByManufacturer(manufacturer);
    }
    
    @Override
    public List<GpuChipset> findAllChipsets() {
        return gdao.findAllChipsets();
    }
    
    @Override
    public List<Gpu> findByChipset(Integer type) {
        return gdao.findByChipset(type);
    }
    
    @Override
    public List<Integer> findAllMemories() {
        return gdao.findAllMemories();
    }
    
    @Override
    public List<Gpu> findByMemory(Integer memory) {
        return gdao.findByMemory(memory);
    }
    
    @Override
    public GpuManufacturer findByManufacturerId(Integer manufacturer) {
        return gdao.findByManufacturerId(manufacturer);
    }
    
    @Override
    public GpuChipset findByChipsetId(Integer chipset) {
        return gdao.findByChipsetId(chipset);
    }
    
    @Override
    public void saveOrUpdate(Gpu gpu) {
        gdao.createOrUpdate(gpu);
    }
    
}
