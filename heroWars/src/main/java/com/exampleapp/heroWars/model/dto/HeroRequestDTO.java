package com.exampleapp.heroWars.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HeroRequestDTO {
    private String name;
}
