package com.company;

import com.sun.org.apache.regexp.internal.RE;
import customLogger.SendMailSSL;
import library.models.*;
import library.models.Library;
import library.models.Reader;
import library.utils.DataManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import proxy.MySet;
import proxy.ProxyCollection;

import java.io.*;
import java.lang.reflect.Proxy;
import java.util.Set;

public class Main {

    static {
        DOMConfigurator.configure("log4j.xml");
    }
    private static Logger logger = Logger.getLogger(Library.class);

    public static void main(String[] args) {
        Library library = new Library();
        boolean deserializeble, serealiseble, createObjects, serializeToXml, proxy, reflection, dbconnection = false;
        boolean logging = false;

        deserializeble = false;
        createObjects = true;
        serealiseble = false;
        serializeToXml = false;
        reflection = false;
        proxy = false;
        dbconnection = false;
        logging = false;

        if (logging) {
            logger.warn("it log message!");
        }

        if (dbconnection) {
            DataBaseManager dataBaseManager = new DataBaseManager();
            //dataBaseManager.insert();
            dataBaseManager.select();
        }

        if (proxy) {
            ProxyCollection proxyCollection = new ProxyCollection();
            Set<Book> catalog = (Set<Book>) Proxy.newProxyInstance(
                    MySet.class.getClassLoader(),
                    MySet.class.getInterfaces(),
                    new ProxyCollection());
            System.out.println("Proxy returned = " + catalog.contains(2));

        }

        if (deserializeble) {

            BufferedReader bufferedReader = null;
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(new File("Books.txt"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            for(Book book : DataManager.deserializeBook(fileInputStream))
                library.buyBook(book.getTitle(), book.getAuthor(), 1, book.getIsbn(), book.getYear());

            for(Reader reader : DataManager.deserializeReader())
                library.addReader(reader.getFistname(), reader.getSecondname(), reader.getLastname(), reader.getPassportNumber());

            for(BookInstance bookInstance: DataManager.deserializeBookInstance())
                library.addBookInstance(bookInstance.getBook(), bookInstance.getNumber());

            for(Booking booking: DataManager.deserializeBooking())
                library.addBooking(booking.getBookInstance(), booking.getReader(), booking.getStartDate(), booking.getFinishDate());
        }

        if (createObjects){
            Reader jon = new Reader("Jone", "Karlovich", "Konor", 12345);
            Reader sara = new Reader("Sara", "Serheevna", "Konor", 54321);

            library.buyBook("Intro to Java", "Pushkin", 3, "123123321sdf", 2017);
            library.buyBook("Oak green lukomorie", "Vasya Pupkin", 2, "1233321231das", 2016);
            library.buyBook("Mick Jagger", "Rock-n-roll", 2, "23423", 2016);

            library.takeBook("Jone", "Karlovich", "Konor", 12345, "Intro to Java");
            library.takeBook("Sara", "Serheevna", "Konor", 54321, "Oak green lukomorie");

            library.returnBook("Jone", "Karlovich", "Konor", 12345, "Intro to Java");
        }

        if (serealiseble) {
            DataManager.serializeToFile(library.getCatalog(), library.getReaders(), library.getBookings(), library.getStore());
        }

        if (serializeToXml) {
            DataManager.serializeClassToXml(Book.class, library);
        }

        if (reflection) {
            Book book = new Book("Mick Jagger", "Rock-n-roll", 2017, "23423");
            book.print();
        }

        library.showAllData();
    }

}
