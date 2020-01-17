package com.shop.service;

import com.shop.entities.*;
import java.util.List;

public interface CpuService {
    
    List<Cpu> getAll();
    
    List<CpuManufacturer> findAllManufacturers();

    List<Cpu> findByManufacturer(Integer manufacturer);
    
    List<CpuChip> findAllChips();
    
    List<Cpu> findByChip(Integer type);
    
    List<Integer> findAllCores();
    
    List<Cpu> findByCores(Integer cores);

    public CpuManufacturer findByManufacturerId(Integer manufacturer);

    public CpuChip findByChipId(Integer chip);

    public void saveOrUpdate(Cpu cpu);
}
