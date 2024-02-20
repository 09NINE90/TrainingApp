package com.example.security20.service.impl;

import com.example.security20.entity.WorkoutPlan;
import com.example.security20.repository.WorkoutPlanRepository;
import com.example.security20.service.WorkoutPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutPlanServiceImpl implements WorkoutPlanService {
    @Autowired
    private WorkoutPlanRepository workoutPlanRepository;
    @Override
    public List<WorkoutPlan> getWorkoutPlansByUserId(Long userId) {
        return workoutPlanRepository.getWorkoutPlansByUserId(userId);
    }

    @Override
    public void saveWorkoutPlan(WorkoutPlan plan) {
        workoutPlanRepository.save(plan);
    }
}
