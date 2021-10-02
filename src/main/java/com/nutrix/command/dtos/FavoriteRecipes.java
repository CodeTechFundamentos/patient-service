package com.nutrix.command.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteRecipes {

    private Integer id;

    private Integer patientId;

    private Recipe recipe;
}