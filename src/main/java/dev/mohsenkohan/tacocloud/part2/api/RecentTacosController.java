package dev.mohsenkohan.tacocloud.part2.api;

import dev.mohsenkohan.tacocloud.part1.domain.Taco;
import dev.mohsenkohan.tacocloud.part2.repository.TacoRestRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RepositoryRestController
public class RecentTacosController {

    private final TacoRestRepository tacoRepository;

    public RecentTacosController(TacoRestRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    @GetMapping(path = "/tacos/recent", produces = "application/hal+json")
    @ResponseBody
    public CollectionModel<TacoResource> recentTacos() {
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        List<Taco> tacos = tacoRepository.findAll(page).getContent();

        CollectionModel<TacoResource> recentResources =
                new TacoResourceAssembler().toCollectionModel(tacos);

        recentResources.add(
                linkTo(methodOn(DesignTacoRestController.class).recentTacos())
                        .withRel("recents"));

        return recentResources;
    }
}
