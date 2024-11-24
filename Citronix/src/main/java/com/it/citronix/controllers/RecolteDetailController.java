package com.it.citronix.controllers;


import com.it.citronix.models.dtos.RecolteDetail.CreateRecolteDetailDTO;
import com.it.citronix.models.dtos.RecolteDetail.ResponseRecolteDetailDTO;
import com.it.citronix.services.implementations.RecolteDetailService;
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
    private RecolteDetailService recolteDetailService;

    @PostMapping
    public ResponseEntity<ResponseRecolteDetailDTO> createRecolteDetail(@Valid @RequestBody CreateRecolteDetailDTO createRecolteDetailDTO) {
        ResponseRecolteDetailDTO recolteDetail = recolteDetailService.create(createRecolteDetailDTO);
        return new ResponseEntity<>(recolteDetail, HttpStatus.OK);
    }
}
