package src.controller;

import src.model.Model;
import src.view.View;

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
        view.theProgramsLogo();
        this.menu();
        view.completionOfTheProgram();
    }

    // Меню Программы:
    private void menu() {
        while (true) {
            try {
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
                    default -> view.wrongChoice();
                }
            } catch (InputMismatchException e) {
                view.wrongChoice();
                scanner.nextLine();
            }
        }
    }

    // Метод для Вывода Доступных Изображений из Директории:
    private void printImagesInDirectory() {
        String filePath = "images" + File.separator;
        File directory = new File(filePath);
        File[] files = directory.listFiles((file, name) ->
                name.toLowerCase().endsWith(".jpg") ||
                        name.toLowerCase().endsWith(".png") ||
                        name.toLowerCase().endsWith(".bmp"));

        if (files != null && files.length > 0) {
            selectImage(files, filePath);
        } else {
            view.directoryError();
        }
    }

    // Метод для Вывода Файлов из Директории:
    private void displayAvailableImages(File[] files) {
        view.availableImages();
        view.printFileNames(files);
    }

    // Метод Выбора Изображения для Конвертации:
    private void selectImage(File[] files, String filePath) {
        int choice;
        while (true) {
            displayAvailableImages(files);
            view.goToTheMainMenu(files);
            view.imageSelection();
            try {
                choice = scanner.nextInt();
                if (choice > 0 && choice <= files.length) {
                    String pathToImage = filePath + files[choice - 1].getName();

                    if (choosePixelColors(pathToImage)) {
                        return;
                    }

                } else if (choice == files.length + 1) {
                    return;
                } else {
                    view.wrongChoice();
                }
            } catch (InputMismatchException e) {
                view.wrongChoice();
                scanner.next();
            }
        }
    }

    // Метод для Выбора Цвета Белых и Черных Пикселей:
    private boolean choosePixelColors(String pathToImage) {
        int blackPixelColor, whitePixelColor;

        while (true) {
            int[] colors = selectPixelColors();
            blackPixelColor = colors[0];
            whitePixelColor = colors[1];

            if (blackPixelColor == whitePixelColor) {
                view.theErrorOfIdenticalColors();
            } else {
                model.printImageToConsole(pathToImage, blackPixelColor, whitePixelColor);
                return true;
            }
        }
    }

    // Метод для Присваивания Цветов Пикселей:
    private int[] selectPixelColors() {
        int[] colors = new int[2];

        view.theColorForBlackPixel();
        view.colorSelection();
        colors[0] = getSymbolValue(scanner);

        view.theColorForWhitePixel();
        view.colorSelection();
        colors[1] = getSymbolValue(scanner);

        return colors;
    }

    // Метод для Проверки Валидности Выбора Цвета Пикселей:
    private int getSymbolValue(Scanner scanner) {
        int value;
        while (true) {
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                scanner.nextLine();
                if (value >= 1 && value <= 8) {
                    return value;
                } else {
                    view.wrongChoice();
                }
            } else {
                view.wrongChoice();
                scanner.nextLine();
            }
        }
    }
}