package com.example.security20.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "report_of_workout")
public class ReportOfWorkout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String exercises1, exercises2, exercises3, exercises4, exercises5, exercises6;
    private String repetitions1Plan, repetitions2Plan, repetitions3Plan, repetitions4Plan, repetitions5Plan, repetitions6Plan;
    private String repetitions1, repetitions2, repetitions3, repetitions4, repetitions5, repetitions6;
    @Column(columnDefinition = "TIMESTAMP USING date::TIMESTAMP(6) WITHOUT TIME ZONE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
}
