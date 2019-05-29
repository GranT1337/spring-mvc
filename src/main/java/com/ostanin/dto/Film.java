package com.ostanin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Film {
    //@Min(value = 0, message = "Правильно ID!")
    //@NotNull(message = "Please enter id")
    private long id;
    @NotEmpty(message="Название фильма должно быть задано")
    private String title;
    @NotEmpty(message="Режиссёр должен быть указан")
    private String producer;
    //@Pattern(regexp = "(\\d+.?,?\\d+)", message = "Неправильно указан балл")
    //@NotNull(message = "Please enter points")
    private double points;
}
