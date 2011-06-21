package net.opengis.gml;

import java.util.List;

public interface MultiLineStringType extends AbstractGeometricAggregateType {
	public List<? extends LineStringPropertyType> getLineStringMember();

	public boolean isSetLineStringMember();

	public void unsetLineStringMember();

}
