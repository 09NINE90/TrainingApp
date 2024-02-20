package com.example.security20.repository;

import com.example.security20.entity.Nutrition;
import com.example.security20.entity.WorkoutPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NutritionRepository extends JpaRepository<Nutrition,Long> {
    @Query(value = "SELECT * FROM nutrition n WHERE user_id = :userId ORDER BY n.id DESC", nativeQuery = true)
    List<Nutrition> getNutritionByUserId(@Param("userId") Long userId);
}
