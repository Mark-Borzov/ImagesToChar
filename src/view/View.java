package src.view;

import java.io.File;

public class View {
    public void mainMenu() {
        String menu = """
            ----------------------
            --- Меню Программы ---
            ----------------------
            1. Конвертация Изображений.
            2. Завершение Программы.
            """;
        System.out.print(menu);
    }

    public void imageSelection() {
        System.out.println("Выберите Номер Изображения для Конвертации.");
    }

    public void availableImages() {
        System.out.println("Список Доступных Изображений.");
    }

    public void wrongChoice() {
        System.out.println("Некорректный выбор Пункта Меню. Пожалуйста повторите Попытку.");
    }

    public void directoryError() {
        System.out.println("Директория для Изображений Пуста или Не Найдена.");
    }

    public void theColorForBlackPixel() {
        System.out.println("Выберите Цвет для Отображения Черного Пикселя.");
    }

    public void theColorForWhitePixel() {
        System.out.println("Выберите Цвет для Отображения Белого Пикселя.");
    }

    public void colorSelection() {
        System.out.println("""
                1. Черный.
                2. Белый.
                3. Красный.
                4. Желтый.
                5. Голубой.
                6. Зеленый.
                7. Фиолетовый.
                8. Бирюзовый.""");
    }

    public void theErrorOfIdenticalColors() {
        System.out.println("Пиксели не Могут быть Одинакового Цвета. Пожалуйста повторите Попытку.");
    }

    public void goToTheMainMenu(File[] files) {
        System.out.println((files.length + 1) + ". Перейти в Главное Меню.");
    }

    public void printFileNames(File[] files) {
        for (int i = 0; i < files.length; i++) {
            System.out.println((i + 1) + ". " + files[i].getName());
        }
    }

    public void theProgramsLogo() {
        String logoLines =
                """
                ██╗ ███╗   ███╗  █████╗   ██████╗  ███████╗    ██╗ ███╗   ██╗    ██████╗  ██╗ ██╗  ██╗ ███████╗ ██╗      ███████╗
                ██║ ████╗ ████║ ██╔══██╗ ██╔════╝  ██╔════╝    ██║ ████╗  ██║    ██╔══██╗ ██║ ╚██╗██╔╝ ██╔════╝ ██║      ██╔════╝
                ██║ ██╔████╔██║ ███████║ ██║  ███╗ █████╗      ██║ ██╔██╗ ██║    ██████╔╝ ██║  ╚███╔╝  █████╗   ██║      ███████╗
                ██║ ██║╚██╔╝██║ ██╔══██║ ██║   ██║ ██╔══╝      ██║ ██║╚██╗██║    ██╔═══╝  ██║  ██╔██╗  ██╔══╝   ██║      ╚════██║
                ██║ ██║ ╚═╝ ██║ ██║  ██║ ╚██████╔╝ ███████╗    ██║ ██║ ╚████║    ██║      ██║ ██╔╝ ██╗ ███████╗ ███████╗ ███████║  ██╗
                ╚═╝ ╚═╝     ╚═╝ ╚═╝  ╚═╝  ╚═════╝  ╚══════╝    ╚═╝ ╚═╝  ╚═══╝    ╚═╝      ╚═╝ ╚═╝  ╚═╝ ╚══════╝ ╚══════╝ ╚══════╝  ╚═╝
                """;
        System.out.println(logoLines);
    }

    public void completionOfTheProgram() {
        String logoLines =
                """
                 ██████╗   ██████╗   ██████╗  ██████╗  ██████╗  ██╗   ██╗ ███████╗  ██╗
                ██╔════╝  ██╔═══██╗ ██╔═══██╗ ██╔══██╗ ██╔══██╗ ╚██╗ ██╔╝ ██╔════╝  ██║
                ██║  ███╗ ██║   ██║ ██║   ██║ ██║  ██║ ██████╔╝  ╚████╔╝  █████╗    ██║
                ██║   ██║ ██║   ██║ ██║   ██║ ██║  ██║ ██╔══██╗   ╚██╔╝   ██╔══╝    ╚═╝
                ╚██████╔╝ ╚██████╔╝ ╚██████╔╝ ██████╔╝ ██████╔╝    ██║    ███████╗  ██╗
                 ╚═════╝   ╚═════╝   ╚═════╝  ╚═════╝  ╚═════╝     ╚═╝    ╚══════╝  ╚═╝
                """;
        System.out.println(logoLines);
    }
}