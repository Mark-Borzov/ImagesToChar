all: uninstall install

# Цель для Установки Программы:
install:
	# 1. Создание Структуры Папок:
	mkdir -p install_out/ImagesToChar

	# 2. Компиляция Директории Байт-Кода:
	javac -d install_out/ImagesToChar -cp src/libraries/JColor/JColor-5.5.1.jar src/**/*.java

	# 3. Создание Файла Манифеста:
	echo "Manifest-Version: 1.0" > MANIFEST.MF
	echo "Main-Class: src.main.Main" >> MANIFEST.MF
	echo "Class-Path: src/libraries/JColor/JColor-5.5.1.jar" >> MANIFEST.MF

	# 4. Копирование Ресурсов (Изображений) в Папку для JAR:
	mkdir -p install_out/ImagesToChar/images
	cp -r images/* install_out/ImagesToChar/images/

	# 5. Создание JAR-Файла:
	jar cfm ImagesToChar.jar MANIFEST.MF -C install_out/ImagesToChar .

	# 6. Удаление Директории Компиляции Байт-Кода:
	rm -rf install_out

	# 7. Удаление Файла Манифеста:
	rm -rf MANIFEST.MF

	# 8. Создание Исполняемого .sh Файла:
	touch ImagesToChar.sh
	echo "#!/bin/bash" > ImagesToChar.sh
	echo "java -jar ImagesToChar.jar" >> ImagesToChar.sh
	chmod +x ImagesToChar.sh
	clear

	@echo "Программа Успешно Собрана."

# Цель для Удаления .jar и .sh Файлов:
uninstall:
	rm -rf ImagesToChar.sh
	rm -rf ImagesToChar.jar
	@echo "Файлы Сборки Успешно Удалены."

# Цель для Запуска .jar Файла:
run:
	java -jar ImagesToChar.jar