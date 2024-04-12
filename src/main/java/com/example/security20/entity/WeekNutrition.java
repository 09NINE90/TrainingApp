package com.example.security20.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@Table(name = "week_nutrition")
@ToString
public class WeekNutrition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date weekStart;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date weekEnd;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastDate;
    String checkDays;
    private int countDaysOfWeek; // количество записей в неделе
    private Double sumCalories; // сумма ккал за неделю
    private Double sumProteins; // сумма белки за неделю
    private Double sumFats; //  сумма жиры за неделю
    private Double sumCarbohydrates; //  сумма углеводы за неделю

    private Long userId; // id пользователя, которому пренадлежит запись


}
