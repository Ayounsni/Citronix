package com.it.citronix.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Vente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private LocalDate date;

    @ManyToOne
    private Recolte recolte;

    @NotNull
    private Double prixUnitaire;

    @NotNull
    private Double quantiteDemande;

    @NotBlank
    private String clientName;
}
