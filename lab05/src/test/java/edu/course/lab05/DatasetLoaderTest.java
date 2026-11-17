package edu.course.lab05;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DatasetLoaderTest {

    @TempDir
    Path tempDir; // JUnit автоматически создаст временную папку

    private final DatasetLoader loader = new DatasetLoader();

    @Test
    void loadsValidFileSuccessfully() throws IOException {
        Path file = tempDir.resolve("valid.csv");
        List<String> lines = List.of(
                "S-101;cat;camera-1;128;READY",
                "S-102;dog;camera-2;256;PENDING"
        );
        Files.write(file, lines, StandardCharsets.UTF_8);

        DatasetLoadResult result = loader.load(file);

        assertEquals(2, result.getValidRecords().size());
        assertTrue(result.getErrors().isEmpty());
        
        AIDatasetRecord first = result.getValidRecords().get(0);
        assertEquals("S-101", first.getId());
        assertEquals("cat", first.getLabel());
        assertEquals(128, first.getFeatureCount());
    }

    @Test
    void fileNotFoundThrowsDataLoadingException() {
        Path missingFile = tempDir.resolve("missing.csv");
        
        DataLoadingException ex = assertThrows(DataLoadingException.class, () -> loader.load(missingFile));
        assertTrue(ex.getMessage().contains("Cannot read"));
        assertNotNull(ex.getCause()); // Исходная причина (IOException) должна сохраниться
    }

    @Test
    void collectsErrorsAndKeepsValidLines() throws IOException {
        Path file = tempDir.resolve("partial_invalid.csv");
        List<String> lines = List.of(
                "S-101;cat;camera-1;128;READY",          // Валидная
                "S-102;dog;camera-2;256",                // Мало полей
                "S-103;bird;camera-3;abc;READY",         // Число не парсится
                "S-104;fish;camera-4;512;UNKNOWN_STATUS",// Неизвестный статус
                "S-105;;camera-5;64;READY"               // Пустое поле label
        );
        Files.write(file, lines, StandardCharsets.UTF_8);

        DatasetLoadResult result = loader.load(file);

        // Одна валидная строка
        assertEquals(1, result.getValidRecords().size());
        assertEquals("S-101", result.getValidRecords().get(0).getId());

        // 4 ошибки
        List<String> errors = result.getErrors();
        assertEquals(4, errors.size());
        assertTrue(errors.get(0).contains("Строка 2: неверное количество полей"));
        assertTrue(errors.get(1).contains("Строка 3: неверный формат числа"));
        assertTrue(errors.get(2).contains("Строка 4: неизвестный статус"));
        assertTrue(errors.get(3).contains("Строка 5: пустое поле"));
    }
}