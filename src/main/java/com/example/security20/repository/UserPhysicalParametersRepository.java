package com.example.security20.repository;

import com.example.security20.entity.UserPhysicalParameters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPhysicalParametersRepository extends JpaRepository<UserPhysicalParameters,Long> {
    @Query(value = "SELECT * FROM user_physical_parameters u WHERE user_id = :userId ORDER BY u.id DESC", nativeQuery = true)
    List<UserPhysicalParameters> findPhysicalParametersByUserId(@Param("userId") Long userId);

}
