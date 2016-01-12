/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webf.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.el.ELContext;
import javax.faces.context.FacesContext;
import static utils.Utils.addMessage;
import static utils.Utils.tinf;
import webf.ws.Person;
import webf.ws.Role;
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
    private int role = -1;
    private String firstname;
    private String lastname;
    private Date birthday;
    
    private List<Person> persons;
    private List<Role> roles;
    
    private Login login;
    
    public Persons()
    {
        persons = new ArrayList<Person>();
        roles = new ArrayList<Role>();
    }
    
    public void onload()
    {
        
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        login = (Login) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "login");

        if(login.getAccount() == null)
        {
            tinf("not logged in!");
            FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "logout.xhtml");
            return;
        }
        resetVars();
        getAll();
    }
    
    public void createPerson()
    {
        
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
        if(getRole() == -1)
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
            addMessage(role, username, username);
        }
    }
    
    public void getAll()
    {
        getAllPersons();
        getAllRoles();
    }
    
    public void getAllPersons()
    {
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();  

        setPersons(port.getAllPersons());
        
        if( getPersons() != null)
        {
            //printAllPersons();
        }
    }
    
    public void getAllRoles()
    {
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();  

        setRoles(port.getAllRoles());
        
        if( getRoles() != null)
        {
            //printAllRoles();
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
            addMessage(3, "Fehler! ", "Löschen fehlgeschlagen!");
        }
    }

    public void printAllPersons()
    {
        for (Person person : persons)
        {
            tinf(person.getUsername() + " " + person.getRole().getTitle() + " " + person.getBirthday().toString());
        }
    }

    public void printAllRoles()
    {
        for (Role role : roles)
        {
            tinf("role: " + role.getRoleId()+ " " + role.getTitle());
        }
    }
    
    public void resetVars()
    {
        setUsername("");
        setPassword("");
        setRole(-1);
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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
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

    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }

    public List<Role> getRoles()
    {
        //remove the first role(ERROR)
        List<Role> roles = new ArrayList<Role>();
        
        for(int i = 0; i < this.roles.size(); i ++)
        {
            if(i > 0)
                roles.add(this.roles.get(i));
        }
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
