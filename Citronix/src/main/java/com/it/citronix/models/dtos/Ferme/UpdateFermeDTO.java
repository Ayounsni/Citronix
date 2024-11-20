package com.it.citronix.models.dtos.Ferme;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFermeDTO {

    private String nom;

    private String localisation;

    private Double superficie;

    private LocalDate dateCreation;

}
