package com.example.security20.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@Table(name = "week_nutrition")
@ToString
public class WeekNutrition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numOfWeek; // номер недели в таблице
    private int numDayOfWeek; // номер ддня недели
    private int countDaysOfWeek; // количество записей в неделе
    private Double sumCalories; // сумма ккал за неделю
    private Double sumProteins; // сумма белки за неделю
    private Double sumFats; //  сумма жиры за неделю
    private Double sumCarbohydrates; //  сумма углеводы за неделю

    private Long userId; // id пользователя, которому пренадлежит запись
}
