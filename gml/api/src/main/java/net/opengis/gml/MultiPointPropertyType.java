package net.opengis.gml;

import org.jvnet.jaxb2_commons.lang.CopyTo;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.MergeFrom;
import org.jvnet.jaxb2_commons.lang.ToString;

public interface MultiPointPropertyType extends Cloneable, CopyTo, Equals,
		HashCode, MergeFrom, ToString {
	
	public MultiPointType getMultiPoint();

	public void setMultiPoint(MultiPointType value);

	public boolean isSetMultiPoint();

}
