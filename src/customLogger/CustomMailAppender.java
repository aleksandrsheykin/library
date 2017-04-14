package customLogger;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

/**
 * Created by admin on 14.04.2017.
 */
public class CustomMailAppender extends AppenderSkeleton {

    private String tutle;
    private String book;
    private String isbn;
    private String url;

    public String getTutle() {
        return tutle;
    }

    public void setTutle(String tutle) {
        this.tutle = tutle;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    protected void append(LoggingEvent event) {
        try
        {
            SendMailSSL.send("test.mail.for.java.777@gmail.com", layout.format(event));

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {

    }

    @Override
    public boolean requiresLayout() {
        return false;
    }

}
