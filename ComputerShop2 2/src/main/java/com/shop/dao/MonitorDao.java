package com.shop.dao;

import com.shop.entities.*;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MonitorDao implements InterfaceDao<Monitor> {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Monitor> findAll() {
        Query q = getSession().createNamedQuery("Monitor.findAll");
        return q.getResultList();
    }

    @Override
    public boolean createOrUpdate(Monitor t) {
        try {
            getSession().saveOrUpdate(t);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<MonitorManufacturer> findAllManufacturers() {
        Query q = getSession().createNamedQuery("MonitorManufacturer.findAll");
        return q.getResultList();
    }

    public List<Monitor> findByManufacturer(Integer manufacturer) {
        MonitorManufacturer mm = getSession().byId(MonitorManufacturer.class).load(manufacturer);
        Query q = getSession().createQuery("SELECT m FROM Monitor m WHERE m.manufacturer= :mm");
        q.setParameter("mm", mm);
        return q.getResultList();
    }

    public List<Integer> findAllInches() {
        Query q = getSession().createQuery("SELECT DISTINCT m.inches FROM Monitor m ORDER BY m.inches");
        return q.getResultList();
    }

    public List<Monitor> findByInches(Integer inches) {
        Query q = getSession().createQuery("SELECT m FROM Monitor m WHERE m.inches= :inches");
        q.setParameter("inches", inches);
        return q.getResultList();
    }

    public MonitorManufacturer findByManufacturerId(Integer manufacturer) {
        return getSession().byId(MonitorManufacturer.class).load(manufacturer);
    }

    @Override
    public void delete(Monitor t) {
        getSession().delete(t);
    }

}
