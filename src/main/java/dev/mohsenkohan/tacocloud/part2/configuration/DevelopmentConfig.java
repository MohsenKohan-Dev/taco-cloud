package dev.mohsenkohan.tacocloud.part2.configuration;

import dev.mohsenkohan.tacocloud.part1.domain.Ingredient;
import dev.mohsenkohan.tacocloud.part1.domain.Taco;
import dev.mohsenkohan.tacocloud.part1.domain.User;
import dev.mohsenkohan.tacocloud.part1.repository.IngredientRepository;
import dev.mohsenkohan.tacocloud.part1.repository.TacoRepository;
import dev.mohsenkohan.tacocloud.part1.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;

@Profile("!prod")
@Configuration
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner dataLoader(IngredientRepository repo, UserRepository userRepo,
                                        TacoRepository tacoRepo, PasswordEncoder encoder) {
        return args -> {
            Ingredient flourTortilla = repo.findById("FLTO").get();
            Ingredient cornTortilla = repo.findById("COTO").get();
            Ingredient groundBeef = repo.findById("GRBF").get();
            Ingredient carnitas = repo.findById("CARN").get();
            Ingredient tomatoes = repo.findById("TMTO").get();
            Ingredient lettuce = repo.findById("LETC").get();
            Ingredient cheddar = repo.findById("CHED").get();
            Ingredient jack = repo.findById("JACK").get();
            Ingredient salsa = repo.findById("SLSA").get();
            Ingredient sourCream = repo.findById("SRCR").get();

            userRepo.save(new User("habuma", encoder.encode("password"),
                    "Craig Walls", "123 North Street", "Cross Roads", "TX",
                    "76227", "123-123-1234"));

            Taco taco1 = new Taco();
            taco1.setName("Carnivore");
            taco1.setIngredients(Arrays.asList(flourTortilla, groundBeef, carnitas, sourCream, salsa, cheddar));
            tacoRepo.save(taco1);

            Taco taco2 = new Taco();
            taco2.setName("Bovine Bounty");
            taco2.setIngredients(Arrays.asList(cornTortilla, groundBeef, cheddar, jack, sourCream));
            tacoRepo.save(taco2);

            Taco taco3 = new Taco();
            taco3.setName("Veg-Out");
            taco3.setIngredients(Arrays.asList(flourTortilla, cornTortilla, tomatoes, lettuce, salsa));
            tacoRepo.save(taco3);

            System.out.println(getIngredientById(flourTortilla.getId()));

            System.out.println(getIngredientResponseEntityById(lettuce.getId()).getHeaders());
            System.out.println(getIngredientResponseEntityById(lettuce.getId()).getBody());

            System.out.println(addIngredient(new Ingredient("ABCD", "ABCD Cheese", Ingredient.Type.CHEESE)));
        };
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Traverson traverson() {
        return new Traverson(
                URI.create("http://localhost:8080/api"),
                MediaTypes.HAL_JSON
        );
    }

    private Ingredient getIngredientById(String ingredientId) {
        return restTemplate().getForObject("http://localhost:8080/ingredients/{id}",
                Ingredient.class, ingredientId);
    }

    private ResponseEntity<Ingredient> getIngredientResponseEntityById(String ingredientId) {
        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("id", ingredientId);

        URI uri = UriComponentsBuilder
                .fromHttpUrl("http://localhost:8080/ingredients/{id}")
                .build(uriVariables);

        return restTemplate().getForEntity(uri, Ingredient.class);
    }

    private Ingredient addIngredient(Ingredient ingredient) {
        String ingredientsUrl = traverson().follow("ingredients").asLink().getHref();
        return restTemplate().postForObject(ingredientsUrl, ingredient, Ingredient.class);
    }
}
