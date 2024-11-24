package com.it.citronix.models.dtos.RecolteDetail;


import com.it.citronix.models.embeddableId.RecolteDetailId;
import com.it.citronix.models.enums.Saison;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRecolteDetailDTO {

    private Long arbreId;

    private Long recolteId;

    @NotNull
    @Min(0)
    private Double quantiteRecolte;

}
