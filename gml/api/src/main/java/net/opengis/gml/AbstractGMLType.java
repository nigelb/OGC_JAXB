package net.opengis.gml;

import java.util.List;

import javax.xml.bind.JAXBElement;

import org.jvnet.jaxb2_commons.lang.CopyTo;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.MergeFrom;
import org.jvnet.jaxb2_commons.lang.ToString;

public interface AbstractGMLType extends Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{
    public List<? extends MetaDataPropertyType> getMetaDataProperty();

    public boolean isSetMetaDataProperty();

    public void unsetMetaDataProperty();

//    public StringOrRefType getDescription();

//    public void setDescription(StringOrRefType value);

//    public boolean isSetDescription();

//    public List<JAXBElement<? extends CodeType>> getName();
//
//    public boolean isSetName();
//
//    public void unsetName();

    public String getId();

    public void setId(String value);

    public boolean isSetId();

}
