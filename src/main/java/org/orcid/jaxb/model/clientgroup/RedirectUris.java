package org.orcid.jaxb.model.clientgroup;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * Java class for anonymous complex type.
 * 
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}redirect-uri" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "redirectUri" })
@XmlRootElement(name = "redirect-uris")
public class RedirectUris {

    @XmlElement(name = "redirect-uri", required = true)
    protected List<RedirectUri> redirectUri;

    /**
     * Gets the value of the redirectUri property.
     * 
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the redirectUri property.
     * 
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRedirectUri().add(newItem);
     * </pre>
     * 
     * 
     * Objects of the following type(s) are allowed in the list
     * {@link RedirectUri }
     * 
     * @return a List of RedirectUri objects
     * 
     */
    public List<RedirectUri> getRedirectUri() {
        if (redirectUri == null) {
            redirectUri = new ArrayList<RedirectUri>();
        }
        return this.redirectUri;
    }

}
