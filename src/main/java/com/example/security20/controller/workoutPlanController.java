package com.example.security20.controller;

import com.example.security20.dto.CustomUserDetails;
import com.example.security20.entity.User;
import com.example.security20.entity.UserPhysicalParameters;
import com.example.security20.entity.WorkoutPlan;
import com.example.security20.service.WorkoutPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1")
public class workoutPlanController {
    @Autowired
    WorkoutPlanService workoutPlanService;
    @GetMapping("/setWorkoutPlan/{userId}")
    public String createPhysicalParameters(@PathVariable("userId") Long id, Model model){
        model.addAttribute("userId", id);
        model.addAttribute("workoutPlan",new WorkoutPlan());
        return "workoutForm";
    }
    @PostMapping("/setWorkoutPlan/{userId}")
    public String createPhysicalParameters(@PathVariable("userId") Long id, @ModelAttribute("workoutPlan") WorkoutPlan workoutPlan) {
        workoutPlan.setUserId(id);
        workoutPlanService.saveWorkoutPlan(workoutPlan);
        return "redirect:/user/" + id;
    }

    @GetMapping("/getWorkoutPage")
    public String getWorkoutPage(Authentication authentication, Model model){
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();
        List<WorkoutPlan> workoutPlans = workoutPlanService.getWorkoutPlansByUserId(userId);
        model.addAttribute("workoutPlans", workoutPlans);
        return "workoutPlanPage";
    }

}
