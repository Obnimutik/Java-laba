package edu.course.lab07;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class DataAnalyticsTest {
    private final DataAnalytics analytics = new DataAnalytics();
    private final List<AIDatasetRecord> data = List.of(
        new AIDatasetRecord("S-2", "cat", "cam-1", 128, "READY"),
        new AIDatasetRecord("S-1", "dog", "cam-2", 256, "PENDING"),
        new AIDatasetRecord("S-3", "cat", "cam-1", 64, "READY")
    );

    @Test
    void filterByStatusTest() {
        List<AIDatasetRecord> ready = analytics.filterByStatus(data, "READY");
        assertEquals(2, ready.size());
    }

    @Test
    void sortedLabelsTest() {
        assertEquals(List.of("CAT", "DOG"), analytics.sortedLabels(data));
    }

    @Test
    void countBySourceTest() {
        assertEquals(2L, analytics.countBySource(data).get("cam-1"));
        assertEquals(1L, analytics.countBySource(data).get("cam-2"));
    }

    @Test
    void maxByFeatureCountTest() {
        Optional<AIDatasetRecord> max = analytics.maxByFeatureCount(data);
        assertTrue(max.isPresent());
        assertEquals("S-1", max.get().id());
    }

    @Test
    void maxByFeatureCountEmptyTest() {
        assertTrue(analytics.maxByFeatureCount(List.of()).isEmpty());
    }

    @Test
    void sortByFeatureCountAndIdTest() {
        List<AIDatasetRecord> sorted = analytics.sortByFeatureCountAndId(data);
        assertEquals("S-3", sorted.get(0).id()); // 64
        assertEquals("S-2", sorted.get(1).id()); // 128
        assertEquals("S-1", sorted.get(2).id()); // 256
    }
} 