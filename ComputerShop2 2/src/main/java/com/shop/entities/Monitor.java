package com.shop.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "monitor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Monitor.findAll", query = "SELECT m FROM Monitor m")
    , @NamedQuery(name = "Monitor.findById", query = "SELECT m FROM Monitor m WHERE m.id = :id")
})
public class Monitor implements Serializable {
    
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
    private MonitorManufacturer manufacturer;
    
    @Column(name = "inches")
    private Integer inches;
    
    @Column(name = "Hz")
    private Integer Hz;

    public Monitor() {
    }
    
    public Monitor(Integer id, Product product, MonitorManufacturer manufacturer, Integer inches, Integer Hz) {
        this.id = id;
        this.product = product;
        this.manufacturer = manufacturer;
        this.inches = inches;
        this.Hz = Hz;
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

    public MonitorManufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(MonitorManufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getInches() {
        return inches;
    }

    public void setInches(Integer inches) {
        this.inches = inches;
    }

    public Integer getHz() {
        return Hz;
    }

    public void setHz(Integer Hz) {
        this.Hz = Hz;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.id);
        hash = 83 * hash + Objects.hashCode(this.product);
        hash = 83 * hash + Objects.hashCode(this.manufacturer);
        hash = 83 * hash + Objects.hashCode(this.inches);
        hash = 83 * hash + Objects.hashCode(this.Hz);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Monitor other = (Monitor) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        if (!Objects.equals(this.manufacturer, other.manufacturer)) {
            return false;
        }
        if (!Objects.equals(this.inches, other.inches)) {
            return false;
        }
        if (!Objects.equals(this.Hz, other.Hz)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Monitor{" + "id=" + id + ", product=" + product + ", manufacturer=" + manufacturer + ", inches=" + inches + ", Hz=" + Hz + '}';
    }
    
    
}
