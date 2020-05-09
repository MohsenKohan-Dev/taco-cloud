package dev.mohsenkohan.tacocloud.part1.repository;

import dev.mohsenkohan.tacocloud.part1.domain.Ingredient;
import dev.mohsenkohan.tacocloud.part1.domain.Taco;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.time.Instant;
import java.util.Arrays;

@Repository
public class JdbcTacoRepository {

    private final JdbcTemplate jdbc;

    public JdbcTacoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Taco save(Taco taco) {
        long tacoId = saveTacoInfo(taco);
        taco.setId(tacoId);

        for (Ingredient ingredient : taco.getIngredients()) {
            saveIngredientToTaco(ingredient, tacoId);
        }

        return taco;
    }

    private long saveTacoInfo(Taco taco) {
        taco.setCreatedAt(Instant.now());

        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "INSERT INTO Taco (name, createdAt) VALUES (?, ?)",
                Types.VARCHAR, Types.TIMESTAMP);

        pscf.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(taco.getName(), taco.getCreatedAt()));

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update(psc, keyHolder);

        return keyHolder.getKey().longValue();
    }

    private void saveIngredientToTaco(Ingredient ingredient, long tacoId) {
        jdbc.update("INSERT INTO Taco_Ingredients (taco, ingredient) VALUES (?, ?)",
                tacoId, ingredient.getId());
    }
}
