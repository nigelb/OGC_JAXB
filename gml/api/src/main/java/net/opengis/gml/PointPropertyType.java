package net.opengis.gml;

import org.jvnet.jaxb2_commons.lang.CopyTo;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.MergeFrom;
import org.jvnet.jaxb2_commons.lang.ToString;


public interface PointPropertyType extends Cloneable, CopyTo, Equals, HashCode,
		MergeFrom, ToString {

	public PointType getPoint();

	public void setPoint(PointType value);

	public boolean isSetPoint();

}
