package com.shop.service;

import com.shop.dao.MotherboardDao;
import com.shop.entities.Motherboard;
import com.shop.entities.MotherboardChipset;
import com.shop.entities.MotherboardManufacturer;
import com.shop.entities.MotherboardPort;
import com.shop.entities.MotherboardSize;
import com.shop.entities.MotherboardSocket;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
public class MotherboardServiceImpl implements MotherboardService {
    
    @Autowired
    MotherboardDao mdao;
    
    @Override
    public List<Motherboard> getAll() {
        return mdao.findAll();
    }
    
    @Override
    public List<MotherboardManufacturer> findAllManufacturers() {
        return mdao.findAllManufacturers();
    }
    
    @Override
    public List<Motherboard> findByManufacturer(Integer manufacturer) {
        return mdao.findByManufacturer(manufacturer);
    }
    
    @Override
    public List<MotherboardSocket> findAllSockets() {
        return mdao.findAllSockets();
    }
    
    @Override
    public List<Motherboard> findBySocket(Integer type) {
        return mdao.findBySocket(type);
    }
    
    @Override
    public List<MotherboardSize> findAllSizes() {
        return mdao.findAllSizes();
    }
    
    @Override
    public List<Motherboard> findBySize(Integer size) {
        return mdao.findBySize(size);
    }
    
    @Override
    public List<MotherboardChipset> findAllChipsets() {
        return mdao.findAllChipsets();
    }
    
    @Override
    public List<Motherboard> findByChipset(Integer chipset) {
        return mdao.findByChipset(chipset);
    }
    
    @Override
    public List<MotherboardPort> findAllPorts() {
        return mdao.findAllPorts();
    }
    
    @Override
    public List<Motherboard> findByPort(Integer port) {
        return mdao.findByPort(port);
    }
    
    @Override
    public MotherboardManufacturer findManufacturerById(Integer manufacturer) {
        return mdao.findManufacturerById(manufacturer);
    }
    
    @Override
    public MotherboardSocket findSocketById(Integer socket) {
        return mdao.findSocketById(socket);
    }
    
    @Override
    public MotherboardSize findSizeById(Integer size) {
        return mdao.findSizeById(size);
    }
    
    @Override
    public MotherboardPort findPortById(Integer port) {
        return mdao.findPortById(port);
    }
    
    @Override
    public MotherboardChipset findChipsetById(Integer chipset) {
        return mdao.findChipsetById(chipset);
    }
    
    @Override
    public void saveOrUpdate(Motherboard motherboard) {
        mdao.createOrUpdate(motherboard);
    }
    
}
