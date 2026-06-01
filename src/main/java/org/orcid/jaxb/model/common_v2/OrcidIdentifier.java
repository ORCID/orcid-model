package org.orcid.jaxb.model.common_v2;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@XmlRootElement(name = "orcid-identifier")
@Schema(description = "OrcidIdentifierV2_0")
public class OrcidIdentifier extends OrcidIdBase implements Serializable {

    private static final long serialVersionUID = 1L;

    public OrcidIdentifier() {
        super();
    }

    public OrcidIdentifier(String path) {
        super(path);
    }

    public OrcidIdentifier(OrcidIdBase other) {
        super(other);
    }

}
