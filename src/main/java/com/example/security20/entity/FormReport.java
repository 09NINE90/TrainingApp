package com.example.security20.entity;

import lombok.Data;

import java.util.Date;

@Data
public class FormReport {
    private Long id;
    private String[] exercises;
    private String[] reports;
    private Date date;
    private Long userId;
}
