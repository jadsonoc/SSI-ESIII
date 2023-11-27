package es3.cookit.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@NamedQueries({
    @NamedQuery(name = "User.findByName", query = "from User where name = ?1"),
    @NamedQuery(name = "User.findByEmail", query = "from User where email = ?1")
})

@Entity
@Table(name = "ckt_user")
public class User extends PanacheEntity {
   
    @Column 
    private String name;

    @Column 
    private String email;

    @Column
    private boolean lactoseIntolerant;

    @Column
    private boolean glutenIntolerant;

    @Column
    private boolean oilseedsIntolerant;

    @Column
    private Date lastLogin;

    @ManyToMany (cascade = CascadeType.ALL)
    private List<Recipe> favouritedRecipes = new ArrayList<>();

    @ManyToMany (cascade = CascadeType.ALL)
    private List<Food> dislikesIngredients = new ArrayList<>();

    public User() {
    }

    public User(String name, String email, boolean lactoseIntolerant, boolean glutenIntolerant,
            boolean oilseedsIntolerant, Date lastLogin, List<Recipe> favouritedRecipes,
            List<Food> dislikesIngredients) {
        this.name = name;
        this.email = email;
        this.lactoseIntolerant = lactoseIntolerant;
        this.glutenIntolerant = glutenIntolerant;
        this.oilseedsIntolerant = oilseedsIntolerant;
        this.lastLogin = lastLogin;
        this.favouritedRecipes = favouritedRecipes;
        this.dislikesIngredients = dislikesIngredients;
    }

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

    public List<Food> getDislikesIngredients() {
        return dislikesIngredients;
    }

    public void setDislikesIngredients(List<Food> dislikesIngredients) {
        this.dislikesIngredients = dislikesIngredients;
    }

    public static User findByName(String name) {
        return find("#User.findByName", name).firstResult();
    }

    public static User findByEmail(String email) {
        return find("#User.findByEmail", email).firstResult();
    }

}
