package org.orcid.jaxb.model.v3.release.record;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author Declan Newman (declan) Date: 31/07/2012
 */
@XmlType(name = "type")
@XmlEnum
@Schema(description = "AffiliationTypeV3_0")
public enum AffiliationType implements Serializable {

    @XmlEnumValue("distinction")
    DISTINCTION("distinction"),
    
    @XmlEnumValue("education")
    EDUCATION("education"),

    @XmlEnumValue("employment")
    EMPLOYMENT("employment"),
    
    @XmlEnumValue("invited-postition")
    INVITED_POSITION("invited-position"),
    
    @XmlEnumValue("membership")
    MEMBERSHIP("membership"),
    
    @XmlEnumValue("qualification")
    QUALIFICATION("qualification"),
    
    @XmlEnumValue("service")
    SERVICE("service");
    
    private final String value;

    AffiliationType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }       

    public static AffiliationType fromValue(String v) {
        for (AffiliationType c : AffiliationType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
