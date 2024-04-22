package com.example.security20.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "workout_plan")
public class WorkoutPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dayOfWeek;
    private String exercises;
    private String repetitions;
    private Long userId;
}
