package es3.cookit.controller;

import java.util.ArrayList;
import java.util.List;

import es3.cookit.dto.FoodDto;
import es3.cookit.dto.IngredientDto;
import es3.cookit.dto.RecipeDto;
import es3.cookit.entities.Food;
import es3.cookit.entities.Ingredient;
import es3.cookit.entities.Recipe;
import es3.cookit.services.FoodService;
import es3.cookit.services.RecipeService;
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

@Path("recipes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RecipeController {

    @Inject
    RecipeService recipeService;

    @Inject
    FoodService foodService;

    @GET
    @PermitAll
    public Response listRecipes() {
        List<Recipe> recipes = recipeService.listRecipes();
        return Response.ok(recipes).build();
    }

    @GET
    @Path("{id}")
    @PermitAll
    public Response listRecipe(@PathParam("id") Long id) {
        Recipe recipe = recipeService.listRecipeById(id);
        return Response.ok(recipe).build();
    }

    @POST
    @Path("/mainSearchByFoods")
    @PermitAll
    public Response searchRecipeByFoods(List<FoodDto> dto) {
        List<Recipe> recipes = recipeService.searchRecipeByFoods(dto);
        return Response.ok(recipes).build();
    } 

    @POST
    public Response saveRecipe(RecipeDto dto) {
        Recipe recipe = recipeService.saveRecipe(dto);
        return Response.ok(recipe).status(201).build();
    }

    @GET
    @Path("/teste")
    public Response testSaveRecipe() {
        List<Ingredient> ings = new ArrayList<>();
        Recipe recipe1 = new Recipe();
        recipe1.setName("Teste");
        recipe1.setPreparation("Pegue o teste");
        recipe1.setTime("40");
        recipe1.setServe(4);
        recipe1.setDifficulty(2);

        List<Food> foods = foodService.listFood();
        
        Ingredient ing1 = new Ingredient();
        ing1.setFood(foods.get(0));
        ing1.setQuantity(1);
        ing1.setRequired(false);

        Ingredient ing2 = new Ingredient();
        ing2.setFood(foods.get(1));
        ing2.setQuantity(3);
        ing2.setRequired(true);

        ings.add(ing1);
        ings.add(ing2);

        recipe1.setIngredients(ings);
        System.out.println(recipe1);

        RecipeDto dto = new RecipeDto();
        dto.setName(recipe1.getName());
        dto.setPreparation(recipe1.getPreparation());
        dto.setTime(recipe1.getTime());
        dto.setServe(recipe1.getServe());
        dto.setDifficulty(recipe1.getDifficulty());
        dto.setIngredients(recipe1.getIngredients());
        
        Recipe recipe = recipeService.saveRecipe(dto);
        return Response.ok(recipe).status(201).build();
    }

    @PUT
    @Path("{id}")
    public Response updateRecipe(@PathParam("id") Long id, RecipeDto dto) {
        recipeService.updateRecipe(id, dto);
        return Response.status(204).build();
    } 

    @PUT
    @Path("/ingredients/{id}")
    public Response updateRecipeIngredients(@PathParam("id") Long idRecipe, List<IngredientDto> dto) {
        recipeService.updateRecipeIngredients(idRecipe, dto);
        return Response.status(204).build();
    } 

    @DELETE
    @Path("{id}")
    public Response removeRecipe(@PathParam("id") Long id) {
        recipeService.removeRecipe(id);
        return Response.status(204).build();
    }

}
