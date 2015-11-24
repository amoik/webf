/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webf.beans;

import javax.inject.Named;
import static webf.beans.utils.tinf;
import webf.ws.LoginRequest;
import webf.ws.LoginRequestType;
import webf.ws.LoginResponse;
import webf.ws.LoginResponse2;
import webf.ws.LoginResponseType;
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
    
    
    public void login(){
    
        //call WS        
        
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();      
        
        LoginRequestType lrt = new LoginRequestType();
        LoginRequest lr = new LoginRequest();
        lr.setUsername(username);
        lr.setPassw(password);
        lrt.setLoginRequest(lr);
          
        LoginResponseType lRespT =  port.login(lrt);
        LoginResponse2 lResp = lRespT.getLoginResponse();
        String status = lResp.getStatus();
        String userId = lResp.getUserId();
        
        
        if(status.equals("Success!"))
            tinf("Erfolgreich eingeloggt " + userId);
        else
            tinf("Login Fehlgeschlagen");
    }
}
