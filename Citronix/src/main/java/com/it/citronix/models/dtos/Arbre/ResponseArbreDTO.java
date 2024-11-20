package com.it.citronix.models.dtos.Arbre;


import com.it.citronix.models.entities.Champ;
import com.it.citronix.models.entities.RecolteDetail;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseArbreDTO {

    private Integer id;

    private Champ champ;

    private LocalDate datePlantation;

    private List<RecolteDetail> recolteDetails;

}
