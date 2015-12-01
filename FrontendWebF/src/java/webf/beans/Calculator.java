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
public class Calculator {
    private double resp;
    private double one, two;
    
    public void add()
    {
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();
        this.setResp(port.add(one, two));
    }
    
    public void subtract()
    {
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();
        this.setResp(port.subtract(one, two));
    }
    
    public void multiply()
    {
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();
        this.setResp(port.multiply(one, two));
    }
    
    public void divide()
    {
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();
        this.setResp(port.divide(one, two));
    }

    public double getResp() {
        return resp;
    }

    public void setResp(double resp) {
        this.resp = resp;
    }   

    public double getOne() {
        return one;
    }

    public void setOne(double one) {
        this.one = one;
    }

    public double getTwo() {
        return two;
    }

    public void setTwo(double two) {
        this.two = two;
    }
}
