package org.orcid.jaxb.model.v3.release.common;

/**
 * =============================================================================
 *
 * ORCID (R) Open Source
 * http://orcid.org
 *
 * Copyright (c) 2012-2014 ORCID, Inc.
 * Licensed under an MIT-Style License (MIT)
 * http://orcid.org/open-source-license
 *
 * This copyright and license information (including a link to the full license)
 * shall be included in its entirety in all copies or substantial portion of
 * the software.
 *
 * =============================================================================
 */
import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.v3.oas.annotations.media.Schema;

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = { "uri", "path", "host" })
@JsonPropertyOrder({ "uri", "path", "host" })
@JsonIgnoreProperties({ "value", "valueAsString" })
@Schema(description = "OrcidIdBaseV3_0")
public class OrcidIdBase implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String uri;

    protected String path;

    protected String host;

    public OrcidIdBase() {
        super();
    }

    public OrcidIdBase(OrcidIdBase other) {
        this.uri = other.uri;
        this.path = other.path;
        this.host = other.host;
    }

    public OrcidIdBase(String path) {
        this.path = path;
    }

    @XmlElement(namespace = "http://www.orcid.org/ns/common")
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @XmlElement(namespace = "http://www.orcid.org/ns/common")
    public String getPath() {
        if (path != null) {
            return path;
        }
        if (uri != null) {
            return uri.substring(uri.lastIndexOf('/') + 1);
        }
        return null;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @XmlElement(namespace = "http://www.orcid.org/ns/common")
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((path == null) ? 0 : path.hashCode());
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
        OrcidIdBase other = (OrcidIdBase) obj;
        if (path == null) {
            if (other.path != null)
                return false;
        } else if (!path.equals(other.path))
            return false;
        return true;
    }

}
