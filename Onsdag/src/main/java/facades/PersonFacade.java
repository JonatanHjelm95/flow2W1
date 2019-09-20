package facades;

import dto.PersonDTO;
import entities.Address;
import entities.Person;
import exceptions.PersonNotFoundException;
import interfaces.IPersonFacade;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class PersonFacade implements IPersonFacade {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private PersonFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getPersonFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public Person addPerson(String fName, String lName, String phone, Date created, Address address) {
        EntityManager em = emf.createEntityManager();
        Person p = new Person(fName, lName, phone, created, address);
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.persist(address);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return p;
    }

    @Override
    public Person deletePerson(int id) throws PersonNotFoundException {
        EntityManager em = emf.createEntityManager();
        Person p = em.find(Person.class, id);
        if (p != null) {
            try {
                em.getTransaction().begin();
                TypedQuery<Person> query = em.createQuery("DELETE FROM Person p WHERE p.id = :id", Person.class);
                TypedQuery<Address> query2 = em.createQuery("DELETE FROM Address a WHERE a.id = :id", Address.class);
                query.setParameter("id", id);
                query2.setParameter("id", p.getAddress().getId());
                query.executeUpdate();
                query2.executeUpdate();
                em.getTransaction().commit();
            } finally {
                em.close();
            }
            return p;
        }
        throw new PersonNotFoundException("");
    }

    /////GET METHODS/////
    @Override
    public Person getPerson(int id) throws PersonNotFoundException {
        EntityManager em = emf.createEntityManager();

        if (em.find(Person.class, id) != null) {

            return em.find(Person.class, id);
        }
        throw new PersonNotFoundException("");
    }

    @Override
    public List<Person> getAllPersons() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Person> query = em.createQuery("Select p from Person p", Person.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public long getPersonsCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long count = (long) em.createQuery("SELECT COUNT(p.id) FROM Person p").getSingleResult();
            return count;
        } finally {
            em.close();
        }
    }

    ////////
    @Override
    public Person editPerson(Person p) throws PersonNotFoundException {
        EntityManager em = emf.createEntityManager();
        Person person = em.find(Person.class, p.getId());
        if (person != null) {
            try {
                em.getTransaction().begin();
                person.setFirstName(p.getFirstName());
                person.setLastName(p.getLastName());
                person.setPhone(p.getPhone());
                person.setLastEdited(new Date());
                em.getTransaction().commit();
            } finally {
                em.close();
            }
            return person;
        }
        throw new PersonNotFoundException("");
    }

    public static void main(String[] args) {
        PersonFacade pf = getPersonFacade(EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.DROP_AND_CREATE));
        Person p = pf.addPerson("Line", "Larsen", "123495", new Date(), new Address("Strandboulevarden", "København", "2100"));
        //Person p1 = pf.addPerson("Lars", "Larsen", "11111", new Date(), new Address("Strandboulevarden", "København", "2100"));

    }
}
