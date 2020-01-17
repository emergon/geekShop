package com.shop.service;

import com.shop.entities.*;
import java.util.List;

public interface PsuService {

    List<Psu> getAll();

    List<PsuManufacturer> findAllManufacturers();

    List<Psu> findByManufacturer(Integer manufacturer);

    List<PsuCableManagement> findAllCableManagement();

    List<Psu> findByCableManagement(Integer type);

    List<PsuEfficiency> findAllEfficiencies();

    List<Psu> findByEfficiency(Integer efficiency);

    List<Integer> findAllWatts();

    List<Psu> findByWatt(Integer watt);

    public PsuManufacturer findManufacturerById(Integer manufacturer);

    public PsuCableManagement findCableManagementById(Integer cableManagement);

    public PsuEfficiency findEfficiencyById(Integer efficiency);

    public void saveOrUpdate(Psu psu);
}
