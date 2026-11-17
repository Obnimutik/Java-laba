package edu.course.lab06;

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

    public String getId() { return id; }
    public String getLabel() { return label; }
    public String getSource() { return source; }
    public int getFeatureCount() { return featureCount; }
    public String getStatus() { return status; }
}