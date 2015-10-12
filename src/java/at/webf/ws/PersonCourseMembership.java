package at.webf.ws;
// Generated 16.09.2015 11:23:20 by Hibernate Tools 4.3.1



/**
 * PersonCourseMembership generated by hbm2java
 */
public class PersonCourseMembership  implements java.io.Serializable {


     private PersonCourseMembershipId id;
     private Course course;
     private Person person;
     private int note;

    public PersonCourseMembership() {
    }

    public PersonCourseMembership(PersonCourseMembershipId id, Course course, Person person, int note) {
       this.id = id;
       this.course = course;
       this.person = person;
       this.note = note;
    }
   
    public PersonCourseMembershipId getId() {
        return this.id;
    }
    
    public void setId(PersonCourseMembershipId id) {
        this.id = id;
    }
    public Course getCourse() {
        return this.course;
    }
    
    public void setCourse(Course course) {
        this.course = course;
    }
    public Person getPerson() {
        return this.person;
    }
    
    public void setPerson(Person person) {
        this.person = person;
    }
    public int getNote() {
        return this.note;
    }
    
    public void setNote(int note) {
        this.note = note;
    }




}


