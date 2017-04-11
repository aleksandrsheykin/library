package test;

import library.models.*;
import library.models.Book;
import library.models.Library;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by admin on 10.04.2017.
 */
public class MainTest {

    private static Library library;
    private static Reader reader;

    @Test
    public void byBookTest() {
        library.buyBook("Intro to Java", "Pushkin", 3, "123123321sdf", 2017);
        assertEquals(1, library.getCatalog().size());
        Book book = new Book("Pushkin", "Intro to Java", 2017, "123123321sdf");
        assertTrue(library.getCatalog().contains(book));
        Book bookFromCatalog = library.getCatalog().iterator().next();
        assertTrue(book.getTitle().equals(bookFromCatalog.getTitle()));
        assertTrue(book.getAuthor().equals(bookFromCatalog.getAuthor()));
        assertTrue(book.getIsbn().equals(bookFromCatalog.getIsbn()));
        assertTrue(book.getYear() == bookFromCatalog.getYear());
    }

    @Test
    public void buyBookTestStore() {
        library.buyBook("Intro to Java", "Pushkin", 3, "123123321sdf", 2017);
        assertEquals(3, library.getStore().size());
        Book book = new Book("Pushkin", "Intro to Java", 2017, "123123321sdf");
        for (BookInstance bi : library.getStore()) {
            library.getStore();
            Book bookFromInstance = bi.getBook();
            assertTrue(book.getTitle().equals(bookFromInstance.getTitle()));
            assertTrue(book.getAuthor().equals(bookFromInstance.getAuthor()));
            assertTrue(book.getIsbn().equals(bookFromInstance.getIsbn()));
            assertTrue(book.getYear() == bookFromInstance.getYear());
        }
    }

    @After
    public void clearAll() {
        library = new Library();
    }

    @Test
    public void readerTest() {
        reader = new Reader("Mick", "Sergeevich", "Jagger", 1236548);
        assertTrue(reader.getFistname() == "Mick");
    }

    @BeforeClass
    public static void init() {
        library = new Library();
    }

}