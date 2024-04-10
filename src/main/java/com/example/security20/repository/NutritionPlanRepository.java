package com.example.security20.repository;

import com.example.security20.entity.NutritionPlan;
import com.example.security20.entity.WeekNutrition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NutritionPlanRepository extends JpaRepository<NutritionPlan,Long> {

    @Query(value = "SELECT * FROM nutrition_plan np WHERE user_id = :userId ORDER BY np.id DESC LIMIT 1", nativeQuery = true)
    NutritionPlan findLastNutritionPlanByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT * FROM nutrition_plan np WHERE user_id = :userId ORDER BY np.id DESC", nativeQuery = true)
    List<NutritionPlan> findNutritionPlanByUserId(@Param("userId") Long userId);
}
