package webf.beans;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
    
}
