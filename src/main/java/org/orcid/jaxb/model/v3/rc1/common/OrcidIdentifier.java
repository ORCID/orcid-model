package org.orcid.jaxb.model.v3.rc1.common;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@XmlRootElement(name = "orcid-identifier")
@Schema(description = "OrcidIdentifierV3_0_rc1")
public class OrcidIdentifier extends OrcidIdBase implements Serializable {
    private static final long serialVersionUID = -9130699025620814488L;

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
