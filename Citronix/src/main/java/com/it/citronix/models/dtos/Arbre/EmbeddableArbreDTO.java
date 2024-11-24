package com.it.citronix.models.dtos.Arbre;


import com.it.citronix.models.entities.Champ;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmbeddableArbreDTO {

    private Long id;

    private LocalDate datePlantation;

}
