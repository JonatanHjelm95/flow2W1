/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author jonab
 */
@Entity
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name, email;

    @ElementCollection
    @OneToMany(mappedBy = "customer")
    private List<_Order> orders;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    
    public Customer() {
    }

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }
    

    public Customer(String name, String email, List<_Order> orders) {
        this.name = name;
        this.email = email;
        this.orders = orders;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<_Order> getOrders() {
        return orders;
    }
    
    
    public void addOrder(_Order o){
        orders.add(o);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Customer[ id=" + id + " ]";
    }
    
}
