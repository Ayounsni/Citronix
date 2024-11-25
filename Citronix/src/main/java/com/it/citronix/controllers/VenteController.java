package com.it.citronix.controllers;

import com.it.citronix.models.dtos.Vente.CreateVenteDTO;
import com.it.citronix.models.dtos.Vente.ResponseVenteDTO;
import com.it.citronix.models.dtos.Vente.UpdateVenteDTO;
import com.it.citronix.models.dtos.Pagination.PageDTO;
import com.it.citronix.models.entities.Vente;
import com.it.citronix.services.implementations.VenteService;
import com.it.citronix.services.interfaces.IVenteService;
import com.it.citronix.validation.annotations.Exists;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/vente")
public class VenteController {
    @Autowired
    private IVenteService venteService;

    @PostMapping
    public ResponseEntity<ResponseVenteDTO> createVente(@Valid @RequestBody CreateVenteDTO createVenteDTO) {
        ResponseVenteDTO vente = venteService.create(createVenteDTO);
        return new ResponseEntity<>(vente, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseVenteDTO> getVenteById(@Exists(entity = Vente.class , message = "Cette vente n'existe pas.")  @PathVariable("id") Long id) {
            ResponseVenteDTO vente = venteService.findById(id);
            return new ResponseEntity<>(vente, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<PageDTO<ResponseVenteDTO>> getAllVentesPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        PageDTO<ResponseVenteDTO> ventes = venteService.findAll(page, size);
        return new ResponseEntity<>(ventes, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVente(@Exists(entity = Vente.class , message = "Cette vente n'existe pas.") @PathVariable("id") Long id) {
            venteService.deleteById(id);
            return new ResponseEntity<>("Vente est supprimé avec succès", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseVenteDTO> updateVente(@Exists(entity = Vente.class , message = "Cette vente n'existe pas.") @PathVariable("id") Long id, @Valid @RequestBody UpdateVenteDTO updateVenteDTO) {

            ResponseVenteDTO updatedVente = venteService.update(id, updateVenteDTO);
            return new ResponseEntity<>(updatedVente, HttpStatus.OK);
    }

}
