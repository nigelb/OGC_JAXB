package net.opengis.gml;

import java.util.List;

public interface MultiPolygonType extends AbstractGeometricAggregateType {
	public List<? extends PolygonPropertyType> getPolygonMember();

	public boolean isSetPolygonMember();

	public void unsetPolygonMember();

}
