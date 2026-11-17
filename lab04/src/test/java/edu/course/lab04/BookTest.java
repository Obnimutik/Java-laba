package edu.course.lab04;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void bookInMapIsFoundByEqualBookInstance() {
        Map<Book, String> library = new HashMap<>();
        Book book1 = new Book("978-3-16-148410-0", "Java Basics");
        Book book2 = new Book("978-3-16-148410-0", "Java Basics (2nd Edition)"); // Тот же ISBN

        library.put(book1, "Shelf A1");

        // Ищем по второму экземпляру, но с тем же ISBN
        assertEquals("Shelf A1", library.get(book2));
        assertEquals(book1, book2);
        assertEquals(book1.hashCode(), book2.hashCode());
    }

    @Test
    void booksWithDifferentIsbnAreNotEqual() {
        Book book1 = new Book("111", "Book A");
        Book book2 = new Book("222", "Book A");
        assertNotEquals(book1, book2);
    }
}