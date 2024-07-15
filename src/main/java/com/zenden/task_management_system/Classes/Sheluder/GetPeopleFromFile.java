package com.zenden.task_management_system.Classes.Sheluder;

import java.io.InputStream;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class GetPeopleFromFile {
    public static final String FILE_PATH = "/people.json";
    public double people = 0;

    @Scheduled(fixedRate = 5000)
    private void loadPeople() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            InputStream is = getClass().getResourceAsStream(FILE_PATH);
            JsonNode jsonNode = mapper.readTree(is);

            people = jsonNode.get("people").asDouble();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double getPeople() {
        return people;
    }

}
