package com.shop.dao;

import com.shop.entities.*;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CpuDao implements InterfaceDao<Cpu> {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Cpu> findAll() {
        Query q = getSession().createNamedQuery("Cpu.findAll");
        return q.getResultList();
    }

    @Override
    public boolean createOrUpdate(Cpu c) {
        try {
            getSession().saveOrUpdate(c);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<CpuManufacturer> findAllManufacturers() {
        Query q = getSession().createNamedQuery("CpuManufacturer.findAll");
        return q.getResultList();
    }

    public List<Cpu> findByManufacturer(Integer manufacturer) {
        CpuManufacturer m = getSession().byId(CpuManufacturer.class).load(manufacturer);
        Query q = getSession().createQuery("SELECT c FROM Cpu c WHERE c.cpuManufacturer= :m");
        q.setParameter("m", m);
        return q.getResultList();
    }

    public List<CpuChip> findAllChips() {
        Query q = getSession().createNamedQuery("CpuChip.findAll");
        return q.getResultList();
    }

    public List<Cpu> findByChip(Integer type) {
        CpuChip m = getSession().byId(CpuChip.class).load(type);
        Query q = getSession().createQuery("SELECT c FROM Cpu c WHERE c.cpuChip= :m");
        q.setParameter("m", m);
        return q.getResultList();
    }

    public List<Integer> findAllCores() {
        Query q = getSession().createQuery("SELECT DISTINCT c.coresNumber FROM Cpu c ORDER BY c.coresNumber");
        return q.getResultList();
    }

    public List<Cpu> findByCores(Integer cores) {
        Query q = getSession().createQuery("SELECT c FROM Cpu c WHERE c.coresNumber= :cores");
        q.setParameter("cores", cores);
        return q.getResultList();
    }

    public CpuManufacturer findByManufacturerId(Integer manufacturer) {
        return getSession().byId(CpuManufacturer.class).load(manufacturer);
    }

    public CpuChip findByChipId(Integer chip) {
        return getSession().byId(CpuChip.class).load(chip);
    }

    @Override
    public void delete(Cpu t) {
        getSession().delete(t);
    }
}
