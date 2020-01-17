package com.shop.service;

import com.shop.dao.StorageDao;
import com.shop.entities.Storage;
import com.shop.entities.StorageInches;
import com.shop.entities.StorageManufacturer;
import com.shop.entities.StorageType;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StorageServiceImpl implements StorageService {

    @Autowired
    StorageDao sdao;

    @Override
    public List<Storage> getAll() {
        return sdao.findAll();
    }

    @Override
    public List<StorageManufacturer> findAllManufacturers() {
        return sdao.findAllManufacturers();
    }

    @Override
    public List<Storage> findByManufacturer(Integer manufacturer) {
        return sdao.findByManufacturer(manufacturer);
    }

    @Override
    public List<StorageType> findAllTypes() {
        return sdao.findAllTypes();
    }

    @Override
    public List<Storage> findByType(Integer type) {
        return sdao.findByType(type);
    }

    @Override
    public List<StorageInches> findAllInches() {
        return sdao.findAllInches();
    }

    @Override
    public List<Storage> findByInches(Integer inches) {
        return sdao.findByInches(inches);
    }

    @Override
    public StorageManufacturer findManufacturerById(Integer manufacturer) {
        return sdao.findManufacturerById(manufacturer);
    }

    @Override
    public StorageInches findInchesById(Integer inches) {
        return sdao.findInchesById(inches);
    }

    @Override
    public void saveOrUpdate(Storage storage) {
        sdao.createOrUpdate(storage);
    }

    @Override
    public StorageType findTypeById(Integer type) {
        return sdao.findTypeById(type);
    }

}
