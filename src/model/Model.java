package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.diogonunes.jcolor.Ansi.*;
import static com.diogonunes.jcolor.Attribute.*;

public class Model {
    // Метод для Конвертации Изображения в Консольный Вид:
    public void printImageToConsole(String imagePath, char whiteSymbol, char blackSymbol) {
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

            printImage(image, whiteSymbol, blackSymbol);
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

    // Метод для Отрисовки Изображения:
    private void printImage(BufferedImage image, char whiteSymbol, char blackSymbol) {
        int width = image.getWidth();
        int height = image.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = image.getRGB(x, y);
                char asciiChar = getAsciiChar(pixel, whiteSymbol, blackSymbol);

                if (asciiChar == blackSymbol) {
                    System.out.print(colorize(String.valueOf(asciiChar), BLACK_TEXT(), BLACK_BACK()));
                } else {
                    System.out.print(colorize(String.valueOf(asciiChar), WHITE_TEXT(), WHITE_BACK()));
                }

                if (asciiChar == blackSymbol) {
                    System.out.print(colorize(String.valueOf(asciiChar), BLACK_TEXT(), BLACK_BACK()));
                } else {
                    System.out.print(colorize(String.valueOf(asciiChar), WHITE_TEXT(), WHITE_BACK()));
                }
            }
            System.out.println();
        }
    }
}