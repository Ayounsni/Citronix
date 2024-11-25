package com.it.citronix.controllers;


import com.it.citronix.models.dtos.Pagination.PageDTO;
import com.it.citronix.models.dtos.RecolteDetail.CreateRecolteDetailDTO;
import com.it.citronix.models.dtos.RecolteDetail.ResponseRecolteDetailDTO;
import com.it.citronix.services.implementations.RecolteDetailService;
import com.it.citronix.services.interfaces.IRecolteDetailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/recolteDetail")
public class RecolteDetailController {
    @Autowired
    private IRecolteDetailService recolteDetailService;

    @PostMapping
    public ResponseEntity<ResponseRecolteDetailDTO> createRecolteDetail(@Valid @RequestBody CreateRecolteDetailDTO createRecolteDetailDTO) {
        ResponseRecolteDetailDTO recolteDetail = recolteDetailService.create(createRecolteDetailDTO);
        return new ResponseEntity<>(recolteDetail, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PageDTO<ResponseRecolteDetailDTO>> getAllRecolteDetailsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        PageDTO<ResponseRecolteDetailDTO> recolteDetails = recolteDetailService.findAll(page, size);
        return new ResponseEntity<>(recolteDetails, HttpStatus.OK);
    }

    @GetMapping("/{recolteId}/{arbreId}")
    public ResponseEntity<ResponseRecolteDetailDTO> getRecolteDetailById(@PathVariable("recolteId") Long recolteId, @PathVariable("arbreId") Long arbreId) {
        ResponseRecolteDetailDTO recolteDetail = recolteDetailService.getRecolteDetailById(recolteId, arbreId);
        return new ResponseEntity<>(recolteDetail, HttpStatus.OK);

    }

    @DeleteMapping("/{recolteId}/{arbreId}")
    public ResponseEntity<String> deleteRecolteDetail(@PathVariable("recolteId") Long recolteId, @PathVariable("arbreId") Long arbreId) {
        recolteDetailService.deleteRecolteDetailById(recolteId, arbreId);
        return new ResponseEntity<>("La recolteDetaile a été supprimée avec succès", HttpStatus.OK);

    }


}
