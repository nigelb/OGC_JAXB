package net.opengis.gml;

import javax.xml.bind.JAXBElement;

import org.jvnet.jaxb2_commons.lang.CopyTo;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.MergeFrom;
import org.jvnet.jaxb2_commons.lang.ToString;

public interface GeometryPropertyType extends Cloneable, CopyTo, Equals,
		HashCode, MergeFrom, ToString {

	public JAXBElement<? extends AbstractGeometryType> getGeometry();

	public boolean isSetGeometry();

}
