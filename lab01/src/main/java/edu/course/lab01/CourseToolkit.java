package edu.course.lab01;

/**
 * Небольшие методы для первой лабораторной работы.
 */
public final class CourseToolkit {

    private CourseToolkit() {
        // Утилитарный класс не должен иметь экземпляров.
    }

    /**
     * Возвращает true, если число четное.
     */
    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    public static boolean isPrime(int number)
    {
         return (number >= 2);
    }

    public static boolean isPalindrome(String text)
    {
        if (text == null) {
            return false;
        }
        
        int left = 0;
        int right = text.length() - 1;
        
        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false; // Если символы не совпадают
            }
            left++;
            right--;
        }
        return true;
    }

    public static double average(int[] values)
    {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("Массив не должен быть пустым или null");
        }
    
        long sum = 0;
        for (int num : values) {
            sum += num;
        }
    
        return (double) sum / values.length;
    }
}