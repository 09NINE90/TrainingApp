package com.example.security20.service;

import com.example.security20.entity.ReportOfWorkout;
import com.example.security20.entity.WorkoutPlan;

import java.util.List;
import java.util.Optional;

public interface WorkoutPlanService {

    Optional<WorkoutPlan> findWorkoutPlanById(Long id);
    void saveReport(ReportOfWorkout reportOfWorkout);
    List<ReportOfWorkout> getReportOfWorkoutByUserId(Long userId);
    Optional<ReportOfWorkout> getReportOfWorkoutById(Long id);
    void saveWorkoutPlan2(WorkoutPlan workoutPlan);
    List<WorkoutPlan> getWorkoutPlans2ByUserId(Long userId);
    void deleteWorkoutPlanById(Long id);

    void updateReportOfWorkoutById(Long id, String exercises, String reports);
}
