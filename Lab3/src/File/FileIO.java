package File;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileIO {
    public void writeToFile(String filePath, StringBuilder content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.append(content);
            System.out.println("Рядок успішно записаний в файл.");
        } catch (IOException e) {
            System.err.println("Помилка при записі в файл: " + e.getMessage());
        }
    }

    public StringBuilder readFromFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath)) ) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
                content.append(System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println("Помилка при читанні з файлу: " + e.getMessage());
        }
        return content;
    }
}