package com.it.citronix;

import com.it.citronix.models.dtos.Ferme.CreateFermeDTO;
import com.it.citronix.models.dtos.Ferme.ResponseFermeDTO;
import com.it.citronix.models.dtos.Ferme.UpdateFermeDTO;
import com.it.citronix.models.dtos.Pagination.PageDTO;
import com.it.citronix.models.entities.Ferme;
import com.it.citronix.models.mappers.GenericMapper;
import com.it.citronix.services.implementations.GenericService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GenericServiceTest {

    @Mock
    private JpaRepository<Ferme, Long> repository;

    @Mock
    private GenericMapper<Ferme, CreateFermeDTO, UpdateFermeDTO, ResponseFermeDTO> mapper;

    @InjectMocks
    private GenericService<Ferme, CreateFermeDTO, UpdateFermeDTO, ResponseFermeDTO> genericService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate_validData() {
        CreateFermeDTO createDTO = new CreateFermeDTO();
        Ferme ownerEntity = new Ferme();
        ResponseFermeDTO responseDTO = new ResponseFermeDTO();

        when(mapper.toEntity(createDTO)).thenReturn(ownerEntity);
        when(repository.save(ownerEntity)).thenReturn(ownerEntity);
        when(mapper.toDTO(ownerEntity)).thenReturn(responseDTO);

        ResponseFermeDTO result = genericService.create(createDTO);

        assertNotNull(result);
        assertEquals(responseDTO, result);
        verify(repository, times(1)).save(ownerEntity);
        verify(mapper, times(1)).toDTO(ownerEntity);
    }

    @Test
    void testCreate_nullData() {
        CreateFermeDTO createDTO = null;

        assertThrows(NullPointerException.class, () -> {
            genericService.create(createDTO);
        });
    }

    @Test
    void testFindById_validId() {
        Long id = 1L;
        Ferme ownerEntity = new Ferme();
        ResponseFermeDTO responseDTO = new ResponseFermeDTO();

        when(repository.findById(id)).thenReturn(Optional.of(ownerEntity));
        when(mapper.toDTO(ownerEntity)).thenReturn(responseDTO);

        ResponseFermeDTO result = genericService.findById(id);

        assertNotNull(result);
        assertEquals(responseDTO, result);
        verify(repository, times(1)).findById(id);
    }

    @Test
    void testFindById_invalidId() {
        Long id = 999L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            genericService.findById(id);
        });
    }

    @Test
    void testFindAll_validPagination() {
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(page, size);
        Ferme ownerEntity = new Ferme();
        ResponseFermeDTO responseDTO = new ResponseFermeDTO();
        Page<Ferme> pagedResult = new PageImpl<>(List.of(ownerEntity));

        when(repository.findAll(pageRequest)).thenReturn(pagedResult);
        when(mapper.toDTO(ownerEntity)).thenReturn(responseDTO);

        PageDTO<ResponseFermeDTO> result = genericService.findAll(page, size);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals(responseDTO, result.getContent().get(0));
        verify(repository, times(1)).findAll(pageRequest);
    }

    @Test
    void testFindAll_emptyPage() {
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Ferme> emptyPage = Page.empty();

        when(repository.findAll(pageRequest)).thenReturn(emptyPage);

        PageDTO<ResponseFermeDTO> result = genericService.findAll(page, size);

        assertNotNull(result);
        assertTrue(result.getContent().isEmpty());
        verify(repository, times(1)).findAll(pageRequest);
    }

    @Test
    void testDeleteById_validId() {
        Long id = 1L;

        genericService.deleteById(id);

        verify(repository, times(1)).deleteById(id);
    }

    @Test
    void testDeleteById_invalidId() {
        Long id = 999L;

        doThrow(new RuntimeException("Entity not found")).when(repository).deleteById(id);

        assertThrows(RuntimeException.class, () -> {
            genericService.deleteById(id);
        });
    }

    @Test
    void testUpdate_validData() {
        Long id = 1L;
        UpdateFermeDTO updateDTO = new UpdateFermeDTO();
        Ferme existingFerme = new Ferme();
        Ferme updatedFerme = new Ferme();
        ResponseFermeDTO responseDTO = new ResponseFermeDTO();

        when(repository.findById(id)).thenReturn(Optional.of(existingFerme));
        when(mapper.updateEntityFromDTO(updateDTO, existingFerme)).thenReturn(updatedFerme);
        when(repository.save(updatedFerme)).thenReturn(updatedFerme);
        when(mapper.toDTO(updatedFerme)).thenReturn(responseDTO);

        ResponseFermeDTO result = genericService.update(id, updateDTO);

        assertNotNull(result);
        assertEquals(responseDTO, result);
        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(updatedFerme);
    }

    @Test
    void testUpdate_invalidId() {
        Long id = 999L;
        UpdateFermeDTO updateDTO = new UpdateFermeDTO();

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            genericService.update(id, updateDTO);
        });
    }

    @Test
    void testUpdate_nullData() {
        Long id = 1L;
        UpdateFermeDTO updateDTO = null;

        assertThrows(NullPointerException.class, () -> {
            genericService.update(id, updateDTO);
        });
    }
}
