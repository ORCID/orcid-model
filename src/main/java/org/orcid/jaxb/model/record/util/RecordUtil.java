package org.orcid.jaxb.model.record.util;

import java.io.StringWriter;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

public class RecordUtil {

    /**
     * 
     * Marshalls the given argment.
     * 
     * @param obj - object to be marshalled
     * @return String representation of the marshalled object
     */
    public static String convertToString(Object obj) {
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            StringWriter writer = new StringWriter();
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(obj, writer);
            return writer.toString();
        } catch (JAXBException e) {
            return ("Unable to unmarshal because: " + e);
        }
    }

}
