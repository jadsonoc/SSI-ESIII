package es3.cookit.services;

import java.util.List;
import java.util.Optional;

import es3.cookit.dto.FoodDto;
import es3.cookit.entities.Food;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class FoodService {

    public List<Food> listFood() {
        return Food.listAll();
    }
    
    @Transactional
    public Food saveFood(FoodDto dto) {
        Food food = new Food();

        food.setName(dto.getName());

        food.persist();

        return food;
    }

    @Transactional
    public void updateFood(Long id, FoodDto dto) {
        Food food = new Food();

        Optional<Food> foodOptional = food.findByIdOptional(id);

        if (foodOptional.isEmpty()) {
            throw new NullPointerException("Food Not Found!");
        }

        food = foodOptional.get();
        food.setName(dto.getName());
        food.persist();
    }

    // public Food update(Long id, Food food) {
    //     Food foodEntity = Food.findById(id);

    //     if (foodEntity == null) {
    //         throw new WebApplicationException("Food with id of " + id + "doesn't exist.", Response.Status.NOT_FOUND);
    //     }

    //     foodEntity.setName(food.getName());
    //     return foodEntity;

    // }

    @Transactional
    public void removeFood(Long id) {
        Optional<Food> foodOptional = Food.findByIdOptional(id);
        if (foodOptional.isEmpty()) {
            throw new NullPointerException("Food Not Found!");
        }
        
        Food food = foodOptional.get();

        food.delete();
    }

}
