package ru.gontarenko.tacojdbc.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.gontarenko.tacojdbc.entity.Ingredient;
import ru.gontarenko.tacojdbc.repository.IngredientRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {
    private final JdbcTemplate jdbc;

    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return jdbc.query("SELECT * FROM ingredient;", this::mapRowToIngredient);
    }

    @Override
    public Optional<Ingredient> findById(String id) {
        return Optional.ofNullable(jdbc.queryForObject(
                "SELECT * FROM ingredient WHERE id = ?;",
                this::mapRowToIngredient, id)
        );
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbc.update("INSERT INTO ingredient(id, name, type) VALUES (?, ?, ?);",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString());
        return ingredient;
    }

    private Ingredient mapRowToIngredient(ResultSet resultSet, int romNum) throws SQLException {
        return new Ingredient(
                resultSet.getString("id"),
                resultSet.getString("name"),
                Ingredient.Type.valueOf(resultSet.getString("type"))
        );
    }
}