package org.jvnet.ogc.gml.v_3_1_1.jts;

import net.opengis.gml.v_3_1_1.AbstractGeometricAggregateType;
import net.opengis.gml.v_3_1_1.MultiGeometryPropertyType;
import net.opengis.gml.v_3_1_1.MultiLineStringType;
import net.opengis.gml.v_3_1_1.MultiPointType;
import net.opengis.gml.v_3_1_1.MultiPolygonType;

import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.GeometryFactory;

public class GML311ToJTSGeometryCollectionConverter
    extends
    AbstractGML311ToJTSConverter<AbstractGeometricAggregateType, MultiGeometryPropertyType, GeometryCollection> {

  // + GeometryCollection
  // + MultiPoint
  // + MultiLineString
  // + MultiPolygon

  private final GML311ToJTSMultiPointConverter multiPointConverter;
  private final GML311ToJTSMultiLineStringConverter multiLineStringConverter;
  private final GML311ToJTSMultiPolygonConverter multiPolygonConverter;

  public GML311ToJTSGeometryCollectionConverter(GeometryFactory geometryFactory) {
    super(geometryFactory);
    multiPointConverter = new GML311ToJTSMultiPointConverter(geometryFactory);
    multiLineStringConverter = new GML311ToJTSMultiLineStringConverter();
    multiPolygonConverter = new GML311ToJTSMultiPolygonConverter();
  }

  public GML311ToJTSGeometryCollectionConverter() {
    this(new GeometryFactory());
  }

  @Override
  public GeometryCollection createGeometry(
      ObjectLocator locator,
      AbstractGeometricAggregateType abstractGeometryType) throws ConversionFailedException {
    if (abstractGeometryType instanceof MultiPointType) {
      return multiPointConverter.createGeometry(locator, (MultiPointType) abstractGeometryType);
    }
    else if (abstractGeometryType instanceof MultiLineStringType) {
      return multiLineStringConverter.createGeometry(
          locator,
          (MultiLineStringType) abstractGeometryType);
    }
    else if (abstractGeometryType instanceof MultiPolygonType) {
      return multiPolygonConverter.createGeometry(locator, (MultiPolygonType) abstractGeometryType);
    }
    else {
      throw new ConversionFailedException(locator, "Unexpected type."); //$NON-NLS-1$
    }

  }

  @Override
  public GeometryCollection createGeometry(
      ObjectLocator locator,
      MultiGeometryPropertyType multiGeometryPropertyType) throws ConversionFailedException {
    if (multiGeometryPropertyType.isSetGeometricAggregate()) {
      return createGeometry(locator.field("GeometricAggregate").field("Value"), //$NON-NLS-1$ //$NON-NLS-2$
          multiGeometryPropertyType.getGeometricAggregate().getValue());
    }
    else {
      throw new ConversionFailedException(locator, "Expected [GeometricAggregate] element."); //$NON-NLS-1$
    }
  }
}
