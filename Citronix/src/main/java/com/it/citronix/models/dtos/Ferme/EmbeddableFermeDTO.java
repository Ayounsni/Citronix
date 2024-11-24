package com.it.citronix.models.dtos.Ferme;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmbeddableFermeDTO {
    private Long id;

    private String nom;

    private String localisation;

    private Double superficie;

    private LocalDate dateCreation;

}
