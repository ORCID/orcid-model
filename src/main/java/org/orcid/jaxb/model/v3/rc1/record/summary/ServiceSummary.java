package org.orcid.jaxb.model.v3.rc1.record.summary;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

@XmlRootElement(name = "service-summary", namespace = "http://www.orcid.org/ns/service")
@Schema(description = "ServiceSummaryV3_0_rc1")
public class ServiceSummary extends AffiliationSummary implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 7314776433582416993L;

}
