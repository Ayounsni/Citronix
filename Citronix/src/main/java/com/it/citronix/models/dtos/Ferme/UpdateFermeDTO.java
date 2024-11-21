package com.it.citronix.models.dtos.Ferme;


import com.it.citronix.models.entities.Ferme;
import com.it.citronix.validation.annotations.Unique;
import jakarta.validation.constraints.DecimalMin;
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

    @Unique(entity = Ferme.class, field = "nom")
    private String nom;

    private String localisation;

    @DecimalMin(value = "2000.0", message = "La superficie doit être supérieure à 2000 m².")
    private Double superficie;

    private LocalDate dateCreation;

}
