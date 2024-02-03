package com.unnkn0own1.autoassembly.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.unnkn0own1.autoassembly.persistence.entity.impl.User;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Клас для реєстрацiї нових користувачiв.
 */
public class Registration {

    /**
     * Метод для реєстрацiї нового користувача.
     *
     * @param fileName iм'я файлу для збереження даних користувачiв.
     * @param scanner  Об'єкт Scanner для зчитування введення користувача.
     */
    public void registration(String fileName, Scanner scanner) {
        int maxAttempts = 3; // Максимальна кiлькiсть спроб

        while (maxAttempts > 0) {
            try {
                System.out.print("Напишiть пароль: ");
                String password = validatePassword(scanner.nextLine());

                System.out.print("Напишiть електронну пошту: ");
                String email = validateEmail(scanner.nextLine());

                System.out.print("Напишiть день народження (YYYY-MM-DD): ");
                LocalDate birthday = validateBirthday(scanner.nextLine());

                System.out.print("Напишiть логiн: ");
                String username = validateUsername(scanner.nextLine());

                // Отримати JSON об'єкт iз файлу
                JsonArray jsonArray = new Gson().fromJson(new FileReader(fileName), JsonArray.class);

                // Перевiрка на унiкальнiсть iменi користувача
                if (isUsernameUnique(jsonArray, username)) {
                    // Створення нового об'єкту користувача
                    User newUser = new User(UUID.randomUUID(), password, email, birthday, username);

                    // Додати нового користувача до масиву
                    jsonArray.add(newUser.toJsonObject());

                    // Записати оновлений масив у файл
                    try (FileWriter writer = new FileWriter(fileName)) {
                        new Gson().toJson(jsonArray, writer);
                        System.out.println("Реєстрацiя успiшна!");
                        return; // Вийти з методу пiсля успiшної реєстрацiї
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Користувач з таким iм'ям вже iснує. Будь ласка, оберiть iнше iм'я.");
                    maxAttempts--;
                }
            } catch (Exception e) {
                System.out.println("Неправильний формат введених даних. Будь ласка, спробуйте знову.");
                maxAttempts--;
                scanner.nextLine(); // Очистити буфер введення
            }
        }

        System.out.println("Ви перевищили максимальну кiлькiсть спроб. Спробуйте пiзнiше.");
    }

    // iншi методи тут...

    private String validatePassword(String password) {
        if (password.isBlank()) {
            throw new IllegalArgumentException("Пароль є обов'язковим полем.");
        }
        if (password.length() < 8 || password.length() > 32) {
            throw new IllegalArgumentException("Пароль повинен бути вiд 8 до 32 символiв.");
        }
        var pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$");
        if (!pattern.matcher(password).matches()) {
            throw new IllegalArgumentException("Пароль повинен мiстити хоча б одну малу лiтеру, велику лiтеру та цифру.");
        }
        return password;
    }

    private String validateEmail(String email) {
        if (email.isBlank()) {
            throw new IllegalArgumentException("Електронна пошта є обов'язковим полем.");
        }
        var pattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$");
        if (!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Неправильний формат електронної пошти.");
        }
        return email;
    }

    private LocalDate validateBirthday(String birthday) {
        if (birthday.isBlank()) {
            throw new IllegalArgumentException("Дата народження є обов'язковим полем.");
        }
        try {
            return LocalDate.parse(birthday);
        } catch (Exception e) {
            throw new IllegalArgumentException("Неправильний формат дати народження. Використовуйте YYYY-MM-DD.");
        }
    }

    private String validateUsername(String username) {
        if (username.isBlank()) {
            throw new IllegalArgumentException("Логiн є обов'язковим полем.");
        }
        if (username.length() < 4 || username.length() > 24) {
            throw new IllegalArgumentException("Логiн повинен бути вiд 4 до 24 символiв.");
        }
        var pattern = Pattern.compile("^[a-zA-Z0-9]+$");
        if (!pattern.matcher(username).matches()) {
            throw new IllegalArgumentException("Логiн повинен мiстити лише латинськi лiтери, цифри та пiдкреслення.");
        }
        return username;
    }

    /**
     * Перевiряє унiкальнiсть iменi користувача у масивi JSON-даних.
     *
     * @param jsonArray Масив JSON-даних користувачiв.
     * @param username  Логiн користувача, який потрiбно перевiрити.
     * @return true, якщо iм'я користувача унiкальне; false, якщо iм'я вже iснує.
     */
    private boolean isUsernameUnique(JsonArray jsonArray, String username) {
        for (JsonElement jsonElement : jsonArray) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String storedUsername = jsonObject.get("username").getAsString();
            if (Objects.equals(username, storedUsername)) {
                return false; // iм'я користувача вже iснує
            }
        }
        return true; // iм'я користувача унiкальне
    }
}
