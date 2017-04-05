package library.utils;

import library.models.*;
import library.models.Library;
import library.models.Reader;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by admin on 05.04.2017.
 */
public class DataManager {

    public static void serializeToFile(Set<Book> books, Set<Reader> readers, Set<Booking> bookings, Set<BookInstance> bookInstances) {
        try(FileOutputStream fos = new FileOutputStream("books.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            for(Book book : books)
                oos.writeObject(book);
        } catch(IOException ex) {
            ex.printStackTrace();
        }

        try(FileOutputStream fos = new FileOutputStream("readers.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            for(Reader reader : readers)
                oos.writeObject(reader);
        } catch(IOException ex) {
            ex.printStackTrace();
        }

        try(FileOutputStream fos = new FileOutputStream("bookings.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            for(Booking booking : bookings)
                oos.writeObject(booking);
        } catch(IOException ex) {
            ex.printStackTrace();
        }

        try(FileOutputStream fos = new FileOutputStream("bookInstances.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            for(BookInstance bookInstance : bookInstances)
                oos.writeObject(bookInstance);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Set<Book> deserializeBook() {
        Set<Book> books = new HashSet<>();
        try(FileInputStream fis = new FileInputStream("books.txt");
            ObjectInputStream ois = new ObjectInputStream(fis)) {

            Book book = null;
            while((book = (Book) ois.readObject()) != null) {
                books.add(book);
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            return books;
        }
    }

    public static Set<Reader> deserializeReader() {
        Set<Reader> readers = new HashSet<>();
        try(FileInputStream fis = new FileInputStream("readers.txt");
            ObjectInputStream ois = new ObjectInputStream(fis)) {

            Reader reader = null;
            while((reader = (Reader) ois.readObject()) != null) {
                readers.add(reader);
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            return readers;
        }
    }

    public static Set<BookInstance> deserializeBookInstance() {
        Set<BookInstance> bookInstances = new HashSet<>();
        try(FileInputStream fis = new FileInputStream("bookInstances.txt");
            ObjectInputStream ois = new ObjectInputStream(fis)) {

            BookInstance bi = null;
            while((bi = (BookInstance) ois.readObject()) != null) {
                bookInstances.add(bi);
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            return bookInstances;
        }
    }

    public static Set<Booking> deserializeBooking() {
        Set<Booking> Bookings = new HashSet<>();
        try(FileInputStream fis = new FileInputStream("bookings.txt");
            ObjectInputStream ois = new ObjectInputStream(fis)) {

            Booking b = null;
            while((b = (Booking) ois.readObject()) != null) {
                Bookings.add(b);
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            return Bookings;
        }
    }

    public static void ExternalizableToFile(Set<Book> books) {
        /*try(FileOutputStream fos = new FileOutputStream("books1.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            for(Book book : books)
                oos.writeExternal(book);

        } catch(IOException ex) {
            ex.printStackTrace();
        }*/
    }
}
