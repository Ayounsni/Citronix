package com.it.citronix.controllers;

import com.it.citronix.models.dtos.Champ.CreateChampDTO;
import com.it.citronix.models.dtos.Champ.ResponseChampDTO;
import com.it.citronix.models.dtos.Champ.UpdateChampDTO;
import com.it.citronix.models.dtos.Pagination.PageDTO;
import com.it.citronix.models.entities.Champ;
import com.it.citronix.services.implementations.ChampService;
import com.it.citronix.validation.annotations.Exists;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/champ")
public class ChampController {
    @Autowired
    private ChampService champService;

    @PostMapping
    public ResponseEntity<ResponseChampDTO> createChamp(@Valid @RequestBody CreateChampDTO createChampDTO) {
        ResponseChampDTO champ = champService.create(createChampDTO);
        return new ResponseEntity<>(champ, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseChampDTO> getChampById(@Exists(entity = Champ.class , message = "Cet champ n'existe pas.")  @PathVariable("id") Long id) {
            ResponseChampDTO champ = champService.findById(id);
            return new ResponseEntity<>(champ, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<PageDTO<ResponseChampDTO>> getAllChampsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        PageDTO<ResponseChampDTO> champs = champService.findAll(page, size);
        return new ResponseEntity<>(champs, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteChamp(@Exists(entity = Champ.class , message = "Cet champ n'existe pas.") @PathVariable("id") Long id) {
            champService.deleteById(id);
            return new ResponseEntity<>("Champ est supprimé avec succès", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseChampDTO> updateChamp(@Exists(entity = Champ.class , message = "Cet champ n'existe pas.") @PathVariable("id") Long id, @Valid @RequestBody UpdateChampDTO updateChampDTO) {

            ResponseChampDTO updatedChamp = champService.update(id, updateChampDTO);
            return new ResponseEntity<>(updatedChamp, HttpStatus.OK);
    }

}
