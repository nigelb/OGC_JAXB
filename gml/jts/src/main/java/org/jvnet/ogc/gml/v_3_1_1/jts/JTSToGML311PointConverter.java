package org.jvnet.ogc.gml.v_3_1_1.jts;

import javax.xml.bind.JAXBElement;

import net.opengis.gml.v_3_1_1.DirectPositionType;
import net.opengis.gml.v_3_1_1.ObjectFactory;
import net.opengis.gml.v_3_1_1.PointPropertyType;
import net.opengis.gml.v_3_1_1.PointType;

import com.vividsolutions.jts.geom.Point;

public class JTSToGML311PointConverter extends AbstractJTSToGML311Converter {
  private final JTSToGML311CoordinateConverter coordinateConverter;

  public JTSToGML311PointConverter(ObjectFactory objectFactory) {
    super(objectFactory);
    coordinateConverter = new JTSToGML311CoordinateConverter(objectFactory);
  }

  public JTSToGML311PointConverter() {
    this(new ObjectFactory());
  }

  public PointType createPointType(Point point) {

    final PointType resultPoint = getObjectFactory().createPointType();

    if (!point.isEmpty()) {
      final DirectPositionType directPosition = coordinateConverter.convertCoordinate(point
          .getCoordinate());
      resultPoint.setPos(directPosition);
    }
    return resultPoint;

  }

  public JAXBElement<PointType> createPoint(Point point) {
    return getObjectFactory().createPoint(createPointType(point));
  }

  public PointPropertyType createPointPropertyType(Point point) {
    final PointPropertyType pointPropertyType = getObjectFactory().createPointPropertyType();

    pointPropertyType.setPoint(createPointType(point));
    return pointPropertyType;

  }
}
