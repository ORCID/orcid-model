package org.orcid.jaxb.model.common_v2;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

/**
 * @author Declan Newman (declan) Date: 07/08/2012
 */
@XmlType(name = "contributorRole")
@XmlEnum
@Schema(description = "ContributorRoleV2_0")
public enum ContributorRole implements Serializable {

    @XmlEnumValue("author")
    AUTHOR("author"), @XmlEnumValue("assignee")
    ASSIGNEE("assignee"), @XmlEnumValue("editor")
    EDITOR("editor"), @XmlEnumValue("chair-or-translator")
    CHAIR_OR_TRANSLATOR("chair-or-translator"), @XmlEnumValue("co-investigator")
    CO_INVESTIGATOR("co-investigator"), @XmlEnumValue("co-inventor")
    CO_INVENTOR("co-inventor"), @XmlEnumValue("graduate-student")
    GRADUATE_STUDENT("graduate-student"), @XmlEnumValue("other-inventor")
    OTHER_INVENTOR("other-inventor"), @XmlEnumValue("principal-investigator")
    PRINCIPAL_INVESTIGATOR("principal-investigator"), @XmlEnumValue("postdoctoral-researcher")
    POSTDOCTORAL_RESEARCHER("postdoctoral-researcher"), @XmlEnumValue("support-staff")
    SUPPORT_STAFF("support-staff");

    private final String value;

    ContributorRole(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @JsonValue
    public String jsonValue() {
        return this.name();
    }
    
    public static ContributorRole fromValue(String v) {
        for (ContributorRole c : ContributorRole.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
