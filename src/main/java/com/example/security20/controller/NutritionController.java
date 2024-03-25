package com.example.security20.controller;

import com.example.security20.dto.CustomUserDetails;
import com.example.security20.entity.Nutrition;
import com.example.security20.service.NutritionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1")
public class NutritionController {
    @Autowired
    NutritionService nutritionService;

    @GetMapping("/deleteNutrition/{nutritionId}")
    public String deleteNutrition(@PathVariable("nutritionId") Long nutritionId){
        nutritionService.deleteById(nutritionId);
        return "redirect:/api/v1/getNutritionPage";
    }
    @GetMapping("/getNutritionPage")
    public String getWorkoutPage(Authentication authentication, Model model, @ModelAttribute("nutrition") Nutrition nutrition){
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();
        List<Nutrition> nutritionList = nutritionService.getNutritionByUserId(userId);
        model.addAttribute("nutritionList", nutritionList);
        return "nutritionPage";
    }

    @GetMapping("/getNutritionPage/{userId}")
    public String getWorkoutPage(Model model, @PathVariable("userId") Long id, @ModelAttribute("nutrition") Nutrition nutrition){
        model.addAttribute("userId", id);
        List<Nutrition> nutritionList = nutritionService.getNutritionByUserId(id);
        model.addAttribute("nutritionList", nutritionList);
        return "nutritionPage";
    }
    @GetMapping("/addNutrition")
    public void addNutrition(Model model){
        model.addAttribute("nutrition",new Nutrition());
    }
    @PostMapping("/addNutrition")
    public String addNutrition(Authentication authentication, @ModelAttribute("nutrition") Nutrition nutrition){
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();
        nutrition.setUserId(userId);
        nutrition.setCalories(nutrition.getCalories() * nutrition.getWeight() / 100);
        nutrition.setProteins(nutrition.getProteins() * nutrition.getWeight() / 100);
        nutrition.setFats(nutrition.getFats() * nutrition.getWeight() / 100);
        nutrition.setCarbohydrates(nutrition.getCarbohydrates() * nutrition.getWeight() / 100);
        nutrition.setDate(nutrition.getDate().replace("T","  "));
        nutritionService.saveNutrition(nutrition);
        return "redirect:/api/v1/getNutritionPage";
    }
}
