package edu.course.lab05;

import java.util.List;

public class DatasetLoadResult {
    private final List<AIDatasetRecord> validRecords;
    private final List<String> errors;

    public DatasetLoadResult(List<AIDatasetRecord> validRecords, List<String> errors) {
        this.validRecords = validRecords;
        this.errors = errors;
    }

    public List<AIDatasetRecord> getValidRecords() { return validRecords; }
    public List<String> getErrors() { return errors; }
}