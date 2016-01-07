/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webf.beans;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import static utils.Utils.addMessage;
import static utils.Utils.tinf;
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
    private static String loginName = "";
    
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
   
    
    public void logout()
    {
        tinf("logging out...");
        Login.setLoginName("");
        setErrStr("");
        setId(-1);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
    
    public void setCenter(String subSite)
    {
        tinf("Center will be set to: " + subSite);
    }
    
    public String login()
    {
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();      
        
          
        int ret =  port.login(username, password);
        setId(ret);
        
        
        if(ret != -1)
        {
            Login.setLoginName(getUsername());
            resetVars();
            return "loggedin.xhtml?faces-redirect=true";
        }
        
        addMessage(3, "Fehler","Login Fehlgeschlagen");
        return "index.xhtml";
    }
    
    public void onload()
    {
        if(Login.getLoginName().equals(""))
        {
            tinf("not logged in!");
            FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "logout.xhtml");
        }
        tinf("servas");
        addMessage(0, "Eingeloggt",getLoginName());
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

    public static String getLoginName() {
        return loginName;
    }

    public static void setLoginName(String loginName) {
        Login.loginName = loginName;
    }
}
