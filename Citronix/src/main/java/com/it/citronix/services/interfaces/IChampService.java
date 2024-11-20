package com.it.citronix.services.interfaces;

import com.it.citronix.models.dtos.Champ.CreateChampDTO;
import com.it.citronix.models.dtos.Champ.ResponseChampDTO;
import com.it.citronix.models.dtos.Champ.UpdateChampDTO;


public interface IChampService extends IGenericService<CreateChampDTO, UpdateChampDTO, ResponseChampDTO> {

}
