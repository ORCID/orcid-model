package org.orcid.jaxb.model.v3.release.record.summary;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@XmlRootElement(name = "invited-position-summary", namespace = "http://www.orcid.org/ns/invited-position")
@Schema(description = "InvitedPositionSummaryV3_0")
public class InvitedPositionSummary extends AffiliationSummary implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5195707602485199190L;

}
