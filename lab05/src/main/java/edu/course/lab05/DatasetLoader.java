package edu.course.lab05;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DatasetLoader {

    public DatasetLoadResult load(Path path) {
        List<AIDatasetRecord> validRecords = new ArrayList<>();
        List<String> errors = new ArrayList<>();

        List<String> lines;
        try {
            lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            // Ошибка источника - сохраняем исходную причину
            throw new DataLoadingException("Cannot read " + path, e);
        }

        int lineNumber = 1;
        for (String line : lines) {
            if (line.trim().isEmpty()) {
                lineNumber++; // Пропускаем пустые строки
                continue;
            }

            String[] parts = line.split(";", -1); // -1 чтобы сохранить пустые строки в конце

            if (parts.length != 5) {
                errors.add("Строка " + lineNumber + ": неверное количество полей (ожидалось 5, найдено " + parts.length + ")");
                lineNumber++;
                continue;
            }

            try {
                String id = parts[0].trim();
                String label = parts[1].trim();
                String source = parts[2].trim();
                String featureCountStr = parts[3].trim();
                String status = parts[4].trim();

                if (id.isEmpty() || label.isEmpty() || source.isEmpty() || featureCountStr.isEmpty() || status.isEmpty()) {
                    errors.add("Строка " + lineNumber + ": пустое поле");
                    lineNumber++;
                    continue;
                }

                int featureCount = Integer.parseInt(featureCountStr);
                if (featureCount < 0) {
                    errors.add("Строка " + lineNumber + ": отрицательное значение featureCount");
                    lineNumber++;
                    continue;
                }

                if (!status.equals("READY") && !status.equals("PENDING") && !status.equals("ERROR")) {
                    errors.add("Строка " + lineNumber + ": неизвестный статус '" + status + "'");
                    lineNumber++;
                    continue;
                }

                validRecords.add(new AIDatasetRecord(id, label, source, featureCount, status));

            } catch (NumberFormatException e) {
                errors.add("Строка " + lineNumber + ": неверный формат числа '" + parts[3] + "'");
            }
            lineNumber++;
        }

        return new DatasetLoadResult(validRecords, errors);
    }
} 