package customLogger;

import library.models.Book;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;

/**
 * Created by admin on 14.04.2017.
 */
public class CustomLogLayout extends PatternLayout {
    public String format(LoggingEvent event)
    {
        StringBuffer sb = new StringBuffer();

        sb.append("<h1>Log: ").append(event.getLevel().toString()).append("</h1>");
        sb.append("\n");
        sb.append("message: ").append((String) event.getMessage());
        sb.append("\n");

        return sb.toString();
    }
}
