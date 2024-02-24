package com.exampleapp.heroWars.model.dto;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class BattleResultDTO {
    private String startOfBattle;
    private String battleLog;
    private String battleResults;

}
