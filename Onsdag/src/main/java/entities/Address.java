package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@NamedQueries({
    @NamedQuery(name = "Persons.getAll", query = "SELECT p FROM Person p"),
    @NamedQuery(name = "Persons.deleteAllRows", query = "DELETE from Person")})
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String phone;
    @Temporal(TemporalType.DATE)
    private Date created, lastEdited;

    public Address() {
    }

    public Address(String firstName, String lastName, String phone, Date created) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.created = created;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(Date lastEdited) {
        this.lastEdited = lastEdited;
    }

        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    } 
}
