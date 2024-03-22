package com.example.security20.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameActivity; // название активности (шаги, бег, велосипед и тд.)

    private String activityLoad; // нагрузка

    private String date; // дата активности

    private Long userId;
}
