package net.opengis.gml;

public interface PointType extends AbstractGeometricPrimitiveType {
	public DirectPositionType getPos();

	public void setPos(DirectPositionType value);

	public boolean isSetPos();

	public CoordinatesType getCoordinates();

	public void setCoordinates(CoordinatesType value);

	public boolean isSetCoordinates();

	public CoordType getCoord();

	public void setCoord(CoordType value);

	public boolean isSetCoord();

}
