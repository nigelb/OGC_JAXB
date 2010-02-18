package org.jvnet.ogc.gml.v_3_1_1.jts;

import javax.xml.bind.JAXBElement;

import net.opengis.gml.v_3_1_1.MultiGeometryPropertyType;
import net.opengis.gml.v_3_1_1.MultiGeometryType;
import net.opengis.gml.v_3_1_1.ObjectFactory;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;

public class JTSToGML311MultiGeometryConverter
    extends
    AbstractJTSToGML311Converter<MultiGeometryType, MultiGeometryPropertyType, GeometryCollection> {
  private final JTSToGML311GeometryConverter geometryConverter;

  public JTSToGML311MultiGeometryConverter(
      ObjectFactory objectFactory,
      JTSToGML311GeometryConverter geometryConverter) {
    super(objectFactory);
    this.geometryConverter = geometryConverter;
  }

  @Override
  public MultiGeometryType createGeometryType(GeometryCollection multiGeometry) {
    final MultiGeometryType multiGeometryType = getObjectFactory().createMultiGeometryType();

    for (int index = 0; index < multiGeometry.getNumGeometries(); index++) {
      final Geometry geometry = multiGeometry.getGeometryN(index);

      multiGeometryType.getGeometryMember().add(geometryConverter.createPropertyType(geometry));

    }
    return multiGeometryType;
  }

  @Override
  public MultiGeometryPropertyType createPropertyType(GeometryCollection multiGeometry) {
    final MultiGeometryPropertyType multiGeometryPropertyType = getObjectFactory()
        .createMultiGeometryPropertyType();
    multiGeometryPropertyType.setGeometricAggregate(createElement(multiGeometry));
    return multiGeometryPropertyType;
  }

  @Override
  public JAXBElement<MultiGeometryType> createElement(GeometryCollection geometryCollection) {
    return getObjectFactory().createMultiGeometry(createGeometryType(geometryCollection));
  }
}
