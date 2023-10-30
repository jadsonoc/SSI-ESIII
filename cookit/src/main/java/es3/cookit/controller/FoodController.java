package es3.cookit.controller;

import java.util.List;

import es3.cookit.dto.FoodDto;
import es3.cookit.entities.Food;
import es3.cookit.services.FoodService;

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

@Path("foods")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FoodController {

    @Inject
    FoodService foodService;

    @GET
    public Response listFoods() {
        List<Food> foods = foodService.listFood();
        return Response.ok(foods).build();
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
