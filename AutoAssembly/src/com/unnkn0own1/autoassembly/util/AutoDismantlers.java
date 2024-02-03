package com.unnkn0own1.autoassembly.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.IOException;

/**
 * Клас для роботи з iнформацiєю про авторозбiрки.
 */
public class AutoDismantlers {

    /**
     * Виводить iнформацiю про всi авторозбiрки з файла JSON.
     *
     * @param filePath Шлях до файлу JSON з iнформацiєю про авторозбiрки.
     */
    public void printAllDismantlersInfo(String filePath) {
        try {
            // Зчитування JSON з файлу
            JsonObject jsonObject = new Gson().fromJson(new FileReader(filePath), JsonObject.class);

            if (jsonObject != null && jsonObject.has("авторозбiрки")) {
                JsonArray dismantlersArray = jsonObject.getAsJsonArray("авторозбiрки");

                for (JsonElement dismantlerElement : dismantlersArray) {
                    printDismantlerInfo(dismantlerElement.getAsJsonObject());
                }
            } else {
                System.out.println("Невiрний або пустий файл JSON.");
            }

        } catch (IOException e) {
            System.err.println("Помилка при зчитуваннi файлу JSON: " + e.getMessage());
        }
    }

    /**
     * Виводить iнформацiю про конкретну авторозбiрку.
     *
     * @param dismantlerObject JSON-об'єкт, що представляє iнформацiю про авторозбiрку.
     */
    private void printDismantlerInfo(JsonObject dismantlerObject) {
        // Видобування та виведення iнформацiї з JSON-об'єкта
        String name = getStringFieldValue(dismantlerObject, "назва");
        String location = getStringFieldValue(dismantlerObject, "розташування");

        System.out.println("Iнформацiя про авторозбiрку " + name + ":");
        System.out.println("   Назва: " + name);
        System.out.println("   Розташування: " + location);
        System.out.println("   Контактнi данi:");

        JsonObject contactInfo = dismantlerObject.getAsJsonObject("контактнi_данi");
        String phone = getStringFieldValue(contactInfo, "телефон");
        String email = getStringFieldValue(contactInfo, "електронна_пошта");

        System.out.println("      Телефон: " + phone);
        System.out.println("      Електронна пошта: " + email);
        System.out.println();
    }

    /**
     * Отримує значення рядкового поля з JSON-об'єкта за iменем поля.
     *
     * @param jsonObject JSON-об'єкт.
     * @param fieldName  iм'я поля.
     * @return Значення рядкового поля або пустий рядок, якщо поле не iснує.
     */
    private String getStringFieldValue(JsonObject jsonObject, String fieldName) {
        return jsonObject.has(fieldName) ? jsonObject.get(fieldName).getAsString() : "";
    }
}
