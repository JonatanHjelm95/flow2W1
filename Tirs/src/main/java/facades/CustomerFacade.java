/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

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

    public Customer getCustomer(int id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Customer.class, id);
    }

    public List<Customer> getCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query = em.createQuery("Select c from Customer c", Customer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void addCustomer(Customer customer) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void deleteCustomer(int id) {
        EntityManager em = emf.createEntityManager();
        try {

            TypedQuery<Customer> query = em.createQuery("DELETE FROM Customer c WHERE c.id = :id", Customer.class);
            query.setParameter("id", id);
            query.executeUpdate();
        } finally {
            em.close();
        }
    }

    public void editCustomer(Customer customer) {
        EntityManager em = emf.createEntityManager();
        Customer c = em.find(Customer.class, customer.getId());
        try {
            em.getTransaction().begin();
            c.setFirstName(customer.getFirstName());
            c.setLastName(c.getLastName());
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
