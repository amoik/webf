/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.webf.ws;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author amoik
 */
@XmlRootElement
public class LoginResponseType {
    
    private LoginRequest loginRequest;

    
    
    
    public LoginRequest getLoginRequest() {
        return loginRequest;
    }

    public void setLoginRequest(LoginRequest loginRequest) {
        this.loginRequest = loginRequest;
    }
    
}
