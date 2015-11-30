/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webf.beans;

import static webf.beans.utils.tinf;
import webf.ws.LoginRequest;
import webf.ws.LoginRequestType;
import webf.ws.LoginResponse2;
import webf.ws.LoginResponseType;
import webf.ws.WebServices;
import webf.ws.WebServices_Service;

/**
 *
 * @author anti88
 */
public class CreateUser {
    
    public void createUser()
    {
        tinf("lege user an...");
        
        //call WS        
        
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();      

        double ret = port.createUser("heisl");
        
        tinf("return: " + ret);
    }
    
}
