package com.example.security20.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserPhysicalParametersDTO {
    private int age; // Возраст

    private double weight; // Вес

    private double height; // Рост

    private double armCircumference; //Обхват рук

    private double hipCircumference; //Обхват бедер

    private double waistCircumference; //Обхват талии

    private String date; //Дата обновления параметров
}
