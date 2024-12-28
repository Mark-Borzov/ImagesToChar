package view;

public class View {
    public void mainMenu() {
        System.out.println("----------------------");
        System.out.println("--- Меню Программы ---");
        System.out.println("----------------------");
        System.out.println("1. Конвертация Изображения.");
        System.out.println("2. Завершение Программы.");
    }

    public void imageSelection() {
        System.out.println("Выберите Номер Изображения для Конвертации.");
    }

    public void availableImages() {
        System.out.println("Список Доступных Изображений.");
    }

    public void wrongChoice() {
        System.out.println("Некорректный выбор Пункта. Пожалуйста повторите Попытку.");
    }

    public void directoryError() {
        System.out.println("Директория для Изображений Пуста или Не Найдена.");
    }

    public void menuSelectionError() {
        System.out.println("Ошибка Выбора Пункта Меню. Пожалуйста повторите Попытку.");
    }
}