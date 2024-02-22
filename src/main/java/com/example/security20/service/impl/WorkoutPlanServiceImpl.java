package com.example.security20.service.impl;

import com.example.security20.entity.ReportOfWorkout;
import com.example.security20.entity.WorkoutPlan;
import com.example.security20.repository.ReportOfWorkoutRepository;
import com.example.security20.repository.WorkoutPlanRepository;
import com.example.security20.service.WorkoutPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutPlanServiceImpl implements WorkoutPlanService {
    @Autowired
    private WorkoutPlanRepository workoutPlanRepository;
    @Autowired
    private ReportOfWorkoutRepository reportOfWorkoutRepository;
    @Override
    public List<WorkoutPlan> getWorkoutPlansByUserId(Long userId) {
        return workoutPlanRepository.getWorkoutPlansByUserId(userId);
    }

    @Override
    public void saveWorkoutPlan(WorkoutPlan plan) {
        workoutPlanRepository.save(plan);
    }

    @Override
    public void deleteWorkoutPlanById(Long id) {
        workoutPlanRepository.deleteById(id);
    }

    @Override
    public Optional<WorkoutPlan> findWorkoutPlanById(Long id) {
        Optional<WorkoutPlan> workoutPlan = workoutPlanRepository.findById(id);
        return workoutPlan;
    }

    @Override
    public void saveReport(ReportOfWorkout reportOfWorkout) {
        reportOfWorkoutRepository.save(reportOfWorkout);
    }

    @Override
    public List<ReportOfWorkout> getReportOfWorkoutByUserId(Long userId) {
        return reportOfWorkoutRepository.findReportOfWorkoutByUserId(userId);
    }

}
