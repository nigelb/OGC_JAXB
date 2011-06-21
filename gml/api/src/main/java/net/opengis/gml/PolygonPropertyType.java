package net.opengis.gml;

import org.jvnet.jaxb2_commons.lang.CopyTo;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.MergeFrom;
import org.jvnet.jaxb2_commons.lang.ToString;

public interface PolygonPropertyType extends Cloneable, CopyTo, Equals,
		HashCode, MergeFrom, ToString {

	public PolygonType getPolygon();

	public void setPolygon(PolygonType value);

	public boolean isSetPolygon();

}
