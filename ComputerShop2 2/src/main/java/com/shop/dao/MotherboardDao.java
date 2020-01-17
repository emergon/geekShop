package com.shop.dao;

import com.shop.entities.*;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MotherboardDao implements InterfaceDao<Motherboard> {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Motherboard> findAll() {
        Query q = getSession().createNamedQuery("Motherboard.findAll");
        return q.getResultList();
    }

    @Override
    public boolean createOrUpdate(Motherboard m) {
        try {
            getSession().saveOrUpdate(m);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<MotherboardManufacturer> findAllManufacturers() {
        Query q = getSession().createNamedQuery("MotherboardManufacturer.findAll");
        return q.getResultList();
    }

    public List<Motherboard> findByManufacturer(Integer manufacturer) {
        MotherboardManufacturer mm = getSession().byId(MotherboardManufacturer.class).load(manufacturer);
        Query q = getSession().createQuery("SELECT m FROM Motherboard m WHERE m.motherboardManufacturer= :mm");
        q.setParameter("mm", mm);
        return q.getResultList();
    }

    public List<MotherboardSocket> findAllSockets() {
        Query q = getSession().createNamedQuery("MotherboardSocket.findAll");
        return q.getResultList();
    }

    public List<Motherboard> findBySocket(Integer type) {
        MotherboardSocket mm = getSession().byId(MotherboardSocket.class).load(type);
        Query q = getSession().createQuery("SELECT m FROM Motherboard m WHERE m.motherboardSocket= :mm");
        q.setParameter("mm", mm);
        return q.getResultList();
    }

    public List<MotherboardSize> findAllSizes() {
        Query q = getSession().createNamedQuery("MotherboardSize.findAll");
        return q.getResultList();
    }

    public List<Motherboard> findBySize(Integer size) {
        MotherboardSize s = getSession().byId(MotherboardSize.class).load(size);
        Query q = getSession().createQuery("SELECT m FROM Motherboard m WHERE m.motherboardSize= :s");
        q.setParameter("s", s);
        return q.getResultList();
    }

    public List<MotherboardChipset> findAllChipsets() {
        Query q = getSession().createNamedQuery("MotherboardChipset.findAll");
        return q.getResultList();
    }

    public List<Motherboard> findByChipset(Integer chipset) {
        MotherboardChipset s = getSession().byId(MotherboardChipset.class).load(chipset);
        Query q = getSession().createQuery("SELECT m FROM Motherboard m WHERE m.motherboardChipset= :s");
        q.setParameter("s", s);
        return q.getResultList();
    }

    public List<MotherboardPort> findAllPorts() {
        Query q = getSession().createNamedQuery("MotherboardPort.findAll");
        return q.getResultList();
    }

    public List<Motherboard> findByPort(Integer port) {
        MotherboardPort s = getSession().byId(MotherboardPort.class).load(port);
        Query q = getSession().createQuery("SELECT m FROM Motherboard m WHERE m.motherboardPort= :s");
        q.setParameter("s", s);
        return q.getResultList();
    }

    public MotherboardManufacturer findManufacturerById(Integer manufacturer) {
        return getSession().byId(MotherboardManufacturer.class).load(manufacturer);
    }

    public MotherboardSocket findSocketById(Integer socket) {
        return getSession().byId(MotherboardSocket.class).load(socket);
    }

    public MotherboardSize findSizeById(Integer size) {
        return getSession().byId(MotherboardSize.class).load(size);
    }

    public MotherboardPort findPortById(Integer port) {
        return getSession().byId(MotherboardPort.class).load(port);
    }

    public MotherboardChipset findChipsetById(Integer chipset) {
        return getSession().byId(MotherboardChipset.class).load(chipset);
    }

    @Override
    public void delete(Motherboard t) {
        getSession().delete(t);
    }

}
