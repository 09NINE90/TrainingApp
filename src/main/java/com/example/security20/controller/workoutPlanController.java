package com.example.security20.controller;

import com.example.security20.dto.CustomUserDetails;
import com.example.security20.entity.*;
import com.example.security20.service.WorkoutPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/v1")
public class workoutPlanController {
    @Autowired
    WorkoutPlanService workoutPlanService;

    @GetMapping("/setWorkoutPlan/{userId}")
    public String createWorkoutPlan(@PathVariable("userId") Long id, Model model){
        model.addAttribute("userId", id);
        return "workoutForm";
    }
    @GetMapping("/getWorkoutPage")
    public String getWorkoutPage(Authentication authentication, Model model){
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();
        List<WorkoutPlan> workoutPlans2 = workoutPlanService.getWorkoutPlans2ByUserId(userId);
        List<FormWorkoutPlan> formWorkoutPlans = getFormWorkoutPlans(workoutPlans2);
        model.addAttribute("workoutPlans2", formWorkoutPlans);
        return "workoutPlanPage";
    }

    @GetMapping("/getWorkoutPage/{userId}")
    public String getWorkoutPageByUserId(Model model, @PathVariable("userId") Long id){
        model.addAttribute("userId", id);
        List<WorkoutPlan> workoutPlans2 = workoutPlanService.getWorkoutPlans2ByUserId(id);
        List<FormWorkoutPlan> formWorkoutPlans = getFormWorkoutPlans(workoutPlans2);
        model.addAttribute("workoutPlans2", formWorkoutPlans);
        return "workoutPlanPage";
    }

    @GetMapping("/deleteWorkoutPlan/{workoutPlanId}/{userId}")
    public String deleteWorkoutPlan(@PathVariable("userId") Long id, @PathVariable("workoutPlanId") Long workoutPlanId){
        workoutPlanService.deleteWorkoutPlanById(workoutPlanId);
        return "redirect:/api/v1/getWorkoutPage/" + id;
    }

    @GetMapping("/createReport/{workoutPlanId}")
    public String createReport(@PathVariable("workoutPlanId") Long workoutPlanId, Model model){
        model.addAttribute("workoutPlanId", workoutPlanId);
        return "reportForm";
    }

    @GetMapping("/edit/{reportOfWorkoutId}")
    public String editReport(@PathVariable("reportOfWorkoutId") Long reportOfWorkoutId, Model model){
        model.addAttribute("reportOfWorkoutId", reportOfWorkoutId);
        return "editReportForm";
    }

    @GetMapping("/getReports")
    public String getReports(Authentication authentication, Model model) throws InterruptedException {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();
        Thread.sleep(300);
        List<ReportOfWorkout> reportOfWorkoutList = workoutPlanService.getReportOfWorkoutByUserId(userId);
        List<FormReport> formReportList = getFormReports(reportOfWorkoutList);
        model.addAttribute("reportOfWorkoutList", formReportList);
        return "reportOfWorkoutPage";
    }

    @GetMapping("/getReportsForAdmin/{userId}")
    public String getReports(@PathVariable("userId") Long id, Model model){
        List<ReportOfWorkout> reportOfWorkoutList = workoutPlanService.getReportOfWorkoutByUserId(id);
        List<FormReport> formReportList = getFormReports(reportOfWorkoutList);
        model.addAttribute("reportOfWorkoutList", formReportList);
        return "reportOfWorkoutPage";
    }

    private static List<FormWorkoutPlan> getFormWorkoutPlans(List<WorkoutPlan> workoutPlans) {
        List<FormWorkoutPlan> formWorkoutPlans = new ArrayList<>();
        for (WorkoutPlan data : workoutPlans) {
            FormWorkoutPlan fw= new FormWorkoutPlan();
            String[] exercisesArray = data.getExercises().split(",");
            String[] repetitionsArray = data.getRepetitions().split(",");
            fw.setId(data.getId());
            fw.setDayOfWeek(data.getDayOfWeek());
            fw.setExercises(exercisesArray);
            fw.setRepetitions(repetitionsArray);
            fw.setUserId(data.getUserId());
            formWorkoutPlans.add(fw);
        }
        return formWorkoutPlans;
    }
    private static List<FormReport> getFormReports(List<ReportOfWorkout> reportOfWorkoutList) {
        List<FormReport> formReportList = new ArrayList<>();
        for (ReportOfWorkout data : reportOfWorkoutList) {
            FormReport fr = new FormReport();
            String[] exercisesArray = data.getExercises().split(",");
            String[] repetitionsArray = data.getReports().split(",");
            fr.setId(data.getId());
            fr.setExercises(exercisesArray);
            fr.setReports(repetitionsArray);
            fr.setUserId(data.getUserId());
            fr.setDate(data.getDate());
            formReportList.add(fr);
        }
        return formReportList;
    }

}
