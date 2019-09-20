/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 *
 * @author jonab
 */
public class CustomerFacade {

    private static CustomerFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private CustomerFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CustomerFacade getFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void createCustomer(Customer c) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Customer findCustomerById(int id) {
        EntityManager em = getEntityManager();
        return em.find(Customer.class, id);
    }

    public List getAllCustomers() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Customer> query = em.createQuery("Select c from Customer c", Customer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void createOrder(_Order o) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(o);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public _Order getOrderById(int id) {
        EntityManager em = getEntityManager();
        return em.find(_Order.class, id);
    }
    
    public void addOrderLineToOrder(int orderID, OrderLine orderline){
        
    }

    public static void main(String[] args) {
        CustomerFacade facade = getFacade(EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE));
//        facade.createCustomer(new Customer("Jonatan", "jonabsv@gmail.com"));
//        facade.createCustomer(new Customer("Idiot", "idiot@gmail.com"));
//            List<OrderLine> orderLines = new ArrayList();
//            orderLines.add(new OrderLine(1, new ItemType("Faxe", "Sodavand", 20)));
        facade.createOrder(new _Order(facade.findCustomerById(1), 20));

    }

}
