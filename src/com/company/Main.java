package com.company;

import library.models.Book;
import library.models.Library;
import library.models.Reader;
import library.utils.DataManager;

public class Main {

    public static void main(String[] args) {
        Library library = new Library();
        boolean deserializeble, serealiseble, createObjects = false;

        deserializeble = false;
        createObjects = true;
        serealiseble = true;

        if (deserializeble) {
            for(Book book : DataManager.deserialize())
                library.buyBook(book.getTitle(), book.getAuthor(), book.getYear(), book.getIsbn(), book.getYear());
        }

        if (createObjects){
            Reader jon = new Reader("Jone", "Karlovich", "Konor", 12345);
            Reader sara = new Reader("Sara", "Serheevna", "Konor", 54321);

            library.buyBook("Intro to Java", "Pushkin", 3, "123123321sdf", 2017);
            library.buyBook("Oak green lukomorie", "Vasya Pupkin", 2, "1233321231das", 2016);

            library.takeBook("Jone", "Karlovich", "Konor", 12345, "Intro to Java");
            library.takeBook("Sara", "Serheevna", "Konor", 54321, "Oak green lukomorie");

            library.returnBook("Jone", "Karlovich", "Konor", 12345, "Intro to Java");
        }

        if (serealiseble) {
            DataManager.serializeToFile(library.getCatalog());
        }

        library.showAllData();
    }

}
