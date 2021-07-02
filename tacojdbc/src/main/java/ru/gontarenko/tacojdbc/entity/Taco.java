package ru.gontarenko.tacojdbc.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public final class Taco {

    private Long id;

    private Date createdAt;

    @NotNull
    @Size(min = 5, max = 50, message = "Name must be at least 5 characters long (50 is maximum)")
    private String name;

    @Size(min = 1, message = "You must choose at least 1 ingredient")
    @NotNull(message = "You must choose at least 1 ingredient")
    private List<String> ingredients;
}
