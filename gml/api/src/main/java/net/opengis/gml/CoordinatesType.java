package net.opengis.gml;

import org.jvnet.jaxb2_commons.lang.CopyTo;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.MergeFrom;
import org.jvnet.jaxb2_commons.lang.ToString;

public interface CoordinatesType extends Cloneable, CopyTo, Equals, HashCode,
		MergeFrom, ToString {

	public String getValue();

	public void setValue(String value);

	public boolean isSetValue();

	public String getDecimal();

	public void setDecimal(String value);

	public boolean isSetDecimal();

	public String getCs();

	public void setCs(String value);

	public boolean isSetCs();

	public String getTs();

	public void setTs(String value);

	public boolean isSetTs();

}
