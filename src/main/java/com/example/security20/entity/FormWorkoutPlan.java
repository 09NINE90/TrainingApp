package com.example.security20.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data

public class FormWorkoutPlan {

    private Long id;
    private String dayOfWeek;
    private String[] exercises;
    private String[] repetitions;
    private Long userId;
}
