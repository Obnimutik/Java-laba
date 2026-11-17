package edu.course.lab06;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class DatasetAnalyticsTest {

    private final DatasetAnalytics analytics = new DatasetAnalytics();

    private List<AIDatasetRecord> getSampleData() {
        return List.of(
            new AIDatasetRecord("S-3", "cat", "camera-1", 128, "READY"),
            new AIDatasetRecord("S-1", "dog", "camera-2", 256, "PENDING"),
            new AIDatasetRecord("S-2", "cat", "camera-1", 128, "READY"),
            new AIDatasetRecord("S-4", "bird", "camera-3", 64, "ERROR")
        );
    }

    @Test
    void countByStatusWorks() {
        Map<String, Integer> counts = analytics.countByStatus(getSampleData());
        assertEquals(2, counts.get("READY"));
        assertEquals(1, counts.get("PENDING"));
        assertEquals(1, counts.get("ERROR"));
    }

    @Test
    void uniqueSourcesWorks() {
        Set<String> sources = analytics.getUniqueSources(getSampleData());
        assertEquals(3, sources.size());
        assertTrue(sources.contains("camera-1"));
        assertTrue(sources.contains("camera-2"));
        assertTrue(sources.contains("camera-3"));
    }

    @Test
    void sortByFeaturesAndIdWorksAndDoesNotMutateOriginal() {
        List<AIDatasetRecord> original = getSampleData();
        
        List<AIDatasetRecord> sorted = analytics.sortByFeaturesAndId(original);

        // Проверка сортировки: 64 (S-4), 128 (S-2), 128 (S-3), 256 (S-1)
        assertEquals("S-4", sorted.get(0).getId());
        assertEquals("S-2", sorted.get(1).getId()); // При равных признаках сортирует по ID
        assertEquals("S-3", sorted.get(2).getId());
        assertEquals("S-1", sorted.get(3).getId());

        // Проверка, что исходный список не изменился
        assertEquals("S-3", original.get(0).getId());
    }
}