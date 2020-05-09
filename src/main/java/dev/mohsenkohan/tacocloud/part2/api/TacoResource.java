package dev.mohsenkohan.tacocloud.part2.api;

import dev.mohsenkohan.tacocloud.part1.domain.Taco;
import lombok.Getter;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;

import java.time.Instant;

@Getter
public class TacoResource extends RepresentationModel<TacoResource> {

    private final String name;
    private final Instant createdAt;
    private final CollectionModel<IngredientResource> ingredients;

    public TacoResource(Taco taco) {
        name = taco.getName();
        createdAt = taco.getCreatedAt();
        ingredients = new IngredientResourceAssembler().toCollectionModel(taco.getIngredients());
    }
}
