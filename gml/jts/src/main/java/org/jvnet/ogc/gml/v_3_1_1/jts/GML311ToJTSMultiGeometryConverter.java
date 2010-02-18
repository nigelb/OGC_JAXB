package org.jvnet.ogc.gml.v_3_1_1.jts;

import java.util.ArrayList;
import java.util.List;

import net.opengis.gml.v_3_1_1.AbstractGeometricAggregateType;
import net.opengis.gml.v_3_1_1.AbstractGeometryType;
import net.opengis.gml.v_3_1_1.GeometryPropertyType;
import net.opengis.gml.v_3_1_1.MultiGeometryPropertyType;
import net.opengis.gml.v_3_1_1.MultiGeometryType;

import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.GeometryFactory;

public class GML311ToJTSMultiGeometryConverter
    extends
    AbstractGML311ToJTSConverter<MultiGeometryType, MultiGeometryPropertyType, GeometryCollection> {

  // + MultiPolygon

  private final GML311ToJTSGeometryConverter geometryConverter;

  public GML311ToJTSMultiGeometryConverter(
      GeometryFactory geometryFactory,
      GML311ToJTSGeometryConverter geometryConverter) {
    super(geometryFactory);
    this.geometryConverter = geometryConverter;
  }

  @Override
  public GeometryCollection createGeometry(
      ObjectLocator locator,
      MultiGeometryType multiGeometryType) throws ConversionFailedException {

    final List<Geometry> geometries = new ArrayList<Geometry>(multiGeometryType
        .getGeometryMember()
        .size()
        + multiGeometryType.getGeometryMembers().getGeometry().size());

    if (multiGeometryType.isSetGeometryMember()) {
      final ObjectLocator geometryMemberLocator = locator.field("GeometryMember");
      for (int index = 0; index < multiGeometryType.getGeometryMember().size(); index++) {
        final GeometryPropertyType geometryPropertyType = multiGeometryType
            .getGeometryMember()
            .get(index);
        geometries.add(this.geometryConverter.createGeometry(
            geometryMemberLocator.entry(index),
            geometryPropertyType));
      }
    }
    if (multiGeometryType.isSetGeometryMembers()) {
      final ObjectLocator geometryMemberLocator = locator
          .field("GeometryMembers")
          .field("Geometry");
      for (int index = 0; index < multiGeometryType.getGeometryMembers().getGeometry().size(); index++) {
        final AbstractGeometryType abstractGeometryType = multiGeometryType
            .getGeometryMembers()
            .getGeometry()
            .get(index)
            .getValue();
        geometries.add(this.geometryConverter.createGeometry(geometryMemberLocator
            .entry(index)
            .field("Value"), abstractGeometryType));
      }
    }
    return getGeometryFactory().createGeometryCollection(
        geometries.toArray(new Geometry[geometries.size()]));
  }

  @Override
  public GeometryCollection createGeometry(
      ObjectLocator locator,
      MultiGeometryPropertyType multiGeometryPropertyType) throws ConversionFailedException {
    if (multiGeometryPropertyType.isSetGeometricAggregate()) {
      final AbstractGeometricAggregateType value = multiGeometryPropertyType
          .getGeometricAggregate()
          .getValue();
      if (value instanceof MultiGeometryType) {
        return createGeometry(locator.field("GeometricAggregate"), //$NON-NLS-1$
            (MultiGeometryType) value);
      }
      else {
        throw new ConversionFailedException(
            locator.field("GeometricAggregate"),
            "Expected [MultiGeometry] element.");
      }
    }
    else {
      throw new ConversionFailedException(locator, "Expected [MultiGeometry] element."); //$NON-NLS-1$
    }
  }
}
