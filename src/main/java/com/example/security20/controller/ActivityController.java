package com.example.security20.controller;

import com.example.security20.dto.CustomUserDetails;
import com.example.security20.entity.Activity;
import com.example.security20.entity.Nutrition;
import com.example.security20.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1")
public class ActivityController {
    @Autowired
    ActivityService activityService;
    @GetMapping("/getActivity")
    public String getActivity(Authentication authentication, Model model, @ModelAttribute("activity") Activity activity){
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();
        List<Activity> activityList = activityService.getActivityByUserId(userId);
        model.addAttribute("activityList",activityList);
        return "activityPage";
    }
    @GetMapping("/getActivity/{userId}")
    public String getActivity(Model model, @PathVariable("userId") Long id, @ModelAttribute("activity") Activity activity){
        model.addAttribute("userId", id);
        List<Activity> activityList = activityService.getActivityByUserId(id);
        model.addAttribute("activityList",activityList);
        return "activityPage";
    }

    @GetMapping("/addActivity")
    public void addActivity(Model model){
        model.addAttribute("activity", new Activity());
    }

    @PostMapping("/addActivity")
    public String addActivity(Authentication authentication, @ModelAttribute("activity") Activity activity){
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();
        activity.setUserId(userId);
        activityService.saveActivity(activity);
        return "redirect:/api/v1/getActivity";
    }
    @GetMapping("/deleteActivity/{activityId}")
    public String deleteActivity(@PathVariable("activityId") Long activityId){
        activityService.deleteById(activityId);
        return "redirect:/api/v1/getActivity";
    }
}
