package es3.cookit.controller;

import es3.cookit.entities.Food;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class FoodController {
    

    public Food update(Long id, Food food) {
        Food foodEntity = Food.findById(id);

        if (foodEntity == null) {
            throw new WebApplicationException("Food with id of " + id + "doesn't exist.", Response.Status.NOT_FOUND);
        }

        foodEntity.setName(food.getName());
        return foodEntity;

    }

    public boolean isFoodNameNotEmpty(Food food) {
        return food.getName().isEmpty();
    }
}
