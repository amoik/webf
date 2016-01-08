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
import webf.ws.Person;
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
    private static Person loginPerson;
    private Boolean write;
    private String accountType;
    
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
        
          
        Person ret =  port.login(username, password);
        
        if(ret != null)
        {
            setId(ret.getPersonPk());
            Login.setLoginPerson(ret);
            Login.setLoginName(getUsername());
            
            setAccountType(ret.getRole().getTitle());
            
            if(ret.getRole().getTitle().equals("Lektor") || ret.getRole().getTitle().equals("ADMIN"))
                this.setWrite(true);
            else
                this.setWrite(false);
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
        addMessage(0, "Eingeloggt",getLoginName() + " " + Login.getLoginPerson().getRole().getTitle());
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

    public static Person getLoginPerson() {
        return loginPerson;
    }

    public static void setLoginPerson(Person loginPerson) {
        Login.loginPerson = loginPerson;
    }

    public Boolean getWrite() {
        return write;
    }

    public void setWrite(Boolean write) {
        this.write = write;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    
}
