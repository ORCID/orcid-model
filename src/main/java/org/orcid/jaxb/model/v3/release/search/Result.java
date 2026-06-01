package org.orcid.jaxb.model.v3.release.search;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

import org.orcid.jaxb.model.v3.release.common.OrcidIdentifier;

import io.swagger.v3.oas.annotations.media.Schema;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "orcidIdentifier" })
@Schema(description = "ResultV3_0")
public class Result implements Serializable {
    private static final long serialVersionUID = -7750679507838375771L;
    @XmlElement(name = "orcid-identifier", namespace = "http://www.orcid.org/ns/common")
    protected OrcidIdentifier orcidIdentifier;

    public OrcidIdentifier getOrcidIdentifier() {
        return orcidIdentifier;
    }

    public void setOrcidIdentifier(OrcidIdentifier orcidIdentifier) {
        this.orcidIdentifier = orcidIdentifier;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((orcidIdentifier == null) ? 0 : orcidIdentifier.hashCode());
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
        Result other = (Result) obj;
        if (orcidIdentifier == null) {
            if (other.orcidIdentifier != null)
                return false;
        } else if (!orcidIdentifier.equals(other.orcidIdentifier))
            return false;
        return true;
    }

}
