package dto;

import com.testProject.generatedClasses.com.testProject.genClasses.User;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{com/namespace/xsd/user.xsd}user" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "users"
})
@XmlRootElement(name = "users")
public class ManyUsersDto {

    private final static long serialVersionUID = -1L;
    @XmlElement(name = "user", namespace = "http://proxy.external.example.com/user.xsd", required = true)
    protected List<UserDto> users;

    /**
     * Gets the value of the users property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the users property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUsers().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link User }
     */
    public List<UserDto> getUsers() {
        if (users == null) {
            users = new ArrayList<UserDto>();
        }
        return this.users;
    }

}
