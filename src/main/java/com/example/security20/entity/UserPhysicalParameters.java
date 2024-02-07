package com.example.security20.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
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

    private String date; //Дата обновления параметров
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
