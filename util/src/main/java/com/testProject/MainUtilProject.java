package com.testProject;

import com.testProject.generatedClasses.com.testProject.genClasses.School;
import com.testProject.generatedClasses.com.testProject.genClasses.Student;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;

public class MainUtilProject {
    public static void main(String[] args) {
        System.out.println("Reading XML file and writing data into objects");

        DateFormat dateFormat = DateFormat.getDateInstance();

        try {
            JAXBContext schoolContext = JAXBContext.newInstance(School.class);
            Unmarshaller unmarshaller = schoolContext.createUnmarshaller();
            File xmlFile = new File("./util/src/main/java/com/namespace/xml/schoolExample.xml");
            File xsdFile = new File("./util/src/main/java/com/namespace/xsd/school.xsd");
            boolean valid = validateAgainstXSD(xmlFile, xsdFile);
            if (!valid) {
                System.out.println("The XML file is not valid");
                return;
            }

            School school = (School) unmarshaller.unmarshal(xmlFile);

            if (school != null) {
                System.out.printf("School address: %s.\t Count of children: %d.\t\n",
                        (school.getAddress() != null ? school.getAddress().getFullAddress() : ""),
                        school.getStudentsCount());
                if (school.getStudentsCount() > 0) {
                    System.out.println("The students are:");
                    for (Student st : school.getStudents().getStudents()) {
                        System.out.printf("Student name : %s.\t BirthDate: %s.\t Address: %s\t\n",
                                st.getFullName(),
                                (st.getBirthDate() != null ? dateFormat.format(st.getBirthDate().getTime()) : ""),
                                (st.getAddress() != null ? st.getAddress().getFullAddress() : ""));

                        st.setFirstName("Changed");
                    }
                }
            }

            Marshaller marshaller = schoolContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            File tmpXmlfile = new File("./util/src/main/java/com/namespace/temp/changedXml.xml");
            marshaller.marshal(school, tmpXmlfile);


            TransformerFactory tFactory = TransformerFactory.newInstance();
            String stylesheet = "./util/src/main/java/com/namespace/xml/school.xsl";
            File pricesHTML = new File("./util/src/main/java/com/namespace/temp/resultHTML.html");
            FileOutputStream os = new FileOutputStream(pricesHTML);
            Transformer transformer = tFactory.newTransformer(new StreamSource(stylesheet));
            transformer.transform(new StreamSource(tmpXmlfile), new StreamResult(os));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean validateAgainstXSD(File xml, File xsd) {
        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(xsd));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xml));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
