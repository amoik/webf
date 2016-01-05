package webf.hibernate.db;
// Generated 05.01.2016 15:15:31 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Role generated by hbm2java
 */
public class Role  implements java.io.Serializable {


     private int roleId;
     private String title;
     private Set persons = new HashSet(0);

    public Role() {
    }

	
    public Role(int roleId) {
        this.roleId = roleId;
    }
    public Role(int roleId, String title, Set persons) {
       this.roleId = roleId;
       this.title = title;
       this.persons = persons;
    }
   
    public int getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    public Set getPersons() {
        return this.persons;
    }
    
    public void setPersons(Set persons) {
        this.persons = persons;
    }




}


