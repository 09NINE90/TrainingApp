package com.example.security20.controller;

import com.example.security20.dto.CustomUserDetails;
import com.example.security20.entity.*;
import com.example.security20.service.WorkoutPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1")
public class workoutPlanController {
    @Autowired
    WorkoutPlanService workoutPlanService;
    @GetMapping("/setWorkoutPlan/{userId}")
    public String createWorkoutPlan(@PathVariable("userId") Long id, Model model){
        model.addAttribute("userId", id);
        model.addAttribute("workoutPlan",new WorkoutPlan());
        return "workoutForm";
    }
    @GetMapping("/getWorkoutPage")
    public String getWorkoutPage(Authentication authentication, Model model){
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();
        List<WorkoutPlan> workoutPlans = workoutPlanService.getWorkoutPlansByUserId(userId);
        model.addAttribute("workoutPlans", workoutPlans);
        return "workoutPlanPage";
    }

    @GetMapping("/getWorkoutPage/{userId}")
    public String getWorkoutPageByUserId(Model model, @PathVariable("userId") Long id){
        model.addAttribute("userId", id);
        List<WorkoutPlan> workoutPlans = workoutPlanService.getWorkoutPlansByUserId(id);
        model.addAttribute("workoutPlans", workoutPlans);
        return "workoutPlanPage";
    }
    @PostMapping("/setWorkoutPlan/{userId}")
    public String createWorkoutPlan(@PathVariable("userId") Long id, @ModelAttribute("workoutPlan") WorkoutPlan workoutPlan) {
        workoutPlan.setUserId(id);
        workoutPlanService.saveWorkoutPlan(workoutPlan);
        return "redirect:/api/v1/getWorkoutPage/" + id;
    }
    @GetMapping("/deleteWorkoutPlan/{workoutPlanId}/{userId}")
    public String deleteWorkoutPlan(@PathVariable("userId") Long id, @PathVariable("workoutPlanId") Long workoutPlanId){
        workoutPlanService.deleteWorkoutPlanById(workoutPlanId);
        return "redirect:/api/v1/getWorkoutPage/" + id;
    }

    @GetMapping("/createReport/{workoutPlanId}")
    public String deleteWorkoutPlan(@PathVariable("workoutPlanId") Long workoutPlanId, @ModelAttribute("reportOfWorkoutPlan") ReportOfWorkout reportOfWorkout, Model model){
        WorkoutPlan workoutPlan = workoutPlanService.findWorkoutPlanById(workoutPlanId).get();
        model.addAttribute("exercises1", workoutPlan.getExercises1());
        model.addAttribute("exercises2", workoutPlan.getExercises2());
        model.addAttribute("exercises3", workoutPlan.getExercises3());
        model.addAttribute("exercises4", workoutPlan.getExercises4());
        model.addAttribute("exercises5", workoutPlan.getExercises5());
        model.addAttribute("exercises6", workoutPlan.getExercises6());
        model.addAttribute("reportOfWorkoutPlan", reportOfWorkout);
        model.addAttribute("workoutPlanId", workoutPlanId);
        return "reportForm";
    }

    @PostMapping("/addReport/{workoutPlanId}")
    public String addReport(Authentication authentication, @ModelAttribute("reportOfWorkoutPlan") ReportOfWorkout reportOfWorkout, @PathVariable("workoutPlanId") Long workoutPlanId){
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();
        WorkoutPlan workoutPlan = workoutPlanService.findWorkoutPlanById(workoutPlanId).get();
        reportOfWorkout.setUserId(userId);
        reportOfWorkout.setExercises1(workoutPlan.getExercises1());
        reportOfWorkout.setExercises2(workoutPlan.getExercises2());
        reportOfWorkout.setExercises3(workoutPlan.getExercises3());
        reportOfWorkout.setExercises4(workoutPlan.getExercises4());
        reportOfWorkout.setExercises5(workoutPlan.getExercises5());
        reportOfWorkout.setExercises6(workoutPlan.getExercises6());
        reportOfWorkout.setRepetitions1Plan(workoutPlan.getRepetitions1());
        reportOfWorkout.setRepetitions2Plan(workoutPlan.getRepetitions2());
        reportOfWorkout.setRepetitions3Plan(workoutPlan.getRepetitions3());
        reportOfWorkout.setRepetitions4Plan(workoutPlan.getRepetitions4());
        reportOfWorkout.setRepetitions5Plan(workoutPlan.getRepetitions5());
        reportOfWorkout.setRepetitions6Plan(workoutPlan.getRepetitions6());
        workoutPlanService.saveReport(reportOfWorkout);
        return "redirect:/api/v1/getWorkoutPage";
    }

    @GetMapping("/getReports")
    public String getReports(Authentication authentication, Model model){
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();
        List<ReportOfWorkout> reportOfWorkoutList = workoutPlanService.getReportOfWorkoutByUserId(userId);

        model.addAttribute("reportOfWorkoutList", reportOfWorkoutList);
        return "reportOfWorkoutPage";
    }

    @GetMapping("/getReportsForAdmin/{userId}")
    public String getReports(@PathVariable("userId") Long id, Model model){
        List<ReportOfWorkout> reportOfWorkoutList = workoutPlanService.getReportOfWorkoutByUserId(id);

        model.addAttribute("reportOfWorkoutList", reportOfWorkoutList);
        return "reportOfWorkoutPage";
    }
}
