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
@Table(name = "tower")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tower.findAll", query = "SELECT t FROM Tower t")
    , @NamedQuery(name = "Tower.findById", query = "SELECT t FROM Tower t WHERE t.id = :id")
    , @NamedQuery(name = "Tower.deleteById", query = "DELETE FROM Tower t WHERE t.id = :id")
})
public class Tower implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Product product;
    @JoinColumn(name = "manufacturer", referencedColumnName = "id")
    @ManyToOne
    private TowerManufacturer manufacturer;
    @JoinColumn(name = "type", referencedColumnName = "id")
    @ManyToOne
    private TowerType towerType;

    public Tower() {
    }

    public Tower(Integer id) {
        this.id = id;
    }

    public Tower(Integer id, Product product, TowerManufacturer manufacturer, TowerType towerType) {
        this.id = id;
        this.product = product;
        this.manufacturer = manufacturer;
        this.towerType = towerType;
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

    public TowerManufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(TowerManufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public TowerType getTowerType() {
        return towerType;
    }

    public void setTowerType(TowerType towerType) {
        this.towerType = towerType;
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
        if (!(object instanceof Tower)) {
            return false;
        }
        Tower other = (Tower) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tower{" + "id=" + id + ", product=" + product + ", manufacturer=" + manufacturer + ", towerType=" + towerType + '}';
    }

   

}
