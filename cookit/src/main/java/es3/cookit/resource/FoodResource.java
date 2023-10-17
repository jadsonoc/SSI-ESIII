package es3.cookit.resource;

import java.util.List;

import es3.cookit.controller.FoodController;
import es3.cookit.entities.Food;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/food")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FoodResource {

    @Inject
    private FoodController foodController;

    @GET
    public List<Food> findAll() {
        return Food.listAll();

    }

    @POST
    @Transactional
    public Response create(Food food) {
        Food.persist(food);
        return Response.ok(food).status(201).build();

    }
    
    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, Food food) {
        
        if (foodController.isFoodNameNotEmpty(food)) {
            return Response.ok("Food not found").type(MediaType.APPLICATION_JSON_TYPE).build();
        }

        Food foodEntity = foodController.update(id, food);

        return Response.ok(foodEntity).build();
    }
}
