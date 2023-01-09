package org.orcid.jaxb.model.v3.rc2.record.summary;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@XmlRootElement(name = "employment-summary", namespace = "http://www.orcid.org/ns/employment")
@Schema(description = "EmploymentSummaryV3_0_rc2")
public class EmploymentSummary extends AffiliationSummary implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4147903643760430166L;
}
