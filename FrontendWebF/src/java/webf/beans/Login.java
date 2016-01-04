/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webf.beans;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;
import static webf.beans.Utils.addMessage;
import static webf.beans.Utils.tinf;
import webf.ws.WebServices;
import webf.ws.WebServices_Service;

/**
 *
 * @author PU
 */
@Named
public class Login 
{
    
    private String username;
    private String password;
    private String errStr;
    private int id = -1;
    private String includedPage = "welcome.xhtml";
    
    public void Login()
    {
        resetVars();
    }

    public String getUsername() 
    {
        return username;
    }

    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getPassword() 
    {
        return password;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }
   
    
    public String logout()
    {
        setErrStr("");
        setId(-1);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml?faces-redirect=true";
    }
    
    public void setCenter(String subSite)
    {
        tinf("Center will be set to: " + subSite);
    }
    
    public String login()
    {
    
        //call WS       
        
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();      
        
          
        int ret =  port.login(username, password);
        setId(ret);
        
        
        if(ret != -1)
        {
            resetVars();
            return "loggedin.xhtml?faces-redirect=true";
        }
        
        addMessage("Fehler","Login Fehlgeschlagen");
        return "index.xhtml";
    }
    
    public void onload()
    {
        tinf("servas");
        addMessage("Erfolg","Erfolgreich eingeloggt");
        setIncludedPage("welcome.xhtml");
    }

    public void resetVars()
    {
        errStr = "";
        password = "";
        username = "";
    }
    
    public String getErrStr()
    {
        return errStr;
    }

    public void setErrStr(String errStr)
    {
        this.errStr = errStr;
    }

    public int getId()
    {
        return id;
    }

    private void setId(int id)
    {
        this.id = id;
    }

    public String getIncludedPage()
    {
        return includedPage;
    }

    public void setIncludedPage(String includedPage)
    {
        tinf("showing page: '" + includedPage + "'");
        this.includedPage = includedPage;
    }
    
    
}
