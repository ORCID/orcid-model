package org.orcid.jaxb.model.clientgroup;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonValue;


/**
 * Java class for client-type.
 * 
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;simpleType name="client-type"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="basic"/&gt;
 *     &lt;enumeration value="premium"/&gt;
 *     &lt;enumeration value="basic-institution"/&gt;
 *     &lt;enumeration value="premium-institution"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "group-type")
@XmlEnum
public enum MemberType {
    
    //@formatter:off
    @XmlEnumValue("basic")
    BASIC("basic"), 
    @XmlEnumValue("premium")
    PREMIUM("premium"), 
    @XmlEnumValue("basic-institution")
    BASIC_INSTITUTION("basic-institution"),
    @XmlEnumValue("premium-institution")
    PREMIUM_INSTITUTION("premium-institution");
    //@formatter:on
    
    private final String value;

    MemberType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @JsonValue
    public String jsonValue() {
        return this.name();
    }
    
    public static MemberType fromValue(String v) {
        for (MemberType c : MemberType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
