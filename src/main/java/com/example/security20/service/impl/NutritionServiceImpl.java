package com.example.security20.service.impl;

import com.example.security20.entity.Nutrition;
import com.example.security20.entity.NutritionPlan;
import com.example.security20.entity.WeekNutrition;
import com.example.security20.repository.NutritionPlanRepository;
import com.example.security20.repository.NutritionRepository;
import com.example.security20.repository.WeekNutritionRepository;
import com.example.security20.service.NutritionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NutritionServiceImpl implements NutritionService {
    @Autowired
    NutritionRepository nutritionRepository;
    @Autowired
    WeekNutritionRepository weekNutritionRepository;
    @Autowired
    NutritionPlanRepository nutritionPlanRepository;

    @Override
    public void saveNutrition(Nutrition nutrition) {
        nutritionRepository.save(nutrition);
    }

    @Override
    public List<Nutrition> getNutritionByUserId(Long userId) {
        return nutritionRepository.getNutritionByUserId(userId);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        Optional<Nutrition> nutritionOptional = nutritionRepository.findById(id);
        Nutrition nutrition = nutritionOptional.get();
        Optional<WeekNutrition> weekNutritionOptional = weekNutritionRepository.findById(nutrition.getWeekId());
        WeekNutrition weekNutrition = weekNutritionOptional.get();
        double calories = weekNutrition.getSumCalories() - nutrition.getCalories();
        double proteins = weekNutrition.getSumProteins() - nutrition.getProteins();
        double fats = weekNutrition.getSumFats() - nutrition.getFats();
        double carbs = weekNutrition.getSumCarbohydrates() - nutrition.getCarbohydrates();

        updateWeekNutrition(weekNutrition.getUserId(),
                            weekNutrition.getNumDayOfWeek(),
                            weekNutrition.getNumOfWeek(),
                            weekNutrition.getCountDaysOfWeek(),
                            calories,
                            proteins,
                            fats,
                            carbs);
        nutritionRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deletePlanById(Long id) {
        nutritionPlanRepository.deleteById(id);
    }


    @Override
    public WeekNutrition getLastWeekNutritionByUserId(Long userId) {
        return weekNutritionRepository.findLastWeekNutritionByUserId(userId);
    }

    @Transactional
    @Override
    public void updateWeekNutrition(Long userId, int numDayOfWeek, int numOfWeek,int countDaysOfWeek, Double sumCalories, Double sumProteins, Double sumFats, Double sumCarbohydrates) {
        int a = weekNutritionRepository.updateWeekNutrition(userId,
                                                    numDayOfWeek,
                                                    numOfWeek,
                                                    countDaysOfWeek,
                                                    sumCalories,
                                                    sumProteins,
                                                    sumFats,
                                                    sumCarbohydrates);
        System.out.println(a);
    }

    @Override
    public void saveWeekNutrition(WeekNutrition weekNutrition) {
        weekNutritionRepository.save(weekNutrition);
    }

    @Override
    public void saveNutritionPlan(NutritionPlan nutritionPlan) {
        nutritionPlanRepository.save(nutritionPlan);
    }

    @Override
    public NutritionPlan getLastNutritionPlanByUserId(Long userId) {
        return nutritionPlanRepository.findLastNutritionPlanByUserId(userId);
    }

    @Override
    public List<NutritionPlan> getNutritionPlanByUserId(Long userId) {
        return nutritionPlanRepository.findNutritionPlanByUserId(userId);
    }
}
