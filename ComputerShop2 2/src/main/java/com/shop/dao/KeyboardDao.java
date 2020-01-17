package com.shop.dao;

import com.shop.entities.*;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class KeyboardDao implements InterfaceDao<Keyboard> {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Keyboard> findAll() {
        Query q = getSession().createNamedQuery("Keyboard.findAll");
        return q.getResultList();
    }

    @Override
    public boolean createOrUpdate(Keyboard t) {
        try {
            getSession().saveOrUpdate(t);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<KeyboardManufacturer> findAllManufacturers() {
        Query q = getSession().createNamedQuery("KeyboardManufacturer.findAll");
        return q.getResultList();
    }

    public List<Keyboard> findByManufacturer(Integer manufacturer) {
        KeyboardManufacturer m = getSession().byId(KeyboardManufacturer.class).load(manufacturer);
        Query q = getSession().createQuery("SELECT k FROM Keyboard k WHERE k.manufacturer= :m");
        q.setParameter("m", m);
        return q.getResultList();
    }

    public List<KeyboardType> findAllTypes() {
        Query q = getSession().createNamedQuery("KeyboardType.findAll");
        return q.getResultList();
    }

    public List<Keyboard> findByType(Integer type) {
        KeyboardType m = getSession().byId(KeyboardType.class).load(type);
        Query q = getSession().createQuery("SELECT k FROM Keyboard k WHERE k.type= :m");
        q.setParameter("m", m);
        return q.getResultList();
    }

    public KeyboardManufacturer findByManufacturerId(Integer manufacturer) {
        return getSession().byId(KeyboardManufacturer.class).load(manufacturer);
    }

    public KeyboardType findByTypeId(Integer type) {
        return getSession().byId(KeyboardType.class).load(type);
    }

    @Override
    public void delete(Keyboard t) {
        getSession().delete(t);
    }

}
