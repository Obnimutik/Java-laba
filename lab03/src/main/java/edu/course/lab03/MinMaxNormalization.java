package edu.course.lab03;

import java.util.Arrays;

public class MinMaxNormalization implements NormalizationStrategy {
    @Override
    public double[] normalize(double[] values) {
        if (values == null || values.length == 0) {
            return new double[0];
        }
        double min = Arrays.stream(values).min().orElse(0);
        double max = Arrays.stream(values).max().orElse(0);
        if (max == min) {
            return new double[values.length];
        }
        double[] result = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            result[i] = (values[i] - min) / (max - min);
        }
        return result;
    }
}