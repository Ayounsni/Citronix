package com.it.citronix.services.interfaces;

import com.it.citronix.models.dtos.Pagination.PageDTO;

import java.util.List;

public interface IGenericService<CreateDTO, UpdateDTO, ResponseDTO> {
    ResponseDTO create(CreateDTO createDTO);
    ResponseDTO findById(Long id);
    PageDTO<ResponseDTO> findAll(int page, int size);
    void deleteById(Long id);
    ResponseDTO update(Long id, UpdateDTO updateDTO);
}

