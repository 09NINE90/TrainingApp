package com.example.security20.repository;

import com.example.security20.entity.WorkoutPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlan,Long> {
    @Query(value = "SELECT * FROM workout_plan w WHERE user_id = :userId ORDER BY w.id DESC", nativeQuery = true)
    List<WorkoutPlan> getWorkoutPlansByUserId(@Param("userId") Long userId);
}
