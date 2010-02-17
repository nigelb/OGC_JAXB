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
  private final JTSToGML311CoordinateConverter coordinateConverter;
  private final JTSToGML311PointConverter pointConverter;
  private final JTSToGML311LinearRingConverter linearRingConverter;
  private final JTSToGML311LineStringConverter lineStringConverter;
  private final JTSToGML311PolygonConverter polygonConverter;
  private final JTSToGML311MultiPointConverter multiPointConverter;
  private final JTSToGML311MultiLineStringConverter multiLineStringConverter;
  private final JTSToGML311MultiPolygonConverter multiPolygonConverter;
  private final JTSToGML311MultiGeometryConverter multiGeometryConverter;
  private final JTSToGML311GeometryConverter geometryConverter;

  public JTSToGML311Converter(ObjectFactory objectFactory) {
    super();
    this.objectFactory = objectFactory;
    coordinateConverter = new JTSToGML311CoordinateConverter(objectFactory);
    pointConverter = new JTSToGML311PointConverter(objectFactory);
    linearRingConverter = new JTSToGML311LinearRingConverter(objectFactory);
    lineStringConverter = new JTSToGML311LineStringConverter(objectFactory);
    polygonConverter = new JTSToGML311PolygonConverter(objectFactory);
    multiPointConverter = new JTSToGML311MultiPointConverter(objectFactory);
    multiLineStringConverter = new JTSToGML311MultiLineStringConverter(objectFactory);
    multiPolygonConverter = new JTSToGML311MultiPolygonConverter(objectFactory);
    multiGeometryConverter = new JTSToGML311MultiGeometryConverter(objectFactory);
    geometryConverter = new JTSToGML311GeometryConverter(objectFactory);
  }

  public JTSToGML311Converter() {
    this(new ObjectFactory());
  }

  public DirectPositionType convertCoordinate(Coordinate coordinate) {
    return coordinateConverter.convertCoordinate(coordinate);
  }

  public DirectPositionType[] convertCoordinates(Coordinate[] coordinates) {
    return coordinateConverter.convertCoordinates(coordinates);
  }

  public PointType createPointType(Point point) {
    return pointConverter.createPointType(point);
  }

  public JAXBElement<PointType> createPoint(Point point) {
    return pointConverter.createPoint(point);
  }

  public PointPropertyType createPointPropertyType(Point point) {
    return pointConverter.createPointPropertyType(point);
  }

  public LineStringType createLineStringType(LineString lineString) {
    return lineStringConverter.createLineStringType(lineString);
  }

  public LineStringPropertyType createLineStringPropertyType(LineString lineString) {
    return lineStringConverter.createLineStringPropertyType(lineString);
  }

  public JAXBElement<LineStringType> createLineString(LineString linearString) {
    return lineStringConverter.createLineString(linearString);
  }

  public LinearRingType createLinearRingType(LinearRing linearRing) {
    return linearRingConverter.createLinearRingType(linearRing);
  }

  public JAXBElement<LinearRingType> createLinearRing(LinearRing linearRing) {
    return linearRingConverter.createLinearRing(linearRing);
  }

  public PolygonType createPolygonType(Polygon polygon) {
    return polygonConverter.createPolygonType(polygon);
  }

  public PolygonPropertyType createPolygonPropertyType(Polygon polygon) {
    return polygonConverter.createPolygonPropertyType(polygon);
  }

  public JAXBElement<PolygonType> createPolygon(Polygon polygon) {
    return polygonConverter.createPolygon(polygon);
  }

  public AbstractRingPropertyType createAbstractRingPropertyType(final LinearRing ring) {
    return linearRingConverter.createAbstractRingPropertyType(ring);
  }

  public MultiPointType createMultiPointType(MultiPoint multiPoint) {
    return multiPointConverter.createMultiPointType(multiPoint);
  }

  public JAXBElement<MultiPointType> createMultiPoint(MultiPoint multiPoint) {
    return multiPointConverter.createMultiPoint(multiPoint);
  }

  public MultiLineStringType createMultiLineStringType(MultiLineString multiLineString) {
    return multiLineStringConverter.createMultiLineStringType(multiLineString);
  }

  public JAXBElement<MultiLineStringType> createMultiLineString(MultiLineString multiLineString) {
    return multiLineStringConverter.createMultiLineString(multiLineString);
  }

  public MultiPolygonType createMultiPolygonType(MultiPolygon multiPolygon) {
    return multiPolygonConverter.createMultiPolygonType(multiPolygon);
  }

  public JAXBElement<MultiPolygonType> createMultiPolygon(MultiPolygon multiPolygon) {
    return multiPolygonConverter.createMultiPolygon(multiPolygon);
  }

  public MultiGeometryType createMultiGeometryType(GeometryCollection multiGeometry) {
    return multiGeometryConverter.createMultiGeometryType(multiGeometry);
  }

  public JAXBElement<MultiGeometryType> createMultiGeometry(GeometryCollection geometryCollection) {
    return multiGeometryConverter.createMultiGeometry(geometryCollection);
  }

  public GeometryPropertyType createGeometryPropertyType(Geometry geometry) {
    return geometryConverter.createGeometryPropertyType(geometry);
  }

  public JAXBElement<? extends AbstractGeometryType> createGeometry(Geometry geometry) {
    return geometryConverter.createGeometry(geometry);
  }

  public AbstractGeometryType createAbstractGeometryType(Geometry geometry) {
    return geometryConverter.createAbstractGeometryType(geometry);
  }
}
