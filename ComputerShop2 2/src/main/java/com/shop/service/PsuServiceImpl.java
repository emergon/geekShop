package com.shop.service;

import com.shop.dao.PsuDao;
import com.shop.entities.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PsuServiceImpl implements PsuService {
    
    @Autowired
    PsuDao pdao;
    
    @Override
    public List<Psu> getAll() {
        return pdao.findAll();
    }
    
    @Override
    public List<PsuManufacturer> findAllManufacturers() {
        return pdao.findAllManufacturers();
    }
    
    @Override
    public List<Psu> findByManufacturer(Integer manufacturer) {
        return pdao.findByManufacturer(manufacturer);
    }
    
    @Override
    public List<PsuCableManagement> findAllCableManagement() {
        return pdao.findAllCableManagement();
    }
    
    @Override
    public List<Psu> findByCableManagement(Integer type) {
        return pdao.findByCableManagement(type);
    }
    
    @Override
    public List<PsuEfficiency> findAllEfficiencies() {
        return pdao.findAllEfficiencies();
    }
    
    @Override
    public List<Psu> findByEfficiency(Integer efficiency) {
        return pdao.findByEfficiency(efficiency);
    }
    
    @Override
    public List<Integer> findAllWatts() {
        return pdao.findAllWatts();
    }
    
    @Override
    public List<Psu> findByWatt(Integer watt) {
        return pdao.findByWatt(watt);
    }
    
    @Override
    public PsuManufacturer findManufacturerById(Integer manufacturer) {
        return pdao.findManufacturerById(manufacturer);
    }
    
    @Override
    public PsuCableManagement findCableManagementById(Integer cableManagement) {
        return pdao.findCableManagementById(cableManagement);
    }
    
    @Override
    public PsuEfficiency findEfficiencyById(Integer efficiency) {
        return pdao.findEfficiencyById(efficiency);
    }
    
    @Override
    public void saveOrUpdate(Psu psu) {
        pdao.createOrUpdate(psu);
    }
    
}
