package dev.mohsenkohan.tacocloud.part2.api;

import dev.mohsenkohan.tacocloud.part1.domain.Ingredient;
import dev.mohsenkohan.tacocloud.part1.domain.Ingredient.Type;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Relation(itemRelation = "ingredient", collectionRelation = "ingredients")
public class IngredientResource extends RepresentationModel<IngredientResource> {

    private final String name;
    private final Type type;

    public IngredientResource(Ingredient ingredient) {
        name = ingredient.getName();
        type = ingredient.getType();
    }
}
