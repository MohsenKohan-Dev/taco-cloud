package dev.mohsenkohan.tacocloud.part1.converter;

import dev.mohsenkohan.tacocloud.part1.domain.Ingredient;
import dev.mohsenkohan.tacocloud.part1.repository.IngredientRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private final IngredientRepository ingredientRepository;

    public IngredientByIdConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientRepository.findById(id).get();
    }
}
