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
@Table(name = "cpu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cpu.findAll", query = "SELECT c FROM Cpu c")
    , @NamedQuery(name = "Cpu.findById", query = "SELECT c FROM Cpu c WHERE c.id = :id")
    , @NamedQuery(name = "Cpu.findByCoresNumber", query = "SELECT c FROM Cpu c WHERE c.coresNumber = :coresNumber")})
public class Cpu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "cores_number")
    private Integer coresNumber;
    @JoinColumn(name = "chip", referencedColumnName = "id")
    @ManyToOne
    private CpuChip cpuChip;
    @JoinColumn(name = "manufacturer", referencedColumnName = "id")
    @ManyToOne
    private CpuManufacturer cpuManufacturer;

    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Product product;

    public Cpu() {
    }

    public Cpu(Integer id) {
        this.id = id;
    }

    public Cpu(Integer id, Integer coresNumber, CpuChip cpuChip, CpuManufacturer cpuManufacturer, Product product) {
        this.id = id;
        this.coresNumber = coresNumber;
        this.cpuChip = cpuChip;
        this.cpuManufacturer = cpuManufacturer;
        this.product = product;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCoresNumber() {
        return coresNumber;
    }

    public void setCoresNumber(Integer coresNumber) {
        this.coresNumber = coresNumber;
    }

    public CpuChip getCpuChip() {
        return cpuChip;
    }

    public void setCpuChip(CpuChip cpuChip) {
        this.cpuChip = cpuChip;
    }

    public CpuManufacturer getCpuManufacturer() {
        return cpuManufacturer;
    }

    public void setCpuManufacturer(CpuManufacturer cpuManufacturer) {
        this.cpuManufacturer = cpuManufacturer;
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
        if (!(object instanceof Cpu)) {
            return false;
        }
        Cpu other = (Cpu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cpu{" + "id=" + id + ", coresNumber=" + coresNumber + ", cpuChip=" + cpuChip + ", cpuManufacturer=" + cpuManufacturer + ", product=" + product + '}';
    }

    

}
