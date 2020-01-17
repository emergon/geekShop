package com.shop.dao;

import com.shop.entities.*;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MouseDao implements InterfaceDao<Mouse> {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Mouse> findAll() {
        Query q = getSession().createNamedQuery("Mouse.findAll");
        return q.getResultList();
    }

    @Override
    public boolean createOrUpdate(Mouse m) {
        try {
            getSession().saveOrUpdate(m);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<MouseManufacturer> findAllManufacturers() {
        Query q = getSession().createNamedQuery("MouseManufacturer.findAll");
        return q.getResultList();
    }

    public List<Mouse> findByManufacturer(Integer manufacturer) {
        MouseManufacturer m = getSession().byId(MouseManufacturer.class).load(manufacturer);
        Query q = getSession().createQuery("SELECT k FROM Mouse k WHERE k.manufacturer= :m");
        q.setParameter("m", m);
        return q.getResultList();
    }

    public List<MouseType> findAllTypes() {
        Query q = getSession().createNamedQuery("MouseType.findAll");
        return q.getResultList();
    }

    public List<Mouse> findByType(Integer type) {
        MouseType m = getSession().byId(MouseType.class).load(type);
        Query q = getSession().createQuery("SELECT k FROM Mouse k WHERE k.type= :m");
        q.setParameter("m", m);
        return q.getResultList();
    }

    public MouseManufacturer findByManufacturerId(Integer manufacturer) {
        return getSession().byId(MouseManufacturer.class).load(manufacturer);
    }

    public MouseType findByTypeId(Integer type) {
        return getSession().byId(MouseType.class).load(type);
    }

    @Override
    public void delete(Mouse t) {
        getSession().delete(t);
    }
}
