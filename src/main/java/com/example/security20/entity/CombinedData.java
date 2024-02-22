package com.example.security20.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CombinedData {
    private WorkoutPlan workoutPlan;
    private ReportOfWorkout reportOfWorkout;
}
