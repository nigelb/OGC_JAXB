package net.opengis.gml;

import java.util.List;

public interface MultiPointType extends AbstractGeometricAggregateType {
	public List<? extends PointPropertyType> getPointMember();

	public boolean isSetPointMember();

	public void unsetPointMember();

	public PointArrayPropertyType getPointMembers();

	public void setPointMembers(PointArrayPropertyType value);

	public boolean isSetPointMembers();

}
