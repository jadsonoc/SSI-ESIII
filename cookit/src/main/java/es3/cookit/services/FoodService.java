package es3.cookit.services;

import java.util.List;
import java.util.Optional;

import es3.cookit.dto.FoodDto;
import es3.cookit.entities.Food;
import es3.cookit.entities.Recipe;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class FoodService {

    public List<Food> listFood() {
        return Food.listAll();
    }

    public Food listFoodById(Long id) {
        return Food.findById(id);
    }
    
    @Transactional
    public Food saveFood(FoodDto dto) {
        Food food = new Food();

        food.setName(dto.getName());
        food.setGlutenFree(dto.isGlutenFree());
        food.setLactoseFree(dto.isLactoseFree());
        food.setOilseedFree(dto.isOilseedFree());
        food.setFoodUnit(dto.getFoodUnit());

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
        food.setGlutenFree(dto.isGlutenFree());
        food.setLactoseFree(dto.isLactoseFree());
        food.setOilseedFree(dto.isOilseedFree());
        food.setFoodUnit(dto.getFoodUnit());
        food.persist();
    }


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
