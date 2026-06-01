package org.orcid.jaxb.model.message;

import java.io.Serializable;
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
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.orcid.org/ns/orcid}funding-external-identifier" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "fundingExternalIdentifier" })
@XmlRootElement(name = "funding-external-identifiers")
public class FundingExternalIdentifiers implements Serializable {
    private static final long serialVersionUID = 1L;
    @XmlElement(name = "funding-external-identifier")
    protected List<FundingExternalIdentifier> fundingExternalIdentifier;

    /**
     * Gets the value of the fundingExternalIdentifier property.
     * 
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the contributor property.
     * 
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getContributor().add(newItem);
     * </pre>
     * 
     * Objects of the following type(s) are allowed in the list
     * {@link Contributor }
     * 
     * @return a List of FundingExternalIdentifier objects
     * 
     */
    public List<FundingExternalIdentifier> getFundingExternalIdentifier() {
        if (fundingExternalIdentifier == null)
            fundingExternalIdentifier = new ArrayList<FundingExternalIdentifier>();
        return fundingExternalIdentifier;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fundingExternalIdentifier == null) ? 0 : fundingExternalIdentifier.hashCode());
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
        FundingExternalIdentifiers other = (FundingExternalIdentifiers) obj;
        if (fundingExternalIdentifier == null) {
            if (other.fundingExternalIdentifier != null)
                return false;
        } else {
            if (other.fundingExternalIdentifier == null)
                return false;
            else if (!(fundingExternalIdentifier.containsAll(other.fundingExternalIdentifier) && other.fundingExternalIdentifier.containsAll(fundingExternalIdentifier) && other.fundingExternalIdentifier
                    .size() == fundingExternalIdentifier.size())) {
                return false;
            }
        }
        return true;
    }

}
