package test;

import library.models.Book;
import library.models.Library;
import library.utils.DataManager;
import org.junit.Before;
import org.junit.Test;
import proxy.MySet;
import proxy.ProxyCollection;

import java.io.*;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.Set;

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

        library.buyBook("Intro to Java", "Pushkin", 3, "123123321sdf", 2017);
        library.buyBook("Oak green lukomorie", "Vasya Pupkin", 2, "1233321231das", 2016);
        library.buyBook("Mick Jagger", "Rock-n-roll", 2, "23423", 2016);

        try {
            when(bufferedReader.readLine()).thenReturn("�� \u0005t \u000BRock-n-rollt \u000BMick Jaggersr \u0011java.lang.Integer\u0012⠤���8\u0002 \u0001I \u0005valuexr \u0010java.lang.Number���\u001D\u000B���\u0002  xp  \u0007�t \u000523423t \u0007Pushkint ");

            FileInputStream fis = null;
            try {
                fis = new FileInputStream("books.txt");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Collection<Book> collection = dataManager.deserializeBook(fis);
            Collection<Book> existsCollection = library.getCatalog();
            assertTrue(collection.equals(existsCollection));
            //assertTrue(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void init() {
        dataManager = new DataManager();
        library = new Library();
    }

    @Test
    public void testDeserializationBookProxy() {
        Book book = new Book("Mick Jagger", "Rock-n-roll", 2017, "23423");

        Book bookProxy = (Book) Proxy.newProxyInstance(
                Book.class.getClassLoader(),
                Book.class.getInterfaces(),
                new ProxyCollection());

        InputStream in = new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        };

        try {
            ObjectInputStream ois = new ObjectInputStream(in);
            bookProxy.readExternal(ois);
            assertTrue(book.equals(bookProxy));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //assertTrue(true);
    }
}
