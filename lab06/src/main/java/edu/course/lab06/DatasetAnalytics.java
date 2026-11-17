package edu.course.lab06;

import java.util.*;

public class DatasetAnalytics {

    // 1. Число наблюдений по статусам через Map
    public Map<String, Integer> countByStatus(List<AIDatasetRecord> records) {
        Map<String, Integer> counts = new HashMap<>();
        for (AIDatasetRecord record : records) {
            String status = record.getStatus();
            counts.put(status, counts.getOrDefault(status, 0) + 1);
        }
        return counts;
    }

    // 2. Уникальные источники через Set
    public Set<String> getUniqueSources(List<AIDatasetRecord> records) {
        Set<String> sources = new HashSet<>();
        for (AIDatasetRecord record : records) {
            sources.add(record.getSource());
        }
        return sources;
    }

    // 3. Новый список, отсортированный по числу признаков, затем по идентификатору
    public List<AIDatasetRecord> sortByFeaturesAndId(List<AIDatasetRecord> records) {
        // Создаем копию, чтобы не изменять исходный список (требование задания)
        List<AIDatasetRecord> sorted = new ArrayList<>(records);
        
        // Сортировка через Comparator без вычитания
        sorted.sort(Comparator
                .comparingInt(AIDatasetRecord::getFeatureCount)
                .thenComparing(AIDatasetRecord::getId));
                
        return sorted;
    }
}