package library.models;

import sun.text.normalizer.UTF16;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by admin on 05.04.2017.
 */
public class BookInstance implements Externalizable {
    private Book book;
    private UUID number;
    private List<Reader> bookHistory;
    private static long serialVersionUID = 3L;

    public BookInstance(Book book, UUID number) {
        this.book = book;
        this.number = number;

        bookHistory = new ArrayList<>(32);
    }

    public BookInstance() {
    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (!(obj instanceof BookInstance))
            return false;

        if (!(this.number.equals(((BookInstance) obj).number)))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return book + "@" + number;
    }

    public Book getBook() {
        return book;
    }

    public UUID getNumber() {
        return number;
    }

    public List<Reader> getBookHistory() {
        return bookHistory;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(book);
        out.writeObject(number);
        out.writeObject(bookHistory);
        out.writeObject("Alex");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.book = (Book) in.readObject();
        this.number = (UUID) in.readObject();
        this.bookHistory = (List<Reader>) in.readObject();
        System.out.println((String) in.readObject());
    }
}

