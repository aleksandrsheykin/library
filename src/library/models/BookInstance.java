package library.models;

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
    private static long serrialVersion = 3L;
    private String name;

    public BookInstance(Book book, UUID number) {
        this.book = book;
        this.number = number;
        this.name = "Aleksandr";

        bookHistory = new ArrayList<>(32);
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
        out.writeUTF(name);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.name = in.readUTF();
    }
}

