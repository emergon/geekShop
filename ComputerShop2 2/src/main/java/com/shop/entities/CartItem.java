package com.shop.entities;


import java.util.Objects;



public class CartItem {
    
    private Product product;
    private int quantity;


    public CartItem(Product product) {
        this.product = product;
        this.quantity = 1;
        
    }
    
    
    public double getTotalPrice(){
        return (this.product.getPrice().doubleValue()-this.product.getSales().doubleValue()) * this.quantity;
    }
    
    public void addQuantity(){
        this.quantity = this.quantity +1;
    }


    public Product getProduct() {
        return product;
    }


    public void setProduct(Product product) {
        this.product = product;
    }


    public int getQuantity() {
        return quantity;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "CartItem{" + "product=" + product + ", quantity=" + quantity + '}';
    }


    @Override
    public int hashCode() {
        int hash = 3;
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
        final CartItem other = (CartItem) obj;
        if (this.quantity != other.quantity) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        return true;
    }
    
    
}
