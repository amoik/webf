/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webf.webservice;

import com.sun.xml.wss.util.DateUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
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
import webf.hibernate.db.PersonCourseMembershipId;
import webf.hibernate.db.Role;

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
    public Person login(@WebParam String name, @WebParam String password)
    {
        SessionFactory sf = HibernateUtil.getSessionFactory();  //Initialisierung der SessionFactory
        Session s = sf.openSession();                           //Öffne eine Session 
        Transaction tx = null;
        Person ret = null;
        
        try{
            tx = s.beginTransaction();
            String hql = "FROM Person P WHERE P.username = :name";
            Query query = s.createQuery(hql);
            query.setParameter("name",name);
            List results = query.list();

            Person personFromDb = (Person)results.get(0);
            
            if(personFromDb.getPassword().equals(password))
            {
                Role r = new Role();
                r.setTitle(personFromDb.getRole().getTitle());
                r.setRoleId(personFromDb.getRole().getRoleId());
                
                ret = new Person();
                ret.setFirstname(personFromDb.getFirstname());
                ret.setLastname(personFromDb.getLastname());
                ret.setUsername(personFromDb.getUsername());
                ret.setPersonPk(personFromDb.getPersonPk());
                ret.setBirthday(personFromDb.getBirthday());
                ret.setPassword(personFromDb.getPassword());
                ret.setRole(r);
                
            }else{
                ret = null;
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
        
        try
        {
            tx = s.beginTransaction();
            
            s.delete(p);
            tx.commit();            //Transaktion durchführen
            
            ret = true;
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
    public Role getRoleById(@WebParam int id)
    {
        SessionFactory sf = HibernateUtil.getSessionFactory();  //Initialisierung der SessionFactory
        Session s = sf.openSession();                           //Öffne eine Session 
        Transaction tx = null;
        
        Role r = new Role();
        
        
        try
        {
            tx = s.beginTransaction();
            String hql = "FROM Role R WHERE R.roleId = :role_id";
            Query query = s.createQuery(hql);
            query.setParameter("role_id",id);
            List results = query.list();
            
            
            for (Object result : results)
            {
                r.setRoleId(((Role) result).getRoleId());
                r.setTitle(((Role) result).getTitle());
            }
            
            tx.commit();            //Transaktion durchführen
        } catch (Exception e)
        {
            tinf("getRoleById EXCEPTION: " + e);
            if(tx !=null){
                tx.rollback();      //Bei Fehlerfall => Rollback!
            }
            r.setRoleId(0);
        } finally
        {
            s.close();              //Session schließen egal ob Erfolg oder Fehler
        }

        if(!(r.getRoleId() >= 0))
        {
            r.setRoleId(0);
            r.setTitle("ERROR");
        }
        
        return r;
    }

    
    @WebMethod
    public Boolean createPerson(@WebParam String name, @WebParam String password, @WebParam int role, @WebParam String firstname, @WebParam String lastname, @WebParam String birthday)
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
            p.setRole(getRoleById(role));
            p.setFirstname(firstname);
            p.setLastname(lastname);
            p.setBirthday(d);
            
            s.save(p);
            
            tx.commit();            //Transaktion durchführen
            ret = true;
        } catch (Exception e) {
            //failed
            System.out.println("createPerson EXCEPTION: " + e);
        
            if(tx !=null){
                tx.rollback();      //Bei Fehlerfall => Rollback!
            }
        } finally {
            s.close();              //Session schließen egal ob Erfolg oder Fehler
        }
        return ret;
    }

    @WebMethod
    public ArrayList<Person> getAllStudents()
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
                
                if(((Person) result).getRole().getTitle().equals("Student"))
                {
                    Person p = new Person();

                    Role r = new Role();
                    r.setRoleId(((Person) result).getRole().getRoleId());
                    r.setTitle(((Person) result).getRole().getTitle());


                    p.setUsername(((Person) result).getUsername());
                    p.setRole(r);
                    p.setPersonPk(((Person) result).getPersonPk());
                    p.setFirstname(((Person) result).getFirstname());
                    p.setLastname(((Person) result).getLastname());
                    p.setBirthday(((Person)result).getBirthday());


                    ret.add(p);
                }
            }
            
            
            tx.commit();            //Transaktion durchführen
        } catch (Exception e) {
            tinf("getAllStudents EXCEPTION: " + e);
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
                
                Role r = new Role();
                r.setRoleId(((Person) result).getRole().getRoleId());
                r.setTitle(((Person) result).getRole().getTitle());
                
                        
                p.setUsername(((Person) result).getUsername());
                p.setRole(r);
                p.setPersonPk(((Person) result).getPersonPk());
                p.setFirstname(((Person) result).getFirstname());
                p.setLastname(((Person) result).getLastname());
                p.setBirthday(((Person)result).getBirthday());
                
                
                ret.add(p);
            }
            
            
            tx.commit();            //Transaktion durchführen
        } catch (Exception e) {
            tinf("getAllPersons EXCEPTION: " + e);
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
    public ArrayList<Role> getAllRoles()
    {
        SessionFactory sf = HibernateUtil.getSessionFactory();  //Initialisierung der SessionFactory
        Session s = sf.openSession();                           //Öffne eine Session 
        Transaction tx = null;
        
        ArrayList<Role> ret = new ArrayList<Role>();
        
        try{
            tx = s.beginTransaction();
            String hql = "FROM Role R";
            Query query = s.createQuery(hql);
            List results = query.list();

            
            for (Object result : results)
            {
                Role r = new Role();
                r.setRoleId(((Role) result).getRoleId());
                r.setTitle(((Role) result).getTitle());
                ret.add(r);
            }
            
            
            tx.commit();            //Transaktion durchführen
        } catch (Exception e) {
            tinf("getAllRoles EXCEPTION: " + e);
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
            tinf("person not found!");
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
    public Person getPersonByUsername(@WebParam String username)
    {
        SessionFactory sf = HibernateUtil.getSessionFactory();  //Initialisierung der SessionFactory
        Session s = sf.openSession();                           //Öffne eine Session 
        Transaction tx = null;
        
        Person ret = new Person();
        tinf("searching for person " + username);
        
        try{
            tx = s.beginTransaction();
            String hql = "FROM Person P WHERE P.username = :username";
            Query query = s.createQuery(hql);
            query.setParameter("username",username);
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
                Person p = new Person();
                
                Course c = new Course();
                c.setCoursePk(((Course)result).getCoursePk());
                c.setDescription(((Course)result).getDescription());
                c.setDescriptionEn(((Course)result).getDescriptionEn());
                c.setRequirements(((Course)result).getRequirements());
                c.setBegin(((Course)result).getBegin());
                c.setEnd(((Course)result).getEnd());
                c.setTitle(((Course)result).getTitle());
                
                if(((Course)result).getPerson() != null)
                { 
                    p.setUsername(((Course)result).getPerson().getUsername());
                    p.setPersonPk(((Course)result).getPerson().getPersonPk());
                    p.setFirstname(((Course)result).getPerson().getFirstname());
                    p.setLastname(((Course)result).getPerson().getLastname());
                    p.setBirthday(((Course)result).getPerson().getBirthday());
                    c.setPerson(p);
                }
                
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
                Person p = new Person();
                Course c = new Course();
                
                c.setCoursePk(((PersonCourseMembership)result).getCourse().getCoursePk());
                c.setTitle(((PersonCourseMembership)result).getCourse().getTitle());
                c.setDescription(((PersonCourseMembership)result).getCourse().getDescription());
                c.setDescriptionEn(((PersonCourseMembership)result).getCourse().getDescriptionEn());
                c.setRequirements(((PersonCourseMembership)result).getCourse().getRequirements());
                c.setBegin(((PersonCourseMembership)result).getCourse().getBegin());
                c.setEnd(((PersonCourseMembership)result).getCourse().getEnd());
                
                p.setUsername(((PersonCourseMembership)result).getPerson().getUsername());
                p.setPersonPk(((PersonCourseMembership)result).getPerson().getPersonPk());
                
                PersonCourseMembership m = new PersonCourseMembership();
                
                m.setNote(((PersonCourseMembership)result).getNote());
                m.setPerson(p);
                
                if(((PersonCourseMembership)result).getCourse().getPerson() != null)
                {
                    Person lector = new Person();
                    lector.setUsername(((PersonCourseMembership)result).getCourse().getPerson().getUsername());
                    c.setPerson(lector);
                    
                }
                
                m.setCourse(c);
                
                
                ret.add(m);
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
    public Boolean createRole(@WebParam String title)
    {
        SessionFactory sf = HibernateUtil.getSessionFactory();  //Initialisierung der SessionFactory
        Session s = sf.openSession();                           //Öffne eine Session 
        Transaction tx = null;
        Boolean ret = false;
        
        try{

            tx = s.beginTransaction();
            
            Role r = new Role();
            r.setTitle(title);
            
            s.save(r);
            
            tx.commit();            //Transaktion durchführen
            ret = true;
        } catch (Exception e) {
            //failed
            System.out.println("createRole EXCEPTION: " + e);
        
            if(tx !=null){
                tx.rollback();      //Bei Fehlerfall => Rollback!
            }
        } finally {
            s.close();              //Session schließen egal ob Erfolg oder Fehler
        }
        return ret;
    }
    
    @WebMethod
    public Boolean deleteRole(@WebParam int id)
    {
        SessionFactory sf = HibernateUtil.getSessionFactory();  //Initialisierung der SessionFactory
        Session s = sf.openSession();                           //Öffne eine Session 
        Transaction tx = null;
        Boolean ret = false;
        Role r = getRoleById(id);
        if(!(r.getRoleId() > 0))
            return ret;
        
        try
        {
            tx = s.beginTransaction();
            
            s.delete(r);
            tx.commit();            //Transaktion durchführen
            
            ret = true;
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
    public Boolean deleteCourse(@WebParam int id)
    {
        SessionFactory sf = HibernateUtil.getSessionFactory();  //Initialisierung der SessionFactory
        Session s = sf.openSession();                           //Öffne eine Session 
        Transaction tx = null;
        Boolean ret = false;
        Course c = new Course();
        c.setCoursePk(id);
        
        try
        {
            tx = s.beginTransaction();
            
            s.delete(c);
            tx.commit();            //Transaktion durchführen

            ret = true;
        } catch (Exception e) {
            tinf("delete of course " + id + " failed");
        
            if(tx !=null){
                tx.rollback();      //Bei Fehlerfall => Rollback!
            }
        } finally {
            s.close();              //Session schließen egal ob Erfolg oder Fehler
        }
        return ret;
    }
    
    
    @WebMethod
    public Boolean createCourse(@WebParam String title, @WebParam String descrption, @WebParam String descrption_en, @WebParam String requirements, @WebParam String begin, @WebParam String end, @WebParam String lector)
    {
        SessionFactory sf = HibernateUtil.getSessionFactory();  //Initialisierung der SessionFactory
        Session s = sf.openSession();                           //Öffne eine Session 
        Transaction tx = null;
        Boolean ret = false;
        
        try{

            
            tx = s.beginTransaction();
            
            Course c = new Course();
            
            c.setTitle(title);
            c.setDescription(descrption);
            c.setDescriptionEn(descrption_en);
            c.setRequirements(requirements);
            c.setBegin(begin);
            c.setEnd(end);
            c.setPerson(getPersonByUsername(lector));
            
            s.save(c);
            
            tx.commit();            //Transaktion durchführen
            ret = true;
        } catch (Exception e) {
            //failed
            System.out.println("createCourse EXCEPTION: " + e);
        
            if(tx !=null){
                tx.rollback();      //Bei Fehlerfall => Rollback!
            }
        } finally {
            s.close();              //Session schließen egal ob Erfolg oder Fehler
        }
        return ret;
    }
    
    @WebMethod
    public Boolean saveCourse(@WebParam int id, @WebParam String title, @WebParam String descrption, @WebParam String descrption_en, @WebParam String requirements, @WebParam String begin, @WebParam String end, @WebParam String lector)
    {
        SessionFactory sf = HibernateUtil.getSessionFactory();  //Initialisierung der SessionFactory
        Session s = sf.openSession();                           //Öffne eine Session 
        Transaction tx = null;
        Boolean ret = false;
        
        try{

            tx = s.beginTransaction();
            
            Course c = new Course();
            
            c.setCoursePk(id);
            c.setTitle(title);
            c.setDescription(descrption);
            c.setDescriptionEn(descrption_en);
            c.setRequirements(requirements);
            c.setBegin(begin);
            c.setEnd(end);
            c.setPerson(getPersonByUsername(lector));
            
            s.update(c);
            
            tx.commit();            //Transaktion durchführen
            ret = true;
        } catch (Exception e) {
            //failed
            System.out.println("saveCourse EXCEPTION: " + e);
        
            if(tx !=null){
                tx.rollback();      //Bei Fehlerfall => Rollback!
            }
        } finally {
            s.close();              //Session schließen egal ob Erfolg oder Fehler
        }
        return ret;
    }
    
    
    
    
    @WebMethod
    public ArrayList<Person> getAllLectors()
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
                
                if(((Person) result).getRole().getTitle().equals("Lektor"))
                {
                    Person p = new Person();

                    Role r = new Role();
                    r.setRoleId(((Person) result).getRole().getRoleId());
                    r.setTitle(((Person) result).getRole().getTitle());


                    p.setUsername(((Person) result).getUsername());
                    p.setRole(r);
                    p.setPersonPk(((Person) result).getPersonPk());
                    p.setFirstname(((Person) result).getFirstname());
                    p.setLastname(((Person) result).getLastname());
                    p.setBirthday(((Person)result).getBirthday());


                    ret.add(p);
                }
            }
            
            
            tx.commit();            //Transaktion durchführen
        } catch (Exception e) {
            tinf("getAllLectors EXCEPTION: " + e);
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
    public Boolean createMembership(@WebParam Course c, @WebParam Person p)
    {
        SessionFactory sf = HibernateUtil.getSessionFactory();  //Initialisierung der SessionFactory
        Session s = sf.openSession();                           //Öffne eine Session 
        Transaction tx = null;
        Boolean ret = false;
        
        try
        {
            tx = s.beginTransaction();
            
            PersonCourseMembership pcm = new PersonCourseMembership();
            pcm.setCourse(c);
            pcm.setPerson(p);
            
            PersonCourseMembershipId pid = new PersonCourseMembershipId();
            pid.setCourseFk(c.getCoursePk());
            pid.setPersonFk(p.getPersonPk());
            pcm.setId(pid);
            pcm.setNote(0);
            
            tinf("erstelle: " + pcm.getCourse().getCoursePk() + " " + pcm.getPerson().getPersonPk() + " " + pcm.getNote());
            
            s.save(pcm);
            
            tx.commit();            //Transaktion durchführen
            ret = true;
        } catch (Exception e) {
            //failed
            System.out.println("createMembership EXCEPTION: " + e);
            
            if(tx !=null){
                tx.rollback();      //Bei Fehlerfall => Rollback!
            }
        } finally {
            s.close();              //Session schließen egal ob Erfolg oder Fehler
        }
        return ret;
    }
    
    @WebMethod
    public Boolean deleteMembership(@WebParam int course_id, @WebParam int person_id)
    {
        SessionFactory sf = HibernateUtil.getSessionFactory();  //Initialisierung der SessionFactory
        Session s = sf.openSession();                           //Öffne eine Session 
        Transaction tx = null;
        Boolean ret = false;
        PersonCourseMembershipId pid = new PersonCourseMembershipId();
        pid.setCourseFk(course_id);
        pid.setPersonFk(person_id);
        PersonCourseMembership pcm = new PersonCourseMembership();
        pcm.setId(pid);
        
        
        try
        {
            tx = s.beginTransaction();
            
            s.delete(pcm);
            tx.commit();            //Transaktion durchführen
            
            ret = true;
        } catch (Exception e) {
            tinf("deleteMembership EXCEPTION: " + e);
        
            if(tx !=null){
                tx.rollback();      //Bei Fehlerfall => Rollback!
            }
        } finally {
            s.close();              //Session schließen egal ob Erfolg oder Fehler
        }
        return ret;
    }
    
    
    @WebMethod
    public Course getCourse(@WebParam int id)
    {
        SessionFactory sf = HibernateUtil.getSessionFactory();  //Initialisierung der SessionFactory
        Session s = sf.openSession();                           //Öffne eine Session 
        Transaction tx = null;
        
        Course ret = new Course();
        tinf("searching for course " + id);
        
        try{
            tx = s.beginTransaction();
            String hql = "FROM Course C WHERE C.coursePk = :id";
            Query query = s.createQuery(hql);
            query.setParameter("id",id);
            List results = query.list();

            ret.setTitle(((Course) results.get(0)).getTitle());
            ret.setDescription(((Course) results.get(0)).getDescription());
            ret.setCoursePk(((Course) results.get(0)).getCoursePk());
            ret.setPerson(((Course) results.get(0)).getPerson());
            
            
            tx.commit();            //Transaktion durchführen
        } catch (Exception e) {
            tinf("course not found!");
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
    public Boolean saveMembership(@WebParam Course c, @WebParam Person p, @WebParam int note)
    {
        SessionFactory sf = HibernateUtil.getSessionFactory();  //Initialisierung der SessionFactory
        Session s = sf.openSession();                           //Öffne eine Session 
        Transaction tx = null;
        Boolean ret = false;
        
        try{

            tx = s.beginTransaction();
            
            PersonCourseMembership pcm = new PersonCourseMembership();
            pcm.setCourse(c);
            pcm.setPerson(p);
            
            PersonCourseMembershipId pid = new PersonCourseMembershipId();
            pid.setCourseFk(c.getCoursePk());
            pid.setPersonFk(p.getPersonPk());
            pcm.setId(pid);
            pcm.setNote(note);
            
            s.update(pcm);
            
            tx.commit();            //Transaktion durchführen
            ret = true;
        } catch (Exception e) {
            //failed
            System.out.println("saveMembership EXCEPTION: " + e);
        
            if(tx !=null){
                tx.rollback();      //Bei Fehlerfall => Rollback!
            }
        } finally {
            s.close();              //Session schließen egal ob Erfolg oder Fehler
        }
        return ret;
    }
    
}
