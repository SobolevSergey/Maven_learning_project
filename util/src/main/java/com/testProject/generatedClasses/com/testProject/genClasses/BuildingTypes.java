//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.06.13 at 11:48:27 AM MSK 
//


package com.testProject.generatedClasses.com.testProject.genClasses;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for buildingTypes.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="buildingTypes"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="APARTMENT"/&gt;
 *     &lt;enumeration value="SEPARATE_STRUCTURE"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "buildingTypes", namespace = "com/namespace/xsd/address.xsd")
@XmlEnum
public enum BuildingTypes {

    APARTMENT,
    SEPARATE_STRUCTURE;

    public String value() {
        return name();
    }

    public static BuildingTypes fromValue(String v) {
        return valueOf(v);
    }

}