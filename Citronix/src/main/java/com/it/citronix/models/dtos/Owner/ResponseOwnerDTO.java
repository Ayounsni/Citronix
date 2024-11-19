package com.it.itlens.models.dtos.Owner;

import com.it.itlens.models.dtos.Survey.EmbeddableSurveyDTO;
import com.it.itlens.models.entities.Owner;
import com.it.itlens.models.entities.Survey;
import com.it.itlens.validation.annotations.Unique;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseOwnerDTO {
    private Long id;
    private String name;
    private List<EmbeddableSurveyDTO> surveys ;
}
