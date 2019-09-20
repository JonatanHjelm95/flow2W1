package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.*;
import utils.EMF_Creator;
import facades.CustomerFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("customer")
public class CustomerResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/fredagExam",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE);
    private static final CustomerFacade FACADE = CustomerFacade.getFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllCustomers() {
        return GSON.toJson(FACADE.getAllCustomers());
    }
    
    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getCustomerById(@PathParam("id") int id) {
        return GSON.toJson(FACADE.findCustomerById(id));
    }

}
