package net.opengis.gml;

import java.util.List;

import javax.xml.bind.JAXBElement;

import org.jvnet.jaxb2_commons.lang.CopyTo;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.MergeFrom;
import org.jvnet.jaxb2_commons.lang.ToString;

public interface GeometryArrayPropertyType extends Cloneable, CopyTo, Equals,
		HashCode, MergeFrom, ToString {

	public List getGeometry();

	public boolean isSetGeometry();

	public void unsetGeometry();

}
