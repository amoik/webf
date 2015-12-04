/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webf.beans;

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
    private Date birthday;
    
    private List<Person> persons;
    
    private String msg;
    
    public Persons()
    {
        persons = new ArrayList<Person>();
    }
    
    public void createPerson()
    {
        setRole("Student");//TODO
        
        if(getUsername().equals(""))
        {
            setMsg("kein Username angegeben");
            return;
        }
        
        if(getPassword().equals(""))
        {
            setMsg("kein Passwort angegeben");
            return;
        }
        if(getRole().equals(""))
        {
            setMsg("keine Rolle angegeben");
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
            setMsg("wurde angelegt!");
            resetVars();
            getAll();
        }
        else
            setMsg("Fehlgeschlagen!");
    }
    
    public void getAll()
    {
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();  

        setPersons(port.getAllPersons());
        
        if( getPersons() != null)
        {
            this.setMsg("Erfolgreich");
            printAllPersons();
        }
        else
            this.setMsg("Fehlgeschlagen!");
    }
  
    public void deletePerson(Person p)
    {
        tinf("person " + p.getUsername() + " wird gelöscht");
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();  

        Boolean ret = port.deletePerson(p.getPersonPk);
        
        if(ret)
        {
            this.setMsg("Erfolgreich");
            getAll();
        }
        else
            this.setMsg("Fehlgeschlagen!");
    }

    public void printAllPersons()
    {
        for (Person person : persons) {
            tinf(person.getUsername() + " " + person.getRole() + " " + person.getBirthday().toString());
        }
    }
    
    public void resetVars()
    {
        setMsg("");
        setUsername("");
        setPassword("");
        setRole("");
        setFirstname("");
        setLastname("");
      
        persons.clear();
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = "Ergebnis: " + msg;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    
}
