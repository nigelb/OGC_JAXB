package net.opengis.gml;

import org.jvnet.jaxb2_commons.lang.CopyTo;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.MergeFrom;
import org.jvnet.jaxb2_commons.lang.ToString;

public interface MetaDataPropertyType extends Cloneable, CopyTo, Equals,
		HashCode, MergeFrom, ToString {

	public Object getAny();

	public void setAny(Object value);

	public boolean isSetAny();

	public String getAbout();

	public void setAbout(String value);

	public boolean isSetAbout();

	public String getRemoteSchema();

	public void setRemoteSchema(String value);

	public boolean isSetRemoteSchema();

	public String getType();

	public void setType(String value);

	public boolean isSetType();

	public String getHref();

	public void setHref(String value);

	public boolean isSetHref();

	public String getRole();

	public void setRole(String value);

	public boolean isSetRole();

	public String getArcrole();

	public void setArcrole(String value);

	public boolean isSetArcrole();

	public String getTitle();

	public void setTitle(String value);

	public boolean isSetTitle();

	public String getShow();

	public void setShow(String value);

	public boolean isSetShow();

	public String getActuate();

	public void setActuate(String value);

	public boolean isSetActuate();

}
