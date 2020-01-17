/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author frodi
 */
@Entity
@Table(name = "cpu_manufacturer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CpuManufacturer.findAll", query = "SELECT c FROM CpuManufacturer c ORDER BY c.name")
    , @NamedQuery(name = "CpuManufacturer.findById", query = "SELECT c FROM CpuManufacturer c WHERE c.id = :id")
    , @NamedQuery(name = "CpuManufacturer.findByName", query = "SELECT c FROM CpuManufacturer c WHERE c.name = :name")})
public class CpuManufacturer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 30)
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "cpuManufacturer")
    private Collection<Cpu> cpuCollection;

    public CpuManufacturer() {
    }

    public CpuManufacturer(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<Cpu> getCpuCollection() {
        return cpuCollection;
    }

    public void setCpuCollection(Collection<Cpu> cpuCollection) {
        this.cpuCollection = cpuCollection;
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
        if (!(object instanceof CpuManufacturer)) {
            return false;
        }
        CpuManufacturer other = (CpuManufacturer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.name;
    }
    
}
