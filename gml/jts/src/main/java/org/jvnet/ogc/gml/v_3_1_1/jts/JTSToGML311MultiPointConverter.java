package org.jvnet.ogc.gml.v_3_1_1.jts;

import javax.xml.bind.JAXBElement;

import net.opengis.gml.v_3_1_1.MultiPointPropertyType;
import net.opengis.gml.v_3_1_1.MultiPointType;

import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.Point;

public class JTSToGML311MultiPointConverter
    extends
    AbstractJTSToGML311Converter<MultiPointType, MultiPointPropertyType, MultiPoint> {
  private final JTSToGML311PointConverter pointConverter;

  public JTSToGML311MultiPointConverter(JTSToGML311PointConverter pointConverter) {
    super(pointConverter.getObjectFactory(), pointConverter.getSrsReferenceGroupConverter());
    this.pointConverter = pointConverter;
  }

  public JTSToGML311MultiPointConverter() {
    this(new JTSToGML311PointConverter(new JTSToGML311CoordinateConverter(
        JTSToGML311Constants.DEFAULT_OBJECT_FACTORY,
        JTSToGML311Constants.DEFAULT_SRS_REFERENCE_GROUP_CONVERTER)));
  }

  @Override
  protected MultiPointType doCreateGeometryType(MultiPoint multiPoint) {
    final MultiPointType multiPointType = getObjectFactory().createMultiPointType();

    for (int index = 0; index < multiPoint.getNumGeometries(); index++) {
      final Point point = (Point) multiPoint.getGeometryN(index);
      multiPointType.getPointMember().add(pointConverter.createPropertyType(point));
    }
    return multiPointType;
  }

  @Override
  public MultiPointPropertyType createPropertyType(MultiPoint multiPoint) {
    final MultiPointPropertyType multiPointPropertyType = getObjectFactory()
        .createMultiPointPropertyType();
    multiPointPropertyType.setMultiPoint(createGeometryType(multiPoint));
    return multiPointPropertyType;
  }

  @Override
  public JAXBElement<MultiPointType> createElement(MultiPoint multiPoint) {
    return getObjectFactory().createMultiPoint(createGeometryType(multiPoint));
  }

}
