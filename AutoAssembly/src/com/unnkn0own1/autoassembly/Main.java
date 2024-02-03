package com.unnkn0own1.autoassembly;

import com.unnkn0own1.autoassembly.util.Authorisation;
import com.unnkn0own1.autoassembly.util.Registration;
import com.unnkn0own1.autoassembly.util.Detail;
import com.unnkn0own1.autoassembly.util.AutoDismantlers;
import com.unnkn0own1.autoassembly.util.JsonEditor;
import com.unnkn0own1.autoassembly.util.FileSearch;

import java.util.Scanner;

/**
 * Головний клас для виконання додатка AutoAssembly.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String dataPath = "Data/";

        boolean isAuthenticated = false;
        int userId = -1;

        JsonEditor detailEditor = new JsonEditor(dataPath + "Parts.json");


        while (true) {
            if (!isAuthenticated) {
                // Меню авторизацiї та реєстрацiї
                System.out.println("\n\033[2;1mМеню авторизацiї та реєстрацiї\033[3m:");
                System.out.println("1. Авторизацiя");
                System.out.println("2. Реєстрацiя");
                System.out.println("0. Вихiд");

                String userChoice = scanner.nextLine();

                switch (userChoice) {
                    case "0":
                        System.out.println("Дякуємо за використання додатка. До побачення!");
                        System.exit(0);
                        break;
                    case "1":
                        Authorisation.AuthResult authResult = Authorisation.authenticate(dataPath + "users.json");
                        if (authResult.isAuthenticated()) {
                            isAuthenticated = true;
                            userId = authResult.getUserId();
                            System.out.println("Авторизацiя успiшна. Вiтаємо, користуваче " + userId + "!");
                        } else {
                            System.out.println("Авторизацiя не вдалася. Перевiрте вашi данi та спробуйте ще раз.");
                        }
                        break;
                    case "2":
                        Registration registration = new Registration();
                        registration.registration(dataPath + "users.json", scanner);
                        break;
                    default:
                        System.out.println("Невiрний вибiр. Будь ласка, введiть коректний номер опцii.");
                }
            } else {
                // Код для авторизованого користувача
                System.out.println("\n\033[2;1mГоловне меню\033[3m:");
                System.out.println("1. Перегляд iнформацiї про авторозбiрки");
                System.out.println("2. Перегляд iнформацiї про деталi");
                System.out.println("3. Пошук");
                System.out.println("4. Редагування iнформацiї про деталi");
                System.out.println("0. Вихiд");

                String userChoice = scanner.nextLine();

                switch (userChoice) {
                    case "0":
                        System.out.println("Дякуємо за використання додатка. До побачення!");
                        System.exit(0);
                        break;
                    case "1":
                        AutoDismantlers autoDismantlers = new AutoDismantlers();
                        System.out.println("Вiдображення iнформацiї про авторозбiрки для користувача " + userId);
                        autoDismantlers.printAllDismantlersInfo(dataPath + "AutoDismantlers.json");
                        break;
                    case "2":
                        Detail detail = new Detail();
                        System.out.println("Вiдображення iнформацiї про деталi для користувача " + userId);
                        detail.printAllDetailInfo(dataPath + "Parts.json");
                        break;
                    case "3":
                        // Пошук
                        System.out.println("Введiть ключове слово для пошуку:");
                        String keyword = scanner.nextLine();
                        FileSearch.searchAndPrintResults(dataPath, keyword);
                        break;
                    case "4":
                        // Редагування iнформацiї про деталi
                        System.out.println("Введiть назву деталi для оновлення цiни:");
                        String detailNameForUpdate = scanner.nextLine();
                        System.out.println("Введiть нове значення для цiни:");
                        int newPrice = Integer.parseInt(scanner.nextLine());
                        detailEditor.updateDetailPriceByName(detailNameForUpdate, newPrice);
                        System.out.println("Значення успiшно оновлено.");
                        break;
                    default:
                        System.out.println("Невiрний вибiр. Будь ласка, введiть коректний номер опцiї.");
                }
            }
        }
    }
}
