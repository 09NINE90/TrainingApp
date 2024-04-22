package com.example.security20.service.impl;

import com.example.security20.entity.ReportOfWorkout;
import com.example.security20.entity.WorkoutPlan;
import com.example.security20.repository.ReportOfWorkoutRepository;
import com.example.security20.repository.WorkoutPlanRepository;
import com.example.security20.service.WorkoutPlanService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutPlanServiceImpl implements WorkoutPlanService {

    @Autowired
    private ReportOfWorkoutRepository reportOfWorkoutRepository;
    @Autowired
    private WorkoutPlanRepository workoutPlanRepository;


    @Transactional
    @Override
    public void deleteWorkoutPlanById(Long id) {
        workoutPlanRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void updateReportOfWorkoutById(Long id, String exercises, String reports) {
        reportOfWorkoutRepository.updateReportOfWorkoutById(id, exercises, reports);
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

    @Override
    public Optional<ReportOfWorkout> getReportOfWorkoutById(Long id) {
        return reportOfWorkoutRepository.findById(id);
    }

    @Override
    public void saveWorkoutPlan2(WorkoutPlan workoutPlan) {
        workoutPlanRepository.save(workoutPlan);
    }

    @Override
    public List<WorkoutPlan> getWorkoutPlans2ByUserId(Long userId) {
        return workoutPlanRepository.getWorkoutPlans2ByUserId(userId);
    }


}
