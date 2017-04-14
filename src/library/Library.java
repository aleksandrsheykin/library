package library.models;

import com.sun.org.apache.bcel.internal.classfile.SourceFile;
import com.sun.org.apache.regexp.internal.RE;
import library.models.*;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.w3c.dom.DOMConfiguration;

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

    public void addReader(String fistname, String secondname, String lastname, long passportNumber) {
        Reader newReader = new Reader(fistname, secondname, lastname, passportNumber);
        readers.add(newReader);
    }

    public void addBookInstance(Book book, UUID number) {
        BookInstance bi = new BookInstance(book, number);
        store.add(bi);
    }

    public void addBooking(BookInstance bookInstance, Reader reader, Date startDate, Date finishDate) {
        Booking b = new Booking(bookInstance, reader, startDate, finishDate);
        bookings.add(b);
    }

    public void takeBook(String firstname, String lastname, String secondname, final long passportNumber, String title) {
        Object[] reader = readers.stream().filter((r)->r.getPassportNumber() == passportNumber).toArray();

        Reader tempReader = null;
        if (reader.length != 0) {
            tempReader = (Reader) reader[0];
        } else {
            tempReader = new Reader(firstname, secondname, lastname, passportNumber);
            readers.add(tempReader);
        }

        BookInstance bookInstance = (BookInstance) store.stream().filter((s)->s.getBook().getTitle().equals(title)).toArray()[0];
        if (bookInstance == null) {
            System.out.println("No such book");
            return;
        }

        Booking booking = new Booking(bookInstance, tempReader, new Date(), new Date());
        bookings.add(booking);
        store.remove(bookInstance);
    }

    public void returnBook(String firstname, String lastname, String secondname, final long passportNumber, String title) {
        Reader reader = new Reader(firstname, secondname, lastname, passportNumber);
        Booking booking = (Booking) bookings.stream().filter((b)->b.getBookInstance().getBook().getTitle().equals(title) &&
                                                  b.getReader().equals(reader)).toArray()[0];

        if (booking == null) {
            System.out.println("no such book");
            return;
        }

        store.add(booking.getBookInstance());
        bookings.remove(booking);
    }

    public void showAllData() {
        for (Book book:catalog) {
            System.out.println("book | " + book);
        }
        for (BookInstance bookInstance:store) {
            System.out.println("bookInstance | " + bookInstance);
        }
        for (Reader reader:readers) {
            System.out.println("reader | " + reader);
        }
        for (Booking booking:bookings) {
            System.out.println("booking | " + booking);
        }
    }

    public Set<Book> getCatalog() {
        return catalog;
    }

    public Set<BookInstance> getStore() {
        return store;
    }

    public Set<Reader> getReaders() {
        return readers;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setCatalog(Set<Book> catalog) {
        this.catalog = catalog;
    }
}