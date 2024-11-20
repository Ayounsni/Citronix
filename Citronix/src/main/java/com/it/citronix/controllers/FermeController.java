package com.it.citronix.controllers;

import com.it.citronix.models.dtos.Ferme.CreateFermeDTO;
import com.it.citronix.models.dtos.Ferme.ResponseFermeDTO;
import com.it.citronix.models.dtos.Ferme.UpdateFermeDTO;
import com.it.citronix.models.dtos.Pagination.PageDTO;
import com.it.citronix.models.entities.Ferme;
import com.it.citronix.services.implementations.FermeService;
import com.it.citronix.validation.annotations.Exists;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/ferme")
public class FermeController {
    @Autowired
    private FermeService fermeService;

    @PostMapping
    public ResponseEntity<ResponseFermeDTO> createFerme(@Valid @RequestBody CreateFermeDTO createFermeDTO) {
        ResponseFermeDTO ferme = fermeService.create(createFermeDTO);
        return new ResponseEntity<>(ferme, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseFermeDTO> getFermeById(@Exists(entity = Ferme.class , message = "Cet ferme n'existe pas.")  @PathVariable("id") Long id) {
            ResponseFermeDTO ferme = fermeService.findById(id);
            return new ResponseEntity<>(ferme, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<PageDTO<ResponseFermeDTO>> getAllFermesPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        PageDTO<ResponseFermeDTO> fermes = fermeService.findAll(page, size);
        return new ResponseEntity<>(fermes, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFerme(@Exists(entity = Ferme.class , message = "Cet ferme n'existe pas.") @PathVariable("id") Long id) {
            fermeService.deleteById(id);
            return new ResponseEntity<>("Ferme est supprimé avec succès", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseFermeDTO> updateFerme(@Exists(entity = Ferme.class , message = "Cet ferme n'existe pas.") @PathVariable("id") Long id, @Valid @RequestBody UpdateFermeDTO updateFermeDTO) {

            ResponseFermeDTO updatedFerme = fermeService.update(id, updateFermeDTO);
            return new ResponseEntity<>(updatedFerme, HttpStatus.OK);
    }

}
