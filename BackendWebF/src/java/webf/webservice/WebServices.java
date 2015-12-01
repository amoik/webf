/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webf.webservice;

import java.util.ArrayList;
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
import webf.webservice.student.Student;

/**
 *
 * @author PU
 */
@WebService(serviceName = "WebServices")
public class WebServices
{
    @WebMethod
    public String hello(@WebParam(name = "name") String txt)
    {
        return "Diese Info kommt vom WS! Du hast '" + txt + "' geschickt!";
    }
    
    @WebMethod
    public int login(@WebParam String name, @WebParam String password)
    {
        SessionFactory sf = HibernateUtil.getSessionFactory();  //Initialisierung der SessionFactory
        Session s = sf.openSession();                           //Öffne eine Session 
        Transaction tx = null;
        int ret = -1;
        
        try{
            tx = s.beginTransaction();
            String hql = "FROM Person P WHERE P.username = :name";
            Query query = s.createQuery(hql);
            query.setParameter("name",name);
            List results = query.list();

            Person personFromDb = (Person)results.get(0);
            
            if(personFromDb.getPassword().equals(password))
            {
                ret = personFromDb.getPersonPk();
                
            }else{
                //failed
            }
            
            tx.commit();            //Transaktion durchführen
        } catch (Exception e) {
            //failed
            if(tx !=null){
                tx.rollback();      //Bei Fehlerfall => Rollback!
            }
        } finally {
            s.close();              //Session schließen egal ob Erfolg oder Fehler
        }
        
        return ret;
    }
    
    
    @WebMethod
    public Boolean createUser(@WebParam String name, @WebParam String password, @WebParam String role)
    {
        
        SessionFactory sf = HibernateUtil.getSessionFactory();  //Initialisierung der SessionFactory
        Session s = sf.openSession();                           //Öffne eine Session 
        Transaction tx = null;
        Boolean ret = false;
        
        try{
            tx = s.beginTransaction();
            
            //neuer user
            Person p = new Person();
            p.setUsername(name);
            p.setPassword(password);
            p.setRole(role);
            
            s.save(p);
            ret = true;
            
            tx.commit();            //Transaktion durchführen
        } catch (Exception e) {
            //failed
        
            if(tx !=null){
                tx.rollback();      //Bei Fehlerfall => Rollback!
            }
        } finally {
            s.close();              //Session schließen egal ob Erfolg oder Fehler
        }
        
        
        return ret;
    }

    @WebMethod
    public ArrayList<Student> getStudents()
    {
        ArrayList<Student> students = new ArrayList<Student>();
        students.add(new Student("Hansi"));
        students.add(new Student("Seppi"));
        students.add(new Student("Gurki"));
        students.add(new Student("Peppi"));
        students.add(new Student("Franzi"));
        students.add(new Student("Stinki"));
        students.add(new Student("Beidli"));
        
        return students;
    }

    @WebMethod
    public double add(@WebParam double one, @WebParam double two)
    {
        return one + two;
    }
    @WebMethod
    public double subtract(@WebParam double one, @WebParam double two)
    {
        return one - two;
    }
    @WebMethod
    public double multiply(@WebParam double one, @WebParam double two)
    {
        return one * two;
    }
    @WebMethod
    public double divide(@WebParam double one, @WebParam double two)
    {
        return one / two;
    }
    
    
    
    @WebMethod
    public ArrayList<Person> getPersons()
    {
        SessionFactory sf = HibernateUtil.getSessionFactory();  //Initialisierung der SessionFactory
        Session s = sf.openSession();                           //Öffne eine Session 
        Transaction tx = null;
        
        ArrayList<Person> ret = new ArrayList<Person>();
        
        try{
            tx = s.beginTransaction();
            String hql = "FROM Person P";
            Query query = s.createQuery(hql);
            List results = query.list();

            
            for (Object result : results)
            {
                Person p = new Person();
                p.setUsername(((Person) result).getUsername());
                p.setRole(((Person) result).getRole());
                ret.add(p);
            }
            
            
            tx.commit();            //Transaktion durchführen
        } catch (Exception e) {
            ret = null;
            if(tx !=null){
                tx.rollback();      //Bei Fehlerfall => Rollback!
            }
        } finally {
            s.close();              //Session schließen egal ob Erfolg oder Fehler
        }
        
        return ret;
    }
}
