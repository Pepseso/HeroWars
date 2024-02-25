package com.exampleapp.heroWars;

import com.exampleapp.heroWars.model.monster.Dragon;
import com.exampleapp.heroWars.model.monster.Monster;
import com.exampleapp.heroWars.model.monster.Orc;
import com.exampleapp.heroWars.model.monster.Werewolf;
import com.exampleapp.heroWars.repository.MonsterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class HeroWarsApplication implements CommandLineRunner {

	private final MonsterRepository monsterRepository;

	public static void main(String[] args) {
		SpringApplication.run(HeroWarsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Monster werewolf = new Werewolf();
//		Monster dragon = new Dragon();
//		Monster orc = new Orc();
//		monsterRepository.save(werewolf);
//		monsterRepository.save(dragon);
//		monsterRepository.save(orc);
	}
}
