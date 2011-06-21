package net.opengis.gml;

import javax.xml.bind.JAXBElement;

public interface ObjectFactory {

	public CoordType createCoordType();

	public CoordinatesType createCoordinatesType();

	public DirectPositionType createDirectPositionType();

	public DirectPositionListType createDirectPositionListType();

	public PointType createPointType();

	public PointPropertyType createPointPropertyType();

	public LineStringType createLineStringType();

	public LineStringPropertyType createLineStringPropertyType();

	public LinearRingType createLinearRingType();

	public LinearRingPropertyType createLinearRingPropertyType();

	public PolygonType createPolygonType();

	public PolygonPropertyType createPolygonPropertyType();

	public MultiPointType createMultiPointType();

	public MultiPointPropertyType createMultiPointPropertyType();

	public MultiLineStringType createMultiLineStringType();

	public MultiLineStringPropertyType createMultiLineStringPropertyType();

	public AbstractRingPropertyType createAbstractRingPropertyType();

	public MultiPolygonType createMultiPolygonType();

	public MultiPolygonPropertyType createMultiPolygonPropertyType();

	public MultiGeometryType createMultiGeometryType();

	public MultiGeometryPropertyType createMultiGeometryPropertyType();

	public GeometryPropertyType createGeometryPropertyType();
}
