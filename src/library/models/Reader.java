package library.models;

import java.io.Externalizable;
import java.io.Serializable;

/**
 * Created by admin on 05.04.2017.
 */
public class Reader implements Serializable {
    private String fistname;
    private String secondname;
    private String lastname;
    private long passportNumber;
    private static long serrialVersion = 2L;

    public Reader(String fistname, String secondname, String lastname, long passportNumber) {
        this.fistname = fistname;
        this.secondname = secondname;
        this.lastname = lastname;
        this.passportNumber = passportNumber;
    }

    @Override
    public int hashCode() {
        return (int)passportNumber * 32;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if(!(obj instanceof Reader))
            return false;

        if (passportNumber != ((Reader) obj).passportNumber)
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "fistname='" + fistname + '\'' +
                ", secondname='" + secondname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", passportNumber=" + passportNumber +
                '}';
    }

    public long getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(long passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getFistname() {
        return fistname;
    }

    public String getSecondname() {
        return secondname;
    }

    public String getLastname() {
        return lastname;
    }
}
