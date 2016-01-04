/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webf.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;
import static webf.beans.Utils.tinf;
import static webf.beans.Utils.addMessage;
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
    
    
    public Persons()
    {
        persons = new ArrayList<Person>();
    }
    
    public void onload()
    {
        if(Login.getLoginName().equals(""))
        {
            tinf("not logged in!");
            FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "failure.xhtml");
        }
        resetVars();
        getAll();
    }
    
    public void createPerson()
    {tinf("rolle: " + getRole());
        
        if(getFirstname().equals(""))
        {
            return;
        }
        if(getLastname().equals(""))
        {
            return;
        }
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
        if(getBirthday() == null)
        {
            return;
        }
        
        tinf("creating user "+getUsername()+"...");
        
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();      

        Boolean ret = port.createPerson(getUsername(), getPassword(), getRole(), getFirstname(), getLastname(), getBirthday().toString());

        
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
        else
        {
            addMessage("Fehler!", "Löschen fehlgeschlagen!");
        }
    }

    public void printAllPersons()
    {
        for (Person person : persons)
        {
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
        setBirthday(null);
      
        persons.clear();
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
