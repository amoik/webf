package webf.beans;

import static webf.beans.utils.tinf;
import webf.ws.WebServices;
import webf.ws.WebServices_Service;
import webf.ws.HelloResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author anti88
 */
public class Hello {
    
    public void hello()
    {
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();  

        
        String resp = port.hello("nudlaug");
        tinf(resp);
    }
}
