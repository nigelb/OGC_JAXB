package net.opengis.gml;

import java.util.List;

public interface MultiGeometryType extends AbstractGeometricAggregateType {
	public List<? extends GeometryPropertyType> getGeometryMember();

	public boolean isSetGeometryMember();

	public void unsetGeometryMember();

	public GeometryArrayPropertyType getGeometryMembers();

	public void setGeometryMembers(GeometryArrayPropertyType value);

	public boolean isSetGeometryMembers();

}
