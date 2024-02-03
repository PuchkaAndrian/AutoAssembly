package com.unnkn0own1.autoassembly.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Утилiтарний клас для редагування JSON-даних.
 */
public class JsonEditor {

    private final String filename;
    private final JSONArray detailsData;

    /**
     * Конструктор, який приймає iм'я файлу JSON для редагування.
     *
     * @param filename iм'я файлу JSON.
     */
    public JsonEditor(String filename) {
        this.filename = filename;
        this.detailsData = loadDetailsData();
    }

    /**
     * Завантажує данi з файлу JSON i повертає масив об'єктiв.
     *
     * @return Масив JSON-об'єктiв.
     */
    private JSONArray loadDetailsData() {
        try (FileReader reader = new FileReader(filename)) {
            JSONParser parser = new JSONParser();
            JSONObject jsonData = (JSONObject) parser.parse(reader);
            return (JSONArray) jsonData.get("деталi");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Зберiгає данi у файл JSON.
     */
    private void saveData() {
        try (FileWriter writer = new FileWriter(filename)) {
            JSONObject jsonData = new JSONObject();
            jsonData.put("деталi", detailsData);
            writer.write(jsonData.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Оновлює значення цiни для деталi за назвою у списку JSON-даних.
     *
     * @param detailName Назва деталi.
     * @param newPrice   Нова цiна деталi.
     */
    public void updateDetailPriceByName(String detailName, int newPrice) {
        for (Object detail : detailsData) {
            if (detail instanceof JSONObject) {
                JSONObject detailObject = (JSONObject) detail;
                if (detailObject.containsKey("Назва") && detailObject.get("Назва").equals(detailName)) {
                    detailObject.put("Цiна", newPrice);
                    break;
                }
            }
        }
        saveData();
    }

    /**
     * Повертає данi зi списку JSON-даних.
     *
     * @return Список JSON-об'єктiв.
     */
    public List<Object> getDetailsData() {
        return detailsData;
    }

    /**
     * Метод для прикладу використання.
     *
     * @param args Аргументи командного рядка (не використовуються в цьому випадку).
     */
    public static void main(String[] args) {
        // Приклад використання:
        JsonEditor detailsEditor = new JsonEditor("Data/Details.json");

        // Оновлення цiни для деталi "Бензонасос" на 200
        detailsEditor.updateDetailPriceByName("Бензонасос", 200);

        // Виведення оновлених даних
        System.out.println(detailsEditor.getDetailsData());
    }
}
