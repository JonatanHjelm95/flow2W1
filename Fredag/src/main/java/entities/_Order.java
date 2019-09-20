/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
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
public class _Order implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    private Customer customer;
    
    @ElementCollection
    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines;
    
    private double price;

    public _Order() {
    }

    public _Order(Customer customer, List<OrderLine> orderLines) {
        this.customer = customer;
        this.orderLines = orderLines;
    }

    public _Order(Customer customer, List<OrderLine> orderLines, double price) {
        this.customer = customer;
        this.orderLines = orderLines;
        this.price = price;
    }

    public _Order(Customer customer, double price) {
        this.customer = customer;
        this.price = price;
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
    public String toString() {
        return "entities.Customer[ id=" + id + " ]";
    }

}
