package org.orcid.jaxb.model.v3.rc1.record.summary;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@XmlRootElement(name = "distinction-summary", namespace = "http://www.orcid.org/ns/distinction")
@Schema(description = "DistinctionSummaryV3_0_rc1")
public class DistinctionSummary extends AffiliationSummary implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3789966609440238219L;
}
