//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.06.13 at 11:48:27 AM MSK 
//


package com.testProject.generatedClasses.com.testProject.genClasses;

import java.io.Serializable;
import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="country"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;minLength value="2"/&gt;
 *               &lt;pattern value="[A-ZА-Я][a-zа-я]+"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="city"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;minLength value="2"/&gt;
 *               &lt;pattern value="[A-ZА-Я][a-zа-я]+"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="postcode"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer"&gt;
 *               &lt;pattern value="\d{6,}"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="street"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;minLength value="2"/&gt;
 *               &lt;pattern value="[A-ZА-Я][a-zа-я]+"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="buildingType" type="{com/namespace/xsd/address.xsd}buildingTypes"/&gt;
 *         &lt;element name="buildingNum" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="flatNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "country",
    "city",
    "postcode",
    "street",
    "buildingType",
    "buildingNum",
    "flatNum"
})
@XmlRootElement(name = "address", namespace = "com/namespace/xsd/address.xsd")
public class Address
    implements Serializable
{

    private final static long serialVersionUID = -1L;
    @XmlElement(namespace = "com/namespace/xsd/address.xsd", required = true)
    protected String country;
    @XmlElement(namespace = "com/namespace/xsd/address.xsd", required = true)
    protected String city;
    @XmlElement(namespace = "com/namespace/xsd/address.xsd", required = true)
    protected BigInteger postcode;
    @XmlElement(namespace = "com/namespace/xsd/address.xsd", required = true)
    protected String street;
    @XmlElement(namespace = "com/namespace/xsd/address.xsd", required = true)
    @XmlSchemaType(name = "string")
    protected BuildingTypes buildingType;
    @XmlElement(namespace = "com/namespace/xsd/address.xsd", required = true)
    protected String buildingNum;
    @XmlElementRef(name = "flatNum", namespace = "com/namespace/xsd/address.xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> flatNum;

    /**
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the postcode property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPostcode() {
        return postcode;
    }

    /**
     * Sets the value of the postcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPostcode(BigInteger value) {
        this.postcode = value;
    }

    /**
     * Gets the value of the street property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the value of the street property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreet(String value) {
        this.street = value;
    }

    /**
     * Gets the value of the buildingType property.
     * 
     * @return
     *     possible object is
     *     {@link BuildingTypes }
     *     
     */
    public BuildingTypes getBuildingType() {
        return buildingType;
    }

    /**
     * Sets the value of the buildingType property.
     * 
     * @param value
     *     allowed object is
     *     {@link BuildingTypes }
     *     
     */
    public void setBuildingType(BuildingTypes value) {
        this.buildingType = value;
    }

    /**
     * Gets the value of the buildingNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuildingNum() {
        return buildingNum;
    }

    /**
     * Sets the value of the buildingNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuildingNum(String value) {
        this.buildingNum = value;
    }

    /**
     * Gets the value of the flatNum property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFlatNum() {
        return flatNum;
    }

    /**
     * Sets the value of the flatNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFlatNum(JAXBElement<String> value) {
        this.flatNum = value;
    }

    public String getFullAddress(){
        return String.format("Country: %s. City: %s. Postcode : %s. " +
                "Street: %s. BuildingType: %s. BuildingNum: %s. FlatNum: %s",
                country,city,postcode,street,buildingType,buildingNum,(flatNum != null ? flatNum.getValue() : ""));
    }

}