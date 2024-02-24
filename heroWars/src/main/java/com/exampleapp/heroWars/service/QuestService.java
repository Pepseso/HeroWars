package com.exampleapp.heroWars.service;

import com.exampleapp.heroWars.exception.CannotGenerateMonsterException;
import com.exampleapp.heroWars.exception.MonsterNotFoundException;
import com.exampleapp.heroWars.exception.QuestsFileReadingErrorException;
import com.exampleapp.heroWars.model.Hero;
import com.exampleapp.heroWars.model.dto.BattleResultDTO;
import com.exampleapp.heroWars.model.dto.GetQuestResponseDTO;
import com.exampleapp.heroWars.model.monster.Dragon;
import com.exampleapp.heroWars.model.monster.Monster;
import com.exampleapp.heroWars.model.monster.Orc;
import com.exampleapp.heroWars.model.monster.Werewolf;
import com.exampleapp.heroWars.repository.HeroRepository;
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
    private final HeroService heroService;
    private final HeroRepository heroRepository;

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
        Hero hero = heroService.getMyHero();
        String startOfBattle = "You found the monster and suddenly you hear : " + monster.getBattleRoar();
        String results = "";
        List<String> listOfRounds = new ArrayList<>();
        int round = 0;

        while(heroService.isHeroAbleToFight(hero) && monster.getHp() > 0){
            String roundInfo = "";
            round++;
            roundInfo = "Round " + round + ": ";
            int heroDamage = heroService.receiveDamage(monster.dealDamage(), hero);
            roundInfo = roundInfo + "Monster attacks and deal " + heroDamage  +
                    " damage to your hero. ";
            monster.receiveDamage(heroService.damageTotal(hero));
            roundInfo = roundInfo + "Hero attacks monster and deal: " + heroService.damageTotal(hero);
            listOfRounds.add(roundInfo);
            if(monster.getHp() <= 0){
                results = "Monster is dead.";
                heroService.getPriceGold(monster.getGoldAmount(), hero);
                monster.setAlive(false);
            }
            if(!heroService.isHeroAbleToFight(hero)){
                results = "Monster was too strong. You run away";
            }
        }
        heroRepository.save(hero);
        monsterRepository.save(monster);
        return BattleResultDTO
                .builder()
                .battleLog(listOfRounds.toString())
                .battleResults(results)
                .startOfBattle(startOfBattle)
                .build();
    }
}
