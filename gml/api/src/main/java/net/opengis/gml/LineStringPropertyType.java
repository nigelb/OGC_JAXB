package net.opengis.gml;

import org.jvnet.jaxb2_commons.lang.CopyTo;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.MergeFrom;
import org.jvnet.jaxb2_commons.lang.ToString;

public interface LineStringPropertyType extends Cloneable, CopyTo, Equals,
		HashCode, MergeFrom, ToString {

	public LineStringType getLineString();

	public void setLineString(LineStringType value);

	public boolean isSetLineString();

}
