package net.opengis.gml;

import java.math.BigDecimal;

import org.jvnet.jaxb2_commons.lang.CopyTo;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.MergeFrom;
import org.jvnet.jaxb2_commons.lang.ToString;

public interface CoordType extends Cloneable, CopyTo, Equals, HashCode,
		MergeFrom, ToString {

	public BigDecimal getX();

	public void setX(BigDecimal value);

	public boolean isSetX();

	public BigDecimal getY();

	public void setY(BigDecimal value);

	public boolean isSetY();

	public BigDecimal getZ();

	public void setZ(BigDecimal value);

	public boolean isSetZ();
}
