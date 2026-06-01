package org.orcid.jaxb.model.common_v2;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@XmlRootElement(name = "sourceOrcid")
@Schema(description = "SourceOrcidV2_0")
public class SourceOrcid extends OrcidIdBase implements Serializable {

    private static final long serialVersionUID = 1L;

    public SourceOrcid() {
        super();
    }

    public SourceOrcid(String path) {
        super(path);
    }

    public SourceOrcid(OrcidIdBase other) {
        super(other);
    }

}
