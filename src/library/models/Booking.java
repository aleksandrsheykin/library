package library.models;

import java.util.Date;

/**
 * Created by admin on 05.04.2017.
 * Бронирование
 */
public class Booking {
    private BookInstance BookInstance;
    private Reader reader;
    private Date startDate;
    private Date finishDate;
    private Date returnDate;

    public Booking(library.models.BookInstance bookInstance, Reader reader, Date startOrder, Date finishDate) {
        BookInstance = bookInstance;
        this.reader = reader;
        this.startDate = startOrder;
        this.finishDate = finishDate;
    }

    @Override
    public int hashCode() {
        return BookInstance.hashCode() + reader.hashCode() + startDate.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;

        if(!(obj instanceof Booking))
            return false;

        if(!this.BookInstance.equals(((Booking) obj).BookInstance)
                && reader.equals(((Booking) obj).reader)
                && startDate.equals(((Booking) obj).startDate))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "BookInstance=" + BookInstance +
                ", reader=" + reader +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                ", returnOrder=" + returnDate +
                '}';
    }

    public Date getReturnDate() {
        return finishDate;
    }

    public void setReturnDate(Date d) {
        this.returnDate = d;
    }

    public library.models.BookInstance getBookInstance() {
        return BookInstance;
    }

    public Reader getReader() {
        return reader;
    }
}
