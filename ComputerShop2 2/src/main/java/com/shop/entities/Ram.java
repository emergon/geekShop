/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "ram")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ram.findAll", query = "SELECT r FROM Ram r")
    , @NamedQuery(name = "Ram.findById", query = "SELECT r FROM Ram r WHERE r.id = :id")
    , @NamedQuery(name = "Ram.findByVoltage", query = "SELECT r FROM Ram r WHERE r.voltage = :voltage")})
public class Ram implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "voltage")
    private BigDecimal voltage;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Product product;

    private Integer frequency;
    @JoinColumn(name = "manufacturer", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private RamManufacturer manufacturer;
    @JoinColumn(name = "size", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private RamSize size1;
    @JoinColumn(name = "type", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private RamType type;

    public Ram() {
    }

    public Ram(Integer id) {
        this.id = id;
    }

    public Ram(Integer id, BigDecimal voltage) {
        this.id = id;
        this.voltage = voltage;
    }

    public Ram(Integer id, BigDecimal voltage, Product product, Integer frequency, RamManufacturer manufacturer, RamSize size1, RamType type) {
        this.id = id;
        this.voltage = voltage;
        this.product = product;
        this.frequency = frequency;
        this.manufacturer = manufacturer;
        this.size1 = size1;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getVoltage() {
        return voltage;
    }

    public void setVoltage(BigDecimal voltage) {
        this.voltage = voltage;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public RamManufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(RamManufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public RamSize getSize1() {
        return size1;
    }

    public void setSize1(RamSize size1) {
        this.size1 = size1;
    }

    public RamType getType() {
        return type;
    }

    public void setType(RamType type) {
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
        if (!(object instanceof Ram)) {
            return false;
        }
        Ram other = (Ram) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ram{" + "id=" + id + ", voltage=" + voltage + ", product=" + product + ", frequency=" + frequency + ", manufacturer=" + manufacturer + ", size1=" + size1 + ", type=" + type + '}';
    }

    
}
