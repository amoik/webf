/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webf.webservice.student;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author anti88
 */
@XmlType
public class Student
{
    private String name;
    private ArrayList<String> courses;

    public Student(String name)
    {
        courses = new ArrayList<String>();
        this.name = name;
    }

    public void addCourse(String name)
    {
        courses.add(name);
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<String> courses) {
        this.courses = courses;
    }
    
    
}
