package net.opengis.gml;

import java.math.BigInteger;
import java.util.List;

import org.jvnet.jaxb2_commons.lang.CopyTo;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.MergeFrom;
import org.jvnet.jaxb2_commons.lang.ToString;

public interface DirectPositionListType extends Cloneable, SRSReferenceGroup,
		CopyTo, Equals, HashCode, MergeFrom, ToString {

	public List<Double> getValue();

	public boolean isSetValue();

	public void unsetValue();

	public BigInteger getCount();

	public void setCount(BigInteger value);

	public boolean isSetCount();

	public void setValue(List<Double> value);

}
