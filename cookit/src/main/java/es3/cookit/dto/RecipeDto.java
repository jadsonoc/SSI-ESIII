package es3.cookit.dto;

import java.util.List;

import es3.cookit.entities.Ingredient;

public class RecipeDto {
    
    private String name;

    private String preparation;

    private String time;

    private int serve;

    private int difficulty;

    private List<Ingredient> ingredients;

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

    @Override
    public String toString() {
        return "RecipeDto [name=" + name + ", preparation=" + preparation + ", time=" + time + ", serve=" + serve
                + ", difficulty=" + difficulty + ", ingredients=" + ingredients + "]";
    }

    
}
