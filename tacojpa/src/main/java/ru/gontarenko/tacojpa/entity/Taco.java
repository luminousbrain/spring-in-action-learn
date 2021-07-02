package ru.gontarenko.tacojpa.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "taco")
public final class Taco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private Date createdAt;

    @Size(min = 1, message = "You must choose at least 1 ingredient")
    @ManyToMany(targetEntity = Ingredient.class)
    @JoinTable(
            name = "taco_ingredients",
            joinColumns = @JoinColumn(name = "taco_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredientList;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }
}
