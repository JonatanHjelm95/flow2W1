package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.PersonDTO;
import entities.Address;
import entities.Person;
import exceptions.PersonNotFoundException;
import utils.EMF_Creator;
import facades.PersonFacade;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//Todo Remove or change relevant parts before ACTUAL use
@Path("person")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/Persons",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE);
    private static final PersonFacade FACADE = PersonFacade.getPersonFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllMovies() {
        return GSON.toJson(FACADE.getAllPersons());
    }
    
    @Path("populate")
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    public void populate() {
        FACADE.addPerson("Line", "Larsen", "123495", new Date(), new Address("Strandboulevarden", "København", "2100"));
    }

    /**
     *
     * @param id
     * @return
     */
    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getPersonById(@PathParam("id") int id) {
        try {
            return GSON.toJson(FACADE.getPerson(id));
        } catch (PersonNotFoundException ex) {
            return "{\"code\": 404, \"message\": \"No person with provided id found\"}";
        }
    }

    @Path("edit{id}")
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String editPersonById(@PathParam("id") int id, String person){
        try {
            PersonDTO pDTO = GSON.fromJson(person, PersonDTO.class);
            Person p = FACADE.getPerson(id);
            p.setFirstName(pDTO.getFirstName());
            p.setLastName(pDTO.getLastName());
            p.setPhone(pDTO.getPhone());
            return GSON.toJson(FACADE.editPerson(p));
        } catch (PersonNotFoundException ex) {
            return "{\"code\": 404, \"message\": \"No person with provided id found\"}";
        }
    }
    @Path("delete{id}")
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    public String deletePersonById(@PathParam("id") int id) {
        try {
            FACADE.deletePerson(id);
            return "{\"status\": \"removed\"}";
        } catch (PersonNotFoundException ex) {
            return "{\"code\": 404, \"message\":\"Could not delete, provided id does not exist\"}";
        }
    }

}
