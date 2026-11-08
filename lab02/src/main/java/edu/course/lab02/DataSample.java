package edu.course.lab02;



enum SampleStatus {
    NEW,
    IN_PROGRESS,
    READY,
    REJECTED
}

public class DataSample {
    
    private String id;
    private String label;
    private SampleStatus status;
    private double[] features;

    public DataSample(String id, String label, double[] features) {
       
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id не может быть пустым");
        }

        if (label == null || label.isBlank()) {
            throw new IllegalArgumentException("label не может быть пустым");
        }

        if (features == null) {
            throw new IllegalArgumentException("features не может быть null");
        }

        this.id = id;
        this.label = label;
        this.features = features.clone();
        this.status = SampleStatus.NEW;
    }

    public void changeStatus(SampleStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("Статус не может быть null");
        }
        this.status = newStatus;
    }

    public boolean isReady() {
        return status == SampleStatus.READY;
    }

    public double averageFeature() {
        
        if (features.length == 0) {
            return 0.0;
        }
        double sum = 0.0;
        for (double f : features) {
            sum += f;
        }
        return sum / features.length;
    }

}
