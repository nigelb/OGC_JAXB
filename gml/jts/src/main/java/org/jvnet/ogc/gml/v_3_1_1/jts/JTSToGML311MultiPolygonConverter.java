package org.jvnet.ogc.gml.v_3_1_1.jts;

import javax.xml.bind.JAXBElement;

import net.opengis.gml.v_3_1_1.MultiPolygonPropertyType;
import net.opengis.gml.v_3_1_1.MultiPolygonType;
import net.opengis.gml.v_3_1_1.ObjectFactory;

import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Polygon;

public class JTSToGML311MultiPolygonConverter
    extends
    AbstractJTSToGML311Converter<MultiPolygonType, MultiPolygonPropertyType, MultiPolygon> {
  private final JTSToGML311PolygonConverter polygonConverter;

  public JTSToGML311MultiPolygonConverter(ObjectFactory objectFactory) {
    super(objectFactory);
    polygonConverter = new JTSToGML311PolygonConverter(objectFactory);
  }

  public JTSToGML311MultiPolygonConverter() {
    this(new ObjectFactory());
  }

  @Override
  public MultiPolygonType createGeometryType(MultiPolygon multiPolygon) {
    final MultiPolygonType multiPolygonType = getObjectFactory().createMultiPolygonType();
    for (int index = 0; index < multiPolygon.getNumGeometries(); index++) {
      final Polygon polygon = (Polygon) multiPolygon.getGeometryN(index);
      multiPolygonType.getPolygonMember().add(polygonConverter.createPropertyType(polygon));
    }

    return multiPolygonType;
  }

  @Override
  public MultiPolygonPropertyType createPropertyType(MultiPolygon multiPolygon) {
    final MultiPolygonPropertyType multiPolygonPropertyType = getObjectFactory()
        .createMultiPolygonPropertyType();
    multiPolygonPropertyType.setMultiPolygon(createGeometryType(multiPolygon));
    return multiPolygonPropertyType;
  }

  @Override
  public JAXBElement<MultiPolygonType> createElement(MultiPolygon multiPolygon) {
    return getObjectFactory().createMultiPolygon(createGeometryType(multiPolygon));
  }

}
