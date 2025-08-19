package org.orcid.jaxb.model.message;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

import org.apache.commons.lang3.StringUtils;

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
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}amount" minOccurs="1" maxOccurs="1"/&gt;
 *         &lt;attGroup ref="{http://www.orcid.org/ns/orcid}currency-code" minOccurs="1"/&gt;
 *       &lt;/sequence&gt; *    
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "content" })
@XmlRootElement(name = "amount")
public class Amount implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @XmlValue
    protected String content;
    @XmlAttribute(name = "currency-code", required = true)
    protected String currencyCode;

    public Amount() {
    	
    }

    public Amount(String content) {
        this.content = content;
    }

    public Amount(String content, String currencyCode) {
        this.content = content;
        this.currencyCode = currencyCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int result = 1;
        result = prime * result + (StringUtils.isEmpty(this.content) ? 0 : this.content.hashCode());
        result = prime * result + (this.currencyCode == null ? 0 : this.currencyCode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Amount other = (Amount) obj;
        if (this.content == null) {
            if (other.content != null)
                return false;
        } else if (!this.content.equals(other.content))
            return false;

        if (this.currencyCode == null) {
            if (other.currencyCode != null)
                return false;
        } else if (!this.currencyCode.equals(other.currencyCode))
            return false;

        return true;
    }
}
