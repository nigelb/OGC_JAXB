package net.opengis.gml;

import org.jvnet.jaxb2_commons.lang.CopyTo;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.MergeFrom;
import org.jvnet.jaxb2_commons.lang.ToString;

public interface LinearRingPropertyType extends Cloneable, CopyTo, Equals,
		HashCode, MergeFrom, ToString {
	public LinearRingType getLinearRing();

	public void setLinearRing(LinearRingType value);

	public boolean isSetLinearRing();

}
