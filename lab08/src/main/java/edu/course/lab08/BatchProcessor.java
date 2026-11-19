package edu.course.lab08;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class BatchProcessor {

    // 1. Последовательная обработка (в одном потоке)
    public <T, R> List<TaskResult<R>> processSequentially(List<T> inputs, TaskFunction<T, R> function) {
        List<TaskResult<R>> results = new ArrayList<>();
        for (int i = 0; i < inputs.size(); i++) {
            try {
                R value = function.apply(inputs.get(i));
                results.add(TaskResult.success(i, value));
            } catch (Exception e) {
                results.add(TaskResult.failure(i, e));
            }
        }
        return results;
    }

    // 2. Конкурентная обработка (в нескольких потоках)
    public <T, R> List<TaskResult<R>> processConcurrently(
            List<T> inputs, TaskFunction<T, R> function, ExecutorService executor) throws InterruptedException {
        
        List<Future<TaskResult<R>>> futures = new ArrayList<>();

        // Отправляем задачи в пул потоков
        for (int i = 0; i < inputs.size(); i++) {
            final int index = i;
            final T input = inputs.get(i);
            
            futures.add(executor.submit(() -> {
                try {
                    R value = function.apply(input);
                    return TaskResult.success(index, value);
                } catch (Exception e) {
                    return TaskResult.failure(index, e);
                }
            }));
        }

        // Собираем результаты в том же порядке, в котором отправляли
        List<TaskResult<R>> results = new ArrayList<>();
        for (Future<TaskResult<R>> future : futures) {
            try {
                results.add(future.get());
            } catch (ExecutionException e) {
                // Если поток упал фатально (до catch внутри задачи)
                results.add(TaskResult.failure(results.size(), (Exception) e.getCause()));
            }
        }
        return results;
    }
}