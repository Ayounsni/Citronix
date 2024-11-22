package com.it.citronix.models.entities;

import com.it.citronix.validation.annotations.Unique;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Champ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Ferme ferme;

    @NotNull
    private Double superficie;

    @OneToMany(mappedBy = "champ", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Arbre> arbres;

}
