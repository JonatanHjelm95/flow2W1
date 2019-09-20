/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.*;
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
public class ItemTypeFacade {

    private static ItemTypeFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private ItemTypeFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static ItemTypeFacade getFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ItemTypeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void createItemType(ItemType it) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(it);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public ItemType findItemTypeById(int id) {
        EntityManager em = getEntityManager();
        return em.find(ItemType.class, id);
    }

    public List getAllItemTypes() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<ItemType> query = em.createQuery("Select i from ItemType i", ItemType.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        ItemTypeFacade facade = getFacade(EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE));
        facade.createItemType(new ItemType("Coca Cola", "Sodavand", 20));
//        facade.createCustomer(new Customer("Idiot", "idiot@gmail.com"));

    }

}
