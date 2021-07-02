package ru.gontarenko.tacojpa.repository;

import org.springframework.data.repository.CrudRepository;
import ru.gontarenko.tacojpa.entity.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}