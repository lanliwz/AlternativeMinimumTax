//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.04.08 at 09:49:16 PM EDT 
//


package com.upuptax.model.jaxb;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.upuptax.model.jaxb package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.upuptax.model.jaxb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TaxForm }
     * 
     */
    public TaxForm createTaxForm() {
        return new TaxForm();
    }

    /**
     * Create an instance of {@link NameValue }
     * 
     */
    public NameValue createNameValue() {
        return new NameValue();
    }

    /**
     * Create an instance of {@link Line }
     * 
     */
    public Line createLine() {
        return new Line();
    }

    /**
     * Create an instance of {@link LineItem }
     * 
     */
    public LineItem createLineItem() {
        return new LineItem();
    }

    /**
     * Create an instance of {@link Part }
     * 
     */
    public Part createPart() {
        return new Part();
    }

    /**
     * Create an instance of {@link Header }
     * 
     */
    public Header createHeader() {
        return new Header();
    }

}
