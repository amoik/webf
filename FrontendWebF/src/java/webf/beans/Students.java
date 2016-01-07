/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webf.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;
import static utils.Utils.addMessage;
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
public class Students
{
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String birthday;
    
    private int newMembership_cid;
    
    private List<Person> students;
    private List<PersonCourseMembership> memberships;
    private Courses courses;
    
    
    
    public Students()
    {
        students = new ArrayList<Person>();
        memberships = new ArrayList<PersonCourseMembership>();
        courses = new Courses();
        
    }
    
    public void onload()
    {
        if(Login.getLoginName().equals(""))
        {
            tinf("not logged in!");
            FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "logout.xhtml");
        }
        courses.onload();
        
        resetVars();
        getAll();
    }
    
    public void createStudent()
    {
        if(getFirstname().equals(""))
        {
            return;
        }
        if(getLastname().equals(""))
        {
            return;
        }
        if(getUsername().equals(""))
        {
            return;
        }
        
        if(getPassword().equals(""))
        {
            return;
        }
        
        tinf("creating student "+getUsername()+"...");
        
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();      

        Date dt = new Date();
        dt.setTime(0);

        Boolean ret = port.createPerson(username, password, 3, firstname, lastname, dt.toString());

        
        if(ret)
        {
            resetVars();
            getAll();
        }
    }
    
    public void createMembership(int course_id, int person_id)
    {
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();
        
        Course c = null;
        Person p = null;
        for(Person pi : students)
        {
            if(pi.getPersonPk() == person_id)
            {
                p = pi;
                break;
            }
        }
        for(Course ci : courses.getCourses())
        {
            if(ci.getCoursePk() == course_id)
            {
                c = ci;
                break;
            }
        }
        
        if(c == null || p == null || !port.createMembership(c, p))
        {
            addMessage(3, "Fehler!", "Anlegen fehlgeschlagen!");
        }
        else
        {
            addMessage(0, "Erfolgreich", "wurde angelegt");
            getAll();
        }
    }
    
    public void deleteMembership(int cid, int pid)
    {
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();  

        Boolean ret = port.deleteMembership(cid, pid);
        
        if(ret)
        {
            getAll();
        }
        else
        {
            addMessage(3, "Fehler!", "Löschen fehlgeschlagen!");
        }
    }
    
    public void getAll()
    {
        getAllStudents();
        getAllMemberships();
    }
    
    public void getAllStudents()
    {
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();  

        setStudents(port.getAllStudents());
        
        if( getStudents() != null)
        {
            printAllStudents();
        }
    }
    
    public void getAllMemberships()
    {
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();  

        setMemberships(port.getAllMemberships());
        
        if( getMemberships() != null)
        {
            //printAllMemberships();
        }
    }
  
    public void deletePerson(int id)
    {
        tinf("person " + id + " wird gelöscht");
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();  

        Boolean ret = port.deletePerson(id);
        
        if(ret)
        {
            getAll();
        }
    }

    public void printAllStudents()
    {
        for (Person student : students) {
            tinf(student.getUsername() + " " + student.getRole().getTitle() + " " + student.getBirthday().toString());
        }
    }
    public void printAllMemberships()
    {
        for (PersonCourseMembership mem : memberships) {
            tinf(mem.getPerson().getUsername()+ " is in " + mem.getCourse().getTitle() + " and has " + mem.getNote());
        }
    }
    
    public ArrayList<PersonCourseMembership> getAllMembershipsForUsername(String username)
    {
        ArrayList<PersonCourseMembership> mems = new ArrayList<PersonCourseMembership>();
        
        for(PersonCourseMembership m : memberships)
        {
            if(m.getPerson().getUsername().equals(username))
            {
                mems.add(m);
            }
        }
        
        return mems;
    }
    
    public void saveMembership(int course_id, int person_id, int note)
    {
        
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();
        
        Course c = null;
        Person p = null;
        for(Person pi : students)
        {
            if(pi.getPersonPk() == person_id)
            {
                p = pi;
                break;
            }
        }
        for(Course ci : courses.getCourses())
        {
            if(ci.getCoursePk() == course_id)
            {
                c = ci;
                break;
            }
        }
        
        if(c == null || p == null || !port.saveMembership(c, p, note))
        {
            addMessage(3, "Fehler!", "Speichern fehlgeschlagen!");
        }
        else
        {
            addMessage(0, "Erfolgreich", "wurde gespeichert");
            getAll();
        }
    }
    
    
    public void resetVars()
    {
        setUsername("");
        setPassword("");
        setFirstname("");
        setLastname("");
      
        students.clear();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public List<Person> getStudents() {
        return students;
    }

    public void setStudents(List<Person> students) {
        this.students = students;
    }

    public List<PersonCourseMembership> getMemberships() {
        return memberships;
    }

    public void setMemberships(List<PersonCourseMembership> memberships) {
        this.memberships = memberships;
    }

    public int getNewMembership_cid() {
        return newMembership_cid;
    }

    public void setNewMembership_cid(int newMembership_cid) {
        this.newMembership_cid = newMembership_cid;
    }

    public Courses getCourses() {
        return courses;
    }

    public void setCourses(Courses courses) {
        this.courses = courses;
    }

}
