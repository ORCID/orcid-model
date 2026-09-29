package org.orcid.jaxb.model.record_v2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.InputStream;
import java.io.StringWriter;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

import org.junit.Test;
import org.orcid.jaxb.model.common_v2.Visibility;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The flags are boxed Booleans. java.beans.Introspector only accepts an "is" read method for
 * primitive boolean, so with isVerified() alone the property is write-only to anything that
 * copies beans by introspection, and ORCID-Source's v2.0/v2.1 version converter returned every
 * email with verified and primary set to null. These tests pin the read methods, and check that
 * adding them leaves the XML and JSON output as it was.
 */
public class EmailBeanPropertiesTest {

    @Test
    public void testIntrospectorSeesAReadAndAWriteMethodForEachFlag() throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(Email.class);
        for (String flag : new String[] { "verified", "primary", "current" }) {
            PropertyDescriptor pd = find(beanInfo, flag);
            assertNotNull(flag + " has no read method, so bean copies drop it", pd.getReadMethod());
            assertNotNull(flag + " has no write method", pd.getWriteMethod());
            assertEquals(Boolean.class, pd.getPropertyType());
        }
    }

    @Test
    public void testMarshalledXmlKeepsTheFlagAttributes() throws Exception {
        Email email = emailWithFlags(Boolean.TRUE, Boolean.FALSE);
        email.setCurrent(Boolean.TRUE);

        StringWriter xml = new StringWriter();
        Marshaller marshaller = JAXBContext.newInstance(Email.class).createMarshaller();
        marshaller.marshal(email, xml);

        assertTrue(xml.toString(), xml.toString().contains("verified=\"true\""));
        assertTrue(xml.toString(), xml.toString().contains("primary=\"false\""));
        assertFalse("current is transient and must not be marshalled", xml.toString().contains("current"));
    }

    /**
     * Reads the flags the way a bean copy does, through the introspected read method, so false
     * has to come back as false rather than being skipped as unreadable.
     */
    @Test
    public void testUnmarshalledSampleFlagsAreReadableByIntrospection() throws Exception {
        try (InputStream in = getClass().getResourceAsStream("/record_2.0/samples/read_samples/email-2.0.xml")) {
            Email email = (Email) JAXBContext.newInstance(Email.class).createUnmarshaller().unmarshal(in);
            BeanInfo beanInfo = Introspector.getBeanInfo(Email.class);
            assertEquals(Boolean.TRUE, read(beanInfo, "primary", email));
            assertEquals(Boolean.FALSE, read(beanInfo, "verified", email));
        }
    }

    /**
     * Jackson accepts both isVerified() and getVerified() for a boxed Boolean. It must pick one
     * without a "Conflicting getter definitions" error, and false must stay false.
     */
    @Test
    public void testJacksonSerialisesEachFlagOnceWithoutAConflict() throws Exception {
        Email email = emailWithFlags(Boolean.TRUE, Boolean.FALSE);
        email.setCurrent(Boolean.TRUE);

        String json = new ObjectMapper().writeValueAsString(email);

        assertTrue(json, json.contains("\"verified\":true"));
        assertTrue(json, json.contains("\"primary\":false"));
        assertFalse("current is @JsonIgnore and must not be serialised", json.contains("current"));
    }

    private Email emailWithFlags(Boolean verified, Boolean primary) {
        Email email = new Email();
        email.setEmail("test@orcid.org");
        email.setVisibility(Visibility.PUBLIC);
        email.setVerified(verified);
        email.setPrimary(primary);
        return email;
    }

    private Object read(BeanInfo beanInfo, String name, Email email) throws Exception {
        PropertyDescriptor pd = find(beanInfo, name);
        assertNotNull(name + " has no read method, so bean copies drop it", pd.getReadMethod());
        return pd.getReadMethod().invoke(email);
    }

    private PropertyDescriptor find(BeanInfo beanInfo, String name) {
        for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
            if (name.equals(pd.getName())) {
                return pd;
            }
        }
        throw new AssertionError("no property descriptor named " + name);
    }
}
