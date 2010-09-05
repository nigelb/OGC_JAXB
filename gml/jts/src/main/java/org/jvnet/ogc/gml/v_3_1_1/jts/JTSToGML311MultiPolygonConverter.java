package org.jvnet.ogc.gml.v_3_1_1.jts;

import javax.xml.bind.JAXBElement;

import net.opengis.gml.v_3_1_1.MultiPolygonPropertyType;
import net.opengis.gml.v_3_1_1.MultiPolygonType;

import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Polygon;

public class JTSToGML311MultiPolygonConverter
    extends
    AbstractJTSToGML311Converter<MultiPolygonType, MultiPolygonPropertyType, MultiPolygon> {
  private final JTSToGML311PolygonConverter polygonConverter;

  public JTSToGML311MultiPolygonConverter(JTSToGML311PolygonConverter polygonConverter) {
    super(polygonConverter.getObjectFactory(), polygonConverter.getSrsReferenceGroupConverter());
    this.polygonConverter = polygonConverter;
  }

  public JTSToGML311MultiPolygonConverter() {
    this(new JTSToGML311PolygonConverter(new JTSToGML311LinearRingConverter(
        new JTSToGML311CoordinateConverter(
            JTSToGML311Constants.DEFAULT_OBJECT_FACTORY,
            JTSToGML311Constants.DEFAULT_SRS_REFERENCE_GROUP_CONVERTER))
    ));
  }

  @Override
  protected MultiPolygonType doCreateGeometryType(MultiPolygon multiPolygon) {
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
