package org.jvnet.ogc.gml.v_3_1_1.jts;

import javax.xml.bind.JAXBElement;

import net.opengis.gml.v_3_1_1.AbstractGeometryType;
import net.opengis.gml.v_3_1_1.AbstractRingPropertyType;
import net.opengis.gml.v_3_1_1.DirectPositionType;
import net.opengis.gml.v_3_1_1.GeometryPropertyType;
import net.opengis.gml.v_3_1_1.LineStringPropertyType;
import net.opengis.gml.v_3_1_1.LineStringType;
import net.opengis.gml.v_3_1_1.LinearRingType;
import net.opengis.gml.v_3_1_1.MultiGeometryType;
import net.opengis.gml.v_3_1_1.MultiLineStringType;
import net.opengis.gml.v_3_1_1.MultiPointType;
import net.opengis.gml.v_3_1_1.MultiPolygonType;
import net.opengis.gml.v_3_1_1.ObjectFactory;
import net.opengis.gml.v_3_1_1.PointPropertyType;
import net.opengis.gml.v_3_1_1.PointType;
import net.opengis.gml.v_3_1_1.PolygonPropertyType;
import net.opengis.gml.v_3_1_1.PolygonType;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

public class JTSToGML311Converter {

	// + Geometry
	// + Point
	// + Polygon
	// + LineString
	// + LinearRing
	// + GeometryCollection
	// + MultiPoint
	// + MultiLineString
	// + MultiPolygon

	private final ObjectFactory objectFactory;

	public JTSToGML311Converter(ObjectFactory objectFactory) {
		super();
		this.objectFactory = objectFactory;
	}
	
	public JTSToGML311Converter() {
		this(new ObjectFactory());
	}

	public DirectPositionType convertCoordinate(Coordinate coordinate) {
		final DirectPositionType directPosition = objectFactory
				.createDirectPositionType();

		directPosition.getValue().add(coordinate.x);
		directPosition.getValue().add(coordinate.y);
		if (!Double.isNaN(coordinate.z)) {
			directPosition.getValue().add(coordinate.z);
		}
		return directPosition;

	}

	public DirectPositionType[] convertCoordinates(Coordinate[] coordinates) {
		if (coordinates == null) {
			return null;
		} else {
			final DirectPositionType[] directPositions = new DirectPositionType[coordinates.length];
			for (int index = 0; index < coordinates.length; index++) {

				directPositions[index] = convertCoordinate(coordinates[index]);
			}
			return directPositions;
		}
	}

	public PointType createPointType(Point point) {

		final PointType resultPoint = objectFactory.createPointType();

		if (!point.isEmpty()) {
			final DirectPositionType directPosition = convertCoordinate(point
					.getCoordinate());
			resultPoint.setPos(directPosition);
		}
		return resultPoint;

	}

	public JAXBElement<PointType> createPoint(Point point) {
		return objectFactory.createPoint(createPointType(point));
	}

	public PointPropertyType createPointPropertyType(Point point) {
		final PointPropertyType pointPropertyType = objectFactory
				.createPointPropertyType();

		pointPropertyType.setPoint(createPointType(point));
		return pointPropertyType;

	}

	public LineStringType createLineStringType(LineString lineString) {

		final LineStringType resultLineString = objectFactory
				.createLineStringType();

		for (DirectPositionType directPosition : convertCoordinates(lineString
				.getCoordinates())) {
			final JAXBElement<DirectPositionType> pos = objectFactory
					.createPos(directPosition);
			resultLineString.getPosOrPointPropertyOrPointRep().add(pos);

		}
		return resultLineString;
	}

	public LineStringPropertyType createLineStringPropertyType(
			LineString lineString) {
		final LineStringPropertyType lineStringPropertyType = objectFactory
				.createLineStringPropertyType();
		lineStringPropertyType.setLineString(createLineStringType(lineString));
		return lineStringPropertyType;
	}

	public JAXBElement<LineStringType> createLineString(LineString linearString) {
		return objectFactory
				.createLineString(createLineStringType(linearString));
	}

	public LinearRingType createLinearRingType(LinearRing linearRing) {
		final LinearRingType resultLinearRing = objectFactory
				.createLinearRingType();

		for (DirectPositionType directPosition : convertCoordinates(linearRing
				.getCoordinates())) {
			final JAXBElement<DirectPositionType> pos = objectFactory
					.createPos(directPosition);
			resultLinearRing.getPosOrPointPropertyOrPointRep().add(pos);

		}
		return resultLinearRing;
	}

	public JAXBElement<LinearRingType> createLinearRing(LinearRing linearRing) {
		return objectFactory.createLinearRing(createLinearRingType(linearRing));
	}

	public PolygonType createPolygonType(Polygon polygon) {
		final PolygonType resultPolygon = objectFactory.createPolygonType();
		{
			final LinearRing exteriorRing = (LinearRing) polygon
					.getExteriorRing();
			final AbstractRingPropertyType abstractRingProperty = createAbstractRingPropertyType(exteriorRing);
			resultPolygon.setExterior(objectFactory
					.createExterior(abstractRingProperty));
		}
		for (int index = 0; index < polygon.getNumInteriorRing(); index++) {
			final LinearRing interiorRing = (LinearRing) polygon
					.getInteriorRingN(index);
			resultPolygon
					.getInterior()
					.add(
							objectFactory
									.createInterior(createAbstractRingPropertyType(interiorRing)));
		}
		return resultPolygon;

	}

	public PolygonPropertyType createPolygonPropertyType(Polygon polygon) {
		final PolygonPropertyType polygonPropertyType = objectFactory
				.createPolygonPropertyType();
		polygonPropertyType.setPolygon(createPolygonType(polygon));
		return polygonPropertyType;
	}

	public JAXBElement<PolygonType> createPolygon(Polygon polygon) {
		return objectFactory.createPolygon(createPolygonType(polygon));
	}

	public AbstractRingPropertyType createAbstractRingPropertyType(
			final LinearRing ring) {
		final AbstractRingPropertyType abstractRingProperty = objectFactory
				.createAbstractRingPropertyType();

		abstractRingProperty.setRing(objectFactory
				.createLinearRing(createLinearRingType(ring)));
		return abstractRingProperty;
	}

	public MultiPointType createMultiPointType(MultiPoint multiPoint) {
		final MultiPointType multiPointType = objectFactory
				.createMultiPointType();

		for (int index = 0; index < multiPoint.getNumGeometries(); index++) {
			final Point point = (Point) multiPoint.getGeometryN(index);
			multiPointType.getPointMember().add(createPointPropertyType(point));
		}
		return multiPointType;
	}

	public JAXBElement<MultiPointType> createMultiPoint(MultiPoint multiPoint) {
		return objectFactory.createMultiPoint(createMultiPointType(multiPoint));
	}

	public MultiLineStringType createMultiLineStringType(
			MultiLineString multiLineString) {
		final MultiLineStringType multiLineStringType = objectFactory
				.createMultiLineStringType();
		for (int index = 0; index < multiLineString.getNumGeometries(); index++) {
			final LineString lineString = (LineString) multiLineString
					.getGeometryN(index);
			multiLineStringType.getLineStringMember().add(
					createLineStringPropertyType(lineString));
		}

		return multiLineStringType;
	}

	public JAXBElement<MultiLineStringType> createMultiLineString(
			MultiLineString multiLineString) {
		return objectFactory
				.createMultiLineString(createMultiLineStringType(multiLineString));
	}

	public MultiPolygonType createMultiPolygonType(MultiPolygon multiPolygon) {
		final MultiPolygonType multiPolygonType = objectFactory
				.createMultiPolygonType();
		for (int index = 0; index < multiPolygon.getNumGeometries(); index++) {
			final Polygon polygon = (Polygon) multiPolygon.getGeometryN(index);
			multiPolygonType.getPolygonMember().add(
					createPolygonPropertyType(polygon));
		}

		return multiPolygonType;
	}

	public JAXBElement<MultiPolygonType> createMultiPolygon(
			MultiPolygon multiPolygon) {
		return objectFactory
				.createMultiPolygon(createMultiPolygonType(multiPolygon));
	}

	public MultiGeometryType createMultiGeometryType(
			GeometryCollection multiGeometry) {
		final MultiGeometryType multiGeometryType = objectFactory
				.createMultiGeometryType();

		for (int index = 0; index < multiGeometry.getNumGeometries(); index++) {
			final Geometry geometry = multiGeometry.getGeometryN(index);

			multiGeometryType.getGeometryMember().add(
					createGeometryPropertyType(geometry));

		}
		return multiGeometryType;
	}

	public JAXBElement<MultiGeometryType> createMultiGeometry(
			GeometryCollection geometryCollection) {
		return objectFactory
				.createMultiGeometry(createMultiGeometryType(geometryCollection));
	}

	public GeometryPropertyType createGeometryPropertyType(Geometry geometry) {
		final GeometryPropertyType geometryPropertyType = objectFactory
				.createGeometryPropertyType();
		geometryPropertyType.setGeometry(createGeometry(geometry));
		return geometryPropertyType;
	}

	public JAXBElement<? extends AbstractGeometryType> createGeometry(
			Geometry geometry) {
		if (geometry instanceof Point) {
			return createPoint((Point) geometry);
		} else if (geometry instanceof LineString) {
			return createLineString((LineString) geometry);
		} else if (geometry instanceof LinearRing) {
			return createLinearRing((LinearRing) geometry);
		} else if (geometry instanceof Polygon) {
			return createPolygon((Polygon) geometry);
		} else if (geometry instanceof MultiPoint) {
			return createMultiPoint((MultiPoint) geometry);
		} else if (geometry instanceof MultiLineString) {
			return createMultiLineString((MultiLineString) geometry);
		} else if (geometry instanceof MultiPolygon) {
			return createMultiPolygon((MultiPolygon) geometry);
		} else if (geometry instanceof GeometryCollection) {
			return createMultiGeometry((GeometryCollection) geometry);
		} else {
			// TODo
			throw new IllegalArgumentException();
		}

	}

	public AbstractGeometryType createAbstractGeometryType(Geometry geometry) {
		if (geometry instanceof Point) {
			return createPointType((Point) geometry);
		} else if (geometry instanceof LineString) {
			return createLineStringType((LineString) geometry);
		} else if (geometry instanceof LinearRing) {
			return createLinearRingType((LinearRing) geometry);
		} else if (geometry instanceof Polygon) {
			return createPolygonType((Polygon) geometry);
		} else if (geometry instanceof MultiPoint) {
			return createMultiPointType((MultiPoint) geometry);
		} else if (geometry instanceof MultiLineString) {
			return createMultiLineStringType((MultiLineString) geometry);
		} else if (geometry instanceof MultiPolygon) {
			return createMultiPolygonType((MultiPolygon) geometry);
		} else if (geometry instanceof GeometryCollection) {
			return createMultiGeometryType((GeometryCollection) geometry);
		} else {
			// TODO
			throw new IllegalArgumentException();
		}

	}
}
