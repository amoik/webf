/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.webf.ws;

import at.webf.database.HibernateUtil;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author amoik
 */
@WebService(serviceName = "WEBFService")
public class WEBFService {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "login")
    public LoginResponseType login(@WebParam(name = "parameter") LoginRequestType parameter) {
        
        LoginResponseType lrt = new LoginResponseType();
        LoginResponse lr = new LoginResponse();
        
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        
        Transaction tx = null;
        
        try
        {
            
        }
        catch (Exception e)
        {
            
        }
        finally
        {
            
        }
        
        
        return null;
    }


}
