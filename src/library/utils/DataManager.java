package library.utils;

import library.models.*;
import library.models.Library;
import library.models.Reader;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import sun.misc.IOUtils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Collection;
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
                //oos.writeObject(book);
                book.writeExternal(oos);
            oos.flush();
            fos.close();
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
                System.out.println("name | " + book.getName());
                books.add(book);
            }

            /*Book book = new Book();
            book.readExternal(ois);
            Book book2 = new Book();
            book2.readExternal(ois);

            System.out.println("name | " + book.toString());
            System.out.println("name | " + book2.toString());*/

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

    public static void serializeClassToFileXml(Class c, Library library) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element rootElement = doc.createElement("Books");
            doc.appendChild(rootElement);

            Element book = doc.createElement("BookClass");
            rootElement.appendChild(book);

            Element fields = doc.createElement("Fields");
            book.appendChild(fields);

            for (Field field : c.getDeclaredFields()) {
                Element f = doc.createElement("Field");
                fields.appendChild(f);

                Attr attr = doc.createAttribute(field.getName());
                attr.setValue(field.getType().getName());
                f.setAttributeNode(attr);

                f.appendChild(doc.createTextNode("isAccessible="+field.isAccessible()));
            }


            Element methods = doc.createElement("Methods");
            book.appendChild(methods);
            for (Method method: c.getClass().getMethods()) {
                Element m = doc.createElement("Method");
                methods.appendChild(m);
                Attr attr = doc.createAttribute(method.getName());
                attr.setValue(method.getReturnType().getName());
                m.setAttributeNode(attr);
            }

            Element bookElementsXml = doc.createElement("BookElements");
            rootElement.appendChild(bookElementsXml);

            for(Book elementCatalog : library.getCatalog()) {
                Element bookElementXml = doc.createElement("Book");
                bookElementsXml.appendChild(bookElementXml);

                Element bookTitleXml = doc.createElement("Title");
                bookElementXml.appendChild(bookTitleXml);
                bookTitleXml.appendChild(doc.createTextNode(elementCatalog.getTitle()));

                Element bookAuthorXml = doc.createElement("Author");
                bookElementXml.appendChild(bookAuthorXml);
                bookAuthorXml.appendChild(doc.createTextNode(elementCatalog.getAuthor()));

                Element bookIsbnXml = doc.createElement("Isbn");
                bookElementXml.appendChild(bookIsbnXml);
                bookIsbnXml.appendChild(doc.createTextNode(elementCatalog.getIsbn()));

                Element bookYearXml = doc.createElement("Year");
                bookElementXml.appendChild(bookYearXml);
                bookYearXml.appendChild(doc.createTextNode(Integer.toString(elementCatalog.getYear())));
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("Books.xml"));
            transformer.transform(source, result);
            // Output to console for testing
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

}
