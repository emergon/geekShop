package com.shop.service;

import com.shop.dao.CpuDao;
import com.shop.entities.Cpu;
import com.shop.entities.CpuChip;
import com.shop.entities.CpuManufacturer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CpuServiceImpl implements CpuService {
    
    @Autowired
    CpuDao cdao;
    
    @Override
    public List<Cpu> getAll() {
        return cdao.findAll();
    }
    
    @Override
    public List<CpuManufacturer> findAllManufacturers() {
        return cdao.findAllManufacturers();
    }
    
    @Override
    public List<Cpu> findByManufacturer(Integer manufacturer) {
        return cdao.findByManufacturer(manufacturer);
    }
    
    @Override
    public List<CpuChip> findAllChips() {
        return cdao.findAllChips();
    }
    
    @Override
    public List<Cpu> findByChip(Integer type) {
        return cdao.findByChip(type);
    }
    
    @Override
    public List<Integer> findAllCores() {
        return cdao.findAllCores();
    }
    
    @Override
    public List<Cpu> findByCores(Integer cores) {
        return cdao.findByCores(cores);
    }
    
    @Override
    public CpuManufacturer findByManufacturerId(Integer manufacturer) {
        return cdao.findByManufacturerId(manufacturer);
    }
    
    @Override
    public CpuChip findByChipId(Integer chip) {
        return cdao.findByChipId(chip);
    }
    
    @Override
    public void saveOrUpdate(Cpu cpu) {
        cdao.createOrUpdate(cpu);
    }
}
