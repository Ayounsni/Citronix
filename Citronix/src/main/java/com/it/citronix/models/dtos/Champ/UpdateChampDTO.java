package com.it.citronix.models.dtos.Champ;


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

    private Long fermeId;

    private Double superficie;

}
