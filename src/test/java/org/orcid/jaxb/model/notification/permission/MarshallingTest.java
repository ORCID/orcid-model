package org.orcid.jaxb.model.notification.permission;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import org.apache.commons.io.IOUtils;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Test;
import org.orcid.jaxb.model.v3.release.notification.NotificationType;
import org.orcid.jaxb.model.v3.release.notification.permission.ItemType;
import org.orcid.jaxb.model.v3.release.notification.permission.NotificationPermission;
import org.orcid.jaxb.test.utils.OrcidTranslator;
import org.xml.sax.SAXException;

/**
 * 
 * @author Will Simpson
 * 
 */

public class MarshallingTest {

    private static final String SAMPLE_PATH_V2 = "/notification_2.0/samples/notification-permission-2.0.xml";
    private static final String SAMPLE_PATH_V3 = "/notification_3.0/samples/notification-permission-3.0.xml";


    @Test
    public void testMarshallingV2_0() throws JAXBException, IOException, SAXException {
        JAXBContext context = JAXBContext.newInstance("org.orcid.jaxb.model.notification.permission_v2");
        Unmarshaller unmarshaller = context.createUnmarshaller();
        InputStream inputStream = MarshallingTest.class.getResourceAsStream(SAMPLE_PATH_V2);
        org.orcid.jaxb.model.notification.permission_v2.NotificationPermission notification = (org.orcid.jaxb.model.notification.permission_v2.NotificationPermission) unmarshaller.unmarshal(inputStream);
        assertNotNull(notification);
        assertEquals(org.orcid.jaxb.model.notification_v2.NotificationType.PERMISSION, notification.getNotificationType());
        assertEquals(2, notification.getItems().getItems().size());
        assertEquals("2014-01-01T14:45:32", notification.getSentDate().toXMLFormat());

        // Back the other way
        String expected = IOUtils.toString(getClass().getResourceAsStream(SAMPLE_PATH_V2), "UTF-8");
        Pattern pattern = Pattern.compile("<!--.*?-->\\s*", Pattern.DOTALL);
        expected = pattern.matcher(expected).replaceAll("");
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://www.orcid.org/ns/notification ../notification-permission-2.0.xsd");
        StringWriter writer = new StringWriter();
        marshaller.marshal(notification, writer);
        XMLUnit.setIgnoreWhitespace(true);
        Diff diff = new Diff(expected, writer.toString());
        assertTrue(diff.identical());
    }

    @Test
    public void testMarshallingV3_0() throws JAXBException, IOException, SAXException {
        JAXBContext context = JAXBContext.newInstance("org.orcid.jaxb.model.v3.release.notification.permission");
        Unmarshaller unmarshaller = context.createUnmarshaller();
        InputStream inputStream = MarshallingTest.class.getResourceAsStream(SAMPLE_PATH_V3);
        NotificationPermission notification = (NotificationPermission) unmarshaller.unmarshal(inputStream);
        assertNotNull(notification);
        assertEquals(NotificationType.PERMISSION, notification.getNotificationType());
        assertEquals(4, notification.getItems().getItems().size());
        assertEquals(ItemType.WORK, notification.getItems().getItems().get(0).getItemType());
        assertEquals(ItemType.EMPLOYMENT, notification.getItems().getItems().get(1).getItemType());
        assertEquals(ItemType.DISTINCTION, notification.getItems().getItems().get(2).getItemType());
        assertEquals(ItemType.SERVICE, notification.getItems().getItems().get(3).getItemType());
        assertEquals("2014-01-01T14:45:32", notification.getSentDate().toXMLFormat());

        // Back the other way
        String expected = IOUtils.toString(getClass().getResourceAsStream(SAMPLE_PATH_V3), "UTF-8");
        Pattern pattern = Pattern.compile("<!--.*?-->\\s*", Pattern.DOTALL);
        expected = pattern.matcher(expected).replaceAll("");
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://www.orcid.org/ns/notification ../notification-permission-3.0.xsd");
        StringWriter writer = new StringWriter();
        marshaller.marshal(notification, writer);
        XMLUnit.setIgnoreWhitespace(true);
        Diff diff = new Diff(expected, writer.toString());
        assertTrue(diff.identical());
    }

    @Test
    public void testMarshalNotificationPermission() throws JAXBException, SAXException, URISyntaxException {
        OrcidTranslator<NotificationPermission> t = OrcidTranslator.V3_0_NOTIFICATION_PERMISSION();
        NotificationPermission object = (NotificationPermission) t.unmarshalFromPath("/notification_3.0/samples/notification-permission-3.0.xml", NotificationPermission.class, null);
        t.marshal(object, "/notification_3.0/notification-permission-3.0.xsd");
    }
}
