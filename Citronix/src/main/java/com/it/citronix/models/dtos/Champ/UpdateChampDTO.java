package com.it.citronix.models.dtos.Champ;


import com.it.citronix.models.entities.Ferme;
import com.it.citronix.validation.annotations.Exists;
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

    private String nom;
    @Exists(entity = Ferme.class, message = "Cette ferme n'existe pas.")
    private Long fermeId;

    private Double superficie;

}
