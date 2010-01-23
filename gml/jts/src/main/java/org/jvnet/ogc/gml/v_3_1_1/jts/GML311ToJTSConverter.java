package org.jvnet.ogc.gml.v_3_1_1.jts;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import net.opengis.gml.v_3_1_1.AbstractGeometricAggregateType;
import net.opengis.gml.v_3_1_1.AbstractRingPropertyType;
import net.opengis.gml.v_3_1_1.AbstractRingType;
import net.opengis.gml.v_3_1_1.CoordType;
import net.opengis.gml.v_3_1_1.CoordinatesType;
import net.opengis.gml.v_3_1_1.DirectPositionListType;
import net.opengis.gml.v_3_1_1.DirectPositionType;
import net.opengis.gml.v_3_1_1.LineStringPropertyType;
import net.opengis.gml.v_3_1_1.LineStringType;
import net.opengis.gml.v_3_1_1.LinearRingPropertyType;
import net.opengis.gml.v_3_1_1.LinearRingType;
import net.opengis.gml.v_3_1_1.MultiGeometryPropertyType;
import net.opengis.gml.v_3_1_1.MultiLineStringPropertyType;
import net.opengis.gml.v_3_1_1.MultiLineStringType;
import net.opengis.gml.v_3_1_1.MultiPointPropertyType;
import net.opengis.gml.v_3_1_1.MultiPointType;
import net.opengis.gml.v_3_1_1.MultiPolygonPropertyType;
import net.opengis.gml.v_3_1_1.MultiPolygonType;
import net.opengis.gml.v_3_1_1.PointPropertyType;
import net.opengis.gml.v_3_1_1.PointType;
import net.opengis.gml.v_3_1_1.PolygonPropertyType;
import net.opengis.gml.v_3_1_1.PolygonType;

import org.apache.commons.lang.StringUtils;
import org.jvnet.jaxb2_commons.locator.DefaultRootObjectLocator;
import org.jvnet.jaxb2_commons.locator.FieldObjectLocator;
import org.jvnet.jaxb2_commons.locator.ListEntryObjectLocator;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

public class GML311ToJTSConverter {

	// Geometry
	// + Point
	// + Polygon
	// + LineString
	// + LinearRing
	// GeometryCollection
	// + MultiPoint
	// + MultiLineString
	// + MultiPolygon

	private GeometryFactory geometryFactory;

	public GML311ToJTSConverter(GeometryFactory geometryFactory) {
		super();
		this.geometryFactory = geometryFactory;
	}

	public GML311ToJTSConverter() {
		this(new GeometryFactory());
	}

	public Coordinate createCoordinate(ObjectLocator locator,
			DirectPositionType directPositionType)
			throws ConversionFailedException {
		final List<Double> value = directPositionType.getValue();
		final int count = value.size();
		if (count == 2) {
			double x = value.get(0).doubleValue();
			double y = value.get(1).doubleValue();
			return new Coordinate(x, y);
		} else if (count == 3) {
			double x = value.get(0).doubleValue();
			double y = value.get(1).doubleValue();
			double z = value.get(2).doubleValue();
			return new Coordinate(x, y, z);

		} else {
			throw new ConversionFailedException(locator.field("Value"),
					"Direct position type is expected to have 2 or 3 items.");
		}

	}

	public Coordinate[] createCoordinates(ObjectLocator locator,
			DirectPositionListType directPositionListType)
			throws ConversionFailedException {
		final int dimensions = directPositionListType.isSetSrsDimension() ? directPositionListType
				.getSrsDimension().intValue()
				: 2;

		if (dimensions < 2 || dimensions > 3) {
			throw new ConversionFailedException(locator.field("SrsDimension"),
					"Only two- or three-dimensional coordinates are supported.");
		}

		final List<Double> values = directPositionListType.getValue();
		if (values.size() % dimensions != 0) {
			throw new ConversionFailedException(locator.field("Value"),
					"Wrong number of entries in the list.");
		}

		final Coordinate[] coordinates = new Coordinate[values.size()
				/ dimensions];
		for (int index = 0; index < values.size() / dimensions; index++) {
			if (dimensions == 2) {
				coordinates[index] = new Coordinate(values.get(index
						* dimensions), values.get(index * dimensions + 1));
			} else if (dimensions == 3) {
				coordinates[index] = new Coordinate(values.get(index
						* dimensions), values.get(index * dimensions + 1),
						values.get(index * dimensions + 2));
			}
		}
		return coordinates;

	}

	public Coordinate createCoordinate(ObjectLocator locator,
			CoordType coordType) throws ConversionFailedException {
		if (coordType.isSetX() && coordType.isSetY() && !coordType.isSetZ()) {
			return new Coordinate(coordType.getX().doubleValue(), coordType
					.getY().doubleValue());
		} else if (coordType.isSetX() && coordType.isSetY()
				&& !coordType.isSetZ()) {
			return new Coordinate(coordType.getX().doubleValue(), coordType
					.getY().doubleValue(), coordType.getZ().doubleValue());

		} else {
			throw new ConversionFailedException(locator,
					"Either X, Y or X, Y, Z values are required.");
		}

	}

	public Coordinate[] createCoordinates(ObjectLocator locator, String value,
			String ds, String cs, String ts) throws ConversionFailedException {
		final String coordinateSeparator = cs == null ? " " : cs;

		final String[] coordinates = StringUtils.split(value,
				coordinateSeparator);
		final Coordinate[] coordinatesArray = new Coordinate[coordinates.length];
		for (int index = 0; index < coordinates.length; index++) {
			coordinatesArray[index] = createCoordinate(locator.entry(index),
					coordinates[index], ds, ts);
		}
		return coordinatesArray;
	}

	public Coordinate createCoordinate(ObjectLocator locator, String value,
			String ds, String ts) throws ConversionFailedException {
		final String tupleSeparator = ts == null ? "," : ts;

		final String[] tuples = StringUtils.split(value, tupleSeparator);

		final double[] coordinateComponents = new double[tuples.length];
		for (int index = 0; index < tuples.length; index++) {
			coordinateComponents[index] = createCoordinateComponent(locator
					.entry(index), tuples[index], ds);
		}
		if (coordinateComponents.length == 2) {
			return new Coordinate(coordinateComponents[0],
					coordinateComponents[1]);
		} else if (coordinateComponents.length == 3) {
			return new Coordinate(coordinateComponents[0],
					coordinateComponents[1], coordinateComponents[2]);

		} else {
			throw new ConversionFailedException(locator,
					"Expected two or three coordinates.");
		}
	}

	public double createCoordinateComponent(ObjectLocator locator,
			String value, String ds) throws ConversionFailedException {
		final String decimalSeparator = ds == null ? "." : ds;
		try {
			return Double.parseDouble(value.replace(decimalSeparator, "."));
		} catch (NumberFormatException nfex) {
			throw new ConversionFailedException(locator, nfex);
		}
	}

	public Point createPoint(ObjectLocator locator, PointType pointType)
			throws ConversionFailedException {

		if (pointType.isSetPos()) {
			return geometryFactory.createPoint(createCoordinate(locator
					.field("Pos"), pointType.getPos()));
		} else if (pointType.isSetCoordinates()) {
			final Coordinate[] coords = createCoordinates(locator
					.field("Coordinates"), pointType.getCoordinates());
			if (coords.length == 1) {
				throw new ConversionFailedException(locator
						.field("Coordinates"),
						"Expected exactly one coordinate.");
			} else {
				return geometryFactory.createPoint(coords[0]);

			}

		} else if (pointType.isSetCoord()) {
			return geometryFactory.createPoint(createCoordinate(locator
					.field("Coord"), pointType.getCoord()));
		} else {
			throw new ConversionFailedException(locator,
					"Either [pos], [coordinates] or [coord] elements are expected.");
		}

	}

	public Coordinate[] createCoordinates(ObjectLocator locator,
			final CoordinatesType coordinates) throws ConversionFailedException {
		final Coordinate[] coords = createCoordinates(locator, coordinates
				.getValue(), coordinates.getDecimal(), coordinates.getCs(),
				coordinates.getTs());
		return coords;
	}

	public Point createPoint(ObjectLocator locator,
			PointPropertyType pointPropertyType)
			throws ConversionFailedException {
		if (pointPropertyType.isSetPoint()) {
			return createPoint(locator.field("Point"), pointPropertyType
					.getPoint());
		} else {
			throw new ConversionFailedException(locator,
					"Expected [Point] element.");
		}
	}

	public LineString createLineString(ObjectLocator locator,
			LineStringType lineStringType) throws ConversionFailedException {
		if (lineStringType.isSetPosOrPointPropertyOrPointRep()) {

			final List<Coordinate> coordinates = new LinkedList<Coordinate>();
			final FieldObjectLocator fieldLocator = locator
					.field("PosOrPointPropertyOrPointRep");
			for (int index = 0; index < lineStringType
					.getPosOrPointPropertyOrPointRep().size(); index++) {
				final ListEntryObjectLocator itemLocator = fieldLocator
						.entry(index);
				final JAXBElement<?> item = lineStringType
						.getPosOrPointPropertyOrPointRep().get(index);
				final Object value = item.getValue();
				final ObjectLocator itemValueLocator = itemLocator
						.field("Value");

				if (value instanceof PointType) {
					coordinates.add(createPoint(

					itemValueLocator, (PointType) value).getCoordinate());
				} else if (value instanceof PointPropertyType) {
					coordinates.add(createPoint(itemValueLocator,
							(PointPropertyType) value).getCoordinate());
				} else if (value instanceof CoordType) {
					coordinates.add(createCoordinate(itemValueLocator,
							(CoordType) value));
				} else {
					throw new ConversionFailedException(itemLocator,
							"Unexpected type.");
				}
			}
			final Coordinate[] coordinatesArray = coordinates
					.toArray(new Coordinate[coordinates.size()]);
			return geometryFactory.createLineString(coordinatesArray);

		} else if (lineStringType.isSetPosList()) {

			final Coordinate[] coordinates = createCoordinates(locator
					.field("PosList"), lineStringType.getPosList());
			return geometryFactory.createLineString(coordinates);

		} else if (lineStringType.isSetCoordinates()) {
			final Coordinate[] coordinates = createCoordinates(locator
					.field("Coordinates"), lineStringType.getCoordinates());
			return geometryFactory.createLineString(coordinates);

		} else {
			throw new ConversionFailedException(locator);
		}
	}

	public LineString createLineString(ObjectLocator locator,
			LineStringPropertyType lineStringPropertyType)
			throws ConversionFailedException {
		if (lineStringPropertyType.isSetLineString()) {
			return createLineString(locator.field("LineString"),
					lineStringPropertyType.getLineString());
		} else {
			throw new ConversionFailedException(locator,
					"Expected [LineString] element.");
		}
	}

	public LinearRing createLinearRing(ObjectLocator locator,
			LinearRingType linearRingType) throws ConversionFailedException {
		if (linearRingType.isSetPosOrPointPropertyOrPointRep()) {
			final ObjectLocator fieldLocator = locator
					.field("PosOrPointPropertyOrPointRep");

			final List<Coordinate> coordinates = new LinkedList<Coordinate>();
			for (int index = 0; index < linearRingType
					.getPosOrPointPropertyOrPointRep().size(); index++) {
				final JAXBElement<?> item = linearRingType
						.getPosOrPointPropertyOrPointRep().get(index);
				final ObjectLocator itemLocator = fieldLocator.entry(index);
				final Object value = item.getValue();
				final ObjectLocator itemValueLocator = itemLocator
						.field("Value");

				if (value instanceof PointType) {
					coordinates.add(createPoint(itemValueLocator,
							(PointType) value).getCoordinate());
				} else if (value instanceof PointPropertyType) {
					coordinates.add(createPoint(itemValueLocator,
							(PointPropertyType) value).getCoordinate());
				} else if (value instanceof CoordType) {
					coordinates.add(createCoordinate(itemValueLocator,
							(CoordType) value));
				} else {
					throw new ConversionFailedException(itemValueLocator,
							"Unexpected type.");
				}
			}
			final Coordinate[] coordinatesArray = coordinates
					.toArray(new Coordinate[coordinates.size()]);
			return geometryFactory.createLinearRing(coordinatesArray);

		} else if (linearRingType.isSetPosList()) {

			final Coordinate[] coordinates = createCoordinates(locator
					.field("PosList"), linearRingType.getPosList());
			return geometryFactory.createLinearRing(coordinates);

		} else if (linearRingType.isSetCoordinates()) {
			final Coordinate[] coordinates = createCoordinates(locator
					.field("Coordinates"), linearRingType.getCoordinates());
			return geometryFactory.createLinearRing(coordinates);

		} else {
			throw new ConversionFailedException(locator);
		}
	}

	public LinearRing createLinearRing(ObjectLocator locator,
			LinearRingPropertyType linearRingPropertyType)
			throws ConversionFailedException {
		if (linearRingPropertyType.isSetLinearRing()) {
			return createLinearRing(locator.field("LinearRing"),
					linearRingPropertyType.getLinearRing());
		} else {
			throw new ConversionFailedException(locator,
					"Expected [LinearRing] element.");
		}
	}

	public Polygon createPolygon(ObjectLocator locator, PolygonType polygonType)
			throws ConversionFailedException {
		final LinearRing shell;
		if (polygonType.isSetExterior()) {
			final AbstractRingType abstractRingType = ((JAXBElement<? extends AbstractRingType>) polygonType
					.getExterior().getValue().getRing()).getValue();
			if (abstractRingType instanceof LinearRingType) {
				shell = createLinearRing(locator.field("Exterior").field(
						"Value").field("Ring").field("Value"),
						(LinearRingType) abstractRingType);
			} else {
				throw new ConversionFailedException(locator.field("Exterior")
						.field("Value").field("Ring"),
						"Only linear rings are supported.");
			}
		} else {
			shell = null;
		}

		final LinearRing[] holes;
		if (polygonType.isSetInterior()) {
			final ObjectLocator interiorObjectLocator = locator
					.field("Interior");
			final List<LinearRing> holesList = new ArrayList<LinearRing>(
					polygonType.getInterior().size());
			for (int index = 0; index < polygonType.getInterior().size(); index++) {
				final ObjectLocator entryLocator = interiorObjectLocator
						.entry(index);
				final JAXBElement<AbstractRingPropertyType> ringElement = polygonType
						.getInterior().get(index);

				final AbstractRingType abstractRingType = ringElement
						.getValue().getRing().getValue();
				if (abstractRingType instanceof LinearRingType) {
					holesList.add(createLinearRing(entryLocator.field("Value")
							.field("Ring").field("Value"),

					(LinearRingType) abstractRingType));
				} else {
					throw new ConversionFailedException(entryLocator,
							"Only linear rings are supported.");
				}
			}

			holes = holesList.toArray(new LinearRing[holesList.size()]);
		} else {
			holes = null;
		}
		return geometryFactory.createPolygon(shell, holes);
	}

	public Polygon createPolygon(ObjectLocator locator,
			PolygonPropertyType polygonPropertyType)
			throws ConversionFailedException {
		if (polygonPropertyType.isSetPolygon()) {
			return createPolygon(locator.field("Polygon"), polygonPropertyType
					.getPolygon());
		} else {
			throw new ConversionFailedException(locator,
					"Expected [Polygon] element.");
		}
	}

	public MultiPoint createMultiPoint(ObjectLocator locator,
			MultiPointType multiPointType) throws ConversionFailedException {
		if (multiPointType.isSetPointMember()) {
			final List<PointPropertyType> pointMembers = multiPointType
					.getPointMember();
			final List<Point> points = new ArrayList<Point>(pointMembers.size());
			for (int index = 0; index < pointMembers.size(); index++) {
				final PointPropertyType pointPropertyType = pointMembers
						.get(index);
				points.add(createPoint(locator.field("PointMember")
						.entry(index), pointPropertyType));
			}
			return geometryFactory.createMultiPoint(points
					.toArray(new Point[points.size()]));
		} else if (multiPointType.isSetPointMembers()) {
			final List<PointType> pointMembers = multiPointType
					.getPointMembers().getPoint();
			final List<Point> points = new ArrayList<Point>(pointMembers.size());
			for (int index = 0; index < pointMembers.size(); index++) {

				points.add(createPoint(locator.field("PointMembers").entry(
						index), pointMembers.get(index)));
			}
			return geometryFactory.createMultiPoint(points
					.toArray(new Point[points.size()]));
		} else {
			throw new ConversionFailedException(locator,
					"Either [PointMember] or [PointMembers] elements are expected.");
		}
	}

	public MultiPoint createMultiPoint(ObjectLocator locator,
			MultiPointPropertyType multiPointPropertyType)
			throws ConversionFailedException {
		if (multiPointPropertyType.isSetMultiPoint()) {
			return createMultiPoint(locator.field("MultiPoint"),
					multiPointPropertyType.getMultiPoint());
		} else {
			throw new ConversionFailedException(locator,
					"Expected [MultiPoint] element.");
		}
	}

	public MultiLineString createMultiLineString(ObjectLocator locator,
			MultiLineStringType multiLineStringType)
			throws ConversionFailedException {
		final List<LineStringPropertyType> lineStringMembers = multiLineStringType
				.getLineStringMember();
		final List<LineString> lineStrings = new ArrayList<LineString>(
				lineStringMembers.size());
		for (int index = 0; index < lineStringMembers.size(); index++) {
			final LineStringPropertyType lineStringPropertyType = lineStringMembers
					.get(index);

			final LineStringType lineStringType = lineStringPropertyType
					.getLineString();
			lineStrings.add(createLineString(locator.field("LineStringMember")
					.entry(index).field("LineString"), lineStringType));
		}
		return geometryFactory.createMultiLineString(lineStrings
				.toArray(new LineString[lineStrings.size()]));
	}

	public MultiLineString createMultiLineString(ObjectLocator locator,
			MultiLineStringPropertyType multiLineStringPropertyType)
			throws ConversionFailedException {
		if (multiLineStringPropertyType.isSetMultiLineString()) {
			return createMultiLineString(locator.field("MultiLineString"),
					multiLineStringPropertyType.getMultiLineString());
		} else {
			throw new ConversionFailedException(locator,
					"Expected [MultiLineString] element.");
		}
	}

	public MultiPolygon createMultiPolygon(ObjectLocator locator,
			MultiPolygonType multiPolygonType) throws ConversionFailedException {
		final List<PolygonPropertyType> polygonMembers = multiPolygonType
				.getPolygonMember();
		final List<Polygon> polygons = new ArrayList<Polygon>(polygonMembers
				.size());
		for (int index = 0; index < polygonMembers.size(); index++) {
			final PolygonPropertyType polygonPropertyType = polygonMembers
					.get(index);
			final PolygonType polygonType = polygonPropertyType.getPolygon();
			polygons.add(createPolygon(locator.field("PolygonMember").entry(
					index).field("Polygon"), polygonType));

		}
		return geometryFactory.createMultiPolygon(polygons
				.toArray(new Polygon[polygons.size()]));
	}

	public MultiPolygon createMultiPolygon(ObjectLocator locator,
			MultiPolygonPropertyType multiPolygonPropertyType)
			throws ConversionFailedException {
		if (multiPolygonPropertyType.isSetMultiPolygon()) {
			return createMultiPolygon(locator.field("MultiPolygon"),
					multiPolygonPropertyType.getMultiPolygon());
		} else {
			throw new ConversionFailedException(locator,
					"Expected [MultiPolygon] element.");
		}
	}

	public GeometryCollection createGeometryCollection(ObjectLocator locator,
			AbstractGeometricAggregateType abstractGeometryType)
			throws ConversionFailedException {
		if (abstractGeometryType instanceof MultiPointType) {
			return createMultiPoint(locator,
					(MultiPointType) abstractGeometryType);
		} else if (abstractGeometryType instanceof MultiLineStringType) {
			return createMultiLineString(locator,
					(MultiLineStringType) abstractGeometryType);
		} else if (abstractGeometryType instanceof MultiPolygonType) {
			return createMultiPolygon(locator,
					(MultiPolygonType) abstractGeometryType);
		} else {
			throw new ConversionFailedException(locator, "Unexpected type.");
		}

	}

	public GeometryCollection createGeometryCollection(ObjectLocator locator,
			MultiGeometryPropertyType multiGeometryPropertyType)
			throws ConversionFailedException {
		if (multiGeometryPropertyType.isSetGeometricAggregate()) {
			return createGeometryCollection(locator.field("GeometricAggregate")
					.field("Value"), multiGeometryPropertyType
					.getGeometricAggregate().getValue());
		} else {
			throw new ConversionFailedException(locator,
					"Expected [GeometricAggregate] element.");
		}
	}

	public Geometry createGeometry(Object abstractGeometryType)
			throws ConversionFailedException {
		return createGeometry(
				new DefaultRootObjectLocator(abstractGeometryType),
				abstractGeometryType);
	}

	public Geometry createGeometry(ObjectLocator locator,
			Object abstractGeometryType) throws ConversionFailedException {
		if (abstractGeometryType instanceof PointType) {
			return createPoint(locator, (PointType) abstractGeometryType);
		} else if (abstractGeometryType instanceof PointPropertyType) {
			return createPoint(locator,
					(PointPropertyType) abstractGeometryType);
		} else if (abstractGeometryType instanceof PolygonType) {
			return createPolygon(locator, (PolygonType) abstractGeometryType);
		} else if (abstractGeometryType instanceof PolygonPropertyType) {
			return createPolygon(locator,
					(PolygonPropertyType) abstractGeometryType);
		} else if (abstractGeometryType instanceof LineStringType) {
			return createLineString(locator,
					(LineStringType) abstractGeometryType);
		} else if (abstractGeometryType instanceof LineStringPropertyType) {
			return createLineString(locator,
					(LineStringPropertyType) abstractGeometryType);
		} else if (abstractGeometryType instanceof LinearRingType) {
			return createLinearRing(locator,
					(LinearRingType) abstractGeometryType);
		} else if (abstractGeometryType instanceof LinearRingPropertyType) {
			return createLinearRing(locator,
					(LinearRingPropertyType) abstractGeometryType);
		} else if (abstractGeometryType instanceof MultiPointType) {
			return createMultiPoint(locator,
					(MultiPointType) abstractGeometryType);
		} else if (abstractGeometryType instanceof MultiPointPropertyType) {
			return createMultiPoint(locator,
					(MultiPointPropertyType) abstractGeometryType);
		} else if (abstractGeometryType instanceof MultiLineStringType) {
			return createMultiLineString(locator,
					(MultiLineStringType) abstractGeometryType);
		} else if (abstractGeometryType instanceof MultiLineStringPropertyType) {
			return createMultiLineString(locator,
					(MultiLineStringPropertyType) abstractGeometryType);
		} else if (abstractGeometryType instanceof MultiPolygonType) {
			return createMultiPolygon(locator,
					(MultiPolygonType) abstractGeometryType);
		} else if (abstractGeometryType instanceof MultiPolygonPropertyType) {
			return createMultiPolygon(locator,
					(MultiPolygonPropertyType) abstractGeometryType);
		} else if (abstractGeometryType instanceof MultiGeometryPropertyType) {
			return createGeometryCollection(locator,
					(MultiGeometryPropertyType) abstractGeometryType);
		} else {
			throw new ConversionFailedException(locator, "Unexpected type.");
		}

	}
}
