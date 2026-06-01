package org.orcid.jaxb.model.v3.release.search.expanded;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import io.swagger.v3.oas.annotations.media.Schema;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "results" })
@XmlRootElement(name = "expanded-search", namespace = "http://www.orcid.org/ns/expanded-search")
@Schema(description = "SearchV3_0")
public class ExpandedSearch implements Serializable {
    
    private static final long serialVersionUID = -1791045354400556107L;
    
    @XmlElement(name = "expanded-result", namespace = "http://www.orcid.org/ns/expanded-search")
    protected List<ExpandedResult> results;
    
    @XmlAttribute(name = "num-found")
    protected Long numFound;

    public List<ExpandedResult> getResults() {
        if (results == null) {
            results = new ArrayList<ExpandedResult>();
        }
        return this.results;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((results == null) ? 0 : results.hashCode());
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
        ExpandedSearch other = (ExpandedSearch) obj;
        if (results == null) {
            if (other.results != null)
                return false;
        } else if (!results.equals(other.results))
            return false;
        return true;
    }

    public Long getNumFound() {
        return numFound;
    }

    public void setNumFound(Long numFound) {
        this.numFound = numFound;
    }
}
