package es3.cookit.controller;

import java.util.List;

import es3.cookit.dto.RecipeDto;
import es3.cookit.entities.Recipe;
import es3.cookit.services.RecipeService;
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

@Path("recipes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RecipeController {

    @Inject
    RecipeService recipeService;

    @GET
    public Response listRecipes() {
        List<Recipe> recipes = recipeService.listRecipes();
        return Response.ok(recipes).build();
    }

    @POST
    public Response saveRecipe(RecipeDto dto) {
        Recipe recipe = recipeService.saveRecipe(dto);
        return Response.ok(recipe).status(201).build();
    }


    @PUT
    @Path("{id}")
    public Response updateRecipe(@PathParam("id") Long id, RecipeDto dto) {
        recipeService.updateRecipe(id, dto);
        return Response.status(204).build();
    } 


    @DELETE
    @Path("{id}")
    public Response removeRecipe(@PathParam("id") Long id) {
        recipeService.removeRecipe(id);
        return Response.status(204).build();
    }

}
