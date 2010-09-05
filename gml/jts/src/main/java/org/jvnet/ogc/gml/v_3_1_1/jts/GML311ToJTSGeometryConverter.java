package org.jvnet.ogc.gml.v_3_1_1.jts;

import net.opengis.gml.v_3_1_1.AbstractGeometricAggregateType;
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

import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;

public class GML311ToJTSGeometryConverter {

  // + Geometry
  // + Point
  // + Polygon
  // + LineString
  // + LinearRing
  // + GeometryCollection
  // + MultiPoint
  // + MultiLineString
  // + MultiPolygon

  private final GML311ToJTSPointConverter pointConverter;
  private final GML311ToJTSLineStringConverter lineStringConverter;
  private final GML311ToJTSLinearRingConverter linearRingConverter;
  private final GML311ToJTSPolygonConverter polygonConverter;
  private final GML311ToJTSMultiPointConverter multiPointConverter;
  private final GML311ToJTSMultiLineStringConverter multiLineStringConverter;
  private final GML311ToJTSMultiPolygonConverter multiPolygonConverter;
  private final GML311ToJTSGeometryCollectionConverter geometryCollectionConverter;

  public GML311ToJTSGeometryConverter(GeometryFactory geometryFactory) {
    pointConverter = new GML311ToJTSPointConverter(geometryFactory);
    lineStringConverter = new GML311ToJTSLineStringConverter(geometryFactory);
    linearRingConverter = new GML311ToJTSLinearRingConverter(geometryFactory);
    polygonConverter = new GML311ToJTSPolygonConverter(geometryFactory);
    multiPointConverter = new GML311ToJTSMultiPointConverter(geometryFactory);
    multiLineStringConverter = new GML311ToJTSMultiLineStringConverter(geometryFactory);
    multiPolygonConverter = new GML311ToJTSMultiPolygonConverter(geometryFactory);
    geometryCollectionConverter = new GML311ToJTSGeometryCollectionConverter(geometryFactory);
  }

  public GML311ToJTSGeometryConverter() {
    this(new GeometryFactory());
  }

  public Geometry createGeometry(ObjectLocator locator, Object abstractGeometryType)
      throws ConversionFailedException {
    if (abstractGeometryType instanceof PointType) {
      return pointConverter.createGeometry(locator, (PointType) abstractGeometryType);
    }
    else if (abstractGeometryType instanceof PointPropertyType) {
      return pointConverter.createGeometry(locator, (PointPropertyType) abstractGeometryType);
    }
    else if (abstractGeometryType instanceof PolygonType) {
      return polygonConverter.createGeometry(locator, (PolygonType) abstractGeometryType);
    }
    else if (abstractGeometryType instanceof PolygonPropertyType) {
      return polygonConverter.createGeometry(locator, (PolygonPropertyType) abstractGeometryType);
    }
    else if (abstractGeometryType instanceof LineStringType) {
      return lineStringConverter.createGeometry(locator, (LineStringType) abstractGeometryType);
    }
    else if (abstractGeometryType instanceof LineStringPropertyType) {
      return lineStringConverter.createGeometry(
          locator,
          (LineStringPropertyType) abstractGeometryType);
    }
    else if (abstractGeometryType instanceof LinearRingType) {
      return linearRingConverter.createGeometry(locator, (LinearRingType) abstractGeometryType);
    }
    else if (abstractGeometryType instanceof LinearRingPropertyType) {
      return linearRingConverter.createGeometry(
          locator,
          (LinearRingPropertyType) abstractGeometryType);
    }
    else if (abstractGeometryType instanceof MultiPointType) {
      return multiPointConverter.createGeometry(locator, (MultiPointType) abstractGeometryType);
    }
    else if (abstractGeometryType instanceof MultiPointPropertyType) {
      return multiPointConverter.createGeometry(
          locator,
          (MultiPointPropertyType) abstractGeometryType);
    }
    else if (abstractGeometryType instanceof MultiLineStringType) {
      return multiLineStringConverter.createGeometry(
          locator,
          (MultiLineStringType) abstractGeometryType);
    }
    else if (abstractGeometryType instanceof MultiLineStringPropertyType) {
      return multiLineStringConverter.createGeometry(
          locator,
          (MultiLineStringPropertyType) abstractGeometryType);
    }
    else if (abstractGeometryType instanceof MultiPolygonType) {
      return multiPolygonConverter.createGeometry(locator, (MultiPolygonType) abstractGeometryType);
    }
    else if (abstractGeometryType instanceof MultiPolygonPropertyType) {
      return multiPolygonConverter.createGeometry(
          locator,
          (MultiPolygonPropertyType) abstractGeometryType);
    }
    else if (abstractGeometryType instanceof AbstractGeometricAggregateType) {
      return geometryCollectionConverter.createGeometry(
          locator,
          (AbstractGeometricAggregateType) abstractGeometryType);
    }
    else if (abstractGeometryType instanceof MultiGeometryPropertyType) {
      return geometryCollectionConverter.createGeometry(
          locator,
          (MultiGeometryPropertyType) abstractGeometryType);
    }
    else {
      throw new ConversionFailedException(locator, "Unexpected type."); //$NON-NLS-1$
    }

  }
}
