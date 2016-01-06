/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webf.beans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import static webf.beans.Utils.addMessage;
import static webf.beans.Utils.tinf;
import webf.ws.Course;
import webf.ws.WebServices;
import webf.ws.WebServices_Service;

/**
 *
 * @author anti88
 */
public class Courses {
    
    private String newTitle;
    private String newDescription;
    private List<Course> courses;
    
    public Courses()
    {
        courses = new ArrayList<Course>();
    }
    
    public void onload()
    {
        if(Login.getLoginName().equals(""))
        {
            tinf("not logged in!");
            FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "logout.xhtml");
        }
        resetVars();
        getAll();
    }
    
    public void resetVars()
    {
        this.setNewTitle("");
        this.setNewDescription("");
    }
    
    public void getAll()
    {
        getAllCourses();
    }
        
    public void getAllCourses()
    {
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();      
        
          
        courses.clear();
        courses =  port.getAllCourses();
        //printAllCourses();
    }
    
    public void createCourse(String title, String description)
    {
        if(title.equals(""))
            return;
        if(description.equals(""))
            return;
        
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();
        
        Boolean ret = port.createCourse(title, description);

        if(ret)
        {
            addMessage(0, "Erfolgreich", "wurde angelegt");
            resetVars();
            getAll();
        }
        else
        {
            addMessage(3, "Fehler!", "Anlegen fehlgeschlagen!");
        }
    }

    public void printAllCourses()
    {
        for (Course course : courses)
        {
            tinf(course.getCoursePk() + ": " + course.getTitle()+ " " + course.getDescription());
        }
    }
    
    public void saveCourse(int id, String title, String description)
    {
        if(!(id >= 0))
            return;
        if(title.equals(""))
            return;
        if(description.equals(""))
            return;
        
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();

        Boolean ret = port.saveCourse(id, title, description);

        if(ret)
        {
            addMessage(0, "Erfolgreich", "wurde gespeichert");
            resetVars();
            getAll();
        }
        else
        {
            addMessage(3, "Fehler!", "Speichern fehlgeschlagen!");
        }
    }
    
    public void deleteCourse(int id)
    {
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();
        
        Boolean ret = port.deleteCourse(id);

        if(ret)
        {
            addMessage(0, "Erfolgreich", "wurde gelöscht");
            getAll();
        }
        else
        {
            addMessage(3, "Fehler!", "Löschen fehlgeschlagen!");
        }
    }

    public String getNewTitle() {
        return newTitle;
    }

    public void setNewTitle(String newTitle) {
        this.newTitle = newTitle;
    }

    public String getNewDescription() {
        return newDescription;
    }

    public void setNewDescription(String newDescription) {
        this.newDescription = newDescription;
    }

    

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
    
}
