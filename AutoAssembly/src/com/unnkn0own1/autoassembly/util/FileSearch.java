package com.unnkn0own1.autoassembly.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Утилiтарний клас для пошуку ключового слова у файлах.
 */
public class FileSearch {

    /**
     * Метод для використання у iнших класах для виводу результатiв пошуку в файлi "Parts.json".
     *
     * @param dataPath Шлях до каталогу даних.
     * @param keyword  Ключове слово для пошуку.
     */
    public static void searchAndPrintResults(String dataPath, String keyword) {
        List<String> detailsLines = readFile(dataPath + "Parts.json");

        System.out.println("Результати пошуку в Parts:");
        searchAndPrintDetails(detailsLines, keyword);
    }

    /**
     * Зчитує вмiст файлу та повертає його у виглядi списку рядкiв.
     *
     * @param filename iм'я файлу для зчитування.
     * @return Список рядкiв з вмiстом файлу.
     */
    private static List<String> readFile(String filename) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    /**
     * Виконує пошук та виведення рядкiв, якi мiстять ключове слово у заданому списку рядкiв.
     *
     * @param lines   Список рядкiв для пошуку.
     * @param keyword Ключове слово для пошуку.
     */
    private static void searchAndPrintDetails(List<String> lines, String keyword) {
        JSONParser jsonParser = new JSONParser();

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);

            try {
                Object obj = jsonParser.parse(line);
                JSONObject jsonObject = (JSONObject) obj;

                JSONArray detailsArray = (JSONArray) jsonObject.get("деталi");

                for (Object detailObj : detailsArray) {
                    JSONObject detail = (JSONObject) detailObj;
                    if (detail.toString().contains(keyword)) {
                        System.out.println("Рядок " + (i + 1) + ": " + detail);
                    }
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
