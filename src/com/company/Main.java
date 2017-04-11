package com.company;

import com.sun.org.apache.regexp.internal.RE;
import library.models.*;
import library.models.Library;
import library.utils.DataManager;
import proxy.MySet;
import proxy.ProxyCollection;

import java.lang.reflect.Proxy;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Library library = new Library();
        boolean deserializeble, serealiseble, createObjects = false;

        //Book book = new Book("Mick Jagger", "Rock-n-roll", 2017, "23423");
        //book.print();

        /*ProxyCollection proxyCollection = new ProxyCollection();
        Set<Book> catalog = (Set<Book>) Proxy.newProxyInstance(
                MySet.class.getClassLoader(),
                MySet.class.getInterfaces(),
                new ProxyCollection());
        System.out.println("Proxy returned = " + catalog.contains(2));*/

        deserializeble = true;
        createObjects = false;
        serealiseble = false;
        //serealiseble = false;

        if (deserializeble) {
            for(Book book : DataManager.deserializeBook())
                library.buyBook(book.getTitle(), book.getAuthor(), 1, book.getIsbn(), book.getYear());

            /*for(Reader reader : DataManager.deserializeReader())
                library.addReader(reader.getFistname(), reader.getSecondname(), reader.getLastname(), reader.getPassportNumber());

            for(BookInstance bookInstance: DataManager.deserializeBookInstance())
                library.addBookInstance(bookInstance.getBook(), bookInstance.getNumber());

            for(Booking booking: DataManager.deserializeBooking())
                library.addBooking(booking.getBookInstance(), booking.getReader(), booking.getStartDate(), booking.getFinishDate());*/
        }

        if (createObjects){
            Reader jon = new Reader("Jone", "Karlovich", "Konor", 12345);
            Reader sara = new Reader("Sara", "Serheevna", "Konor", 54321);

            library.buyBook("Intro to Java", "Pushkin", 3, "123123321sdf", 2017);
            library.buyBook("Oak green lukomorie", "Vasya Pupkin", 2, "1233321231das", 2016);

            //library.takeBook("Jone", "Karlovich", "Konor", 12345, "Intro to Java");
            //library.takeBook("Sara", "Serheevna", "Konor", 54321, "Oak green lukomorie");

            //library.returnBook("Jone", "Karlovich", "Konor", 12345, "Intro to Java");
        }

        if (serealiseble) {
            DataManager.serializeToFile(library.getCatalog(), library.getReaders(), library.getBookings(), library.getStore());
        }

        //DataManager.serializeClassToFileXml(Book.class, library);

        library.showAllData();
    }

}
