package org.jvnet.ogc.gml.v_3_1_1.jts;

import javax.xml.bind.JAXBElement;

import net.opengis.gml.v_3_1_1.AbstractGeometryType;
import net.opengis.gml.v_3_1_1.GeometryPropertyType;
import net.opengis.gml.v_3_1_1.ObjectFactory;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;

public class JTSToGML311GeometryConverter extends AbstractJTSToGML311Converter {

  private final JTSToGML311PointConverter pointConverter;
  private final JTSToGML311LinearRingConverter linearRingConverter;
  private final JTSToGML311LineStringConverter lineStringConverter;
  private final JTSToGML311PolygonConverter polygonConverter;
  private final JTSToGML311MultiPointConverter multiPointConverter;
  private final JTSToGML311MultiLineStringConverter multiLineStringConverter;
  private final JTSToGML311MultiPolygonConverter multiPolygonConverter;
  private final JTSToGML311MultiGeometryConverter multiGeometryConverter;

  public JTSToGML311GeometryConverter(ObjectFactory objectFactory) {
    super(objectFactory);
    pointConverter = new JTSToGML311PointConverter(objectFactory);
    linearRingConverter = new JTSToGML311LinearRingConverter(objectFactory);
    lineStringConverter = new JTSToGML311LineStringConverter(objectFactory);
    polygonConverter = new JTSToGML311PolygonConverter(objectFactory);
    multiPointConverter = new JTSToGML311MultiPointConverter(objectFactory);
    multiLineStringConverter = new JTSToGML311MultiLineStringConverter(objectFactory);
    multiPolygonConverter = new JTSToGML311MultiPolygonConverter(objectFactory);
    multiGeometryConverter = new JTSToGML311MultiGeometryConverter(objectFactory);
  }

  public JTSToGML311GeometryConverter() {
    this(new ObjectFactory());
  }

  public GeometryPropertyType createGeometryPropertyType(Geometry geometry) {
    final GeometryPropertyType geometryPropertyType = getObjectFactory()
        .createGeometryPropertyType();
    geometryPropertyType.setGeometry(createGeometry(geometry));
    return geometryPropertyType;
  }

  public JAXBElement<? extends AbstractGeometryType> createGeometry(Geometry geometry) {
    if (geometry instanceof Point) {
      return pointConverter.createPoint((Point) geometry);
    }
    else if (geometry instanceof LineString) {
      return lineStringConverter.createLineString((LineString) geometry);
    }
    else if (geometry instanceof LinearRing) {
      return linearRingConverter.createLinearRing((LinearRing) geometry);
    }
    else if (geometry instanceof Polygon) {
      return polygonConverter.createPolygon((Polygon) geometry);
    }
    else if (geometry instanceof MultiPoint) {
      return multiPointConverter.createMultiPoint((MultiPoint) geometry);
    }
    else if (geometry instanceof MultiLineString) {
      return multiLineStringConverter.createMultiLineString((MultiLineString) geometry);
    }
    else if (geometry instanceof MultiPolygon) {
      return multiPolygonConverter.createMultiPolygon((MultiPolygon) geometry);
    }
    else if (geometry instanceof GeometryCollection) {
      return multiGeometryConverter.createMultiGeometry((GeometryCollection) geometry);
    }
    else {
      // TODo
      throw new IllegalArgumentException();
    }

  }

  public AbstractGeometryType createAbstractGeometryType(Geometry geometry) {
    if (geometry instanceof Point) {
      return pointConverter.createPointType((Point) geometry);
    }
    else if (geometry instanceof LineString) {
      return lineStringConverter.createLineStringType((LineString) geometry);
    }
    else if (geometry instanceof LinearRing) {
      return linearRingConverter.createLinearRingType((LinearRing) geometry);
    }
    else if (geometry instanceof Polygon) {
      return polygonConverter.createPolygonType((Polygon) geometry);
    }
    else if (geometry instanceof MultiPoint) {
      return multiPointConverter.createMultiPointType((MultiPoint) geometry);
    }
    else if (geometry instanceof MultiLineString) {
      return multiLineStringConverter.createMultiLineStringType((MultiLineString) geometry);
    }
    else if (geometry instanceof MultiPolygon) {
      return multiPolygonConverter.createMultiPolygonType((MultiPolygon) geometry);
    }
    else if (geometry instanceof GeometryCollection) {
      return multiGeometryConverter.createMultiGeometryType((GeometryCollection) geometry);
    }
    else {
      // TODO
      throw new IllegalArgumentException();
    }

  }
}
