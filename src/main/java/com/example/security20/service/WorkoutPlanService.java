package com.example.security20.service;

import com.example.security20.entity.WorkoutPlan;

import java.util.List;

public interface WorkoutPlanService {
    List<WorkoutPlan> getWorkoutPlansByUserId(Long userId);
    void saveWorkoutPlan(WorkoutPlan plan);
}
