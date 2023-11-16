package es3.cookit.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import es3.cookit.dto.FoodDto;
import es3.cookit.dto.IngredientDto;
import es3.cookit.dto.RecipeDto;
import es3.cookit.entities.Food;
import es3.cookit.entities.Ingredient;
import es3.cookit.entities.Recipe;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class RecipeService {

    private long contador;

    public List<Recipe> listRecipes() {
        return Recipe.listAll();
    }
    
    public Recipe listRecipeById(Long id) {
        return Recipe.findById(id);
    }

    public List<Recipe> searchRecipeByFoods(List<FoodDto> dto) {
        
        List<Recipe> allRecipes = Recipe.listAll();
        List<Recipe> responseRecipes = new ArrayList<>();

        for (Recipe recipe : allRecipes) {
            this.contador = 0;
            for (FoodDto food : dto) {
                List<Ingredient> essentialIngredients = recipe.getIngredients().stream()
                        .filter(Ingredient::isRequired)
                        .collect(Collectors.toList());
                for (Ingredient essential : essentialIngredients) {
                    if (essential.getFood().getName().equals(food.getName())) {
                        this.contador++;
                    }
                }

            }
            if (this.contador == recipe.getIngredients().stream()
                    .filter(Ingredient::isRequired)
                    .count()) {
                    responseRecipes.add(recipe);       
            }
        }
        return responseRecipes;
    };

    /* 
    
            allRecipes.forEach(recipe -> {
            dto.forEach(food -> {
                recipe.getIngredients().stream()
                    .filter(Ingredient::isRequired)
                    .collect(Collectors.toList())
                    .forEach(ingredientRequired -> {
                        if ( food.getName() == ingredientRequired.getFood().getName() ){
                            
                        }
                    });
            });
        });
    allRecipes.forEach(recipe -> {
        dto.forEach(food -> {
            Stream<Ingredient> required = recipe.getIngredients().stream()
                    .filter(Ingredient::isRequired);
                                                
            if ( required.allMatch(r -> {
                    return r.getFood().getName().equals(food.getName());
            })) {
                responseRecipes.add(recipe);
            }
            recipe.getIngredients().forEach(ingredient -> {
                if (ingredient.getFood().getName().equals(food.getName())) {
                    System.out.println(required);
                    responseRecipes.add(recipe);
                }
            });
            
        });
    });
    
    return responseRecipes;
    }
    */
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
            throw new NullPointerException("Recipe Not Found!");
        }

        recipe = reciOptional.get();
        recipe.setName(dto.getName());
        recipe.setPreparation(dto.getPreparation());
        recipe.setServe(dto.getServe());
        recipe.setTime(dto.getTime());
        recipe.setDifficulty(dto.getDifficulty());
        recipe.setIngredients(dto.getIngredients());
        recipe.persist();
    }

    @Transactional
    public void updateRecipeIngredients(Long idRecipe, List<IngredientDto> dto) {
        Recipe recipe = new Recipe();

        Optional<Recipe> reciOptional = recipe.findByIdOptional(idRecipe);

        if (reciOptional.isEmpty()) {
            throw new NullPointerException("Recipe Not Found!");
        }

        List<Ingredient> ingredients = new ArrayList<>();

        for (IngredientDto iDto : dto) {
            ingredients.add(new Ingredient(iDto.getFood(), iDto.getQuantity(), iDto.isRequired()));
        }

        recipe = reciOptional.get();
        recipe.setIngredients(ingredients);
        recipe.persist();
    }

    @Transactional
    public void removeRecipe(Long id) {
        Optional<Recipe> recipeOptional = Recipe.findByIdOptional(id);
        if (recipeOptional.isEmpty()) {
            throw new NullPointerException("Recipe Not Found!");
        }

        Recipe recipe = recipeOptional.get();

        recipe.delete();
    }

}
