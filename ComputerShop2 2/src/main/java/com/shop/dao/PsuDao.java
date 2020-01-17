package com.shop.dao;

import com.shop.entities.*;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PsuDao implements InterfaceDao<Psu> {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Psu> findAll() {
        Query q = getSession().createNamedQuery("Psu.findAll");
        return q.getResultList();
    }

    @Override
    public boolean createOrUpdate(Psu t) {
        try {
            getSession().saveOrUpdate(t);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<PsuManufacturer> findAllManufacturers() {
        Query q = getSession().createNamedQuery("PsuManufacturer.findAll");
        return q.getResultList();
    }

    public List<Psu> findByManufacturer(Integer manufacturer) {
        PsuManufacturer m = getSession().byId(PsuManufacturer.class).load(manufacturer);
        Query q = getSession().createQuery("SELECT p FROM Psu p WHERE p.manufacturer= :m");
        q.setParameter("m", m);
        return q.getResultList();
    }

    public List<PsuCableManagement> findAllCableManagement() {
        Query q = getSession().createNamedQuery("PsuCableManagement.findAll");
        return q.getResultList();
    }

    public List<Psu> findByCableManagement(Integer type) {
        PsuCableManagement m = getSession().byId(PsuCableManagement.class).load(type);
        Query q = getSession().createQuery("SELECT p FROM Psu p WHERE p.cableManagement= :m");
        q.setParameter("m", m);
        return q.getResultList();
    }

    public List<PsuEfficiency> findAllEfficiencies() {
        Query q = getSession().createNamedQuery("PsuEfficiency.findAll");
        return q.getResultList();
    }

    public List<Psu> findByEfficiency(Integer efficiency) {
        PsuEfficiency m = getSession().byId(PsuEfficiency.class).load(efficiency);
        Query q = getSession().createQuery("SELECT p FROM Psu p WHERE p.efficiency= :m");
        q.setParameter("m", m);
        return q.getResultList();
    }

    public List<Integer> findAllWatts() {
        Query q = getSession().createQuery("SELECT DISTINCT p.watt FROM Psu p ORDER BY p.watt");
        return q.getResultList();
    }

    public List<Psu> findByWatt(Integer watt) {
        Query q = getSession().createQuery("SELECT p FROM Psu p WHERE p.watt= :watt");
        q.setParameter("watt", watt);
        return q.getResultList();
    }

    public PsuManufacturer findManufacturerById(Integer manufacturer) {
        return getSession().byId(PsuManufacturer.class).load(manufacturer);
    }

    public PsuCableManagement findCableManagementById(Integer cableManagement) {
        return getSession().byId(PsuCableManagement.class).load(cableManagement);
    }

    public PsuEfficiency findEfficiencyById(Integer efficiency) {
        return getSession().byId(PsuEfficiency.class).load(efficiency);
    }

    @Override
    public void delete(Psu t) {
        getSession().delete(t);
    }

}
