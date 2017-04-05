package library.models;

import java.io.Serializable;

/**
 * Created by admin on 05.04.2017.
 */
public class Book implements Serializable{

    private String author;
    private String title;
    private int year;
    private String isbn;
    private static long serrialVersion = 21;

    public Book(String author, String title, int year, String isbn) {
        this.author = author;
        this.title = title;
        this.year = year;
        this.isbn = isbn;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;

        if(!(obj instanceof Book))
            return false;

        if(!this.isbn.equals(((Book) obj).isbn))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return super.toString();
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
}
