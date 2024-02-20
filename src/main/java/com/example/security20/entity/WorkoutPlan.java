package com.example.security20.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "workout_plan")
public class WorkoutPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dayOfWeek;

    private String exercises1, exercises2, exercises3, exercises4, exercises5, exercises6;

    private String repetitions1, repetitions2, repetitions3, repetitions4, repetitions5, repetitions6;

    private Long userId;

}