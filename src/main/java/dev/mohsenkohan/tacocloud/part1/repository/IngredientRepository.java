package dev.mohsenkohan.tacocloud.part1.repository;

import dev.mohsenkohan.tacocloud.part1.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
