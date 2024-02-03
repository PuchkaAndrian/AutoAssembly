package com.unnkn0own1.autoassembly.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Утилiтарний клас для аутентифiкацiї користувача на основi облiкових даних у форматi JSON.
 */
public class Authorisation {

    /**
     * Аутентифiкує користувача на основi наданого файлу JSON з облiковими даними.
     *
     * @param fileName Назва файлу JSON iз облiковими даними користувача.
     * @return Об'єкт типу {@code AuthResult}, який вказує на результат аутентифiкацiї.
     */
    public static AuthResult authenticate(String fileName) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Зчитує JSON з файлу
            JsonArray jsonArray = new Gson().fromJson(new FileReader(fileName), JsonArray.class);
            System.out.println("\n\033[2;1mВхiд в облiковий запис\033[3m:");
            System.out.print("Введiть iм'я користувача: ");
            String enteredUsername = scanner.nextLine();

            System.out.print("Введiть пароль: ");
            String enteredPassword = scanner.nextLine();

            // Проходить по об'єктах у масивi
            for (JsonElement jsonElement : jsonArray) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();

                // Отримує iнформацiю iз JSON
                String storedUsername = jsonObject.get("username").getAsString();
                String storedPassword = jsonObject.get("password").getAsString();

                // Перевiряє введенi данi
                if (enteredUsername.equals(storedUsername) && enteredPassword.equals(storedPassword)) {
                    System.out.println("Вхiд успiшний!");
                    int userId = jsonObject.has("userId") ? jsonObject.get("userId").getAsInt() : -1;
                    return new AuthResult(true, userId);
                }
            }

            // Аутентифiкацiя не вдалася
            System.out.println("Помилка входу. Неправильне iм'я користувача або пароль.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new AuthResult(false, -1); // Аутентифiкацiя не вдалася
    }

    /**
     * Об'єкт, який представляє результат аутентифiкацiї.
     */
    public static class AuthResult {
        private final boolean isAuthenticated;
        private final int userId;

        /**
         * Конструктор, який створює новий об'єкт {@code AuthResult}.
         *
         * @param isAuthenticated Прапорець, що вказує на успiшнiсть аутентифiкацiї.
         * @param userId          iдентифiкатор користувача (якщо аутентифiкацiя успiшна).
         */
        public AuthResult(boolean isAuthenticated, int userId) {
            this.isAuthenticated = isAuthenticated;
            this.userId = userId;
        }

        /**
         * Повертає значення прапорця, що вказує на успiшнiсть аутентифiкацiї.
         *
         * @return {@code true}, якщо аутентифiкацiя успiшна; iнакше {@code false}.
         */
        public boolean isAuthenticated() {
            return isAuthenticated;
        }

        /**
         * Повертає iдентифiкатор користувача (якщо аутентифiкацiя успiшна).
         *
         * @return iдентифiкатор користувача або -1, якщо аутентифiкацiя не вдалася.
         */
        public int getUserId() {
            return userId;
        }
    }
}
