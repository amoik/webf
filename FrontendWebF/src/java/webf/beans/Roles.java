/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webf.beans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import static utils.Utils.addMessage;
import static utils.Utils.tinf;
import webf.ws.Role;
import webf.ws.WebServices;
import webf.ws.WebServices_Service;

/**
 *
 * @author anti88
 */
public class Roles {
    private List<Role> roles;
    private String newTitle;
    
    public Roles()
    {
        roles = new ArrayList<Role>();
        resetVars();
    }
    
    public void onload()
    {
        
        if(Login.getLoginName().equals(""))
        {
            tinf("not logged in!");
            FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "logout.xhtml");
        }
        getAll();
    }
    
    public void getAll()
    {
        getAllRoles();
    }
    public void getAllRoles()
    {
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();  

        setRoles(port.getAllRoles());
        
        if( getRoles() != null)
        {
            printAllRoles();
        }
        
    }

    public void printAllRoles()
    {
        for (Role role : roles)
        {
            tinf("role: " + role.getRoleId()+ " " + role.getTitle());
        }
    }
    
    
    public void deleteRole(int id)
    {
        tinf("deleting role " + id);
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();  

        Boolean ret = port.deleteRole(id);

        if(ret)
        {
            addMessage(0, "Erfolgreich", "wurde gelöscht");
            getAll();
        }
        else
        {
            addMessage(3, "Fehler!", "Löschen fehlgeschlagen!");
        }
    }
    public void saveRole(int id)
    {
        
    }
    public void createRole(String title)
    {
        
        tinf("creating role "+getNewTitle()+"...");
        
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();
        
        Boolean ret = port.createRole(title);

        
        if(ret)
        {
            resetVars();
            getAll();
            addMessage(0, "Erfolgreich", "wurde angelegt");
        }
        else
        {
            addMessage(3, "Fehler!", "Anlegen fehlgeschlagen!");
        }
    }
    
    public void resetVars()
    {
        this.newTitle = "";
    }

    public String getNewTitle() {
        return newTitle;
    }

    public void setNewTitle(String newTitle) {
        this.newTitle = newTitle;
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
