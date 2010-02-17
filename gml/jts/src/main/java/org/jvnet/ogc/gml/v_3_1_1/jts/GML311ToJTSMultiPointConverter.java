package org.jvnet.ogc.gml.v_3_1_1.jts;

import java.util.ArrayList;
import java.util.List;

import net.opengis.gml.v_3_1_1.MultiPointPropertyType;
import net.opengis.gml.v_3_1_1.MultiPointType;
import net.opengis.gml.v_3_1_1.PointPropertyType;
import net.opengis.gml.v_3_1_1.PointType;

import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.Point;

public class GML311ToJTSMultiPointConverter
    extends
    AbstractGML311ToJTSConverter<MultiPointType, MultiPointPropertyType, MultiPoint> {

  // + MultiPoint

  private final GML311ToJTSPointConverter pointConverter;

  public GML311ToJTSMultiPointConverter(GeometryFactory geometryFactory) {
    super(geometryFactory);
    pointConverter = new GML311ToJTSPointConverter(geometryFactory);
  }

  public GML311ToJTSMultiPointConverter() {
    this(new GeometryFactory());
  }

  public MultiPoint createGeometry(ObjectLocator locator, MultiPointType multiPointType)
      throws ConversionFailedException {
    if (multiPointType.isSetPointMember()) {
      final List<PointPropertyType> pointMembers = multiPointType.getPointMember();
      final List<Point> points = new ArrayList<Point>(pointMembers.size());
      for (int index = 0; index < pointMembers.size(); index++) {
        final PointPropertyType pointPropertyType = pointMembers.get(index);
        points.add(pointConverter.createGeometry(
            locator.field("PointMember").entry(index), pointPropertyType)); //$NON-NLS-1$
      }
      return getGeometryFactory().createMultiPoint(points.toArray(new Point[points.size()]));
    }
    else if (multiPointType.isSetPointMembers()) {
      final List<PointType> pointMembers = multiPointType.getPointMembers().getPoint();
      final List<Point> points = new ArrayList<Point>(pointMembers.size());
      for (int index = 0; index < pointMembers.size(); index++) {

        points.add(pointConverter.createGeometry(
            locator.field("PointMembers").entry(index), pointMembers.get(index))); //$NON-NLS-1$
      }
      return getGeometryFactory().createMultiPoint(points.toArray(new Point[points.size()]));
    }
    else {
      throw new ConversionFailedException(
          locator,
          "Either [PointMember] or [PointMembers] elements are expected."); //$NON-NLS-1$
    }
  }

  public MultiPoint createGeometry(
      ObjectLocator locator,
      MultiPointPropertyType multiPointPropertyType) throws ConversionFailedException {
    if (multiPointPropertyType.isSetMultiPoint()) {
      return createGeometry(locator.field("MultiPoint"), multiPointPropertyType.getMultiPoint()); //$NON-NLS-1$
    }
    else {
      throw new ConversionFailedException(locator, "Expected [MultiPoint] element."); //$NON-NLS-1$
    }
  }
}
