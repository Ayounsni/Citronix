package com.it.citronix.models.dtos.Champ;


import com.it.citronix.models.entities.Ferme;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmbeddableChampDTO {

    private Integer id;

    private String nom;

    private Double superficie;

}
