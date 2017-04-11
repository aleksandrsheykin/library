package library.models;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

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

    public void print() {
        System.out.println(Book.class.getCanonicalName());

        for (Method method:
             this.getClass().getMethods()) {

            System.out.println(method.getName());
            System.out.println(method.getReturnType().getName());

            for (Parameter param :method.getParameters()) {
                System.out.println(param.getName()+" "+param.getType()+" "+param.getName());
            }

            System.out.println(method.getModifiers());
        }

        try {
            for (Field field : Class.forName("library.models.Book").getFields()) {
                System.out.println(field.getName());
                System.out.println(field.getType().getName());
                System.out.println(field.isAccessible());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Book(String author, String title, int year, String isbn) {
        this.author = author;
        this.title = title;
        this.year = year;
        this.isbn = isbn;
        this.name = "Aleksandr";
    }

    public Book() {
        //
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
        out.writeInt(year);
        out.writeUTF(isbn);
        out.writeUTF(this.name);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.author = (String) in.readUTF();
        this.title = (String) in.readUTF();
        this.year = (Integer) in.readInt();
        this.isbn = (String) in.readUTF();
        this.name = (String) in.readUTF();
        //System.out.println(in.readUTF());
    }

}
