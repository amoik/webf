/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webf.webservice.login.types;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import webf.webservice.login.LoginResponse;

/**
 *
 * @author PU
 */

@XmlRootElement
public class LoginResponseType {
    
    private LoginResponse loginResp;

    public LoginResponse getLoginResponse() {
        return loginResp;
    }

    public void setLoginResponse(LoginResponse loginResp) {
        this.loginResp = loginResp;
    }

    
    
}
