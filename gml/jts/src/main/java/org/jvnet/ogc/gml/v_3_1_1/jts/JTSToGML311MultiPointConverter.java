package org.jvnet.ogc.gml.v_3_1_1.jts;

import javax.xml.bind.JAXBElement;

import net.opengis.gml.v_3_1_1.MultiPointType;
import net.opengis.gml.v_3_1_1.ObjectFactory;

import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.Point;

public class JTSToGML311MultiPointConverter extends AbstractJTSToGML311Converter {
  private final JTSToGML311PointConverter pointConverter;

  public JTSToGML311MultiPointConverter(ObjectFactory objectFactory) {
    super(objectFactory);
    pointConverter = new JTSToGML311PointConverter(objectFactory);
  }

  public JTSToGML311MultiPointConverter() {
    this(new ObjectFactory());
  }

  public MultiPointType createMultiPointType(MultiPoint multiPoint) {
    final MultiPointType multiPointType = getObjectFactory().createMultiPointType();

    for (int index = 0; index < multiPoint.getNumGeometries(); index++) {
      final Point point = (Point) multiPoint.getGeometryN(index);
      multiPointType.getPointMember().add(pointConverter.createPointPropertyType(point));
    }
    return multiPointType;
  }

  public JAXBElement<MultiPointType> createMultiPoint(MultiPoint multiPoint) {
    return getObjectFactory().createMultiPoint(createMultiPointType(multiPoint));
  }

}
