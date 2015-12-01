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
import static webf.beans.utils.tinf;
import webf.ws.WebServices;
import webf.ws.WebServices_Service;
import webf.ws.Student;

/**
 *
 * @author anti88
 */
public class Students
{
    private List<Student> students;
    private String msg;
    
    public void Students()
    {
        students = new ArrayList<Student>();
    }
    
    public void getStudents()
    {
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();
        
        students =  port.getStudents();
    }
    
    public void printAllStudents()
    {
        for(int i = 0; i < students.size(); i++)
        {
            tinf("Student: " + students.get(i).getName());
        }
    }
    
    public void uiGetAndPrint()
    {
        getStudents();
        printAllStudents();
    }


    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
}
