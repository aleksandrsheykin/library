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
import java.util.Arrays;
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
                reader.writeExternal(oos);
        } catch(IOException ex) {
            ex.printStackTrace();
        }

        try(FileOutputStream fos = new FileOutputStream("bookings.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            for(Booking booking : bookings)
                //oos.writeObject(booking);
                booking.writeExternal(oos);
        } catch(IOException ex) {
            ex.printStackTrace();
        }

        try(FileOutputStream fos = new FileOutputStream("bookInstances.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            for(BookInstance bookInstance : bookInstances)
                //oos.writeObject(bookInstance);
                bookInstance.writeExternal(oos);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Set<Book> deserializeBook() {
        Set<Book> books = new HashSet<>();
        try(FileInputStream fis = new FileInputStream("books.txt");
            ObjectInputStream ois = new ObjectInputStream(fis)) {

            while (fis.available() > 0) {
                Book b = new Book();
                b.readExternal(ois);
                books.add(b);
            }

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

            /*Reader reader = null;
            while((reader = (Reader) ois.readObject()) != null) {
                readers.add(reader);
            }*/
            while (fis.available() > 0) {
                Reader r = new Reader();
                r.readExternal(ois);
                readers.add(r);
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

            while (fis.available() > 0) {
                BookInstance bi = new BookInstance();
                bi.readExternal(ois);
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

            while (fis.available() > 0) {
                Booking b = new Booking();
                b.readExternal(ois);
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

    public static void serializeClassToXml(Class c, Library library) {
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
            for (Method method: c.getMethods()) {
                Element m = doc.createElement("Method");
                methods.appendChild(m);
                Attr attr = doc.createAttribute(method.getName());
                attr.setValue(method.getReturnType().getName());
                m.setAttributeNode(attr);
                m.appendChild(doc.createTextNode("parametrs="+ Arrays.toString(method.getParameters())));
            }

            Element bookElementsXml = doc.createElement("BookElements");
            rootElement.appendChild(bookElementsXml);

            for(Book elementCatalog : library.getCatalog()) {
                Element bookElementXml = doc.createElement("Book");
                bookElementsXml.appendChild(bookElementXml);

                String[] fieldsList = new String[] {"title", "author", "isbn", "year"};

                for (String fieldName: fieldsList) {
                    Element bookTitleXml = doc.createElement(fieldName);
                    try {
                        Field f = Book.class.getDeclaredField(fieldName);
                        f.setAccessible(true);
                        bookElementXml.appendChild(bookTitleXml);
                        bookTitleXml.appendChild(doc.createTextNode(f.get(elementCatalog).toString()));
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            //transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("Books.xml"));
            transformer.transform(source, result);
            // Output to console for testing
            /*StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);*/


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

}
