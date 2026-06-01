package org.orcid.jaxb.model.v3.release.groupid;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import org.orcid.jaxb.model.v3.release.common.CreatedDate;
import org.orcid.jaxb.model.v3.release.common.LastModifiedDate;
import org.orcid.jaxb.model.v3.release.common.Source;
import org.orcid.jaxb.model.v3.release.record.SourceAware;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Java class for anonymous complex type.
 * 
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.orcid.org/ns/common}source-type"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="name" type="{http://www.orcid.org/ns/common}string-1000"/&gt;
 *         &lt;element name="group-id"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.orcid.org/ns/common}string-1000"&gt;
 *               &lt;pattern value="(ringgold:|issn:|orcid-generated:|fundref:).*"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="description" type="{http://www.orcid.org/ns/common}non-empty-string"/&gt;
 *         &lt;element name="type"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="publisher"/&gt;
 *               &lt;enumeration value="institution"/&gt;
 *               &lt;enumeration value="journal"/&gt;
 *               &lt;enumeration value="conference"/&gt;
 *               &lt;enumeration value="newspaper"/&gt;
 *               &lt;enumeration value="newsletter"/&gt;
 *               &lt;enumeration value="magazine"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "lastModifiedDate", "createdDate", "name", "groupId", "description", "type", "source", "putCode" })
@XmlRootElement(name = "group-id-record", namespace = "http://www.orcid.org/ns/group-id")
public class GroupIdRecord implements Serializable, SourceAware {
    private static final long serialVersionUID = 1516751098639982549L;
    @XmlElement(namespace = "http://www.orcid.org/ns/group-id", required = true)
    protected String name;
    @XmlElement(name = "group-id", namespace = "http://www.orcid.org/ns/group-id", required = true)
    protected String groupId;
    @XmlElement(namespace = "http://www.orcid.org/ns/group-id", required = true)
    protected String description;
    @XmlElement(namespace = "http://www.orcid.org/ns/group-id", required = true)
    protected String type;
    @XmlElement(namespace = "http://www.orcid.org/ns/common")
    @JsonIgnore
    protected Source source;
    @XmlAttribute(name = "put-code")
    protected Long putCode;
    @JsonIgnore
    @XmlElement(name = "last-modified-date", namespace = "http://www.orcid.org/ns/common")    
    protected LastModifiedDate lastModifiedDate;
    @JsonIgnore
    @XmlElement(name = "created-date", namespace = "http://www.orcid.org/ns/common")
    protected CreatedDate createdDate;

    /**
     * Gets the value of the name property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the groupId property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * Sets the value of the groupId property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setGroupId(String value) {
        this.groupId = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setType(String value) {
        this.type = value;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public Long getPutCode() {
        return putCode;
    }

    public void setPutCode(Long putCode) {
        this.putCode = putCode;
    }

    public LastModifiedDate getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LastModifiedDate lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public CreatedDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(CreatedDate createdDate) {
        this.createdDate = createdDate;
    }

    public String retrieveSourcePath() {
        if (source == null) {
            return null;
        }
        return source.retrieveSourcePath();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
        result = prime * result + ((lastModifiedDate == null) ? 0 : lastModifiedDate.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((putCode == null) ? 0 : putCode.hashCode());
        result = prime * result + ((source == null) ? 0 : source.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        GroupIdRecord other = (GroupIdRecord) obj;
        if (createdDate == null) {
            if (other.createdDate != null)
                return false;
        } else if (!createdDate.equals(other.createdDate))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (groupId == null) {
            if (other.groupId != null)
                return false;
        } else if (!groupId.equals(other.groupId))
            return false;
        if (lastModifiedDate == null) {
            if (other.lastModifiedDate != null)
                return false;
        } else if (!lastModifiedDate.equals(other.lastModifiedDate))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (putCode == null) {
            if (other.putCode != null)
                return false;
        } else if (!putCode.equals(other.putCode))
            return false;
        if (source == null) {
            if (other.source != null)
                return false;
        } else if (!source.equals(other.source))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }
}
