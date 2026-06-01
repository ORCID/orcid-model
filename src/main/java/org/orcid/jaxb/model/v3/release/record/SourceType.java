package org.orcid.jaxb.model.v3.release.record;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author Daniel Palafox
 *
 */
@XmlEnum
@Schema(description = "SourceTypeV3_0")
public enum SourceType {
    @XmlEnumValue("USER") USER("USER");

    private final String value;

    SourceType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SourceType fromValue(String v) {
        for (SourceType c : SourceType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
