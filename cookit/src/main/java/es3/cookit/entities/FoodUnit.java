package es3.cookit.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="ckt_food_unit")
public class FoodUnit extends PanacheEntity{
    
    @Column
    @NotEmpty
    private String name;

    @Column
    @NotEmpty
    private String abbreviation;
}
