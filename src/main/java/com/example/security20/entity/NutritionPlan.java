package com.example.security20.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "nutrition_plan")
public class NutritionPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private int calories; // калории
    private int proteins; // белки
    private int fats; // жиры
    private int carbohydrates; // углеводы
    private int weight; // вес человека
    private String duration; //когда начало и конец плана
}
