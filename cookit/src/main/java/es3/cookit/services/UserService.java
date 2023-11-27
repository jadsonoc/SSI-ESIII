package es3.cookit.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import es3.cookit.dto.UserDto;
import es3.cookit.entities.Food;
import es3.cookit.entities.Ingredient;
import es3.cookit.entities.Recipe;
import es3.cookit.entities.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserService {

    public User listUserById(Long id) {
        return User.findById(id);
    }

    public List<Recipe> listUsersFavouritesRecipesById(Long id) {
        User user = new User();

        Optional<User> userOptional = user.findByIdOptional(id);

        if (userOptional.isEmpty()) {
            throw new NullPointerException("User Not Found!");
        }

        user = userOptional.get();

        return user.getFavouritedRecipes();
    }
    
    public boolean isFavouriteUsersRecipe(Long idUser, Long idRecipe) {
        List<Recipe> favouriteRecipes = this.listUsersFavouritesRecipesById(idUser);
        Recipe searchedRecipe = Recipe.findById(idRecipe);

        return favouriteRecipes.contains(searchedRecipe);
    }

    @Transactional
    public User logginUser(UserDto dto) {
        User user = new User();

        if (User.findByEmail(dto.getEmail()) != null) {
            Optional<User> userOptional = user.findByIdOptional(User.findByEmail(dto.getEmail()).id);
            user = userOptional.get();
        } else {
            user.setEmail(dto.getEmail());
            user.setName(dto.getName());
        }
        user.setLastLogin(new java.sql.Date(new java.util.Date().getTime()));
        user.persist();
        return user;
    }
    
    @Transactional
    public void loggoutUser(Long id) {
        User user = new User();

        Optional<User> userOptional = user.findByIdOptional(id);

        if (userOptional.isEmpty()) {
            throw new NullPointerException("User Not Found!");
        }

        user = userOptional.get();
        user.persist();

    }

    @Transactional
    public void updateUser(Long id, UserDto dto) {
        User user = new User();

        Optional<User> userOptional = user.findByIdOptional(id);

        if (userOptional.isEmpty()) {
            throw new NullPointerException("User Not Found!");
        }

        user = userOptional.get();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setLactoseIntolerant(dto.isLactoseIntolerant());
        user.setGlutenIntolerant(dto.isGlutenIntolerant());
        user.setOilseedsIntolerant(dto.isOilseedsIntolerant());

        user.persist();
    }
    
    @Transactional
    public void dislikeIngredients(Long idUser, Long idIngredient, boolean add) {
        User user = new User();
        Food food = new Food();

        List<Food> dislikeUserIngredients = new ArrayList<>();

        Optional<User> userOptional = user.findByIdOptional(idUser);

        Optional<Food> foodOptional = food.findByIdOptional(idIngredient);

        if (userOptional.isEmpty()) {
            throw new NullPointerException("User Not Found!");
        }
        
        user = userOptional.get();
        food = foodOptional.get();
        
        dislikeUserIngredients = user.getDislikesIngredients();
        if (add) {
            dislikeUserIngredients.add(food);
        } else {
            dislikeUserIngredients.remove(food);
        }
        
        user.persist();

    }


    @Transactional
    public void favouriteRecipe(Long idUser, Long idRecipe) {
        User user = new User();
        Recipe recipe = new Recipe();
        List<Recipe> favouritedRecipes = new ArrayList<>();


        Optional<User> userOptional = user.findByIdOptional(idUser);

        Optional<Recipe> recipeOptional = recipe.findByIdOptional(idRecipe);

        if (userOptional.isEmpty()) {
            throw new NullPointerException("User Not Found!");
        }

        user = userOptional.get();
        recipe = recipeOptional.get();

        favouritedRecipes = user.getFavouritedRecipes();
        favouritedRecipes.add(recipe);

        user.persist();

    }

    @Transactional
    public void unfavouriteRecipe(Long idUser, Long idRecipe) {
        User user = new User();
        Recipe recipe = new Recipe();
        List<Recipe> favouritedRecipes = new ArrayList<>();

        Optional<User> userOptional = user.findByIdOptional(idUser);

        Optional<Recipe> recipeOptional = recipe.findByIdOptional(idRecipe);

        if (userOptional.isEmpty()) {
            throw new NullPointerException("User Not Found!");
        }

        user = userOptional.get();
        recipe = recipeOptional.get();

        favouritedRecipes = user.getFavouritedRecipes();
        favouritedRecipes.remove(recipe);

        user.persist();

    }

}


    