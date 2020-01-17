package com.shop.dao;

import com.shop.entities.*;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RamDao implements InterfaceDao<Ram> {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Ram> findAll() {
        Query q = getSession().createNamedQuery("Ram.findAll");
        return q.getResultList();
    }

    @Override
    public boolean createOrUpdate(Ram r) {
        try {
            getSession().saveOrUpdate(r);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<RamManufacturer> findAllManufacturers() {
        Query q = getSession().createNamedQuery("RamManufacturer.findAll");
        return q.getResultList();
    }

    public List<Ram> findByManufacturer(Integer manufacturer) {
        RamManufacturer m = getSession().byId(RamManufacturer.class).load(manufacturer);
        Query q = getSession().createQuery("SELECT r FROM Ram r WHERE r.manufacturer= :m");
        q.setParameter("m", m);
        return q.getResultList();
    }

    public List<RamType> findAllTypes() {
        Query q = getSession().createNamedQuery("RamType.findAll");
        return q.getResultList();
    }

    public List<Ram> findByType(Integer type) {
        RamType m = getSession().byId(RamType.class).load(type);
        Query q = getSession().createQuery("SELECT r FROM Ram r WHERE r.type= :m");
        q.setParameter("m", m);
        return q.getResultList();
    }

    public List<RamSize> findAllSizes() {
        Query q = getSession().createNamedQuery("RamSize.findAll");
        return q.getResultList();
    }

    public List<Ram> findBySize(Integer size) {
        RamSize m = getSession().byId(RamSize.class).load(size);
        Query q = getSession().createQuery("SELECT r FROM Ram r WHERE r.size1= :m");
        q.setParameter("m", m);
        return q.getResultList();
    }

    public List<Integer> findAllFrequencies() {
        Query q = getSession().createQuery("SELECT DISTINCT r.frequency FROM Ram r ORDER BY r.frequency");
        return q.getResultList();
    }

    public List<Ram> findByFrequency(Integer frequency) {
        Query q = getSession().createQuery("SELECT r FROM Ram r WHERE r.frequency= :frequency");
        q.setParameter("frequency", frequency);
        return q.getResultList();
    }

    public List<BigDecimal> findAllVoltage() {
        Query q = getSession().createQuery("SELECT DISTINCT r.voltage FROM Ram r ORDER BY r.voltage");
        return q.getResultList();
    }

    public List<Ram> findByVoltage(BigDecimal voltage) {
        Query q = getSession().createQuery("SELECT r FROM Ram r WHERE r.voltage= :voltage");
        q.setParameter("voltage", voltage);
        System.out.println("**********************************************************************voltages" + voltage);
        return q.getResultList();
    }

    public RamManufacturer findByManufacturerId(Integer manufacturer) {
        return getSession().byId(RamManufacturer.class).load(manufacturer);
    }

    public RamType findByTypeId(Integer type) {
        return getSession().byId(RamType.class).load(type);
    }

    public RamSize findBySizeId(Integer size) {
        return getSession().byId(RamSize.class).load(size);
    }

    @Override
    public void delete(Ram t) {
        getSession().delete(t);
    }

}
