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
@Table(name = "mouse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mouse.findAll", query = "SELECT m FROM Mouse m")
    , @NamedQuery(name = "Mouse.findById", query = "SELECT m FROM Mouse m WHERE m.id = :id")})
public class Mouse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "manufacturer", referencedColumnName = "id")
    @ManyToOne
    private MouseManufacturer manufacturer;
    @JoinColumn(name = "type", referencedColumnName = "id")
    @ManyToOne
    private MouseType type;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Product product;

    public Mouse() {
    }

    public Mouse(Integer id) {
        this.id = id;
    }

    public Mouse(Integer id, MouseManufacturer manufacturer, MouseType type, Product product) {
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

    public MouseManufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(MouseManufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public MouseType getType() {
        return type;
    }

    public void setType(MouseType type) {
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
        if (!(object instanceof Mouse)) {
            return false;
        }
        Mouse other = (Mouse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.shop.entities.Mouse[ id=" + id + " ]";
    }
    
}
