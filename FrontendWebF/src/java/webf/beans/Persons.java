/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webf.beans;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static webf.beans.Utils.tinf;
import webf.ws.Person;
import webf.ws.WebServices;
import webf.ws.WebServices_Service;

/**
 *
 * @author anti88
 */
public class Persons
{
    private String username;
    private String password;
    private String role;
    private String firstname;
    private String lastname;
    private String birthday;
    
    private List<Person> persons;
    
    
    public Persons()
    {
        persons = new ArrayList<Person>();
    }
    
    public void onload()
    {
        resetVars();
        getAll();
    }
    
    public void createPerson()
    {
        setRole("Student");//TODO
        
        if(getUsername().equals(""))
        {
            return;
        }
        
        if(getPassword().equals(""))
        {
            return;
        }
        if(getRole().equals(""))
        {
            return;
        }
        
        tinf("lege user "+getUsername()+" an...");
        
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();      

        Date dt = new Date();
        dt.setTime(0);

        Boolean ret = port.createPerson(username, password, role, firstname, lastname, dt.toString());

        
        if(ret)
        {
            resetVars();
            getAll();
        }
    }
    
    public void getAll()
    {
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();  

        setPersons(port.getAllPersons());
        
        if( getPersons() != null)
        {
            printAllPersons();
        }
    }
  
    public void deletePerson(int id)
    {
        tinf("person " + id + " wird gelöscht");
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();  

        Boolean ret = port.deletePerson(id);
        
        if(ret)
        {
            getAll();
        }
    }

    public void printAllPersons()
    {
        for (Person person : persons) {
            tinf(person.getUsername() + " " + person.getRole() + " " + person.getBirthday().toString());
        }
    }
    
    public void resetVars()
    {
        setUsername("");
        setPassword("");
        setRole("");
        setFirstname("");
        setLastname("");
      
        persons.clear();
    }
    public static String dateToStr(XMLGregorianCalendarImpl gcd)
    {
        Date d = gcd.toGregorianCalendar().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.mm.yyyy");
        return sdf.format(d);
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
