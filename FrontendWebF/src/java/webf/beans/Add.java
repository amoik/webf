/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webf.beans;

import webf.ws.WebServices;
import webf.ws.WebServices_Service;

/**
 *
 * @author anti88
 */
public class Add {
    private int resp;
    
    public void add()
    {
        
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();  

       
        this.setResp(port.add(1,4));
    }

    public int getResp() {
        return resp;
    }

    public void setResp(int resp) {
        this.resp = resp;
    }   
}
