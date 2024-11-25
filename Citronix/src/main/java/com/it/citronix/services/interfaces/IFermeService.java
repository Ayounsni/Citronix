package com.it.citronix.services.interfaces;

import com.it.citronix.models.dtos.Ferme.CreateFermeDTO;
import com.it.citronix.models.dtos.Ferme.ResponseFermeDTO;
import com.it.citronix.models.dtos.Ferme.SearchFermeDTO;
import com.it.citronix.models.dtos.Ferme.UpdateFermeDTO;

import java.util.List;


public interface IFermeService extends IGenericService<CreateFermeDTO, UpdateFermeDTO, ResponseFermeDTO> {

    List<ResponseFermeDTO> rechercherFermes(String nom, String localisation);
}
