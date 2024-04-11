package com.example.security20.repository;

import com.example.security20.entity.WeekNutrition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface WeekNutritionRepository extends JpaRepository<WeekNutrition,Long> {
//    @Query(value = "SELECT * FROM week_nutrition wn WHERE user_id = :userId ORDER BY wn.num_of_week DESC", nativeQuery = true)
//    List<WeekNutrition> findWeekNutritionByUserId(@Param("userId") Long userId);
//
//    @Query(value = "SELECT * FROM week_nutrition wn WHERE user_id = :userId ORDER BY wn.num_of_week DESC LIMIT 1", nativeQuery = true)
//    WeekNutrition findLastWeekNutritionByUserId(@Param("userId") Long userId);
//
//
//    @Modifying
//    @Query(value = "UPDATE week_nutrition SET count_days_of_week = :countDaysOfWeek, num_day_of_week = :numDayOfWeek, sum_calories = sum_calories + :sumCalories, sum_proteins = sum_proteins + :sumProteins, sum_fats = sum_fats + :sumFats, sum_carbohydrates = sum_carbohydrates + :sumCarbohydrates WHERE user_id = :userId and num_of_week = :numOfWeek", nativeQuery = true)
//    int updateWeekNutrition(@Param("userId") Long userId,
//                             @Param("numDayOfWeek") int numDayOfWeek,
//                             @Param("numOfWeek") int numOfWeek,
//                             @Param("countDaysOfWeek") int countDaysOfWeek,
//                             @Param("sumCalories") Double sumCalories,
//                             @Param("sumProteins") Double sumProteins,
//                             @Param("sumFats") Double sumFats,
//                             @Param("sumCarbohydrates") Double sumCarbohydrates);
    @Query(value = "SELECT * FROM week_nutrition_2 wn WHERE user_id = :userId ORDER BY wn.num_of_week DESC", nativeQuery = true)
    List<WeekNutrition> findWeekNutritionByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT * FROM week_nutrition_2 wn WHERE user_id = :userId and week_start = :startWeek", nativeQuery = true)
    WeekNutrition findLastWeekNutritionByUserIdAndWeekStart(@Param("userId") Long userId,
                                                @Param("startWeek") Date startWeek);

    @Query(value = "SELECT * FROM week_nutrition_2 wn WHERE user_id = :userId ORDER BY wn.week_start DESC LIMIT 1", nativeQuery = true)
    WeekNutrition findLastWeekNutritionByUserId(@Param("userId") Long userId);
    @Modifying
    @Query(value = "UPDATE week_nutrition_2 SET check_days = :checkDays, last_date = :lastDate, count_days_of_week = :countDaysOfWeek, sum_calories = sum_calories + :sumCalories, sum_proteins = sum_proteins + :sumProteins, sum_fats = sum_fats + :sumFats, sum_carbohydrates = sum_carbohydrates + :sumCarbohydrates WHERE user_id = :userId and week_start = :startWeek", nativeQuery = true)
    void updateWeekNutrition(@Param("userId") Long userId,
                            @Param("countDaysOfWeek") int countDaysOfWeek,
                            @Param("lastDate") Date lastDate,
                            @Param("startWeek") Date startWeek,
                            @Param("checkDays") String checkDays,
                            @Param("sumCalories") Double sumCalories,
                            @Param("sumProteins") Double sumProteins,
                            @Param("sumFats") Double sumFats,
                            @Param("sumCarbohydrates") Double sumCarbohydrates);
}
