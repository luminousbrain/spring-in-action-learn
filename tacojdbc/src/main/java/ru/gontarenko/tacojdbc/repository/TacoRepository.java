package ru.gontarenko.tacojdbc.repository;

import ru.gontarenko.tacojdbc.entity.Taco;

public interface TacoRepository {
    Taco save(Taco taco);
}
