/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.entities;

 

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
    , @NamedQuery(name = "Product.findLimit", query = "SELECT p FROM Product p order by p.id desc")
    , @NamedQuery(name = "Product.findSales", query = "SELECT p FROM Product p where p.sales>0 ORDER BY RAND()")
    , @NamedQuery(name = "Product.findByCategory", query = "SELECT p FROM Product p WHERE p.category = 1")
})
public class Product implements Serializable {

 

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "pcode")
    private String pcode;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "sales")
    private BigDecimal sales;
    @Column(name = "quantity")
    private Integer quantity;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
//    @JoinColumn(name = "manufacturer", referencedColumnName = "id")
//    @ManyToMany
//    private Collection<MonitorManufacturer> monitorManufacturerCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    private Storage storage;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    private Mouse mouse;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    private Psu psu;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    private Ram ram;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    private Tower tower;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    private Keyboard keyboard;

 

    @JoinColumn(name = "category", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Category category;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
//    private Collection<Motherboard> motherboardCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
//    private Collection<Cpu> cpuCollection;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
//    private Collection<Gpu> gpuCollection;

 

    public Product() {
    }

 

    public Product(Integer id) {
        this.id = id;
    }

 

    public Product(Integer id, String name, String pcode, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.pcode = pcode;
        this.price = price;
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

 

    public String getPcode() {
        return pcode;
    }

 

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

 

    public BigDecimal getPrice() {
        return price;
    }

 

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

 

    public BigDecimal getSales() {
        return sales;
    }

 

    public void setSales(BigDecimal sales) {
        this.sales = sales;
    }

 

    public Integer getQuantity() {
        return quantity;
    }

 

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

 

    public String getDescription() {
        return description;
    }

 

    public void setDescription(String description) {
        this.description = description;
    }

 

    
    public Storage getStorage() {
        return storage;
    }

 

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

 

    public Mouse getMouse() {
        return mouse;
    }

 

    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }

 

    public Psu getPsu() {
        return psu;
    }

 

    public void setPsu(Psu psu) {
        this.psu = psu;
    }

 

    public Ram getRam() {
        return ram;
    }

 

    public void setRam(Ram ram) {
        this.ram = ram;
    }

 

    

 

    public Keyboard getKeyboard() {
        return keyboard;
    }

 

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

 

    public Category getCategory() {
        return category;
    }

 

    public void setCategory(Category category) {
        this.category = category;
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
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", pcode=" + pcode + ", price=" + price + ", sales=" + sales + ", quantity=" + quantity + ", description=" + description + '}';
    }

}