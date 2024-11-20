package com.it.citronix.controllers;

import com.it.citronix.models.dtos.Arbre.CreateArbreDTO;
import com.it.citronix.models.dtos.Arbre.ResponseArbreDTO;
import com.it.citronix.models.dtos.Arbre.UpdateArbreDTO;
import com.it.citronix.models.dtos.Pagination.PageDTO;
import com.it.citronix.models.entities.Arbre;
import com.it.citronix.services.implementations.ArbreService;
import com.it.citronix.validation.annotations.Exists;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/arbre")
public class ArbreController {
    @Autowired
    private ArbreService arbreService;

    @PostMapping
    public ResponseEntity<ResponseArbreDTO> createArbre(@Valid @RequestBody CreateArbreDTO createArbreDTO) {
        ResponseArbreDTO arbre = arbreService.create(createArbreDTO);
        return new ResponseEntity<>(arbre, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseArbreDTO> getArbreById(@Exists(entity = Arbre.class , message = "Cet arbre n'existe pas.")  @PathVariable("id") Long id) {
            ResponseArbreDTO arbre = arbreService.findById(id);
            return new ResponseEntity<>(arbre, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<PageDTO<ResponseArbreDTO>> getAllArbresPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        PageDTO<ResponseArbreDTO> arbres = arbreService.findAll(page, size);
        return new ResponseEntity<>(arbres, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArbre(@Exists(entity = Arbre.class , message = "Cet arbre n'existe pas.") @PathVariable("id") Long id) {
            arbreService.deleteById(id);
            return new ResponseEntity<>("Arbre est supprimé avec succès", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseArbreDTO> updateArbre(@Exists(entity = Arbre.class , message = "Cet arbre n'existe pas.") @PathVariable("id") Long id, @Valid @RequestBody UpdateArbreDTO updateArbreDTO) {

            ResponseArbreDTO updatedArbre = arbreService.update(id, updateArbreDTO);
            return new ResponseEntity<>(updatedArbre, HttpStatus.OK);
    }

}
