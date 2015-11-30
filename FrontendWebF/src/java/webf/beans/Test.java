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
public class Test {
    private double resp;
    
    public void test()
    {
        
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();  

       
        this.setResp(port.floattest(1,4));
    }

    public double getResp() {
        return resp;
    }

    public void setResp(double resp) {
        this.resp = resp;
    }   
}
