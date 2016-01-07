/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webf.beans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import static utils.Utils.addMessage;
import static utils.Utils.tinf;
import webf.ws.Course;
import webf.ws.Person;
import webf.ws.WebServices;
import webf.ws.WebServices_Service;

/**
 *
 * @author anti88
 */
public class Courses {
    
    private String newTitle;
    private String newDescription;
    private String newLector;
    private List<Course> courses;
    private List<Person> lectors;
    
    
    public Courses()
    {
        courses = new ArrayList<Course>();
        lectors = new ArrayList<Person>();
        
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
        this.setNewLector("");
    }
    
    public void getAll()
    {
        getAllCourses();
        getAllLectors();
    }
        
    public void getAllCourses()
    {
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();      
        
          
        courses.clear();
        courses =  port.getAllCourses();
        
        for(Course c : courses)
        {
            if(c.getPerson()==null)
            {
                c.setPerson(new Person());
                c.getPerson().setUsername("keiner");
            }
        }
        
        
        //printAllCourses();
    }
        
    public void getAllLectors()
    {
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();      
        
          
        lectors = port.getAllLectors();
        Person l = new Person();
        l.setUsername("keiner");
        lectors.add(l);
        
        //printAllLectors();
    }
    
    public void createCourse(String title, String description, String lector)
    {
        if(lector.equals(""))
            return;
        if(title.equals(""))
            return;
        if(description.equals(""))
            return;
        
        
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();
        
        Boolean ret = port.createCourse(title, description, lector);

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
            tinf(course.getCoursePk() + ": " + course.getTitle()+ " " + course.getDescription() + " Lector: " + course.getPerson().getUsername());
        }
    }

    public void printAllLectors()
    {
        for (Person lector : lectors)
        {
            tinf(lector.getPersonPk()+ ": " + lector.getUsername());
        }
    }
    
    public void saveCourse(int id, String title, String description, String lector)
    {
        if(!(id >= 0))
            return;
        if(title.equals(""))
            return;
        if(description.equals(""))
            return;
        if(lector.equals(""))
            return;
        if(lector.equals("keiner"))
            lector = null;
        
        tinf("LEKTOR: " + lector);
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();

        Boolean ret = port.saveCourse(id, title, description, lector);

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

    public String getNewLector() {
        return newLector;
    }

    public void setNewLector(String newLector) {
        this.newLector = newLector;
    }

    public List<Person> getLectors() {
        return lectors;
    }

    public void setLectors(List<Person> lectors) {
        this.lectors = lectors;
    }
    
}
