package com.shop.service;

import com.shop.entities.*;
import java.util.List;

public interface GpuService {

    List<Gpu> getAll();
    
    List<GpuManufacturer> findAllManufacturers();

    List<Gpu> findByManufacturer(Integer manufacturer);
    
    List<GpuChipset> findAllChipsets();
    
    List<Gpu> findByChipset(Integer type);
    
    List<Integer> findAllMemories();
    
    List<Gpu> findByMemory(Integer memory);

    public GpuManufacturer findByManufacturerId(Integer manufacturer);

    public GpuChipset findByChipsetId(Integer chipset);

    public void saveOrUpdate(Gpu gpu);
}
