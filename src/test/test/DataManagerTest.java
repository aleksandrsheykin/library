package test;

import library.models.Book;
import library.models.Library;
import library.utils.DataManager;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by admin on 10.04.2017.
 */
public class DataManagerTest {

    private static DataManager dataManager;
    private static Library library;

    @Test
    public void testDeserializationBook() {
        File file = mock(File.class);
        FileReader fileReader = mock(FileReader.class);
        BufferedReader bufferedReader = mock(BufferedReader.class);

        try {
            when(bufferedReader.readLine()).thenReturn("�� \u0005wp \u0007Pushkin ");
            Collection<Book> collection = dataManager.deserializeBook();
            Collection<Book> existsCollection = library.getCatalog();
            assertTrue(collection.equals(existsCollection));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void init() {
        dataManager = new DataManager();
        library = new Library();
    }
}
