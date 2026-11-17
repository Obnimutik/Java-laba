package edu.course.lab04;

import java.util.HashMap;
import java.util.Map;

public class SampleRegistry {
    // В качестве значения возьмем, например, точность модели (Double)
    private final Map<SampleId, Double> registry = new HashMap<>();

    public void addOrReplace(SampleId id, Double value) {
        registry.put(id, value);
    }

    public Double get(SampleId id) {
        return registry.get(id);
    }

    public void remove(SampleId id) {
        registry.remove(id);
    }

    public boolean contains(SampleId id) {
        return registry.containsKey(id);
    }

    public int size() {
        return registry.size();
    }
}