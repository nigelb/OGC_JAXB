package org.jvnet.ogc.gml.v_3_1_1.jts;

import javax.xml.bind.JAXBElement;

import net.opengis.gml.v_3_1_1.MultiGeometryPropertyType;
import net.opengis.gml.v_3_1_1.MultiGeometryType;
import net.opengis.gml.v_3_1_1.ObjectFactory;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;

public class JTSToGML311MultiGeometryConverter extends AbstractJTSToGML311Converter {
  private final JTSToGML311GeometryConverter geometryConverter;

  public JTSToGML311MultiGeometryConverter(
      ObjectFactory objectFactory,
      JTSToGML311GeometryConverter geometryConverter) {
    super(objectFactory);
    this.geometryConverter = geometryConverter;
  }

  public MultiGeometryType createMultiGeometryType(GeometryCollection multiGeometry) {
    final MultiGeometryType multiGeometryType = getObjectFactory().createMultiGeometryType();

    for (int index = 0; index < multiGeometry.getNumGeometries(); index++) {
      final Geometry geometry = multiGeometry.getGeometryN(index);

      multiGeometryType.getGeometryMember().add(
          geometryConverter.createGeometryPropertyType(geometry));

    }
    return multiGeometryType;
  }

  public MultiGeometryPropertyType createMultiGeometryPropertyType(GeometryCollection multiGeometry) {
    final MultiGeometryPropertyType multiGeometryPropertyType = getObjectFactory()
        .createMultiGeometryPropertyType();
    multiGeometryPropertyType.setGeometricAggregate(createMultiGeometry(multiGeometry));
    return multiGeometryPropertyType;
  }

  public JAXBElement<MultiGeometryType> createMultiGeometry(GeometryCollection geometryCollection) {
    return getObjectFactory().createMultiGeometry(createMultiGeometryType(geometryCollection));
  }
}
