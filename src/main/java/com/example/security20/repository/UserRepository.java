package com.example.security20.repository;

import com.example.security20.entity.Nutrition;
import com.example.security20.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String username);

    Optional<User> findById(Long id);

    @Query(value = "SELECT * FROM security_user su WHERE roles = :role AND trainer_id =:Tid ORDER BY su.id DESC", nativeQuery = true)
    List<User> findUserByRoles(@Param("role") String role, @Param("Tid") Long Tid);

}
