package edu.course.lab07;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DataAnalytics {

    // 1. Фильтрация по статусу
    public List<AIDatasetRecord> filterByStatus(List<AIDatasetRecord> records, String status) {
        return records.stream()
                .filter(r -> r.status().equals(status))
                .toList();
    }

    // 2. Отсортированный список нормализованных меток (в верхнем регистре)
    public List<String> sortedLabels(List<AIDatasetRecord> records) {
        return records.stream()
                .map(r -> r.label().toUpperCase())
                .distinct()
                .sorted()
                .toList();
    }

    // 3. Группировка и подсчет количества
    public Map<String, Long> countBySource(List<AIDatasetRecord> records) {
        return records.stream()
                .collect(Collectors.groupingBy(AIDatasetRecord::source, Collectors.counting()));
    }

    // 4. Поиск максимума (Optional)
    public Optional<AIDatasetRecord> maxByFeatureCount(List<AIDatasetRecord> records) {
        return records.stream()
                .max(Comparator.comparingInt(AIDatasetRecord::featureCount));
    }

    // 5. Сортировка по двум ключам
    public List<AIDatasetRecord> sortByFeatureCountAndId(List<AIDatasetRecord> records) {
        return records.stream()
                .sorted(Comparator.comparingInt(AIDatasetRecord::featureCount)
                        .thenComparing(AIDatasetRecord::id))
                .toList();
    }
}