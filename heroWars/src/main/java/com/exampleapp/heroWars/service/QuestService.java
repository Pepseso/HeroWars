package com.exampleapp.heroWars.service;

import com.exampleapp.heroWars.exception.NoQuestsFoundException;
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


    public String getRandomQuest() {
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
                throw new RuntimeException("No quest descriptions found in the file.");
            }

            // Pick a random quest description
            Random random = new Random();

            return questDescriptions.get(random.nextInt(questDescriptions.size()));
        } catch (IOException e) {
            throw new NoQuestsFoundException();
        }
    }
}
