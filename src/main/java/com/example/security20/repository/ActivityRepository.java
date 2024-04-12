package com.example.security20.repository;

import com.example.security20.entity.Activity;
import com.example.security20.entity.Nutrition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    @Query(value = "SELECT * FROM activity a WHERE user_id = :userId ORDER BY a.date DESC", nativeQuery = true)
    List<Activity> getActivityByUserId(@Param("userId") Long userId);
}
