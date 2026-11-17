package edu.course.lab04;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class SampleRegistryTest {

    @Test
    void logicalKeyFindsValue() {
        SampleRegistry registry = new SampleRegistry();
        SampleId id1 = new SampleId("mnist", 42);
        SampleId id2 = new SampleId("mnist", 42); // Другой экземпляр, но логически равный

        registry.addOrReplace(id1, 0.99);
        assertEquals(0.99, registry.get(id2));
    }

    @Test
    void replacementDoesNotIncreaseSize() {
        SampleRegistry registry = new SampleRegistry();
        SampleId id1 = new SampleId("cifar10", 1);
        SampleId id2 = new SampleId("cifar10", 1);

        registry.addOrReplace(id1, 0.85);
        registry.addOrReplace(id2, 0.90);

        assertEquals(1, registry.size());
        assertEquals(0.90, registry.get(id1));
    }

    @Test
    void differentKeysWithSameHashCodeKeepDifferentValues() {

        Map<String, String> map = new HashMap<>();
        map.put("Aa", "value1"); 
        map.put("BB", "value2"); 
        
        assertEquals(2, map.size());
        assertEquals("value1", map.get("Aa"));
        assertEquals("value2", map.get("BB"));
    }

    @Test
    void nullAndDifferentTypeReturnFalse() {
        SampleId id = new SampleId("test", 1);
        assertFalse(id.equals(null));
        assertFalse(id.equals("string"));
    }

    static class MutableKey {
        String name;
        MutableKey(String name) { this.name = name; }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            return Objects.equals(name, ((MutableKey) o).name);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    @Test
    void mutableKeyBreaksHashMap() {
        Map<MutableKey, String> map = new HashMap<>();
        MutableKey key = new MutableKey("AI");
        map.put(key, "data");

        key.name = "ML"; 

        assertNull(map.get(key)); 

        assertEquals(1, map.size()); 
    }
}