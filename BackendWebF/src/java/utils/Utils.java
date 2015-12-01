/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author anti88
 */
public class Utils
{
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
