package library.models;

import java.io.*;

/**
 * Created by admin on 05.04.2017.
 */
public class Book implements Externalizable {

    private String author;
    private String title;
    private int year;
    private String isbn;
    private static long serrialVersion = 3L;
    private String name;

    public Book(String author, String title, int year, String isbn) {
        this.author = author;
        this.title = title;
        this.year = year;
        this.isbn = isbn;
        this.name = "Aleksandr";
    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (!(obj instanceof Book))
            return false;

        if (!this.isbn.equals(((Book) obj).isbn))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return author + "@" + title + "@" + year + "@" + isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(author);
        out.writeUTF(title);
        out.write(year);
        out.writeUTF(isbn);
        out.writeUTF(name);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.author = in.readUTF();
        this.title = in.readUTF();
        this.year = in.readInt();
        this.isbn = in.readUTF();
        this.name = in.readUTF();
    }
}
