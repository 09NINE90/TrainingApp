package com.example.security20.service;

import com.example.security20.entity.ReportOfWorkout;
import com.example.security20.entity.WorkoutPlan;

import java.util.List;
import java.util.Optional;

public interface WorkoutPlanService {
    List<WorkoutPlan> getWorkoutPlansByUserId(Long userId);
    void saveWorkoutPlan(WorkoutPlan plan);

    void deleteWorkoutPlanById(Long id);

    Optional<WorkoutPlan> findWorkoutPlanById(Long id);

    void saveReport(ReportOfWorkout reportOfWorkout);

    List<ReportOfWorkout> getReportOfWorkoutByUserId(Long userId);
}
