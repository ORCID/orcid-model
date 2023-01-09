package org.orcid.jaxb.model.v3.rc1.record.summary;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@XmlRootElement(name = "qualification-summary", namespace = "http://www.orcid.org/ns/qualification")
@Schema(description = "QualificationSummaryV3_0_rc1")
public class QualificationSummary extends AffiliationSummary implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1058178985146686275L;

}
