package es3.cookit.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import es3.cookit.dto.FoodDto;
import es3.cookit.dto.IngredientDto;
import es3.cookit.dto.RecipeDto;
import es3.cookit.entities.Ingredient;
import es3.cookit.entities.Recipe;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class RecipeService {

    public List<Recipe> listRecipes() {
        return Recipe.listAll();
    }
    
    @Transactional
    public Recipe saveRecipe(RecipeDto dto) {
        Recipe recipe = new Recipe();

        recipe.setName(dto.getName());
        recipe.setDifficulty(dto.getDifficulty());
        recipe.setPreparation(dto.getPreparation());
        recipe.setServe(dto.getServe());
        recipe.setTime(dto.getTime());
        recipe.setIngredients(dto.getIngredients());
    
        recipe.persist();

        return recipe;
    }

    @Transactional
    public void updateRecipe(Long id, RecipeDto dto) {
        Recipe recipe = new Recipe();

        Optional<Recipe> reciOptional = recipe.findByIdOptional(id);

        if (reciOptional.isEmpty()) {
            throw new NullPointerException("Food Not Found!");
        }

        recipe = reciOptional.get();
        recipe.setName(dto.getName());
        recipe.persist();
    }

    @Transactional
    public void removeRecipe(Long id) {
        Optional<Recipe> recipeOptional = Recipe.findByIdOptional(id);
        if (recipeOptional.isEmpty()) {
            throw new NullPointerException("Food Not Found!");
        }

        Recipe recipe = recipeOptional.get();

        recipe.delete();
    }

}
