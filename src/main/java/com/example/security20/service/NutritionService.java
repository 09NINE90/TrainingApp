package com.example.security20.service;

import com.example.security20.entity.Nutrition;
import com.example.security20.entity.NutritionPlan;
import com.example.security20.entity.WeekNutrition;
import com.example.security20.entity.WorkoutPlan;
import jakarta.transaction.Transactional;

import java.util.Date;
import java.util.List;

public interface NutritionService {

    void saveNutrition(Nutrition nutrition);

    List<Nutrition> getNutritionByUserId(Long userId);

//    void deleteById(Long id);
    void deletePlanById(Long id);

    WeekNutrition findLastWeekNutritionByUserIdAndWeekStart(Long userId, Date startWeek);

    WeekNutrition findLastWeekNutritionByUserId(Long userId);

//    void updateWeekNutrition(Long userId,
//                             int numOfWeek,
//                             int countDaysOfWeek,
//                             Date lastDate,
//                             Double sumCalories,
//                             Double sumProteins,
//                             Double sumFats,
//                             Double sumCarbohydrates);

    void updateWeekNutrition(Long userId, int countDaysOfWeek, Date lastDate, Date startWeek, String checkDays, Double sumCalories, Double sumProteins, Double sumFats, Double sumCarbohydrates);

    void saveWeekNutrition(WeekNutrition weekNutrition);
    void saveNutritionPlan(NutritionPlan nutritionPlan);
    NutritionPlan getLastNutritionPlanByUserId(Long userId);
    List<NutritionPlan> getNutritionPlanByUserId(Long userId);
}
