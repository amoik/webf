package webf.beans;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author anti88
 */
public class Utils {
    
    static public void tinf(String msg)
    {
        System.out.println(msg);
    } 
    public static Date strToDate(String d)
    {
        Date da = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", new Locale("us"));
        
        try
        {
            da = sdf.parse(d);
        }
        catch (Exception e)
        {
            
        }
        return da;
    }
    
    public static String dateToStr(XMLGregorianCalendarImpl gcd)
    {
        Date d = gcd.toGregorianCalendar().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy");
        return sdf.format(d);
    }
    
         
    public static void addMessage(String summary, String detail)
    {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}
