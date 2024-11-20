package com.it.citronix.models.dtos.Arbre;


import com.it.citronix.models.entities.Champ;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateArbreDTO {

    @NotNull
    private Long champId;

    @NotNull
    private LocalDate datePlantation;

}
