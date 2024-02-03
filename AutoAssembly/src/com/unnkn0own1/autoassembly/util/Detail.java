package com.unnkn0own1.autoassembly.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Клас для виведення iнформацiї про деталi.
 */
public class Detail {

    /**
     * Виводить iнформацiю про всi деталi з файлу JSON.
     *
     * @param filePath Шлях до файлу JSON з iнформацiєю про деталi.
     */
    public void printAllDetailInfo(String filePath) {
        try {
            // Зчитування вмiсту JSON-файлу в рядок
            String content = new String(Files.readAllBytes(Paths.get(filePath)));

            // Розбиває JSON-рядок на окремi деталi
            String[] details = content.split("\\},\\s*\\{");
            for (String detail : details) {
                printDetailInfo(detail);
                System.out.println(); // Додати порожнiй рядок мiж деталями для кращого виведення
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Виводить iнформацiю про конкретну деталь.
     *
     * @param detail Рядок JSON, що представляє iнформацiю про деталь.
     */
    public void printDetailInfo(String detail) {
        // Видобуває та виводить iнформацiю з JSON-об'єкта
        String name = parseFieldValue(detail, "Назва");
        String year = parseFieldValue(detail, "Рiк");
        String price = parseFieldValue(detail, "Цiна");

        System.out.println("Iнформацiя про деталь:");

        if (name != null) {
            System.out.println("   Назва: " + name);
        }

        if (year != null) {
            System.out.println("   Рiк виробництва: " + year);
        }

        if (price != null) {
            System.out.println("   Цiна: " + price);
        }
    }

    /**
     * Видобуває значення поля з JSON-рядка за iм'ям поля.
     *
     * @param jsonString Рядок JSON.
     * @param fieldName  iм'я поля.
     * @return Значення поля або null, якщо поле не iснує.
     */
    private String parseFieldValue(String jsonString, String fieldName) {
        // Знаходить значення поля за iм'ям
        String[] tokens = jsonString.split("\"" + fieldName + "\":");
        if (tokens.length > 1) {
            String fieldValue = tokens[1].split(",")[0].replaceAll("[\"{}]", "").trim();
            return "null".equals(fieldValue) ? null : fieldValue;
        }
        return null;
    }
}
