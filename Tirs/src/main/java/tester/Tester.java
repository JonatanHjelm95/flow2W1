/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import entities.Customer;
import entities.Address;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author jonab
 */
public class Tester {

    public static void main(String[] args) {
//        Persistence.generateSchema("pu", null);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        List<String> hobbies = new ArrayList();
        hobbies.add("Gaming");
        hobbies.add("Rengøring");

        Customer c1 = new Customer("Hans", "Hansen", hobbies);
        Customer c2 = new Customer("Birthe", "Kjær", hobbies);

        Address a1 = new Address("Lærkevej", "Odense");
        Address a2 = new Address("Strandboulevard", "København");
        
        c1.addToAddresses(a1);
        c2.addToAddresses(a1);
        c2.addToAddresses(a2);
        
        a1.addToCustomers(c1);
        a1.addToCustomers(c2);
        try {
            em.getTransaction().begin();
            em.persist(c1);
            em.persist(c2);
            em.persist(a1);
            em.persist(a2);
            em.getTransaction().commit();

        } finally {
            em.close();

        }
    }
}
