package edu.course.lab01;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class CourseToolkitTest {

    @Test
    void returnsTrueForEvenNumber() {
        boolean result = CourseToolkit.isEven(8);

        assertTrue(result);
    }

    @Test
    void returnsFalseForOddNumber() {
        boolean result = CourseToolkit.isEven(7);

        assertFalse(result);
    }

    @Test
    void returnsTrueForZiro() {
        boolean result = CourseToolkit.isEven(0);

        assertTrue(result);
    }

    @Test
    void returnsTrueForNegativeEvenNumber() {
        boolean result = CourseToolkit.isEven(-8);

        assertTrue(result);
    }

     @Test
    void returnsFalseForPrime() {
        boolean result = CourseToolkit.isPrime(-8);

        assertFalse(result);
    }

    @Test
    void returnsTrueForPrime() {
        boolean result = CourseToolkit.isPrime(4);

        assertTrue(result);
    }
    
     @Test
    void returnsTrueFor2Prime() {
        boolean result = CourseToolkit.isPrime(2);

        assertTrue(result);
    }

     @Test
    void returnsFalseFor0Prime() {
        boolean result = CourseToolkit.isPrime(0);

        assertFalse(result);
    }

    @Test
    void returnsTrueForPalindrom() {
        boolean result = CourseToolkit.isPalindrome("котток");

        assertTrue(result);
    }

    @Test
    void returnsTrueForCAPSPalindrom() {
        boolean result = CourseToolkit.isPalindrome("КОТТОК");

        assertTrue(result);
    }

    @Test
    void returnsFalseForPalindrom() {
        boolean result = CourseToolkit.isPalindrome("rjn");

        assertFalse(result);
    }

    @Test
    void returnsTrueForAverage() {
     
        int[] arg = {1, 2, 3};

        boolean result = (2 == CourseToolkit.average(arg));

        assertTrue(result);
    }

    @Test
    void returnsFalseForAverage() {
     
        int[] arg = {1, 2, 3};

        boolean result = (1 == CourseToolkit.average(arg));

        assertFalse(result);
    }

    @Test
    void returnsTrueForErrorAverage()
     {
     
        int[] arg = {};
        boolean result;

       try {
            // 2. Вызываем метод, который должен выбросить ошибку
            CourseToolkit.average(arg);
        
            // Если код дошел до этой строчки, значит исключение НЕ выбросилось
            result = false; 
        } catch (IllegalArgumentException e) {
            // Если исключение перехвачено — все правильно
            result = true; 
        }

        // 3. Проверяем, что результат true
        assertTrue(result);
    }



    //test commit
}
