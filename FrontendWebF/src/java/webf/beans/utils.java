package webf.beans;

import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.bind.DatatypeConverter;
/**
 *
 * @author anti88
 */
public class utils {
    
    static public void tinf(String msg)
    {
        System.out.println(msg);
    }
    


    public static Date unmarshal(String dateTime) {
        return DatatypeConverter.parseDate(dateTime).getTime();
    }

    public static String marshalDate(Date date) {
        final GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return DatatypeConverter.printDate(calendar);
    }

    public static String marshalDateTime(Date dateTime) {
        final GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(dateTime);
        return DatatypeConverter.printDateTime(calendar);
    }
}
