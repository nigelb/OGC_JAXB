package org.jvnet.ogc.gml.v_3_1_1.jts;

import javax.xml.bind.JAXBElement;

import net.opengis.gml.v_3_1_1.DirectPositionType;
import net.opengis.gml.v_3_1_1.PointPropertyType;
import net.opengis.gml.v_3_1_1.PointType;

import com.vividsolutions.jts.geom.Point;

public class JTSToGML311PointConverter
    extends
    AbstractJTSToGML311Converter<PointType, PointPropertyType, Point> {
  private final JTSToGML311CoordinateConverter coordinateConverter;

  public JTSToGML311PointConverter(JTSToGML311CoordinateConverter coordinateConverter) {
    super(coordinateConverter.getObjectFactory(), coordinateConverter.getSrsReferenceGroupConverter());
    this.coordinateConverter = coordinateConverter;
  }

  public JTSToGML311PointConverter() {
    this(new JTSToGML311CoordinateConverter(
        JTSToGML311Constants.DEFAULT_OBJECT_FACTORY,
        JTSToGML311Constants.DEFAULT_SRS_REFERENCE_GROUP_CONVERTER));
  }

  @Override
  protected PointType doCreateGeometryType(Point point) {

    final PointType resultPoint = getObjectFactory().createPointType();

    if (!point.isEmpty()) {
      final DirectPositionType directPosition = coordinateConverter.convertCoordinate(point
          .getCoordinate());
      resultPoint.setPos(directPosition);
    }
    return resultPoint;

  }

  @Override
  public PointPropertyType createPropertyType(Point point) {
    final PointPropertyType pointPropertyType = getObjectFactory().createPointPropertyType();

    pointPropertyType.setPoint(createGeometryType(point));
    return pointPropertyType;

  }

  @Override
  public JAXBElement<PointType> createElement(Point point) {
    return getObjectFactory().createPoint(createGeometryType(point));
  }

}
