package org.orcid.record.v3.release;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.orcid.jaxb.model.common.ContributorRole;
import org.orcid.jaxb.model.common.FundingContributorRole;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Guards the swagger {@link Schema} annotations on the v3.0 model, which the
 * Registry's OpenAPI generator reads. The annotations have no effect on JAXB or
 * Jackson serialisation; they only describe it, so this test checks that the
 * description stays true:
 * <ul>
 * <li>every v3.0 model class has an explicit schema name, unique across the
 * model, because the generator otherwise names schemas after XML elements and
 * classes such as the two {@code Works} overwrite each other;</li>
 * <li>no class, and no enum a v3.0 field uses, still carries an old
 * {@code XxxV3_0} or {@code XxxV2_0} label as its description;</li>
 * <li>every enum-valued field documents the values the API writes in JSON,
 * which is the normative format: what the field's XmlAdapter writes, else the
 * enum's {@code @JsonValue}, else its {@code @XmlEnumValue}, else the constant
 * name. Where the XSD enumerates the type, it may only narrow that list (it
 * drops {@code system} from visibility); where JSON and the XSD disagree
 * ({@code creation-method} is {@code DIRECT} in JSON and {@code Direct} in XML),
 * JSON wins. Fields whose generated values already equal the JSON values need no
 * annotation.</li>
 * <li>a field that lists allowed values without being an enum lists a known
 * vocabulary: the contributor roles are the type's original roles (the role
 * enums) plus the CRediT role URIs of {@code common-3.0.xsd}, which is the list
 * the Registry's own error 9058 gave when checked.</li>
 * </ul>
 */
public class OpenApiSchemaAnnotationsTest {

    private static final String[] MODEL_PACKAGES = { "org/orcid/jaxb/model/v3/release", "org/orcid/model/v3/release" };

    private static final String[] XSDS = { "/common_3.0/common-3.0.xsd", "/record_3.0/history-3.0.xsd", "/record_3.0/work-3.0.xsd" };

    /** Enums whose allowed values are fixed by an XSD simple type. */
    private static final Map<String, String> XSD_TYPE_BY_ENUM = Map.of("Visibility", "visibility", "SequenceType", "contributor-sequence",
            "CreationMethod", "creation-method");

    private static final Pattern OLD_LABEL = Pattern.compile("\\w*V\\d_\\d");

    @Test
    public void everyModelClassHasAUniqueSchemaName() throws Exception {
        List<String> problems = new ArrayList<>();
        Map<String, List<String>> classesByName = new TreeMap<>();
        for (Class<?> c : modelClasses()) {
            Schema schema = c.getAnnotation(Schema.class);
            if (schema == null || schema.name().isEmpty()) {
                problems.add(c.getName() + " has no @Schema(name)");
                continue;
            }
            classesByName.computeIfAbsent(schema.name(), k -> new ArrayList<>()).add(c.getName());
        }
        classesByName.forEach((name, classes) -> {
            if (classes.size() > 1) {
                problems.add("schema name " + name + " is used by " + classes);
            }
        });
        assertNoProblems(problems);
    }

    @Test
    public void noClassKeepsTheOldLabelAsItsDescription() throws Exception {
        List<String> problems = new ArrayList<>();
        Set<Class<?>> types = new LinkedHashSet<>(allTypes());
        for (Class<?> c : classesWithFields()) {
            for (Field field : c.getDeclaredFields()) {
                Class<?> enumType = enumTypeOf(field, adapterFor(field));
                if (enumType != null) {
                    types.add(enumType);
                }
            }
        }
        for (Class<?> c : types) {
            Schema schema = c.getAnnotation(Schema.class);
            if (schema != null && OLD_LABEL.matcher(schema.description()).matches()) {
                problems.add(c.getName() + " has description \"" + schema.description() + "\"");
            }
        }
        assertNoProblems(problems);
    }

    @Test
    public void enumFieldsDocumentTheirWireValues() throws Exception {
        Map<String, Set<String>> xsdEnumerations = xsdEnumerations();
        List<String> problems = new ArrayList<>();
        for (Class<?> c : classesWithFields()) {
            for (Field field : c.getDeclaredFields()) {
                Schema schema = field.getAnnotation(Schema.class);
                String[] allowed = schema == null ? new String[0] : schema.allowableValues();
                String where = c.getSimpleName() + "." + field.getName();
                for (String value : allowed) {
                    if (value.contains(",") || !value.equals(value.trim()) || value.isEmpty()) {
                        problems.add(where + " has allowableValues entry \"" + value + "\"; each value must be its own array element");
                    }
                }
                XmlAdapter<Object, Object> adapter = adapterFor(field);
                Class<?> enumType = enumTypeOf(field, adapter);
                if (enumType == null) {
                    continue;
                }
                Set<String> contract = contractValues(enumType, adapter, xsdEnumerations);
                if (allowed.length > 0) {
                    Set<String> documented = new TreeSet<>(Arrays.asList(allowed));
                    if (!documented.equals(contract)) {
                        problems.add(where + " documents " + documented + " but the wire values are " + contract);
                    }
                } else {
                    Set<String> generated = generatorDefaults(enumType);
                    if (!generated.equals(contract)) {
                        problems.add(where + " needs allowableValues " + contract + "; without them the generator emits " + abbreviate(generated));
                    }
                }
            }
        }
        assertNoProblems(problems);
    }

    @Test
    public void stringFieldsListTheVocabularyTheApiValidates() throws Exception {
        Set<String> credit = creditRoleUris();
        assertEquals("CRediT role URIs in common-3.0.xsd", 14, credit.size());
        Map<String, Set<String>> vocabularies = new HashMap<>();
        Set<String> work = new TreeSet<>(credit);
        for (ContributorRole role : ContributorRole.values()) {
            work.add(role.value());
        }
        Set<String> funding = new TreeSet<>(credit);
        for (FundingContributorRole role : FundingContributorRole.values()) {
            funding.add(role.value());
        }
        vocabularies.put("ContributorAttributes.contributorRole", work);
        vocabularies.put("FundingContributorAttributes.contributorRole", funding);

        List<String> problems = new ArrayList<>();
        Set<String> checked = new TreeSet<>();
        for (Class<?> c : classesWithFields()) {
            for (Field field : c.getDeclaredFields()) {
                Schema schema = field.getAnnotation(Schema.class);
                if (schema == null || schema.allowableValues().length == 0 || enumTypeOf(field, adapterFor(field)) != null) {
                    continue;
                }
                String where = c.getSimpleName() + "." + field.getName();
                checked.add(where);
                Set<String> expected = vocabularies.get(where);
                Set<String> documented = new TreeSet<>(Arrays.asList(schema.allowableValues()));
                if (expected == null) {
                    problems.add(where + " lists allowed values but this test knows no vocabulary to check them against");
                } else if (!documented.equals(expected)) {
                    problems.add(where + " documents " + documented + " but the vocabulary is " + expected);
                }
            }
        }
        for (String where : vocabularies.keySet()) {
            if (!checked.contains(where)) {
                problems.add(where + " no longer lists its vocabulary");
            }
        }
        assertNoProblems(problems);
    }

    /** The CRediT role URIs listed in the documentation of common-3.0.xsd's contributor-role type. */
    private static Set<String> creditRoleUris() throws Exception {
        Set<String> uris = new TreeSet<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        try (InputStream in = OpenApiSchemaAnnotationsTest.class.getResourceAsStream("/common_3.0/common-3.0.xsd")) {
            Document doc = factory.newDocumentBuilder().parse(in);
            NodeList simpleTypes = doc.getElementsByTagNameNS("http://www.w3.org/2001/XMLSchema", "simpleType");
            for (int i = 0; i < simpleTypes.getLength(); i++) {
                Element simpleType = (Element) simpleTypes.item(i);
                if ("contributor-role".equals(simpleType.getAttribute("name"))) {
                    for (String line : simpleType.getTextContent().split("\\n")) {
                        if (line.trim().startsWith("http://credit.niso.org/contributor-roles/")) {
                            uris.add(line.trim());
                        }
                    }
                }
            }
        }
        return uris;
    }

    private static void assertNoProblems(List<String> problems) {
        Collections.sort(problems);
        assertTrue(problems.size() + " problem(s):\n" + String.join("\n", problems), problems.isEmpty());
    }

    private static String abbreviate(Set<String> values) {
        List<String> list = new ArrayList<>(values);
        return list.size() <= 6 ? list.toString() : list.subList(0, 6) + " … (" + list.size() + " values)";
    }

    /** Values the API writes in JSON, narrowed by the XSD enumeration when the XSD lists a subset of them. */
    private static Set<String> contractValues(Class<?> enumType, XmlAdapter<Object, Object> adapter, Map<String, Set<String>> xsdEnumerations)
            throws Exception {
        Set<String> json = new TreeSet<>();
        for (Object constant : enumType.getEnumConstants()) {
            if (adapter == null) {
                json.add(defaultValue(enumType, constant));
                continue;
            }
            try {
                Object marshalled = adapter.marshal(constant);
                if (marshalled != null) {
                    json.add(marshalled.toString());
                }
            } catch (Exception e) {
                // A constant the adapter refuses never reaches the wire.
            }
        }
        String xsdType = XSD_TYPE_BY_ENUM.get(enumType.getSimpleName());
        if (xsdType != null) {
            Set<String> fromXsd = xsdEnumerations.get(xsdType);
            assertTrue("XSD simple type " + xsdType + " not found", fromXsd != null);
            if (json.containsAll(fromXsd)) {
                return new TreeSet<>(fromXsd);
            }
        }
        return json;
    }

    /** What the generator emits for an enum when nothing says otherwise: @JsonValue, else @XmlEnumValue, else name(). */
    private static Set<String> generatorDefaults(Class<?> enumType) throws Exception {
        Set<String> values = new TreeSet<>();
        for (Object constant : enumType.getEnumConstants()) {
            values.add(defaultValue(enumType, constant));
        }
        return values;
    }

    private static String defaultValue(Class<?> enumType, Object constant) throws Exception {
        for (Method method : enumType.getDeclaredMethods()) {
            if (method.isAnnotationPresent(JsonValue.class) && method.getParameterCount() == 0) {
                method.setAccessible(true);
                return String.valueOf(method.invoke(constant));
            }
        }
        String name = ((Enum<?>) constant).name();
        XmlEnumValue xmlEnumValue = enumType.getField(name).getAnnotation(XmlEnumValue.class);
        return xmlEnumValue != null ? xmlEnumValue.value() : name;
    }

    @SuppressWarnings("unchecked")
    private static XmlAdapter<Object, Object> adapterFor(Field field) throws Exception {
        XmlJavaTypeAdapter annotation = field.getAnnotation(XmlJavaTypeAdapter.class);
        if (annotation == null) {
            return null;
        }
        return (XmlAdapter<Object, Object>) annotation.value().getDeclaredConstructor().newInstance();
    }

    /** The enum a field carries, through its adapter or a collection if need be; null for anything else. */
    private static Class<?> enumTypeOf(Field field, XmlAdapter<Object, Object> adapter) {
        Type type = field.getGenericType();
        if (adapter != null) {
            Type superType = adapter.getClass().getGenericSuperclass();
            if (superType instanceof ParameterizedType) {
                type = ((ParameterizedType) superType).getActualTypeArguments()[1];
            }
        } else if (type instanceof ParameterizedType && Collection.class.isAssignableFrom(field.getType())) {
            type = ((ParameterizedType) type).getActualTypeArguments()[0];
        }
        return type instanceof Class && ((Class<?>) type).isEnum() ? (Class<?>) type : null;
    }

    private static Map<String, Set<String>> xsdEnumerations() throws Exception {
        Map<String, Set<String>> enumerations = new HashMap<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        for (String xsd : XSDS) {
            try (InputStream in = OpenApiSchemaAnnotationsTest.class.getResourceAsStream(xsd)) {
                assertTrue(xsd + " not found on the classpath", in != null);
                Document doc = factory.newDocumentBuilder().parse(in);
                NodeList simpleTypes = doc.getElementsByTagNameNS("http://www.w3.org/2001/XMLSchema", "simpleType");
                for (int i = 0; i < simpleTypes.getLength(); i++) {
                    Element simpleType = (Element) simpleTypes.item(i);
                    NodeList values = simpleType.getElementsByTagNameNS("http://www.w3.org/2001/XMLSchema", "enumeration");
                    if (simpleType.hasAttribute("name") && values.getLength() > 0) {
                        Set<String> set = new LinkedHashSet<>();
                        for (int j = 0; j < values.getLength(); j++) {
                            set.add(((Element) values.item(j)).getAttribute("value"));
                        }
                        enumerations.put(simpleType.getAttribute("name"), set);
                    }
                }
            }
        }
        return enumerations;
    }

    /** Concrete and abstract JAXB-bound classes of the v3.0 model; not enums, interfaces, factories or serializers. */
    private static List<Class<?>> modelClasses() throws Exception {
        List<Class<?>> classes = new ArrayList<>();
        for (Class<?> c : allClasses()) {
            boolean jaxbBound = c.isAnnotationPresent(XmlRootElement.class) || c.isAnnotationPresent(XmlType.class);
            if (jaxbBound) {
                classes.add(c);
            }
        }
        return classes;
    }

    /** Every model class whose fields can reach a schema, including @XmlTransient superclasses. */
    private static List<Class<?>> classesWithFields() throws Exception {
        return allClasses();
    }

    /** Every class, enum included. */
    private static List<Class<?>> allTypes() throws Exception {
        return scan(true);
    }

    private static List<Class<?>> allClasses() throws Exception {
        return scan(false);
    }

    private static List<Class<?>> scan(boolean includeEnums) throws Exception {
        List<Class<?>> classes = new ArrayList<>();
        // A scan that finds nothing (classes in a jar, a moved package) would let every test pass.
        int minimum = 140;
        ClassLoader loader = OpenApiSchemaAnnotationsTest.class.getClassLoader();
        for (String pkg : MODEL_PACKAGES) {
            Enumeration<URL> roots = loader.getResources(pkg);
            while (roots.hasMoreElements()) {
                URL root = roots.nextElement();
                if (!"file".equals(root.getProtocol())) {
                    continue;
                }
                File dir = new File(root.toURI());
                collect(dir, pkg.replace('/', '.'), loader, classes, includeEnums);
            }
        }
        classes.sort((a, b) -> a.getName().compareTo(b.getName()));
        assertTrue("only " + classes.size() + " model classes found on the file system", classes.size() >= minimum);
        return classes;
    }

    private static void collect(File dir, String pkg, ClassLoader loader, List<Class<?>> classes, boolean includeEnums) throws Exception {
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                collect(file, pkg + "." + file.getName(), loader, classes, includeEnums);
                continue;
            }
            String fileName = file.getName();
            if (!fileName.endsWith(".class") || fileName.equals("package-info.class") || fileName.matches(".*\\$\\d+.*")) {
                continue;
            }
            Class<?> c = Class.forName(pkg + "." + fileName.substring(0, fileName.length() - ".class".length()), false, loader);
            if ((includeEnums || !c.isEnum()) && !c.isInterface() && !c.isAnnotation() && !c.getSimpleName().equals("ObjectFactory")
                    && !Modifier.isPrivate(c.getModifiers())) {
                classes.add(c);
            }
        }
    }
}
