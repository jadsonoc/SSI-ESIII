package es3.cookit.dto;

public class FoodDto {
    
    private String name;

    private boolean lactoseFree;

    private boolean glutenFree;

    private boolean oilseedFree;

    private String foodUnit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLactoseFree() {
        return lactoseFree;
    }

    public void setLactoseFree(boolean lactoseFree) {
        this.lactoseFree = lactoseFree;
    }

    public boolean isGlutenFree() {
        return glutenFree;
    }

    public void setGlutenFree(boolean glutenFree) {
        this.glutenFree = glutenFree;
    }

    public boolean isOilseedFree() {
        return oilseedFree;
    }

    public void setOilseedFree(boolean oilseedFree) {
        this.oilseedFree = oilseedFree;
    }

    public String getFoodUnit() {
        return foodUnit;
    }

    public void setFoodUnit(String foodUnit) {
        this.foodUnit = foodUnit;
    }

    @Override
    public String toString() {
        return "FoodDto [name=" + name + ", lactoseFree=" + lactoseFree + ", glutenFree=" + glutenFree
                + ", oilseedFree=" + oilseedFree + ", foodUnit=" + foodUnit + "]";
    }

    
}
