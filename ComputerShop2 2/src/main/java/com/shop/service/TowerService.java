package com.shop.service;

import com.shop.entities.Tower;
import com.shop.entities.TowerManufacturer;
import com.shop.entities.TowerType;
import java.util.List;

public interface TowerService {

    List<Tower> getAll();

    List<Tower> findByManufacturer(Integer manufacturer);
    
    List<TowerManufacturer> findAllManufacturers();
    
    List<Tower> findByType(Integer type);
    
    List<TowerType> findAllTypes();

    public TowerManufacturer findByManufacturerId(Integer manufacturer);

    public TowerType findByTypeId(Integer manufacturer);

    public boolean saveOrUpdate(Tower tower);

    public Tower findById(Integer productId);

    public void delete(Tower tower);
}
