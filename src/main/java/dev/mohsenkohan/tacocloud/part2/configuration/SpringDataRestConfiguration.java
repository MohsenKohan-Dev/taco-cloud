package dev.mohsenkohan.tacocloud.part2.configuration;

import dev.mohsenkohan.tacocloud.part1.domain.Taco;
import dev.mohsenkohan.tacocloud.part2.api.RecentTacosController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Configuration
public class SpringDataRestConfiguration {

    @Bean
    public RepresentationModelProcessor<CollectionModel<EntityModel<Taco>>> tacoProcessor(RepositoryRestConfiguration restConfiguration) {
        return new RepresentationModelProcessor<CollectionModel<EntityModel<Taco>>>() {
            @Override
            public CollectionModel<EntityModel<Taco>> process(CollectionModel<EntityModel<Taco>> model) {
                model.add(
                        linkTo(RecentTacosController.class)
                                .slash(restConfiguration.getBasePath().toString())
                                .slash("tacos/recent")
                                .withRel("recents"));
                return model;
            }
        };
    }
}
