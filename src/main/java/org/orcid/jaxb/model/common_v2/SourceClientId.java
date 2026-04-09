package org.orcid.jaxb.model.common_v2;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@XmlRootElement(name = "sourceClientId")
@Schema(description = "SourceClientIdV2_0")
public class SourceClientId extends OrcidIdBase implements Serializable {

    private static final long serialVersionUID = 1L;

    public SourceClientId() {
        super();
    }

    public SourceClientId(String path) {
        super(path);
    }

    public SourceClientId(OrcidIdBase other) {
        super(other);
    }

}
