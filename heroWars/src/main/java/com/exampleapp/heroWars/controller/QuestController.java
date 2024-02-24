package com.exampleapp.heroWars.controller;

import com.exampleapp.heroWars.model.dto.BattleResultDTO;
import com.exampleapp.heroWars.model.dto.GetQuestResponseDTO;
import com.exampleapp.heroWars.service.QuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/quest/")
@RequiredArgsConstructor
public class QuestController {

    private final QuestService questService;

    @GetMapping("/newQuest")
    public ResponseEntity<GetQuestResponseDTO> getNewQuest(){
        return ResponseEntity.ok(questService.getNewQuest());
    }

    @GetMapping("/battle/{id}")
    public ResponseEntity<BattleResultDTO> battle(
            @PathVariable int id
    ){
        return ResponseEntity.ok(questService.battle(id));
    }
}

