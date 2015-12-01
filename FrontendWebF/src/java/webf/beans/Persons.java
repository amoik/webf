/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webf.beans;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import static webf.beans.utils.marshalDate;
import static webf.beans.utils.tinf;
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
    
    private List<Person> persons;
    
    private String msg;
    
    public Persons()
    {
        persons = new ArrayList<Person>();
        getAll();
        resetVars();
    }
    
    public void create()
    {
        tinf("lege user an...");
        
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
        
        
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();      


        
        Boolean ret = port.createUser(username, password, role, "TestFirst", "TestLast", marshalDate(new Date()));
        
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

        setPersons(port.getPersons());
        
        if( getPersons() != null)
        {
            this.setMsg("Erfolgreich");
        }
        else
            this.setMsg("Fehlgeschlagen!");
    }
  
    public void deletePerson(Person p)
    {
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();  

        Boolean ret = port.deletePerson(p);
        
        if(ret)
        {
            this.setMsg("Erfolgreich");
        }
        else
            this.setMsg("Fehlgeschlagen!");
    }

    public void printAllPersons()
    {
        for(int i = 0; i < persons.size(); i++)
            tinf(persons.get(i).getUsername() + " " + persons.get(i).getRole());
    }
    
    public void resetVars()
    {
        setMsg("");
        setUsername("");
        setPassword("");
        setRole("");
        persons.clear();
    }
    
    public void uiGetAndPrint()
    {
        getAll();
        printAllPersons();
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
        this.msg = msg;
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
    
    
}
