package com.it.citronix.repository;

import com.it.citronix.models.entities.Ferme;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FermeRepository extends JpaRepository<Ferme, Long>, JpaSpecificationExecutor<Ferme> {

}
