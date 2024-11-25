package com.it.citronix;


import com.it.citronix.models.dtos.Vente.CreateVenteDTO;
import com.it.citronix.models.entities.Vente;
import com.it.citronix.models.entities.Recolte;
import com.it.citronix.models.mappers.VenteMapper;
import com.it.citronix.repository.RecolteRepository;
import com.it.citronix.repository.VenteRepository;
import com.it.citronix.services.implementations.VenteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class VenteServiceTest {

    @Mock
    private VenteRepository venteRepository;

    @Mock
    private RecolteRepository recolteRepository;

    @InjectMocks
    private VenteService venteService;
    private Recolte recolte;
    private CreateVenteDTO createVenteDTO;
    private Vente vente;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        recolte = new Recolte();
        recolte.setId(1L);
        recolte.setQuantiteRestante(100.0);
        recolte.setDateRecolte(LocalDate.of(2024, 11, 1));

        createVenteDTO = new CreateVenteDTO();
        createVenteDTO.setRecolteId(1L);
        createVenteDTO.setQuantiteDemande(50.0);
        createVenteDTO.setDate(LocalDate.of(2024, 11, 2));

        vente = new Vente();
        vente.setId(1L);
        vente.setQuantiteDemande(50.0);
    }

    @Test
    public void testCreateVenteWithInsufficientQuantity() {
        createVenteDTO.setQuantiteDemande(150.0);
        when(recolteRepository.findById(1L)).thenReturn(Optional.of(recolte));

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            venteService.create(createVenteDTO);
        });
        assertEquals("La quantité demandée (150.0) dépasse la quantité restante disponible (100.0).", exception.getMessage());
    }

    @Test
    public void testCreateVenteWithDateBeforeRecolte() {
        createVenteDTO.setDate(LocalDate.of(2024, 10, 31));
        when(recolteRepository.findById(1L)).thenReturn(Optional.of(recolte));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            venteService.create(createVenteDTO);
        });
        assertEquals("La date de vente ne peut pas être antérieure à la date de récolte (2024-11-01).", exception.getMessage());
    }

    @Test
    public void testDeleteVenteSuccess() {
        when(venteRepository.findById(1L)).thenReturn(Optional.of(vente));
        when(recolteRepository.findById(1L)).thenReturn(Optional.of(recolte));

        venteService.deleteById(1L);

        verify(venteRepository).deleteById(1L);
        verify(recolteRepository).save(recolte);
    }
}

