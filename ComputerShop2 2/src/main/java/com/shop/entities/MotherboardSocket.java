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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author frodi
 */
@Entity
@Table(name = "motherboard_socket")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MotherboardSocket.findAll", query = "SELECT m FROM MotherboardSocket m ORDER BY m.name")
    , @NamedQuery(name = "MotherboardSocket.findById", query = "SELECT m FROM MotherboardSocket m WHERE m.id = :id")
    , @NamedQuery(name = "MotherboardSocket.findByName", query = "SELECT m FROM MotherboardSocket m WHERE m.name = :name")})
public class MotherboardSocket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "motherboardSocket")
    private Collection<Motherboard> motherboardCollection;

    public MotherboardSocket() {
    }

    public MotherboardSocket(Integer id) {
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
    public Collection<Motherboard> getMotherboardCollection() {
        return motherboardCollection;
    }

    public void setMotherboardCollection(Collection<Motherboard> motherboardCollection) {
        this.motherboardCollection = motherboardCollection;
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
        if (!(object instanceof MotherboardSocket)) {
            return false;
        }
        MotherboardSocket other = (MotherboardSocket) object;
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
