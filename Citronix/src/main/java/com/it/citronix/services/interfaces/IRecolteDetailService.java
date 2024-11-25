package com.it.citronix.services.interfaces;

import com.it.citronix.models.dtos.Pagination.PageDTO;
import com.it.citronix.models.dtos.Recolte.CreateRecolteDTO;
import com.it.citronix.models.dtos.Recolte.ResponseRecolteDTO;
import com.it.citronix.models.dtos.Recolte.UpdateRecolteDTO;
import com.it.citronix.models.dtos.RecolteDetail.CreateRecolteDetailDTO;
import com.it.citronix.models.dtos.RecolteDetail.ResponseRecolteDetailDTO;


public interface IRecolteDetailService {

    ResponseRecolteDetailDTO create(CreateRecolteDetailDTO createRecolteDetailDTO);
    void deleteRecolteDetailById(Long RecolteId, Long ArbreId);
    PageDTO<ResponseRecolteDetailDTO> findAll(int page, int size);
    ResponseRecolteDetailDTO getRecolteDetailById(Long RecolteId, Long ArbreId);

}
