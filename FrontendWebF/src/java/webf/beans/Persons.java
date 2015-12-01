/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webf.beans;

import java.util.ArrayList;
import java.util.List;
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
    
    private List<Person> personen;
    
    private String msg;
    
    public Persons()
    {
        personen = new ArrayList<Person>();
        msg = "";
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

        Boolean ret = port.createUser(username, password, role);
        
        if(ret)
        {
            setMsg("wurde angelegt!");
            setUsername("");
            setPassword("");
            setRole("");
        }
        else
            setMsg("Fehlgeschlagen!");
    }
    
    public void getAll()
    {
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();  

        setPersonen(port.getPersons());
        
        if( getPersonen() != null)
            this.setMsg("Erfolgreich");
        else
            this.setMsg("Fehlgeschlagen!");
    }
  

    public void printAllPersons()
    {
        for(int i = 0; i < personen.size(); i++)
            tinf(personen.get(i).getUsername() + " " + personen.get(i).getRole());
    }
    
    public void uiGetAndPrint()
    {
        getAll();
        printAllPersons();
    }

    public List<Person> getPersonen() {
        return personen;
    }

    public void setPersonen(List<Person> personen) {
        this.personen = personen;
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
