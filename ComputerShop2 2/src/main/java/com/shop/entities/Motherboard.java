/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author frodi
 */
@Entity
@Table(name = "motherboard")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Motherboard.findAll", query = "SELECT m FROM Motherboard m")
    , @NamedQuery(name = "Motherboard.findById", query = "SELECT m FROM Motherboard m WHERE m.id = :id")})
public class Motherboard implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "chipset", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private MotherboardChipset motherboardChipset;
    @JoinColumn(name = "manufacturer", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private MotherboardManufacturer motherboardManufacturer;
    @JoinColumn(name = "ports", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private MotherboardPort motherboardPort;
    @JoinColumn(name = "size", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private MotherboardSize motherboardSize;
    @JoinColumn(name = "socket", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private MotherboardSocket motherboardSocket;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Product product;

    public Motherboard() {
    }

    public Motherboard(Integer id) {
        this.id = id;
    }

    public Motherboard(Integer id, MotherboardChipset motherboardChipset, MotherboardManufacturer motherboardManufacturer, MotherboardPort motherboardPort, MotherboardSize motherboardSize, MotherboardSocket motherboardSocket, Product product) {
        this.id = id;
        this.motherboardChipset = motherboardChipset;
        this.motherboardManufacturer = motherboardManufacturer;
        this.motherboardPort = motherboardPort;
        this.motherboardSize = motherboardSize;
        this.motherboardSocket = motherboardSocket;
        this.product = product;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MotherboardChipset getMotherboardChipset() {
        return motherboardChipset;
    }

    public void setMotherboardChipset(MotherboardChipset motherboardChipset) {
        this.motherboardChipset = motherboardChipset;
    }

    public MotherboardManufacturer getMotherboardManufacturer() {
        return motherboardManufacturer;
    }

    public void setMotherboardManufacturer(MotherboardManufacturer motherboardManufacturer) {
        this.motherboardManufacturer = motherboardManufacturer;
    }

    public MotherboardPort getMotherboardPort() {
        return motherboardPort;
    }

    public void setMotherboardPort(MotherboardPort motherboardPort) {
        this.motherboardPort = motherboardPort;
    }

    public MotherboardSize getMotherboardSize() {
        return motherboardSize;
    }

    public void setMotherboardSize(MotherboardSize motherboardSize) {
        this.motherboardSize = motherboardSize;
    }

    public MotherboardSocket getMotherboardSocket() {
        return motherboardSocket;
    }

    public void setMotherboardSocket(MotherboardSocket motherboardSocket) {
        this.motherboardSocket = motherboardSocket;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Motherboard)) {
            return false;
        }
        Motherboard other = (Motherboard) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Motherboard{" + "id=" + id + ",\n motherboardChipset=" + motherboardChipset + ",\n motherboardManufacturer=" + motherboardManufacturer + ",\n motherboardPort=" + motherboardPort + ",\n motherboardSize=" + motherboardSize + ",\n motherboardSocket=" + motherboardSocket + ", product=" + product + '}';
    }

}
