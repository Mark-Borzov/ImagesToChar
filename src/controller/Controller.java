package controller;

import model.Model;
import view.View;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Controller {
    private final View view;
    private final Model model;
    private final Scanner scanner;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        this.scanner = new Scanner(System.in);
    }

    // Метод для Начала Работы Программы:
    public void start() {
        this.menu();
    }

    // Меню Программы:
    private void menu() {
        while (true) {
            view.mainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                // Конвертация Изображений:
                case 1 -> printImagesInDirectory();
                // Завершение Программы:
                case 2 -> {
                    scanner.close();
                    return;
                }
                default -> view.menuSelectionError();
            }
        }
    }

    // Метод для Вывода Доступных Изображений из Директории:
    private void printImagesInDirectory() {
        String filePath = "src/images/";
        File directory = new File(filePath);
        File[] files = directory.listFiles((dir, name) ->
                name.toLowerCase().endsWith(".jpg") ||
                        name.toLowerCase().endsWith(".png") ||
                        name.toLowerCase().endsWith(".bmp"));

        if (files != null && files.length > 0) {
            view.availableImages();
            for (int i = 0; i < files.length; i++) {
                System.out.println((i + 1) + ". " + files[i].getName());
            }
            selectImage(files, filePath);
        } else {
            view.directoryError();
        }
    }

    // Метод для Выбора Изображения для Конвертации:
    private void selectImage(File[] files, String filePath) {
        int choice;
        while (true) {
            view.imageSelection();
            try {
                choice = scanner.nextInt();
                if (choice > 0 && choice <= files.length) {
                    String pathToImage = filePath + files[choice - 1].getName();
                    model.printImageToConsole(pathToImage, '.', '#');
                    break;
                } else {
                    view.wrongChoice();
                }
            } catch (InputMismatchException e) {
                view.wrongChoice();
                scanner.next();
            }
        }
    }
}