package es3.cookit.entities;

import java.util.ArrayList;
import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="ckt_recipe")
public class Recipe extends PanacheEntity {
    
    @Column
    @NotEmpty
    private String name;

    @Column
    @NotEmpty
    private String preparation;

    @Column
    private String time;

    @Column
    private int serve;

    @Column
    private int difficulty;

    @OneToMany (targetEntity = Ingredient.class, cascade = CascadeType.ALL,
                fetch = FetchType.EAGER)
    private List<Ingredient> ingredients = new ArrayList<>();

    public Recipe() {
        
    }

    public Recipe(@NotEmpty String name, @NotEmpty String preparation, String time, int serve, int difficulty,
            List<Ingredient> ingredients) {
        this.name = name;
        this.preparation = preparation;
        this.time = time;
        this.serve = serve;
        this.difficulty = difficulty;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getServe() {
        return serve;
    }

    public void setServe(int serve) {
        this.serve = serve;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

}

