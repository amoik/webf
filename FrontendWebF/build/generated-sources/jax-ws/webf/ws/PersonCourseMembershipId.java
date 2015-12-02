
package webf.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für personCourseMembershipId complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="personCourseMembershipId">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="courseFk" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="personFk" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "personCourseMembershipId", propOrder = {
    "courseFk",
    "personFk"
})
public class PersonCourseMembershipId {

    protected int courseFk;
    protected int personFk;

    /**
     * Ruft den Wert der courseFk-Eigenschaft ab.
     * 
     */
    public int getCourseFk() {
        return courseFk;
    }

    /**
     * Legt den Wert der courseFk-Eigenschaft fest.
     * 
     */
    public void setCourseFk(int value) {
        this.courseFk = value;
    }

    /**
     * Ruft den Wert der personFk-Eigenschaft ab.
     * 
     */
    public int getPersonFk() {
        return personFk;
    }

    /**
     * Legt den Wert der personFk-Eigenschaft fest.
     * 
     */
    public void setPersonFk(int value) {
        this.personFk = value;
    }

}
