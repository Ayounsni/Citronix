package com.it.citronix.controllers;

import com.it.citronix.models.dtos.Recolte.CreateRecolteDTO;
import com.it.citronix.models.dtos.Recolte.ResponseRecolteDTO;
import com.it.citronix.models.dtos.Recolte.UpdateRecolteDTO;
import com.it.citronix.models.dtos.Pagination.PageDTO;
import com.it.citronix.models.entities.Recolte;
import com.it.citronix.services.implementations.RecolteService;
import com.it.citronix.validation.annotations.Exists;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/recolte")
public class RecolteController {
    @Autowired
    private RecolteService recolteService;

    @PostMapping
    public ResponseEntity<ResponseRecolteDTO> createRecolte(@Valid @RequestBody CreateRecolteDTO createRecolteDTO) {
        ResponseRecolteDTO recolte = recolteService.create(createRecolteDTO);
        return new ResponseEntity<>(recolte, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseRecolteDTO> getRecolteById(@Exists(entity = Recolte.class , message = "Cet recolte n'existe pas.")  @PathVariable("id") Long id) {
            ResponseRecolteDTO recolte = recolteService.findById(id);
            return new ResponseEntity<>(recolte, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<PageDTO<ResponseRecolteDTO>> getAllRecoltesPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        PageDTO<ResponseRecolteDTO> recoltes = recolteService.findAll(page, size);
        return new ResponseEntity<>(recoltes, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecolte(@Exists(entity = Recolte.class , message = "Cet recolte n'existe pas.") @PathVariable("id") Long id) {
            recolteService.deleteById(id);
            return new ResponseEntity<>("Recolte est supprimé avec succès", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseRecolteDTO> updateRecolte(@Exists(entity = Recolte.class , message = "Cet recolte n'existe pas.") @PathVariable("id") Long id, @Valid @RequestBody UpdateRecolteDTO updateRecolteDTO) {

            ResponseRecolteDTO updatedRecolte = recolteService.update(id, updateRecolteDTO);
            return new ResponseEntity<>(updatedRecolte, HttpStatus.OK);
    }

}
