package com.example.security20.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "nutrition")
public class Nutrition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String food; // название продукта
    private Double weight; // граммовка
    private Double calories; // калории
    private Double proteins; // белки
    private Double fats; // жиры
    private Double carbohydrates; // углеводы
    private String date; // дата и время приема пищи

    private Long userId;
}
