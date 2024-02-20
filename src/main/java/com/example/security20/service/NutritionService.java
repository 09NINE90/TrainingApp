package com.example.security20.service;

import com.example.security20.entity.Nutrition;
import com.example.security20.entity.WorkoutPlan;

import java.util.List;

public interface NutritionService {

    void saveNutrition(Nutrition nutrition);

    List<Nutrition> getNutritionByUserId(Long userId);

    void deleteById(Long id);
}
