package ru.gontarenko.tacojpa.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
// ??? не работает
//@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
@Table(name = "ingredient")
public final class Ingredient {
    @Id
    private final String id;

    private final String name;

    @Enumerated(value = EnumType.STRING)
    private final Type type;

    public Ingredient() {
        id = null;
        name = null;
        type = null;
    }

    public static enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}