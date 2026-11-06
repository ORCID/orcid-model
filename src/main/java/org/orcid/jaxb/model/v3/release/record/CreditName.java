package org.orcid.jaxb.model.v3.release.record;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author Angel Montenegro
 * 
 */
@XmlRootElement(name = "credit-name", namespace = "http://www.orcid.org/ns/personal-details")
@Schema(name = "RecordCreditName")
public class CreditName extends org.orcid.jaxb.model.v3.release.common.CreditName implements Serializable {
    private static final long serialVersionUID = -4407704518314072926L;

}
