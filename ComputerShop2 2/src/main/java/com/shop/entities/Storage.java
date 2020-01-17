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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author frodi
 */
@Entity
@Table(name = "storage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Storage.findAll", query = "SELECT s FROM Storage s")
    , @NamedQuery(name = "Storage.findById", query = "SELECT s FROM Storage s WHERE s.id = :id")})
public class Storage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Product product;
    @JoinColumn(name = "inches", referencedColumnName = "id")
    @ManyToOne
    private StorageInches inches;
    @JoinColumn(name = "manufacturer", referencedColumnName = "id")
    @ManyToOne
    private StorageManufacturer manufacturer;
    @JoinColumn(name = "type", referencedColumnName = "id")
    @ManyToOne
    private StorageType type;

    public Storage() {
    }

    public Storage(Integer id) {
        this.id = id;
    }

    public Storage(Integer id, Product product, StorageInches inches, StorageManufacturer manufacturer, StorageType type) {
        this.id = id;
        this.product = product;
        this.inches = inches;
        this.manufacturer = manufacturer;
        this.type = type;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public StorageInches getInches() {
        return inches;
    }

    public void setInches(StorageInches inches) {
        this.inches = inches;
    }

    public StorageManufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(StorageManufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public StorageType getType() {
        return type;
    }

    public void setType(StorageType type) {
        this.type = type;
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
        if (!(object instanceof Storage)) {
            return false;
        }
        Storage other = (Storage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.shop.entities.Storage[ id=" + id + " ]";
    }
    
}
