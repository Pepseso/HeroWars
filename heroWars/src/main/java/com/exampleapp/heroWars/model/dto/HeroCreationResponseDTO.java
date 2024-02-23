package com.exampleapp.heroWars.model.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HeroCreationResponseDTO {

    private String name;
    private Integer hp;
    private Integer level;
    private Integer experiencePoints;
    private Integer gold;
}
