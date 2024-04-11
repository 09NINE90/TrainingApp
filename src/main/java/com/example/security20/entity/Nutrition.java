package com.example.security20.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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
    @Column(columnDefinition = "TIMESTAMP USING date::TIMESTAMP(6) WITHOUT TIME ZONE")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date date; // дата и время приема пищи
    private Long weekId;
    private Long userId;

}
