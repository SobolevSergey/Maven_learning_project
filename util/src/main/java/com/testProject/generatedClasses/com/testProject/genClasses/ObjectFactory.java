//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.06.13 at 11:48:27 AM MSK 
//


package com.testProject.generatedClasses.com.testProject.genClasses;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.testProject.genClasses package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AddressFlatNum_QNAME = new QName("com/namespace/xsd/address.xsd", "flatNum");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.testProject.genClasses
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link School }
     * 
     */
    public School createSchool() {
        return new School();
    }

    /**
     * Create an instance of {@link Address }
     * 
     */
    public Address createAddress() {
        return new Address();
    }

    /**
     * Create an instance of {@link School.Students }
     * 
     */
    public School.Students createSchoolStudents() {
        return new School.Students();
    }

    /**
     * Create an instance of {@link Student }
     * 
     */
    public Student createStudent() {
        return new Student();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "com/namespace/xsd/address.xsd", name = "flatNum", scope = Address.class)
    public JAXBElement<String> createAddressFlatNum(String value) {
        return new JAXBElement<String>(_AddressFlatNum_QNAME, String.class, Address.class, value);
    }

}
