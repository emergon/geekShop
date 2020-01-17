package com.shop.service;

import com.shop.entities.*;
import java.math.BigDecimal;
import java.util.List;

public interface RamService {
    
    List<Ram> getAll();
    
    List<RamManufacturer> findAllManufacturers();

    List<Ram> findByManufacturer(Integer manufacturer);
    
    List<RamType> findAllTypes();
    
    List<Ram> findByType(Integer type);

    List<RamSize> findAllSizes();
    
    List<Ram> findBySize(Integer size);
    
    List<Integer> findAllFrequencies();
    
    List<Ram> findByFrequency(Integer frequency);
    
    List<BigDecimal> findAllVoltage();
    
    List<Ram> findByVoltage(BigDecimal voltage);

    public RamManufacturer findByManufacturerId(Integer manufacturer);

    public RamSize findBySizeId(Integer size);

    public RamType findByTypeId(Integer type);

    public void saveOrUpdate(Ram ram);
}
