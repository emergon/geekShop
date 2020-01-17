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
@Table(name = "keyboard")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Keyboard.findAll", query = "SELECT k FROM Keyboard k")
    , @NamedQuery(name = "Keyboard.findById", query = "SELECT k FROM Keyboard k WHERE k.id = :id")})
public class Keyboard implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "manufacturer", referencedColumnName = "id")
    @ManyToOne
    private KeyboardManufacturer manufacturer;
    @JoinColumn(name = "type", referencedColumnName = "id")
    @ManyToOne
    private KeyboardType type;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Product product;

    public Keyboard() {
    }

    public Keyboard(Integer id) {
        this.id = id;
    }

    public Keyboard(Integer id, KeyboardManufacturer manufacturer, KeyboardType type, Product product) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.type = type;
        this.product = product;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public KeyboardManufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(KeyboardManufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public KeyboardType getType() {
        return type;
    }

    public void setType(KeyboardType type) {
        this.type = type;
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
        if (!(object instanceof Keyboard)) {
            return false;
        }
        Keyboard other = (Keyboard) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.shop.entities.Keyboard[ id=" + id + " ]";
    }
    
}
