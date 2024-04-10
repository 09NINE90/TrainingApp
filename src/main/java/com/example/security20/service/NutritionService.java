package com.example.security20.service;

import com.example.security20.entity.Nutrition;
import com.example.security20.entity.NutritionPlan;
import com.example.security20.entity.WeekNutrition;
import com.example.security20.entity.WorkoutPlan;

import java.util.List;

public interface NutritionService {

    void saveNutrition(Nutrition nutrition);

    List<Nutrition> getNutritionByUserId(Long userId);

    void deleteById(Long id);
    void deletePlanById(Long id);

    WeekNutrition getLastWeekNutritionByUserId(Long userId);

    void updateWeekNutrition(Long userId,
                             int numDayOfWeek,
                             int numOfWeek,
                             int countDaysOfWeek,
                             Double sumCalories,
                             Double sumProteins,
                             Double sumFats,
                             Double sumCarbohydrates);
    void saveWeekNutrition(WeekNutrition weekNutrition);
    void saveNutritionPlan(NutritionPlan nutritionPlan);
    NutritionPlan getLastNutritionPlanByUserId(Long userId);
    List<NutritionPlan> getNutritionPlanByUserId(Long userId);
}
