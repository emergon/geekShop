package com.shop.service;

import com.shop.entities.*;
import java.util.List;

public interface StorageService {

    List<Storage> getAll();

    List<StorageManufacturer> findAllManufacturers();

    List<Storage> findByManufacturer(Integer manufacturer);

    List<StorageType> findAllTypes();

    List<Storage> findByType(Integer type);
    
    List<StorageInches> findAllInches();
    
    List<Storage> findByInches(Integer inches);

    public StorageManufacturer findManufacturerById(Integer manufacturer);

    public StorageInches findInchesById(Integer inches);

    public void saveOrUpdate(Storage storage);

    public StorageType findTypeById(Integer type);
}
