package org.jvnet.ogc.gml.v_3_1_1.jts;

import javax.xml.bind.JAXBElement;

import net.opengis.gml.v_3_1_1.DirectPositionType;
import net.opengis.gml.v_3_1_1.ObjectFactory;
import net.opengis.gml.v_3_1_1.PointPropertyType;
import net.opengis.gml.v_3_1_1.PointType;

import com.vividsolutions.jts.geom.Point;

public class JTSToGML311PointConverter
    extends
    AbstractJTSToGML311Converter<PointType, PointPropertyType, Point> {
  private final JTSToGML311CoordinateConverter coordinateConverter;

  public JTSToGML311PointConverter(ObjectFactory objectFactory) {
    super(objectFactory);
    coordinateConverter = new JTSToGML311CoordinateConverter(objectFactory);
  }

  public JTSToGML311PointConverter() {
    this(new ObjectFactory());
  }

  public PointType createGeometryType(Point point) {

    final PointType resultPoint = getObjectFactory().createPointType();

    if (!point.isEmpty()) {
      final DirectPositionType directPosition = coordinateConverter.convertCoordinate(point
          .getCoordinate());
      resultPoint.setPos(directPosition);
    }
    return resultPoint;

  }

  public PointPropertyType createPropertyType(Point point) {
    final PointPropertyType pointPropertyType = getObjectFactory().createPointPropertyType();

    pointPropertyType.setPoint(createGeometryType(point));
    return pointPropertyType;

  }

  public JAXBElement<PointType> createElement(Point point) {
    return getObjectFactory().createPoint(createGeometryType(point));
  }

}
