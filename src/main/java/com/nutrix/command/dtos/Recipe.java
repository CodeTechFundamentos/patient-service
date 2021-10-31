package com.nutrix.command.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    private Integer id;

    private String name;

    private String description;

    private String preparation;

    private String ingredients;

    private Long favorite;

    private Date created_at;

    private Date last_modification;

    private Integer nutritionistId;
}
