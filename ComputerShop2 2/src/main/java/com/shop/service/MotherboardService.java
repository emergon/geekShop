package com.shop.service;

import com.shop.entities.*;
import java.util.List;

public interface MotherboardService {
    
    List<Motherboard> getAll();

    List<MotherboardManufacturer> findAllManufacturers();

    List<Motherboard> findByManufacturer(Integer manufacturer);
    
    List<MotherboardSocket> findAllSockets();
    
    List<Motherboard> findBySocket(Integer type);
    
    List<MotherboardSize> findAllSizes();

    List<Motherboard> findBySize(Integer size);
    
    List<MotherboardChipset> findAllChipsets();

    List<Motherboard> findByChipset(Integer chipset);
    
    List<MotherboardPort> findAllPorts();

    List<Motherboard> findByPort(Integer port);

    public MotherboardManufacturer findManufacturerById(Integer manufacturer);

    public MotherboardSocket findSocketById(Integer socket);

    public MotherboardSize findSizeById(Integer size);

    public MotherboardPort findPortById(Integer port);

    public MotherboardChipset findChipsetById(Integer chipset);

    public void saveOrUpdate(Motherboard motherboard);
}
