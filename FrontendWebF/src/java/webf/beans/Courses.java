/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webf.beans;

import java.util.ArrayList;
import java.util.List;
import static webf.beans.Utils.tinf;
import webf.ws.Course;
import webf.ws.Person;
import webf.ws.WebServices;
import webf.ws.WebServices_Service;

/**
 *
 * @author anti88
 */
public class Courses {
    
    private String msg;
    private List<Course> courses;
    
    public Courses()
    {
        courses = new ArrayList<Course>();
    }
    
    
    public void setTab(int num)
    {
        setMsg("Setting tab to " + num);
    }   
        
    public void getAllCourses()
    {
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();      
        
          
        courses.clear();
        courses =  port.getAllCourses();
        printAllPersons();
    }

    public void printAllPersons()
    {
        for (Course course : courses)
        {
            tinf(course.getTitle()+ " " + course.getDescription());
        }
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
    
}
