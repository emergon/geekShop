package com.shop.service;

import com.shop.dao.TowerDao;
import com.shop.entities.Tower;
import com.shop.entities.TowerManufacturer;
import com.shop.entities.TowerType;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TowerServiceImpl implements TowerService {

    @Autowired
    TowerDao tdao;

    @Override
    public List<Tower> getAll() {
        return tdao.findAll();
    }

    @Override
    public List<Tower> findByManufacturer(Integer manufacturer) {
        return tdao.findByManufacturer(manufacturer);
    }

    @Override
    public List<TowerManufacturer> findAllManufacturers() {
        return tdao.findAllManufacturers();
    }

    @Override
    public List<Tower> findByType(Integer type) {
        return tdao.findByType(type);
    }

    @Override
    public List<TowerType> findAllTypes() {
        return tdao.findAllTypes();
    }

    @Override
    public TowerManufacturer findByManufacturerId(Integer manufacturer) {
        return tdao.findByManufacturerId(manufacturer);
    }

    @Override
    public TowerType findByTypeId(Integer type) {
        return tdao.findByTypeId(type);
    }

    @Override
    public boolean saveOrUpdate(Tower tower) {
        return tdao.createOrUpdate(tower);
    }

    @Override
    public Tower findById(Integer productId) {
        return tdao.findById(productId);
    }

    @Override
    public void delete(Tower t) {
        tdao.delete(t);
    }

}
