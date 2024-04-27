package com.example.security20.controller;

import com.example.security20.dto.CustomUserDetails;
import com.example.security20.entity.*;
import com.example.security20.service.WorkoutPlanService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController

public class RestControllerWpAndReports {
    @Autowired
    WorkoutPlanService workoutPlanService;

    @PostMapping("/submitForm")
    public String processDataWorkoutPlan(@RequestBody FormWorkoutPlan data) {
        // Обработка данных
        WorkoutPlan workoutPlan = new WorkoutPlan();
        workoutPlan.setDayOfWeek(data.getDayOfWeek());
        workoutPlan.setUserId(data.getUserId());
        workoutPlan.setExercises(Arrays.toString(data.getExercises()).replace("[", "").replace("]", ""));
        workoutPlan.setRepetitions(Arrays.toString(data.getRepetitions()).replace("[", "").replace("]", ""));
        workoutPlanService.saveWorkoutPlan2(workoutPlan);
        return "redirect:/api/v1/getWorkoutPage/" + data.getUserId();
    }

    @GetMapping("/workoutPlan/{id}")
    public ResponseEntity<FormWorkoutPlan> getWorkoutPlan(@PathVariable Long id){
        Optional<WorkoutPlan> workoutPlan2 = workoutPlanService.findWorkoutPlanById(id);
        WorkoutPlan result = workoutPlan2.get();
        FormWorkoutPlan formWorkoutPlan = new FormWorkoutPlan();
        String[] exercisesArray = result.getExercises().split(", ");
        String[] repetitionsArray = result.getRepetitions().split(", ");
        formWorkoutPlan.setId(id);
        formWorkoutPlan.setDayOfWeek(result.getDayOfWeek());
        formWorkoutPlan.setExercises(exercisesArray);
        formWorkoutPlan.setRepetitions(repetitionsArray);
        formWorkoutPlan.setUserId(result.getUserId());

        return ResponseEntity.ok(formWorkoutPlan);
    }

    @GetMapping("/getReport/{id}")
    public ResponseEntity<FormReport> getReport(@PathVariable Long id){
        Optional<ReportOfWorkout> reportOfWorkout = workoutPlanService.getReportOfWorkoutById(id);
        ReportOfWorkout report = reportOfWorkout.get();
        FormReport formReport = new FormReport();
        String[] exercisesArray = report.getExercises().split(", ");
        String[] reportsArray = report.getReports().split(", ");
        formReport.setId(report.getId());
        formReport.setExercises(exercisesArray);
        formReport.setReports(reportsArray);
        formReport.setDate(report.getDate());
        formReport.setUserId(report.getUserId());
        return ResponseEntity.ok(formReport);
    }

    @PostMapping("/submitReportForm")
    public ResponseEntity<String> processDataFormReport(Authentication authentication, @RequestBody FormReport data) {
        // Обработка данных
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();
        ReportOfWorkout reportOfWorkout = new ReportOfWorkout();
        reportOfWorkout.setUserId(userId);
        reportOfWorkout.setExercises(Arrays.toString(data.getExercises()).replace("[", "").replace("]", ""));
        reportOfWorkout.setReports(Arrays.toString(data.getReports()).replace("[", "").replace("]", ""));
        reportOfWorkout.setDate(data.getDate());
        workoutPlanService.saveReport(reportOfWorkout);
        return ResponseEntity.ok().body("Отчет успешно сохранен");
    }

    @PostMapping("/submitReportForm/{id}")
    public String processDataFormReportId( @RequestBody FormReport data, @PathVariable Long id){

        ReportOfWorkout reportOfWorkout = new ReportOfWorkout();
        reportOfWorkout.setExercises(Arrays.toString(data.getExercises()).replace("[", "").replace("]", ""));
        reportOfWorkout.setReports(Arrays.toString(data.getReports()).replace("[", "").replace("]", ""));
        workoutPlanService.updateReportOfWorkoutById(id, reportOfWorkout.getExercises(), reportOfWorkout.getReports());
        return "redirect:/api/v1/getWorkoutPage/" + data.getUserId();
    }
}