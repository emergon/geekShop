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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author frodi
 */
@Entity
@Table(name = "psu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Psu.findAll", query = "SELECT p FROM Psu p")
    , @NamedQuery(name = "Psu.findById", query = "SELECT p FROM Psu p WHERE p.id = :id")
    , @NamedQuery(name = "Psu.findByWatt", query = "SELECT p FROM Psu p WHERE p.watt = :watt")})
public class Psu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "watt")
    private Integer watt;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Product product;
    @JoinColumn(name = "cable_management", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PsuCableManagement cableManagement;
    @JoinColumn(name = "efficiency", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PsuEfficiency efficiency;
    @JoinColumn(name = "manufacturer", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PsuManufacturer manufacturer;

    public Psu() {
    }

    public Psu(Integer id) {
        this.id = id;
    }

    public Psu(Integer id, int watt) {
        this.id = id;
        this.watt = watt;
    }

    public Psu(Integer id, Integer watt, Product product, PsuCableManagement cableManagement, PsuEfficiency efficiency, PsuManufacturer manufacturer) {
        this.id = id;
        this.watt = watt;
        this.product = product;
        this.cableManagement = cableManagement;
        this.efficiency = efficiency;
        this.manufacturer = manufacturer;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getWatt() {
        return watt;
    }

    public void setWatt(Integer watt) {
        this.watt = watt;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public PsuCableManagement getCableManagement() {
        return cableManagement;
    }

    public void setCableManagement(PsuCableManagement cableManagement) {
        this.cableManagement = cableManagement;
    }

    public PsuEfficiency getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(PsuEfficiency efficiency) {
        this.efficiency = efficiency;
    }

    public PsuManufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(PsuManufacturer manufacturer) {
        this.manufacturer = manufacturer;
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
        if (!(object instanceof Psu)) {
            return false;
        }
        Psu other = (Psu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.shop.entities.Psu[ id=" + id + " ]";
    }
    
}
