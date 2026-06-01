package org.orcid.jaxb.model.record_v2;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;

@XmlType(name = "type")
@XmlEnum
@Schema(description = "PeerReviewTypeV2_0")
public enum PeerReviewType implements Serializable {
    @XmlEnumValue("review")
    REVIEW("review"),
    @XmlEnumValue("evaluation")
    EVALUATION("evaluation");
        
    private final String value;

    PeerReviewType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @JsonValue
    public String jsonValue() {
        return this.name();
    }
    
    public static PeerReviewType fromValue(String v) {
        for (PeerReviewType c : PeerReviewType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
