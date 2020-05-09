package dev.mohsenkohan.tacocloud.part2.api;

import dev.mohsenkohan.tacocloud.part1.domain.Ingredient;
import dev.mohsenkohan.tacocloud.part1.repository.IngredientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/ingredients", produces = "application/json")
@CrossOrigin("*")
public class IngredientRestController {

    private final IngredientRepository ingredientRepository;

    public IngredientRestController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping
    public Iterable<Ingredient> allIngredients() {
        return ingredientRepository.findAll();
    }

    @GetMapping("/{ingredientId}")
    public ResponseEntity<Ingredient> ingredientById(@PathVariable String ingredientId) {
        Optional<Ingredient> optionalIngredient = ingredientRepository.findById(ingredientId);
        return ResponseEntity.of(optionalIngredient);
    }
}
