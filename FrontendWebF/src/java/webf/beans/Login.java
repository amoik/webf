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
    private Person account;
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
        this.setAccount(null);
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
            this.setAccount(ret);
            
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
        if(this.getAccount() == null)
        {
            tinf("not logged in!");
            FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "logout.xhtml");
            return;
        }
        tinf("servas");
        addMessage(0, "Eingeloggt",getAccount().getUsername()+ " " + getAccount().getRole().getTitle());
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

    public Person getAccount() {
        return account;
    }

    public void setAccount(Person account) {
        this.account = account;
    }
    
}
