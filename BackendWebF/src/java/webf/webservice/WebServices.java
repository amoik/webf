/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webf.webservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import static utils.Utils.strToDate;
import static utils.Utils.tinf;
import webf.hibernate.HibernateUtil;
import webf.hibernate.db.Course;
import webf.hibernate.db.Person;
import webf.hibernate.db.PersonCourseMembership;
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
    public Boolean deletePerson(@WebParam int id)
    {
        SessionFactory sf = HibernateUtil.getSessionFactory();  //Initialisierung der SessionFactory
        Session s = sf.openSession();                           //Öffne eine Session 
        Transaction tx = null;
        Boolean ret = false;
        Person p = getPerson(id);
        
        try{
            tx = s.beginTransaction();
            
            s.delete(p);
            ret = true;
            
            tx.commit();            //Transaktion durchführen
        } catch (Exception e) {
            tinf("delete of person " + id + " failed");
        
            if(tx !=null){
                tx.rollback();      //Bei Fehlerfall => Rollback!
            }
        } finally {
            s.close();              //Session schließen egal ob Erfolg oder Fehler
        }
        return ret;
    }

    
    @WebMethod
    public Boolean createPerson(@WebParam String name, @WebParam String password, @WebParam String role, @WebParam String firstname, @WebParam String lastname, @WebParam String birthday)
    {
        
        SessionFactory sf = HibernateUtil.getSessionFactory();  //Initialisierung der SessionFactory
        Session s = sf.openSession();                           //Öffne eine Session 
        Transaction tx = null;
        Boolean ret = false;
        
        try{

            tx = s.beginTransaction();
            Date d = strToDate(birthday);
            
            //neuer user
            Person p = new Person();
            p.setUsername(name);
            p.setPassword(password);
            p.setRole(role);
            p.setFirstname(firstname);
            p.setLastname(lastname);
            p.setBirthday(d);
            
            s.save(p);
            
            tx.commit();            //Transaktion durchführen
            ret = true;
        } catch (Exception e) {
            //failed
            System.out.println("Save Fehlgeschlagen");
        
            if(tx !=null){
                tx.rollback();      //Bei Fehlerfall => Rollback!
            }
        } finally {
            s.close();              //Session schließen egal ob Erfolg oder Fehler
        }
        return ret;
    }

    @WebMethod
    public ArrayList<Person> getStudents()
    {
        SessionFactory sf = HibernateUtil.getSessionFactory();  //Initialisierung der SessionFactory
        Session s = sf.openSession();                           //Öffne eine Session 
        Transaction tx = null;
        
        ArrayList<Person> ret = new ArrayList<Person>();
        
        try{
            tx = s.beginTransaction();
            String hql = "FROM Person P WHERE P.role = 'Student'";
            Query query = s.createQuery(hql);
            List results = query.list();
            
            for (Object result : results)
            {
                Person p = new Person();
                p.setUsername(((Person) result).getUsername());
                p.setRole(((Person) result).getRole());
                p.setPersonPk(((Person) result).getPersonPk());
                p.setFirstname(((Person) result).getFirstname());
                p.setLastname(((Person) result).getLastname());
                p.setBirthday(((Person) result).getBirthday());
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
    public ArrayList<Person> getAllPersons()
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
                p.setPersonPk(((Person) result).getPersonPk());
                p.setFirstname(((Person) result).getFirstname());
                p.setLastname(((Person) result).getLastname());
                p.setBirthday(((Person)result).getBirthday());
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
    
    @WebMethod
    public Person getPerson(@WebParam int id)
    {
        SessionFactory sf = HibernateUtil.getSessionFactory();  //Initialisierung der SessionFactory
        Session s = sf.openSession();                           //Öffne eine Session 
        Transaction tx = null;
        
        Person ret = new Person();
        tinf("searching for person " + id);
        
        try{
            tx = s.beginTransaction();
            String hql = "FROM Person P WHERE P.personPk = :id";
            Query query = s.createQuery(hql);
            query.setParameter("id",id);
            List results = query.list();

            ret.setUsername(((Person) results.get(0)).getUsername());
            ret.setRole(((Person) results.get(0)).getRole());
            ret.setPersonPk(((Person) results.get(0)).getPersonPk());
            
            
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
    
    @WebMethod
    public ArrayList<Course> getAllCourses()
    {
        SessionFactory sf = HibernateUtil.getSessionFactory();  //Initialisierung der SessionFactory
        Session s = sf.openSession();                           //Öffne eine Session 
        Transaction tx = null;
        
        ArrayList<Course> ret = new ArrayList<Course>();
        
        try{
            tx = s.beginTransaction();
            String hql = "FROM Course";
            Query query = s.createQuery(hql);
            List results = query.list();

            
            for (Object result : results)
            {
                Course c = new Course();
                c.setDescription(((Course)result).getDescription());
                c.setTitle(((Course)result).getTitle());
                ret.add(c);
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
    @WebMethod
    public ArrayList<PersonCourseMembership> getAllMemberships()
    {
        SessionFactory sf = HibernateUtil.getSessionFactory();  //Initialisierung der SessionFactory
        Session s = sf.openSession();                           //Öffne eine Session 
        Transaction tx = null;
        
        ArrayList<PersonCourseMembership> ret = new ArrayList<PersonCourseMembership>();
        
        try{
            tx = s.beginTransaction();
            String hql = "FROM PersonCourseMembership";
            Query query = s.createQuery(hql);
            List results = query.list();

            
            for (Object result : results)
            {
                ret.add((PersonCourseMembership)result);
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
