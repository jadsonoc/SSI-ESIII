package es3.cookit.entities;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FoodRepository implements PanacheRepository<Food>{
    
}
