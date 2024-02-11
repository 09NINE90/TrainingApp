package com.example.security20.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_physical_parameters")
public class UserPhysicalParameters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int age; // Возраст

    private double weight; // Вес

    private double height; // Рост

    private double armCircumference; //Обхват рук

    private double hipCircumference; //Обхват бедер

    private double waistCircumference; //Обхват талии

    private LocalDate date; //Дата обновления параметров

    private Long userId;

}
