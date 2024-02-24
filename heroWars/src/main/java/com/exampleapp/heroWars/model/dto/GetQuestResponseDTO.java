package com.exampleapp.heroWars.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetQuestResponseDTO {
    private String questDescription;
    private Integer monsterId;
}
