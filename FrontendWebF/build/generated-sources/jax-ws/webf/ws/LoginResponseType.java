
package webf.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für loginResponseType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="loginResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="loginResponse" type="{http://webservice.webf/}loginResponse2" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "loginResponseType", propOrder = {
    "loginResponse"
})
public class LoginResponseType {

    protected LoginResponse2 loginResponse;

    /**
     * Ruft den Wert der loginResponse-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link LoginResponse2 }
     *     
     */
    public LoginResponse2 getLoginResponse() {
        return loginResponse;
    }

    /**
     * Legt den Wert der loginResponse-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link LoginResponse2 }
     *     
     */
    public void setLoginResponse(LoginResponse2 value) {
        this.loginResponse = value;
    }

}
