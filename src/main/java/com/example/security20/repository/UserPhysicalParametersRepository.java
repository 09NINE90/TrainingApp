package com.example.security20.repository;

import com.example.security20.entity.User;

import com.example.security20.entity.UserPhysicalParameters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserPhysicalParametersRepository extends JpaRepository<UserPhysicalParameters,Long> {
}
