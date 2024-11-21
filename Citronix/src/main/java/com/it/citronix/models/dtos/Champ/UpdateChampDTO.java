package com.it.citronix.models.dtos.Champ;


import com.it.citronix.models.entities.Champ;
import com.it.citronix.models.entities.Ferme;
import com.it.citronix.validation.annotations.Exists;
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
public class UpdateChampDTO {

    @Unique(entity = Champ.class, field = "nom")
    private String nom;

    @Exists(entity = Ferme.class, message = "Cette ferme n'existe pas.")
    private Long fermeId;

    @DecimalMin(value = "1000.0", message = "La superficie doit être supérieure à 1000 m².")
    private Double superficie;

}
