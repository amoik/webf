/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webf.beans;

import javax.inject.Named;
import static webf.beans.Utils.tinf;
import webf.ws.WebServices;
import webf.ws.WebServices_Service;

/**
 *
 * @author PU
 */
@Named
public class Login {
    
    private String username;
    private String password;
    private String errStr;
    private int id = -1;
    private String includedPage;
    
    public void Login()
    {
        resetVars();
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
    
    public String logout()
    {
        setErrStr("");
        setId(-1);
        return "index.xhtml";
    }
    
    public void setCenter(String subSite)
    {
        tinf("Center will be set to: " + subSite);
    }
    
    public String login(){
    
        //call WS       
        
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();      
        
          
        int ret =  port.login(username, password);
        setId(ret);
        
        
        if(ret != -1)
        {
            tinf("Erfolgreich eingeloggt " + id); 
            resetVars();
            return "loggedin.xhtml";
        }
        
        setErrStr("Login Fehlgeschlagen");
        tinf("Login Fehlgeschlagen (" + username + "/" + password + ")");
        return "index.xhtml";
    }

    public void resetVars()
    {
        errStr = "";
        password = "";
        username = "";
    }
    
    public String getErrStr() {
        return errStr;
    }

    public void setErrStr(String errStr) {
        this.errStr = errStr;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getIncludedPage() {
        return includedPage;
    }

    public void setIncludedPage(String includedPage) {
        this.includedPage = includedPage;
    }
    
    
}
