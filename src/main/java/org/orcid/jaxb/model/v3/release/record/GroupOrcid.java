package org.orcid.jaxb.model.v3.release.record;

import jakarta.xml.bind.annotation.XmlRootElement;

import org.orcid.jaxb.model.v3.release.common.OrcidIdentifier;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author Angel Montenegro
 * 
 */
@XmlRootElement(name = "application-group-orcid")
@Schema(description = "GroupOrcidV3_0")
public class GroupOrcid extends OrcidIdentifier {
    private static final long serialVersionUID = -7831298842584309866L;

}
