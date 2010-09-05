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

public class JTSToGML311GeometryConverter
    extends
    AbstractJTSToGML311Converter<AbstractGeometryType, GeometryPropertyType, Geometry> {

  private final JTSToGML311CoordinateConverter coordinateConverter;
  private final JTSToGML311PointConverter pointConverter;
  private final JTSToGML311LinearRingConverter linearRingConverter;
  private final JTSToGML311LineStringConverter lineStringConverter;
  private final JTSToGML311PolygonConverter polygonConverter;
  private final JTSToGML311MultiPointConverter multiPointConverter;
  private final JTSToGML311MultiLineStringConverter multiLineStringConverter;
  private final JTSToGML311MultiPolygonConverter multiPolygonConverter;
  private final JTSToGML311MultiGeometryConverter multiGeometryConverter;

  public JTSToGML311GeometryConverter(JTSToGML311CoordinateConverter coordinateConverter) {
    super(coordinateConverter.getObjectFactory(), coordinateConverter
        .getSrsReferenceGroupConverter());
    this.coordinateConverter = coordinateConverter;
    this.pointConverter = new JTSToGML311PointConverter(this.coordinateConverter);
    this.linearRingConverter = new JTSToGML311LinearRingConverter(this.coordinateConverter);
    this.lineStringConverter = new JTSToGML311LineStringConverter(this.coordinateConverter);
    this.polygonConverter = new JTSToGML311PolygonConverter(this.linearRingConverter);
    this.multiPointConverter = new JTSToGML311MultiPointConverter(this.pointConverter);
    this.multiLineStringConverter = new JTSToGML311MultiLineStringConverter(
        this.lineStringConverter);
    this.multiPolygonConverter = new JTSToGML311MultiPolygonConverter(this.polygonConverter);
    this.multiGeometryConverter = new JTSToGML311MultiGeometryConverter(this);
  }

  public JTSToGML311GeometryConverter() {
    this(new JTSToGML311CoordinateConverter(
        JTSToGML311Constants.DEFAULT_OBJECT_FACTORY,
        JTSToGML311Constants.DEFAULT_SRS_REFERENCE_GROUP_CONVERTER));
  }

  public JTSToGML311GeometryConverter(
      ObjectFactory objectFactory,
      JTSToGML311SRSReferenceGroupConverterInterface srsReferenceGroupConverter) {
    this(new JTSToGML311CoordinateConverter(objectFactory, srsReferenceGroupConverter));
  }

  @Override
  protected AbstractGeometryType doCreateGeometryType(Geometry geometry) {
    if (geometry instanceof Point) {
      return pointConverter.createGeometryType((Point) geometry);
    }
    else if (geometry instanceof LineString) {
      return lineStringConverter.createGeometryType((LineString) geometry);
    }
    else if (geometry instanceof LinearRing) {
      return linearRingConverter.createGeometryType((LinearRing) geometry);
    }
    else if (geometry instanceof Polygon) {
      return polygonConverter.createGeometryType((Polygon) geometry);
    }
    else if (geometry instanceof MultiPoint) {
      return multiPointConverter.createGeometryType((MultiPoint) geometry);
    }
    else if (geometry instanceof MultiLineString) {
      return multiLineStringConverter.createGeometryType((MultiLineString) geometry);
    }
    else if (geometry instanceof MultiPolygon) {
      return multiPolygonConverter.createGeometryType((MultiPolygon) geometry);
    }
    else if (geometry instanceof GeometryCollection) {
      return multiGeometryConverter.createGeometryType((GeometryCollection) geometry);
    }
    else {
      // TODO
      throw new IllegalArgumentException();
    }

  }

  @Override
  public GeometryPropertyType createPropertyType(Geometry geometry) {
    final GeometryPropertyType geometryPropertyType = getObjectFactory()
        .createGeometryPropertyType();
    geometryPropertyType.setGeometry(createElement(geometry));
    return geometryPropertyType;
  }

  @Override
  public JAXBElement<? extends AbstractGeometryType> createElement(Geometry geometry) {
    if (geometry instanceof Point) {
      return pointConverter.createElement((Point) geometry);
    }
    else if (geometry instanceof LineString) {
      return lineStringConverter.createElement((LineString) geometry);
    }
    else if (geometry instanceof LinearRing) {
      return linearRingConverter.createElement((LinearRing) geometry);
    }
    else if (geometry instanceof Polygon) {
      return polygonConverter.createElement((Polygon) geometry);
    }
    else if (geometry instanceof MultiPoint) {
      return multiPointConverter.createElement((MultiPoint) geometry);
    }
    else if (geometry instanceof MultiLineString) {
      return multiLineStringConverter.createElement((MultiLineString) geometry);
    }
    else if (geometry instanceof MultiPolygon) {
      return multiPolygonConverter.createElement((MultiPolygon) geometry);
    }
    else if (geometry instanceof GeometryCollection) {
      return multiGeometryConverter.createElement((GeometryCollection) geometry);
    }
    else {
      // TODo
      throw new IllegalArgumentException();
    }

  }

}
