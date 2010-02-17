package org.jvnet.ogc.gml.v_3_1_1.jts;

import net.opengis.gml.v_3_1_1.AbstractGeometricAggregateType;
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

import org.jvnet.jaxb2_commons.locator.DefaultRootObjectLocator;
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

  // + Geometry
  // + Point
  // + Polygon
  // + LineString
  // + LinearRing
  // + GeometryCollection
  // + MultiPoint
  // + MultiLineString
  // + MultiPolygon

  private final GML311ToJTSCoordinateConverter coordinateConverter;
  private final GML311ToJTSPointConverter pointConverter;
  private final GML311ToJTSLineStringConverter lineStringConverter;
  private final GML311ToJTSLinearRingConverter linearRingConverter;
  private final GML311ToJTSPolygonConverter polygonConverter;
  private final GML311ToJTSMultiPointConverter multiPointConverter;
  private final GML311ToJTSMultiLineStringConverter multiLineStringConverter;
  private final GML311ToJTSMultiPolygonConverter multiPolygonConverter;
  private final GML311ToJTSGeometryCollectionConverter geometryCollectionConverter;
  private final GML311ToJTSGeometryConverter geometryConverter;

  public GML311ToJTSConverter(GeometryFactory geometryFactory) {
    coordinateConverter = new GML311ToJTSCoordinateConverter();
    pointConverter = new GML311ToJTSPointConverter(geometryFactory);
    lineStringConverter = new GML311ToJTSLineStringConverter(geometryFactory);
    linearRingConverter = new GML311ToJTSLinearRingConverter(geometryFactory);
    polygonConverter = new GML311ToJTSPolygonConverter(geometryFactory);
    multiPointConverter = new GML311ToJTSMultiPointConverter(geometryFactory);
    multiLineStringConverter = new GML311ToJTSMultiLineStringConverter();
    multiPolygonConverter = new GML311ToJTSMultiPolygonConverter();
    geometryCollectionConverter = new GML311ToJTSGeometryCollectionConverter();
    geometryConverter = new GML311ToJTSGeometryConverter();
  }

  public GML311ToJTSConverter() {
    this(new GeometryFactory());
  }

  public Coordinate createCoordinate(ObjectLocator locator, DirectPositionType directPositionType)
      throws ConversionFailedException {
    return coordinateConverter.createCoordinate(locator, directPositionType);
  }

  public Coordinate[] createCoordinates(
      ObjectLocator locator,
      DirectPositionListType directPositionListType) throws ConversionFailedException {
    return coordinateConverter.createCoordinates(locator, directPositionListType);
  }

  public Coordinate createCoordinate(ObjectLocator locator, CoordType coordType)
      throws ConversionFailedException {
    return coordinateConverter.createCoordinate(locator, coordType);
  }

  public Coordinate[] createCoordinates(ObjectLocator locator, final CoordinatesType coordinates)
      throws ConversionFailedException {
    return coordinateConverter.createCoordinates(locator, coordinates);
  }

  public Coordinate[] createCoordinates(
      ObjectLocator locator,
      String value,
      String ds,
      String cs,
      String ts) throws ConversionFailedException {
    return coordinateConverter.createCoordinates(locator, value, ds, cs, ts);
  }

  public Coordinate createCoordinate(ObjectLocator locator, String value, String ds, String ts)
      throws ConversionFailedException {
    return coordinateConverter.createCoordinate(locator, value, ds, ts);
  }

  public double createCoordinateComponent(ObjectLocator locator, String value, String ds)
      throws ConversionFailedException {
    return coordinateConverter.createCoordinateComponent(locator, value, ds);
  }

  public Point createPoint(ObjectLocator locator, PointType pointType)
      throws ConversionFailedException {
    return pointConverter.createGeometry(locator, pointType);
  }

  public Point createPoint(ObjectLocator locator, PointPropertyType pointPropertyType)
      throws ConversionFailedException {
    return pointConverter.createGeometry(locator, pointPropertyType);
  }

  public LinearRing createLinearRing(ObjectLocator locator, LinearRingType linearRingType)
      throws ConversionFailedException {
    return linearRingConverter.createGeometry(locator, linearRingType);
  }

  public LinearRing createLinearRing(
      ObjectLocator locator,
      LinearRingPropertyType linearRingPropertyType) throws ConversionFailedException {
    return linearRingConverter.createGeometry(locator, linearRingPropertyType);
  }

  public LineString createLineString(ObjectLocator locator, LineStringType lineStringType)
      throws ConversionFailedException {
    return lineStringConverter.createGeometry(locator, lineStringType);
  }

  public LineString createLineString(
      ObjectLocator locator,
      LineStringPropertyType lineStringPropertyType) throws ConversionFailedException {
    return lineStringConverter.createGeometry(locator, lineStringPropertyType);
  }

  public Polygon createPolygon(ObjectLocator locator, PolygonType polygonType)
      throws ConversionFailedException {
    return polygonConverter.createGeometry(locator, polygonType);
  }

  public Polygon createPolygon(ObjectLocator locator, PolygonPropertyType polygonPropertyType)
      throws ConversionFailedException {
    return polygonConverter.createGeometry(locator, polygonPropertyType);
  }

  public MultiPoint createMultiPoint(ObjectLocator locator, MultiPointType multiPointType)
      throws ConversionFailedException {
    return multiPointConverter.createGeometry(locator, multiPointType);
  }

  public MultiPoint createMultiPoint(
      ObjectLocator locator,
      MultiPointPropertyType multiPointPropertyType) throws ConversionFailedException {
    return multiPointConverter.createGeometry(locator, multiPointPropertyType);
  }

  public MultiLineString createMultiLineString(
      ObjectLocator locator,
      MultiLineStringType multiLineStringType) throws ConversionFailedException {
    return multiLineStringConverter.createGeometry(locator, multiLineStringType);
  }

  public MultiLineString createMultiLineString(
      ObjectLocator locator,
      MultiLineStringPropertyType multiLineStringPropertyType) throws ConversionFailedException {
    return multiLineStringConverter.createGeometry(locator, multiLineStringPropertyType);
  }

  public MultiPolygon createMultiPolygon(ObjectLocator locator, MultiPolygonType multiPolygonType)
      throws ConversionFailedException {
    return multiPolygonConverter.createGeometry(locator, multiPolygonType);
  }

  public MultiPolygon createMultiPolygon(
      ObjectLocator locator,
      MultiPolygonPropertyType multiPolygonPropertyType) throws ConversionFailedException {
    return multiPolygonConverter.createGeometry(locator, multiPolygonPropertyType);
  }

  public GeometryCollection createGeometryCollection(
      ObjectLocator locator,
      AbstractGeometricAggregateType abstractGeometryType) throws ConversionFailedException {
    return geometryCollectionConverter.createGeometry(locator, abstractGeometryType);
  }

  public GeometryCollection createGeometryCollection(
      ObjectLocator locator,
      MultiGeometryPropertyType multiGeometryPropertyType) throws ConversionFailedException {
    return geometryCollectionConverter.createGeometry(locator, multiGeometryPropertyType);
  }

  public Geometry createGeometry(Object abstractGeometryType) throws ConversionFailedException {
    return createGeometry(new DefaultRootObjectLocator(abstractGeometryType), abstractGeometryType);
  }

  public Geometry createGeometry(ObjectLocator locator, Object abstractGeometryType)
      throws ConversionFailedException {
    return geometryConverter.createGeometry(locator, abstractGeometryType);
  }
}
