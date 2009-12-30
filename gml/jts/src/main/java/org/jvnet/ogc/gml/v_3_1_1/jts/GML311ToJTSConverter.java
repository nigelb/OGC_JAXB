package org.jvnet.ogc.gml.v_3_1_1.jts;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import net.opengis.gml.v_3_1_1.AbstractRingPropertyType;
import net.opengis.gml.v_3_1_1.AbstractRingType;
import net.opengis.gml.v_3_1_1.CoordType;
import net.opengis.gml.v_3_1_1.CoordinatesType;
import net.opengis.gml.v_3_1_1.DirectPositionListType;
import net.opengis.gml.v_3_1_1.DirectPositionType;
import net.opengis.gml.v_3_1_1.LineStringPropertyType;
import net.opengis.gml.v_3_1_1.LineStringType;
import net.opengis.gml.v_3_1_1.LinearRingType;
import net.opengis.gml.v_3_1_1.MultiLineStringType;
import net.opengis.gml.v_3_1_1.MultiPointType;
import net.opengis.gml.v_3_1_1.MultiPolygonType;
import net.opengis.gml.v_3_1_1.PointPropertyType;
import net.opengis.gml.v_3_1_1.PointType;
import net.opengis.gml.v_3_1_1.PolygonPropertyType;
import net.opengis.gml.v_3_1_1.PolygonType;

import org.apache.commons.lang.StringUtils;

import com.vividsolutions.jts.geom.Coordinate;
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

	public Coordinate createCoordinate(DirectPositionType directPositionType)
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
			throw new ConversionFailedException(
					"Direct position type is expected to have 2 or 3 items.");
		}

	}

	public Coordinate[] createCoordinates(
			DirectPositionListType directPositionListType)
			throws ConversionFailedException {
		final int dimensions = directPositionListType.isSetSrsDimension() ? directPositionListType
				.getSrsDimension().intValue()
				: 2;

		if (dimensions < 2 || dimensions > 3) {
			throw new ConversionFailedException(
					"Only two- or three-dimensional coordinates are supported.");
		}

		List<Double> values = directPositionListType.getValue();
		if (values.size() % dimensions != 0) {
			throw new ConversionFailedException(
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

	public Coordinate createCoordinate(CoordType coordType)
			throws ConversionFailedException {
		if (coordType.isSetX() && coordType.isSetY() && !coordType.isSetZ()) {
			return new Coordinate(coordType.getX().doubleValue(), coordType
					.getY().doubleValue());
		} else if (coordType.isSetX() && coordType.isSetY()
				&& !coordType.isSetZ()) {
			return new Coordinate(coordType.getX().doubleValue(), coordType
					.getY().doubleValue(), coordType.getZ().doubleValue());

		} else {
			throw new ConversionFailedException(
					"Either X, Y or X, Y, Z values are required.");
		}

	}

	public Coordinate[] createCoordinates(String value, String ds, String cs,
			String ts) throws ConversionFailedException {
		final String coordinateSeparator = cs == null ? " " : cs;

		final String[] coordinates = StringUtils.split(value,
				coordinateSeparator);
		final Coordinate[] coordinatesArray = new Coordinate[coordinates.length];
		for (int index = 0; index < coordinates.length; index++) {
			coordinatesArray[index] = createCoordinate(coordinates[index], ds,
					ts);
		}
		return coordinatesArray;
	}

	public Coordinate createCoordinate(String value, String ds, String ts)
			throws ConversionFailedException {
		final String tupleSeparator = ts == null ? "," : ts;

		final String[] tuples = StringUtils.split(value, tupleSeparator);

		final double[] coordinateComponents = new double[tuples.length];
		for (int index = 0; index < tuples.length; index++) {
			coordinateComponents[index] = createCoordinateComponent(
					tuples[index], ds);
		}
		if (coordinateComponents.length == 2) {
			return new Coordinate(coordinateComponents[0],
					coordinateComponents[1]);
		} else if (coordinateComponents.length == 3) {
			return new Coordinate(coordinateComponents[0],
					coordinateComponents[1], coordinateComponents[2]);

		} else {
			throw new ConversionFailedException(
					"Expected two or three coordinates.");
		}
	}

	public double createCoordinateComponent(String value, String ds) {
		final String decimalSeparator = ds == null ? "." : ds;
		return Double.parseDouble(value.replace(decimalSeparator, "."));
	}

	public Point createPoint(PointType pointType)
			throws ConversionFailedException {

		if (pointType.isSetPos()) {
			final DirectPositionType pos = pointType.getPos();
			return geometryFactory.createPoint(createCoordinate(pos));
		} else if (pointType.isSetCoordinates()) {
			final CoordinatesType coordinates = pointType.getCoordinates();
			final Coordinate[] coords = createCoordinates(coordinates);
			if (coords.length == 1) {
				throw new ConversionFailedException(
						"Expected exactly one coordinate.");
			} else {
				return geometryFactory.createPoint(coords[0]);

			}

		} else if (pointType.isSetCoord()) {
			final CoordType coord = pointType.getCoord();
			return geometryFactory.createPoint(createCoordinate(coord));
		} else {
			throw new ConversionFailedException(
					"Either [pos], [coordinates] or [coord] elements are expected.");
		}

	}

	public Coordinate[] createCoordinates(final CoordinatesType coordinates)
			throws ConversionFailedException {
		final Coordinate[] coords = createCoordinates(coordinates.getValue(),
				coordinates.getDecimal(), coordinates.getCs(), coordinates
						.getTs());
		return coords;
	}

	public Point createPoint(PointPropertyType pointPropertyType)
			throws ConversionFailedException {
		if (pointPropertyType.isSetPoint()) {
			return createPoint(pointPropertyType.getPoint());
		} else {
			throw new UnsupportedOperationException();
		}
	}

	public LineString createLineString(LineStringType lineStringType)
			throws ConversionFailedException {
		if (lineStringType.isSetPosOrPointPropertyOrPointRep()) {

			final List<Coordinate> coordinates = new LinkedList<Coordinate>();
			for (JAXBElement<?> item : lineStringType
					.getPosOrPointPropertyOrPointRep()) {
				final Object value = item.getValue();

				if (value instanceof PointType) {
					coordinates.add(createPoint((PointType) value)
							.getCoordinate());
				} else if (value instanceof PointPropertyType) {
					coordinates.add(createPoint((PointPropertyType) value)
							.getCoordinate());
				} else if (value instanceof CoordType) {
					coordinates.add(createCoordinate((CoordType) value));
				} else {
					throw new ConversionFailedException("Unexpected type.");
				}
			}
			final Coordinate[] coordinatesArray = coordinates
					.toArray(new Coordinate[coordinates.size()]);
			return geometryFactory.createLineString(coordinatesArray);

		} else if (lineStringType.isSetPosList()) {

			final Coordinate[] coordinates = createCoordinates(lineStringType
					.getPosList());
			return geometryFactory.createLineString(coordinates);

		} else if (lineStringType.isSetCoordinates()) {
			final Coordinate[] coordinates = createCoordinates(lineStringType
					.getCoordinates());
			return geometryFactory.createLineString(coordinates);

		} else {
			throw new ConversionFailedException();
		}
	}

	public LinearRing createLinearRing(LinearRingType linearRingType)
			throws ConversionFailedException {
		if (linearRingType.isSetPosOrPointPropertyOrPointRep()) {

			final List<Coordinate> coordinates = new LinkedList<Coordinate>();
			for (JAXBElement<?> item : linearRingType
					.getPosOrPointPropertyOrPointRep()) {
				final Object value = item.getValue();

				if (value instanceof PointType) {
					coordinates.add(createPoint((PointType) value)
							.getCoordinate());
				} else if (value instanceof PointPropertyType) {
					coordinates.add(createPoint((PointPropertyType) value)
							.getCoordinate());
				} else if (value instanceof CoordType) {
					coordinates.add(createCoordinate((CoordType) value));
				} else {
					throw new ConversionFailedException("Unexpected type.");
				}
			}
			final Coordinate[] coordinatesArray = coordinates
					.toArray(new Coordinate[coordinates.size()]);
			return geometryFactory.createLinearRing(coordinatesArray);

		} else if (linearRingType.isSetPosList()) {

			final Coordinate[] coordinates = createCoordinates(linearRingType
					.getPosList());
			return geometryFactory.createLinearRing(coordinates);

		} else if (linearRingType.isSetCoordinates()) {
			final Coordinate[] coordinates = createCoordinates(linearRingType
					.getCoordinates());
			return geometryFactory.createLinearRing(coordinates);

		} else {
			throw new ConversionFailedException();
		}
	}

	public Polygon createPolygon(PolygonType polygonType)
			throws ConversionFailedException {
		final LinearRing shell;
		if (polygonType.isSetExterior()) {
			final AbstractRingPropertyType abstractRingPropertyType = polygonType
					.getExterior().getValue();

			final JAXBElement<? extends AbstractRingType> ringElement = abstractRingPropertyType
					.getRing();

			final AbstractRingType abstractRingType = ringElement.getValue();
			if (abstractRingType instanceof LinearRingType) {
				shell = createLinearRing((LinearRingType) abstractRingType);
			} else {
				throw new ConversionFailedException(
						"Only linear rings are supported.");
			}
		} else {
			shell = null;
		}

		final LinearRing[] holes;
		if (polygonType.isSetInterior()) {
			final List<LinearRing> holesList = new ArrayList<LinearRing>(
					polygonType.getInterior().size());
			for (JAXBElement<AbstractRingPropertyType> ringElement : polygonType
					.getInterior()) {
				final AbstractRingType abstractRingType = ringElement
						.getValue().getRing().getValue();
				if (abstractRingType instanceof LinearRingType) {
					holesList
							.add(createLinearRing((LinearRingType) abstractRingType));
				} else {
					throw new ConversionFailedException(
							"Only linear rings are supported.");
				}
			}

			holes = holesList.toArray(new LinearRing[holesList.size()]);
		} else {
			holes = null;
		}
		return geometryFactory.createPolygon(shell, holes);
	}

	public MultiPoint createMultiPoint(MultiPointType multiPointType)
			throws ConversionFailedException {
		if (multiPointType.isSetPointMember()) {
			final List<PointPropertyType> pointMembers = multiPointType
					.getPointMember();
			final List<Point> points = new ArrayList<Point>(pointMembers.size());
			for (PointPropertyType pointPropertyType : pointMembers) {
				points.add(createPoint(pointPropertyType));
			}
			return geometryFactory.createMultiPoint(points
					.toArray(new Point[points.size()]));
		} else if (multiPointType.isSetPointMembers()) {
			final List<PointType> pointMembers = multiPointType
					.getPointMembers().getPoint();
			final List<Point> points = new ArrayList<Point>(pointMembers.size());
			for (PointType pointType : pointMembers) {
				points.add(createPoint(pointType));
			}
			return geometryFactory.createMultiPoint(points
					.toArray(new Point[points.size()]));
		} else {
			throw new ConversionFailedException(
					"Either pointMember or pointMembers elements are expected.");
		}
	}

	public MultiLineString createMultiLineString(
			MultiLineStringType multiLineStringType)
			throws ConversionFailedException {
		final List<LineStringPropertyType> lineStringMembers = multiLineStringType
				.getLineStringMember();
		final List<LineString> lineStrings = new ArrayList<LineString>(
				lineStringMembers.size());
		for (LineStringPropertyType lineStringPropertyType : lineStringMembers) {
			final LineStringType lineStringType = lineStringPropertyType
					.getLineString();
			lineStrings.add(createLineString(lineStringType));
		}
		return geometryFactory.createMultiLineString(lineStrings
				.toArray(new LineString[lineStrings.size()]));
	}

	public MultiPolygon createMultiPolygon(MultiPolygonType multiPolygonType)
			throws ConversionFailedException {
		final List<PolygonPropertyType> polygonMembers = multiPolygonType
				.getPolygonMember();
		final List<Polygon> polygons = new ArrayList<Polygon>(polygonMembers
				.size());
		for (PolygonPropertyType polygonPropertyType : polygonMembers) {
			final PolygonType polygonType = polygonPropertyType.getPolygon();
			polygons.add(createPolygon(polygonType));

		}
		return geometryFactory.createMultiPolygon(polygons
				.toArray(new Polygon[polygons.size()]));
	}

}
