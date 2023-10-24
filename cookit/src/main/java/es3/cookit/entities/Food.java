package es3.cookit.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="ckt_food")
public class Food extends PanacheEntity {

    @Column
    @NotEmpty
    private String name;

    @Column
    private boolean lactoseFree;

    @Column
    private boolean glutenFree;

    @Column
    private boolean oilseedFree;

    @Column
    @OneToMany
    private FoodUnit foodUnit;

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

    public FoodUnit getFoodUnit() {
        return foodUnit;
    }

    public void setFoodUnit(FoodUnit foodUnit) {
        this.foodUnit = foodUnit;
    }

}
