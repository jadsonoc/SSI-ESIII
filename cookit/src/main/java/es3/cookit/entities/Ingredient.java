package es3.cookit.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="ckt_ingredients")
public class Ingredient extends PanacheEntity {

    @ManyToOne
    private Food food;

    @Column
    @NotEmpty
    private float quantity;

    @Column
    private boolean required;

    public Ingredient() {
        
    }

    public Ingredient(Food food, @NotEmpty float quantity, boolean required) {
        this.food = food;
        this.quantity = quantity;
        this.required = required;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

}
