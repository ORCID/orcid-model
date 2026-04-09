package org.orcid.jaxb.model.message;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;

/**
 * Java class for anonymous complex type.
 * 
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "content" })
@XmlRootElement(name = "salesforce-id")
public class SalesforceId implements Serializable {

    private static final long serialVersionUID = 1L;
    @XmlValue
    protected String content;

    public SalesforceId() {
        
    }
    
    public SalesforceId(String content) {
        this.content = content;
    }
    
    /**
     * Gets the value of the content property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the value of the content property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setContent(String value) {
        this.content = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        SalesforceId that = (SalesforceId) o;

        if (content != null ? !content.equals(that.content) : that.content != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return content != null ? content.hashCode() : 0;
    }
}
