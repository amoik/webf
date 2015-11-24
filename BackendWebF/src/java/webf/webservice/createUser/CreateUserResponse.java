/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webf.webservice.createUser;

import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author PU
 */
@XmlType(name = "CreateUserResponse")
public class CreateUserResponse {
    
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
