/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webf.webservice;

import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import webf.hibernate.HibernateUtil;
import webf.hibernate.db.Person;
import webf.webservice.login.types.LoginRequestType;
import webf.webservice.login.LoginResponse;
import webf.webservice.login.types.LoginResponseType;

/**
 *
 * @author PU
 */
@WebService(serviceName = "WebServices")
public class WebServices {

    /**
     * This is a sample web service operation
     */
    @WebMethod
    public String hello(@WebParam(name = "name") String txt) {
        return "Diese Info kommt vom WS! Du hast '" + txt + "' geschickt!";
    }

    /**
     * Web service operation
     */
    @WebMethod
    public LoginResponseType login(@WebParam(name = "parameter") LoginRequestType parameter) {
        
        //String username = parameter.getLoginRequest().getUsername();
        LoginResponse lr = new LoginResponse();
        LoginResponseType lrt = new LoginResponseType();
        
        SessionFactory sf = HibernateUtil.getSessionFactory();  //Initialisierung der SessionFactory
        Session s = sf.openSession();                           //Öffne eine Session 
        Transaction tx = null;
        
        try{
            tx = s.beginTransaction();                          //Beginne Transaktion
            String hql = "FROM Person P WHERE P.username = :name";  //HQL Query um Person zu suchen
            Query query = s.createQuery(hql);                   //HQL Query zuweisen
            query.setParameter("name",parameter.getLoginRequest().getUsername());                    //Wert für den namen einfügen (gegen SQL Injection!)
            List results = query.list();                        //Abfrage durchführen

            Person personFromDb = (Person)results.get(0);       //Resultat in person casten
            
            if(personFromDb.getPassword().equals(parameter.getLoginRequest().getPassw())){             //Überprüfen des Passworts und entsprechend Response mit Sccuess/Failure info befüllen
                lr.setStatus("Success!");
                lr.setUserId(String.valueOf(personFromDb.getPersonPk()));
                
            }else{
                lr.setStatus("Failed!");
            }
            
            tx.commit();            //Transaktion durchführen
        } catch (Exception e) {
            lr.setStatus("Failed!");
        
            if(tx !=null){
                tx.rollback();      //Bei Fehlerfall => Rollback!
            }
        } finally {
            s.close();              //Session schließen egal ob Erfolg oder Fehler
        }
        
        lrt.setLoginResponse(lr);
        
        return lrt;
    }
    
    
    @WebMethod
    public int createUser(@WebParam String name)
    {

        return 1;
    }

    /**
     * Web service operation
     */
    @WebMethod
    public double floattest(@WebParam int i, @WebParam int j) {
        //TODO write your implementation code here:
        double ret = 1.11341232344 + i + j;
        return ret;
    }
    @WebMethod
    public int add(@WebParam int i, @WebParam int j) {
        int k = i + j;
        return k;
      }
    @WebMethod
    public int inttest(@WebParam int i, @WebParam int j) {
        int k = i - j;
        return k;
      }
}
