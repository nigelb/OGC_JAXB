package net.opengis.gml;

import org.jvnet.jaxb2_commons.lang.CopyTo;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.MergeFrom;
import org.jvnet.jaxb2_commons.lang.ToString;


public interface CodeType
    extends Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{
    public String getValue();

    public void setValue(String value);

    public boolean isSetValue();

    public String getCodeSpace();

    public void setCodeSpace(String value);

    public boolean isSetCodeSpace();

}
