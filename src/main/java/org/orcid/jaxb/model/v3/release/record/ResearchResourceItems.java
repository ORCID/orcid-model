package org.orcid.jaxb.model.v3.release.record;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import io.swagger.v3.oas.annotations.media.Schema;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "resourceItems" })
@XmlRootElement(name = "resource-items", namespace = "http://www.orcid.org/ns/research-resource")
@Schema(description = "ResearchResourceItemsV3_0")
public class ResearchResourceItems implements Serializable {
    private static final long serialVersionUID = 6414351720429265799L;
    @XmlElement(namespace = "http://www.orcid.org/ns/research-resource", name = "resource-item")
    List<ResearchResourceItem> resourceItems;

    public List<ResearchResourceItem> getResourceItems() {
        if (resourceItems == null) {
            resourceItems = new ArrayList<ResearchResourceItem>();
        }
        return this.resourceItems;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((resourceItems == null) ? 0 : resourceItems.hashCode());
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
        ResearchResourceItems other = (ResearchResourceItems) obj;
        if (resourceItems == null) {
            if (other.resourceItems != null)
                return false;
        } else if (!resourceItems.equals(other.resourceItems))
            return false;
        return true;
    }
}
