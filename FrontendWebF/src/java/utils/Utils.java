package utils;

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
        if(gcd == null)
            return "00.00.00";
        
        Date d = gcd.toGregorianCalendar().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(d);
    }
    
         
    public static void addMessage(int type, String summary, String detail)
    {
        FacesMessage message;
        
        switch(type)
        {
            case 0:
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
                break;
            case 1:
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, summary, detail);
                break;
            case 2:
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
                break;
            case 3:
                message = new FacesMessage(FacesMessage.SEVERITY_FATAL, summary, detail);
                break;
            default:
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
                break;
        }
        
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}
