/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author frodi
 */
@Entity
@Table(name = "ram_manufacturer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RamManufacturer.findAll", query = "SELECT r FROM RamManufacturer r ORDER BY r.name")
    , @NamedQuery(name = "RamManufacturer.findById", query = "SELECT r FROM RamManufacturer r WHERE r.id = :id")
    , @NamedQuery(name = "RamManufacturer.findByName", query = "SELECT r FROM RamManufacturer r WHERE r.name = :name")})
public class RamManufacturer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manufacturer")
    private Collection<Ram> ramCollection;

    public RamManufacturer() {
    }

    public RamManufacturer(Integer id) {
        this.id = id;
    }

    public RamManufacturer(Integer id, String name) {
        this.id = id;
        this.name = name;
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
    public Collection<Ram> getRamCollection() {
        return ramCollection;
    }

    public void setRamCollection(Collection<Ram> ramCollection) {
        this.ramCollection = ramCollection;
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
        if (!(object instanceof RamManufacturer)) {
            return false;
        }
        RamManufacturer other = (RamManufacturer) object;
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
