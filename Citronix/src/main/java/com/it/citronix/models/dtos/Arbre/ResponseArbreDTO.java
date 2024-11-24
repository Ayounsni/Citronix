package com.it.citronix.models.dtos.Arbre;


import com.it.citronix.models.dtos.Champ.EmbeddableChampDTO;
import com.it.citronix.models.dtos.RecolteDetail.EmbeddableRecolteDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseArbreDTO {

    private Long id;

    private EmbeddableChampDTO champ;

    private LocalDate datePlantation;

    private List<EmbeddableRecolteDetailDTO> recolteDetails;

    private String age;

    private double esmtimationProductiviteAnnuel;

}
