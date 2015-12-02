/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webf.beans;

import static java.lang.System.in;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import static webf.beans.Utils.tinf;
import webf.ws.Person;
import webf.ws.WebServices;
import webf.ws.WebServices_Service;

/**
 *
 * @author anti88
 */
public class Students
{
    private List<Person> students;
    private String msg;
    
    public void Students()
    {
        students = new ArrayList<Person>();
    }
    
    public void getAllStudents()
    {
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();
        
        students =  port.getStudents();
    }
    
    public void printAllStudents()
    {
        for(int i = 0; i < students.size(); i++)
        {
            tinf("Student: " + students.get(i).getUsername());
        }
    }


    public void setStudents(List<Person> students) {
        this.students = students;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Person> getStudents() {
        return students;
    }
    
    
}
