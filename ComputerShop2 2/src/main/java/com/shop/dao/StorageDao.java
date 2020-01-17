package com.shop.dao;

import com.shop.entities.*;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StorageDao implements InterfaceDao<Storage> {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Storage> findAll() {
        Query q = getSession().createNamedQuery("Storage.findAll");
        return q.getResultList();
    }

    @Override
    public boolean createOrUpdate(Storage s) {
        try {
            getSession().saveOrUpdate(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<StorageManufacturer> findAllManufacturers() {
        Query q = getSession().createNamedQuery("StorageManufacturer.findAll");
        return q.getResultList();
    }

    public List<Storage> findByManufacturer(Integer manufacturer) {
        StorageManufacturer m = getSession().byId(StorageManufacturer.class).load(manufacturer);
        Query q = getSession().createQuery("SELECT s FROM Storage s WHERE s.manufacturer= :m");
        q.setParameter("m", m);
        return q.getResultList();
    }

    public List<StorageType> findAllTypes() {
        Query q = getSession().createNamedQuery("StorageType.findAll");
        return q.getResultList();
    }

    public List<Storage> findByType(Integer type) {
        StorageType m = getSession().byId(StorageType.class).load(type);
        Query q = getSession().createQuery("SELECT t FROM Storage t WHERE t.type= :m");
        q.setParameter("m", m);
        return q.getResultList();
    }

    public List<StorageInches> findAllInches() {
        Query q = getSession().createNamedQuery("StorageInches.findAll");
        return q.getResultList();
    }

    public List<Storage> findByInches(Integer inches) {
        StorageInches m = getSession().byId(StorageInches.class).load(inches);
        Query q = getSession().createQuery("SELECT t FROM Storage t WHERE t.inches= :m");
        q.setParameter("m", m);
        return q.getResultList();
    }

    public StorageManufacturer findManufacturerById(Integer manufacturer) {
        return getSession().byId(StorageManufacturer.class).load(manufacturer);
    }

    public StorageInches findInchesById(Integer inches) {
        return getSession().byId(StorageInches.class).load(inches);
    }

    public StorageType findTypeById(Integer type) {
        return getSession().byId(StorageType.class).load(type);
    }

    @Override
    public void delete(Storage t) {
        getSession().delete(t);
    }

}
