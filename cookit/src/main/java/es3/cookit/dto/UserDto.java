package es3.cookit.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import es3.cookit.entities.Ingredient;
import es3.cookit.entities.Recipe;

public class UserDto {
    
    private String name;

    private String email;

    private boolean lactoseIntolerant;

    private boolean glutenIntolerant;

    private boolean oilseedsIntolerant;

    private Date lastLogin;

    private List<Recipe> favouritedRecipes = new ArrayList<>();

    private List<Ingredient> dislikesIngredients = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isLactoseIntolerant() {
        return lactoseIntolerant;
    }

    public void setLactoseIntolerant(boolean lactoseIntolerant) {
        this.lactoseIntolerant = lactoseIntolerant;
    }

    public boolean isGlutenIntolerant() {
        return glutenIntolerant;
    }

    public void setGlutenIntolerant(boolean glutenIntolerant) {
        this.glutenIntolerant = glutenIntolerant;
    }

    public boolean isOilseedsIntolerant() {
        return oilseedsIntolerant;
    }

    public void setOilseedsIntolerant(boolean oilseedsIntolerant) {
        this.oilseedsIntolerant = oilseedsIntolerant;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public List<Recipe> getFavouritedRecipes() {
        return favouritedRecipes;
    }

    public void setFavouritedRecipes(List<Recipe> favouritedRecipes) {
        this.favouritedRecipes = favouritedRecipes;
    }

    public List<Ingredient> getDislikesIngredients() {
        return dislikesIngredients;
    }

    public void setDislikesIngredients(List<Ingredient> dislikesIngredients) {
        this.dislikesIngredients = dislikesIngredients;
    }

}
