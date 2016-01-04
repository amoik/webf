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
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String birthday;
    
    private List<Person> students;
    
    
    public Students()
    {
        students = new ArrayList<Person>();
    }
    
    public void onload()
    {
        if(Login.getLoginName().equals(""))
        {
            tinf("not logged in!");
            FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "failure.xhtml");
        }
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

        Boolean ret = port.createPerson(username, password, "student", firstname, lastname, dt.toString());

        
        if(ret)
        {
            resetVars();
            getAll();
        }
    }
    
    public void getAll()
    {
        WebServices_Service service = new WebServices_Service();
        WebServices port = service.getWebServicesPort();  

        setStudents(port.getAllPersons());
        
        if( getStudents() != null)
        {
            printAllPersons();
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

    public void printAllPersons()
    {
        for (Person student : students) {
            tinf(student.getUsername() + " " + student.getRole() + " " + student.getBirthday().toString());
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

}


/*
package webf.beans;

import static java.lang.System.in;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import static webf.beans.Utils.tinf;
import webf.ws.Person;
import webf.ws.WebServices;
import webf.ws.WebServices_Service;
*/
/**
 *
 * @author anti88
 *//*
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

*/
