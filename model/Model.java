package model;

import com.diogonunes.jcolor.Attribute;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.diogonunes.jcolor.Ansi.*;
import static com.diogonunes.jcolor.Attribute.*;
import static com.diogonunes.jcolor.Attribute.CYAN_TEXT;

public class Model {
    // Метод для Конвертации Изображения в Консольный Вид:
    public void printImageToConsole(String imagePath, int blackPixelColor, int whitePixelColor) {
        File imageFile = new File(imagePath);

        if (!imageFile.exists()) {
            System.out.println("Выбранного Файла не Существует. Пожалуйста повторите Попытку.");
            return;
        }

        try {
            BufferedImage image = ImageIO.read(imageFile);
            if (!isImageSizeValid(image)) {
                System.out.println("Размер Изображения Должен быть от 16x16 до 32x32 Пикселей.");
                return;
            }
            printImage(image, blackPixelColor, whitePixelColor);
        } catch (IOException exception) {
            System.err.println("Ошибка Чтения Файла: " + exception.getMessage());
        }
    }

    // Метод для Проверки Размера Изображения:
    private boolean isImageSizeValid(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        return (width >= 16 && height >= 16) && (width <= 32 && height <= 32);
    }

    // Метод для Определения Пикселя по его Цвету:
    private char getAsciiChar(int pixel, char whiteSymbol, char blackSymbol) {
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = pixel & 0xff;
        return (red + green + blue) / 3 < 128 ? blackSymbol : whiteSymbol;
    }

    // Метод для Возврата Цвета Пикселя:
    private Attribute[] getColorByValue(int colorValue) {
        return switch (colorValue) {
            case 1 -> new Attribute[]{BLACK_TEXT(), BLACK_BACK()};
            case 2 -> new Attribute[]{WHITE_TEXT(), WHITE_BACK()};
            case 3 -> new Attribute[]{RED_TEXT(), RED_BACK()};
            case 4 -> new Attribute[]{YELLOW_TEXT(), YELLOW_BACK()};
            case 5 -> new Attribute[]{BLUE_TEXT(), BLUE_BACK()};
            case 6 -> new Attribute[]{GREEN_TEXT(), GREEN_BACK()};
            case 7 -> new Attribute[]{MAGENTA_TEXT(), MAGENTA_BACK()};
            case 8 -> new Attribute[]{CYAN_TEXT(), CYAN_BACK()};
            // Значение по Умолчанию:
            default -> new Attribute[]{WHITE_TEXT(), WHITE_BACK()};
        };
    }

    // Метод для Отрисовки Изображения:
    private void printImage(BufferedImage image, int blackPixelColor, int whitePixelColor) {
        int width = image.getWidth();
        int height = image.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = image.getRGB(x, y);

                char whiteSymbol = '.';
                char blackSymbol = '#';

                char asciiChar = getAsciiChar(pixel, whiteSymbol, blackSymbol);

                Attribute[] colors;
                if (asciiChar == blackSymbol) {
                    colors = getColorByValue(blackPixelColor);
                } else {
                    colors = getColorByValue(whitePixelColor);
                }

                System.out.print(colorize(String.valueOf(asciiChar), colors[0], colors[1]));
                System.out.print(colorize(String.valueOf(asciiChar), colors[0], colors[1]));
            }
            System.out.println();
        }
    }
}