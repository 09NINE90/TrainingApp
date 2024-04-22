package com.example.security20.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_phys_parameters")
public class UserPhysicalParameters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int age; // Возраст
    private double weight; // Вес
    private double armCircumference; //Обхват руки
    private double legGirth; //Обхват ноги
    private double chestCircumference; //Обхват грудной клетки
    private double hipCircumference; //Обхват бедер
    private double waistCircumference; //Обхват талии (под пупком)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date; //Дата обновления параметров
    private Long userId;

}
