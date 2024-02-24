package com.exampleapp.heroWars.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorDTO {
    private String error;

    public ErrorDTO(String error) {
        this.error = error;
    }
}
