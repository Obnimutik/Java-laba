package edu.course.lab05;

import java.util.Objects;

public final class AIDatasetRecord {
    private final String id;
    private final String label;
    private final String source;
    private final int featureCount;
    private final String status;

    public AIDatasetRecord(String id, String label, String source, int featureCount, String status) {
        this.id = id;
        this.label = label;
        this.source = source;
        this.featureCount = featureCount;
        this.status = status;
    }

    // Геттеры
    public String getId() { return id; }
    public String getLabel() { return label; }
    public String getSource() { return source; }
    public int getFeatureCount() { return featureCount; }
    public String getStatus() { return status; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AIDatasetRecord that = (AIDatasetRecord) o;
        return featureCount == that.featureCount &&
               Objects.equals(id, that.id) &&
               Objects.equals(label, that.label) &&
               Objects.equals(source, that.source) &&
               Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, source, featureCount, status);
    }

    @Override
    public String toString() {
        return "AIDatasetRecord{" + "id='" + id + '\'' + ", label='" + label + '\'' + '}';
    }
}