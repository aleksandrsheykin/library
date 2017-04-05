package library.models;

import com.sun.org.apache.bcel.internal.classfile.SourceFile;
import com.sun.org.apache.regexp.internal.RE;
import library.models.*;

import java.util.*;

/**
 * Created by admin on 05.04.2017.
 */

public class Library {

    private Set<Book> catalog;
    private Set<BookInstance> store;
    private Set<Reader> readers;
    private Set<Booking> bookings;

    public Library() {
        catalog = new HashSet<>(1024);
        store = new HashSet<>(4096);
        readers = new HashSet<>(512);
        bookings = new HashSet<>(248);
    }

    public void buyBook(String title, String author, int quantity, String isbn, int year) {
        Book newBook = new Book(author, title, year, isbn);
        catalog.add(newBook);
        for (int i = 0; i <quantity ; i++) {
            BookInstance bookInstance = new BookInstance(newBook, UUID.randomUUID());
            store.add(bookInstance);
        }
    }

    public void takeBook(String firstname, String lastname, String secondname, final long passportNumber, String title) {
        Reader[] reader = (Reader[]) readers.stream().filter(()->r.setPassportNumber == passportNumber.toArray()[0]);  //java 8

        Reader tempReader = null;
        if (reader.length != 0) {
            tempReader = reader[0];
        } else {
            tempReader = new Reader(firstname, secondname, lastname, passportNumber);
            readers.add(tempReader);
        }

        BookInstance bookInstance = (BookInstance) store.stream().filter((s)->s.getBook().getTitle().equals(title)).toArray()[0];
        if (bookInstance == null) {
            System.out.println("No such book");
        }
        Booking booking = new Booking(bookInstance, reader, new Data(), new Date());

        bookings.add(booking);
        store.remove(bookInstance);
    }

    public void returnBook(String firstname, String lastname, String secondname, final long passportNumber, String title) {
        Reader reader = new Reader(firstname, secondname, lastname, passportNumber);
        Booking booking = bookings.stream().filter((b)->b.getBookInstance().getBook().getTitle().equals(title)
                                                    && b.getReader().equals(reader)).toArray()[0]);

        if (booking == null) {
            System.out.println("no sych book");
            return;
        }

        store.add(booking.getBookInstance());
        bookings.remove(booking);
    }

    public void showAllData() {
        for (Book book:catalog) {
            System.out.println(book);
        }
        for (BookInstance bookInstance:store) {
            System.out.println(bookInstance);
        }
        for (Reader reader:readers) {
            System.out.println(reader);
        }
        for (Booking booking:bookings) {
            System.out.println(booking);
        }
    }

    public Set<Book> getCatalog() {
        return catalog;
    }

    public void setCatalog(Set<Book> catalog) {
        this.catalog = catalog;
    }
}