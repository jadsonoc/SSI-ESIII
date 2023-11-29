package es3.cookit.controller;

import java.util.List;

import es3.cookit.dto.FoodDto;
import es3.cookit.entities.Food;
import es3.cookit.services.FoodService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/foods")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FoodController {

    @Inject
    FoodService foodService;

    @GET
    @PermitAll
    public Response listFoods() {
        List<Food> foods = foodService.listFood();
        return Response.ok(foods).build();
    }

    @GET
    @Path("{id}")
    @PermitAll
    public Response listFood(@PathParam("id") Long id) {
        Food food = foodService.listFoodById(id);
        return Response.ok(food).build();
    }

    @GET
    @Path("/search/{query}")
    public Response listFood(@PathParam("query") String searchQuery) {
        List <Food> foodFound = foodService.listFoodByQuery(searchQuery);
        return Response.ok(foodFound).build();
    }

    @POST
    public Response saveFood(FoodDto dto) {
        Food food = foodService.saveFood(dto);
        return Response.ok(food).status(201).build();
    }


    @PUT
    @Path("{id}")
    public Response updateFood(@PathParam("id") Long id, FoodDto dto) {
        foodService.updateFood(id, dto);
        return Response.status(204).build();
    } 


    @DELETE
    @Path("{id}")
    public Response removeFood(@PathParam("id") Long id) {
        foodService.removeFood(id);
        return Response.status(204).build();
    }

    public boolean isFoodNameNotEmpty(Food food) {
        return food.getName().isEmpty();
    }
}
