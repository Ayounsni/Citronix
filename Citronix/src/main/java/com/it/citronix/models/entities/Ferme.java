package com.it.citronix.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Ferme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nom;

    @NotBlank
    private String localisation;

    @NotNull
    private Double superficie;

    @NotNull
    private LocalDate dateCreation;

    @OneToMany(mappedBy = "ferme", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Champ> champs;
}
