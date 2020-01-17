package com.shop.dao;

import com.shop.entities.*;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GpuDao implements InterfaceDao<Gpu> {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Gpu> findAll() {
        Query q = getSession().createNamedQuery("Gpu.findAll");
        return q.getResultList();
    }

    @Override
    public boolean createOrUpdate(Gpu g) {
        try {
            getSession().saveOrUpdate(g);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<GpuManufacturer> findAllManufacturers() {
        Query q = getSession().createNamedQuery("GpuManufacturer.findAll");
        return q.getResultList();
    }

    public List<Gpu> findByManufacturer(Integer manufacturer) {
        GpuManufacturer m = getSession().byId(GpuManufacturer.class).load(manufacturer);
        Query q = getSession().createQuery("SELECT g FROM Gpu g WHERE g.gpuManufacturer= :m");
        q.setParameter("m", m);
        return q.getResultList();
    }

    public List<GpuChipset> findAllChipsets() {
        Query q = getSession().createNamedQuery("GpuChipset.findAll");
        return q.getResultList();
    }

    public List<Gpu> findByChipset(Integer type) {
        GpuChipset m = getSession().byId(GpuChipset.class).load(type);
        Query q = getSession().createQuery("SELECT g FROM Gpu g WHERE g.gpuChipset= :m");
        q.setParameter("m", m);
        return q.getResultList();
    }

    public List<Integer> findAllMemories() {
        Query q = getSession().createQuery("SELECT DISTINCT g.memory FROM Gpu g ORDER BY g.memory");
        return q.getResultList();
    }

    public List<Gpu> findByMemory(Integer memory) {
        Query q = getSession().createQuery("SELECT g FROM Gpu g WHERE g.memory= :memory");
        q.setParameter("memory", memory);
        return q.getResultList();
    }

    public GpuManufacturer findByManufacturerId(Integer manufacturer) {
        return getSession().byId(GpuManufacturer.class).load(manufacturer);
    }

    public GpuChipset findByChipsetId(Integer chipset) {
        return getSession().byId(GpuChipset.class).load(chipset);
    }

    @Override
    public void delete(Gpu t) {
        getSession().delete(t);
    }
}
