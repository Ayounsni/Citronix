package com.it.citronix.models.dtos.Ferme;


import com.it.citronix.models.dtos.Champ.EmbeddableChampDTO;
import com.it.citronix.models.entities.Champ;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseFermeDTO {

    private Long id;

    private String nom;

    private String localisation;

    private Double superficie;

    private LocalDate dateCreation;

    private List<EmbeddableChampDTO> champs;
}
