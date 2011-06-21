package net.opengis.gml;

import javax.xml.bind.JAXBElement;

import org.jvnet.jaxb2_commons.lang.CopyTo;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.MergeFrom;
import org.jvnet.jaxb2_commons.lang.ToString;

public interface MultiGeometryPropertyType extends Cloneable, CopyTo, Equals,
		HashCode, MergeFrom, ToString {
	
	public JAXBElement<? extends AbstractGeometricAggregateType> getGeometricAggregate();

	public boolean isSetGeometricAggregate();

}
