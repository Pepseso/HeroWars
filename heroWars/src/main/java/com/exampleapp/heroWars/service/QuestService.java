package com.exampleapp.heroWars.service;

import com.exampleapp.heroWars.exception.CannotGenerateMonsterException;
import com.exampleapp.heroWars.exception.MonsterNotFoundException;
import com.exampleapp.heroWars.exception.QuestsFileReadingErrorException;
import com.exampleapp.heroWars.model.dto.BattleResultDTO;
import com.exampleapp.heroWars.model.dto.GetQuestResponseDTO;
import com.exampleapp.heroWars.model.monster.Dragon;
import com.exampleapp.heroWars.model.monster.Monster;
import com.exampleapp.heroWars.model.monster.Orc;
import com.exampleapp.heroWars.model.monster.Werewolf;
import com.exampleapp.heroWars.repository.MonsterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Service
@RequiredArgsConstructor
public class QuestService {

    private final ResourceLoader resourceLoader;
    private final MonsterRepository monsterRepository;


    private String getRandomQuest() {
        try {
            Resource resource = resourceLoader.getResource("classpath:static/quests.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));

            List<String> questDescriptions = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                questDescriptions.add(line);
            }
            reader.close();

            // Check if the list is empty
            if (questDescriptions.isEmpty()) {
                throw new QuestsFileReadingErrorException();
            }

            // Pick a random quest description
            Random random = new Random();

            return questDescriptions.get(random.nextInt(questDescriptions.size()));
        } catch (IOException e) {
            throw new QuestsFileReadingErrorException();
        }
    }

    public GetQuestResponseDTO getNewQuest() {
        Monster generatedMonster = generateRandomMonster();
        monsterRepository.save(generatedMonster);
        return GetQuestResponseDTO
                .builder()
                .questDescription(getRandomQuest())
                .monsterId(generatedMonster.getId())
                .build();

    }

    private Monster generateRandomMonster() {
        Random random = new Random();
        int randomNumber = random.nextInt(3);

        return switch (randomNumber) {
            case 0 -> new Dragon();
            case 1 -> new Orc();
            case 2 -> new Werewolf();
            default -> throw new CannotGenerateMonsterException();
        };
    }

    public BattleResultDTO battle(int id) {
        Monster monster = monsterRepository.findById(id)
                .orElseThrow(MonsterNotFoundException::new);

        return null;
    }
}
