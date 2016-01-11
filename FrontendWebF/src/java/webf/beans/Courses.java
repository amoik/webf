/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webf.beans;

import java.util.ArrayList;
import java.util.List;
import javax.el.ELContext;
import javax.faces.context.FacesContext;
import static utils.Utils.addMessage;
import static utils.Utils.fDateStr;
import static utils.Utils.tinf;
import webf.ws.Course;
import webf.ws.Person;
import webf.ws.PersonCourseMembership;
import webf.ws.WebServices;
import webf.ws.WebServices_Service;

/**
 *
 * @author anti88
 */
public class Courses {
    
    private String newTitle;
    private String newDescription;
    private String newDescription_en;
    private String newRequirements;
    private String newBegin;
    private String newEnd;
    private String newLector;
    private List<Course> courses;
    private List<Person> lectors;
    private Login login;
    
    
    public Courses()
    {
        courses = new ArrayList<Course>();
        lectors = new ArrayList<Person>();
    }
    
    public void onload()
    {
        tinf("loading courses");
        
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        login = (Login) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "login");

        if(login.getAccount() == null)
        {
            tinf("not logged in!");
            FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "logout.xhtml");
            return;
        }
        resetVars();
        getAll();
    }
    
    public void resetVars()
    {
        this.setNewTitle("");
        this.setNewDescription("");
        this.setNewDescription_en("");
        this.setNewRequirements("");
        this.setNewBegin("");
        this.setNewEnd("");
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
        
          
        List<Course> all =  port.getAllCourses();
        ArrayList<Course> added =  new ArrayList<Course>();
        

        switch (login.getAccount().getRole().getTitle())
        {
            case "Student":
                WebServices_Service pcmService = new WebServices_Service();
                WebServices pcmPort = pcmService.getWebServicesPort();  

                List<PersonCourseMembership> allPcms = pcmPort.getAllMemberships();
                for(PersonCourseMembership pcm : allPcms)
                {
                    if(pcm.getPerson().getUsername().equals(login.getAccount().getUsername()))
                    {
                        added.add(pcm.getCourse());
                    }
                }
                break;
            case "Lektor":
                for(Course c : all)
                {
                    if(c.getPerson() != null && c.getPerson().getUsername().equals(login.getAccount().getUsername()))
                        added.add(c);
                }
                
            break;
            case "ADMIN":
                for(Course c : all)
                    added.add(c);
                break;
        }
        
        courses.clear();
        courses = added;
        
        for(Course c : courses)
        {
            if(c.getPerson()==null)
            {
                c.setPerson(new Person());
                c.getPerson().setUsername("keiner");
            }
        }
        
        
        printAllCourses();
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
    
    public void createCourse(String title, String description, String description_en, String requirements, String begin, String end, String lector)
    {
        
        if(lector.equals(""))
            return;
        if(title.equals(""))
            return;
        if(description.equals(""))
            return;
        
        
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();
        
        Boolean ret = port.createCourse(title, description, description_en, requirements, fDateStr(begin), fDateStr(end), lector);

        if(ret)
        {
            addMessage(0, "Erfolgreich! ", "Kurs wurde angelegt.");
            resetVars();
            getAll();
        }
        else
        {
            addMessage(3, "Fehler! ", "Anlegen fehlgeschlagen!");
        }
    }

    public void printAllCourses()
    {
        for (Course course : courses)
        {
            tinf(course.getCoursePk() + ": " + course.getTitle()+ " " + course.getDescription()+ " " + course.getDescriptionEn() + " Lector: " + course.getPerson().getUsername());
        }
    }

    public void printAllLectors()
    {
        for (Person lector : lectors)
        {
            tinf(lector.getPersonPk()+ ": " + lector.getUsername());
        }
    }
    
    public void saveCourse(int id, String title, String description, String description_en, String requirements, String begin, String end, String lector)
    {
        if(!(id >= 0))
            return;
        if(title.equals(""))
            return;
        if(description.equals(""))
            return;
        if(description_en.equals(""))
            return;
        if(lector.equals(""))
            return;
        if(lector.equals("keiner"))
            lector = null;
        if(begin.equals(""))
            return;
        if(end.equals(""))
            return;
        
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();

        Boolean ret = port.saveCourse(id, title, description, description_en, requirements, fDateStr(begin), fDateStr(end), lector);

        if(ret)
        {
            addMessage(0, "Erfolgreich! ", "Kurs wurde gespeichert.");
            resetVars();
            getAll();
        }
        else
        {
            addMessage(3, "Fehler! ", "Speichern fehlgeschlagen!");
        }
    }
    
    public void deleteCourse(int id)
    {
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();
        
        Boolean ret = port.deleteCourse(id);

        if(ret)
        {
            addMessage(0, "Erfolgreich! ", "Kurs wurde gelöscht.");
            getAll();
        }
        else
        {
            addMessage(3, "Fehler! ", "Löschen fehlgeschlagen!");
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

    public String getNewDescription_en() {
        return newDescription_en;
    }

    public void setNewDescription_en(String newDescription_en) {
        this.newDescription_en = newDescription_en;
    }

    public String getNewRequirements() {
        return newRequirements;
    }

    public void setNewRequirements(String newRequirements) {
        this.newRequirements = newRequirements;
    }

    public String getNewBegin() {
        return newBegin;
    }

    public void setNewBegin(String newBegin) {
        this.newBegin = newBegin;
    }

    public String getNewEnd() {
        return newEnd;
    }

    public void setNewEnd(String newEnd) {
        this.newEnd = newEnd;
    }
    
    
}
