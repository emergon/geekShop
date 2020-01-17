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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author frodi
 */
@Entity
@Table(name = "gpu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gpu.findAll", query = "SELECT g FROM Gpu g")
    , @NamedQuery(name = "Gpu.findById", query = "SELECT g FROM Gpu g WHERE g.id = :id")
    , @NamedQuery(name = "Gpu.findByMemory", query = "SELECT g FROM Gpu g WHERE g.memory = :memory")})
public class Gpu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    private Integer memory;

    @JoinColumn(name = "chipset", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private GpuChipset gpuChipset;
    @JoinColumn(name = "manufacturer", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private GpuManufacturer gpuManufacturer;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Product product;

    public Gpu() {
    }

    public Gpu(Integer id) {
        this.id = id;
    }

    public Gpu(Integer id, Integer memory, GpuChipset gpuChipset, GpuManufacturer gpuManufacturer, Product product) {
        this.id = id;
        this.memory = memory;
        this.gpuChipset = gpuChipset;
        this.gpuManufacturer = gpuManufacturer;
        this.product = product;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemory() {
        return memory;
    }

    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    public GpuChipset getGpuChipset() {
        return gpuChipset;
    }

    public void setGpuChipset(GpuChipset gpuChipset) {
        this.gpuChipset = gpuChipset;
    }

    public GpuManufacturer getGpuManufacturer() {
        return gpuManufacturer;
    }

    public void setGpuManufacturer(GpuManufacturer gpuManufacturer) {
        this.gpuManufacturer = gpuManufacturer;
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
        if (!(object instanceof Gpu)) {
            return false;
        }
        Gpu other = (Gpu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + this.memory;
    }

}
