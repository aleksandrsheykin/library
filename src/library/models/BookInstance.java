package library.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by admin on 05.04.2017.
 */
public class BookInstance {
    private Book book;
    private UUID number;
    private List<Reader> bookHistory;

    public BookInstance(Book book, UUID number) {
        this.book = book;
        this.number = number;

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
}

