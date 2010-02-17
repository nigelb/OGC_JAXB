package org.jvnet.ogc.gml.v_3_1_1.jts;

import net.opengis.gml.v_3_1_1.PointPropertyType;
import net.opengis.gml.v_3_1_1.PointType;

import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

public class GML311ToJTSPointConverter
    extends
    AbstractGML311ToJTSConverter<PointType, PointPropertyType, Point> {

  // + Point

  private final GML311ToJTSCoordinateConverter coordinateConverter;

  public GML311ToJTSPointConverter(GeometryFactory geometryFactory) {
    super(geometryFactory);
    coordinateConverter = new GML311ToJTSCoordinateConverter();
  }

  public GML311ToJTSPointConverter() {
    this(new GeometryFactory());
  }

  @Override
  public Point createGeometry(ObjectLocator locator, PointType pointType)
      throws ConversionFailedException {

    if (pointType.isSetPos()) {
      return getGeometryFactory().createPoint(
          coordinateConverter.createCoordinate(locator.field("Pos"), pointType.getPos())); //$NON-NLS-1$
    }
    else if (pointType.isSetCoordinates()) {
      final Coordinate[] coords = coordinateConverter.createCoordinates(locator
          .field("Coordinates"), pointType.getCoordinates()); //$NON-NLS-1$
      if (coords.length == 1) {
        throw new ConversionFailedException(locator.field("Coordinates"), //$NON-NLS-1$
            "Expected exactly one coordinate."); //$NON-NLS-1$
      }
      else {
        return getGeometryFactory().createPoint(coords[0]);

      }

    }
    else if (pointType.isSetCoord()) {
      return getGeometryFactory().createPoint(
          coordinateConverter.createCoordinate(locator.field("Coord"), pointType //$NON-NLS-1$
              .getCoord()));
    }
    else {
      throw new ConversionFailedException(
          locator,
          "Either [pos], [coordinates] or [coord] elements are expected."); //$NON-NLS-1$
    }

  }

  @Override
  public Point createGeometry(ObjectLocator locator, PointPropertyType pointPropertyType)
      throws ConversionFailedException {
    if (pointPropertyType.isSetPoint()) {
      return createGeometry(locator.field("Point"), pointPropertyType.getPoint()); //$NON-NLS-1$
    }
    else {
      throw new ConversionFailedException(locator, "Expected [Point] element."); //$NON-NLS-1$
    }
  }
}
