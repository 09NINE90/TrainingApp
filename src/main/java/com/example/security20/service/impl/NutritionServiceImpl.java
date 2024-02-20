package com.example.security20.service.impl;

import com.example.security20.entity.Nutrition;
import com.example.security20.repository.NutritionRepository;
import com.example.security20.service.NutritionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NutritionServiceImpl implements NutritionService {
    @Autowired
    NutritionRepository nutritionRepository;

    @Override
    public void saveNutrition(Nutrition nutrition) {
        nutritionRepository.save(nutrition);
    }

    @Override
    public List<Nutrition> getNutritionByUserId(Long userId) {
        return nutritionRepository.getNutritionByUserId(userId);
    }

    @Override
    public void deleteById(Long id) {
        nutritionRepository.deleteById(id);
    }
}
