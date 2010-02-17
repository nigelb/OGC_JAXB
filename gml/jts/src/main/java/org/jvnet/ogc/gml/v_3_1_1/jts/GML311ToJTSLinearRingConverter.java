package org.jvnet.ogc.gml.v_3_1_1.jts;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import net.opengis.gml.v_3_1_1.CoordType;
import net.opengis.gml.v_3_1_1.DirectPositionType;
import net.opengis.gml.v_3_1_1.LinearRingPropertyType;
import net.opengis.gml.v_3_1_1.LinearRingType;
import net.opengis.gml.v_3_1_1.PointPropertyType;
import net.opengis.gml.v_3_1_1.PointType;

import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;

public class GML311ToJTSLinearRingConverter
    extends
    AbstractGML311ToJTSConverter<LinearRingType, LinearRingPropertyType, LinearRing> {

  // + LinearRing

  private final GML311ToJTSCoordinateConverter coordinateConverter;
  private final GML311ToJTSPointConverter pointConverter;

  public GML311ToJTSLinearRingConverter(GeometryFactory geometryFactory) {
    super(geometryFactory);
    coordinateConverter = new GML311ToJTSCoordinateConverter();
    pointConverter = new GML311ToJTSPointConverter(geometryFactory);
  }

  public GML311ToJTSLinearRingConverter() {
    this(new GeometryFactory());
  }

  public LinearRing createGeometry(ObjectLocator locator, LinearRingType linearRingType)
      throws ConversionFailedException {
    if (linearRingType.isSetPosOrPointPropertyOrPointRep()) {
      final ObjectLocator fieldLocator = locator.field("PosOrPointPropertyOrPointRep"); //$NON-NLS-1$

      final List<Coordinate> coordinates = new LinkedList<Coordinate>();
      for (int index = 0; index < linearRingType.getPosOrPointPropertyOrPointRep().size(); index++) {
        final JAXBElement<?> item = linearRingType.getPosOrPointPropertyOrPointRep().get(index);
        final ObjectLocator itemLocator = fieldLocator.entry(index);
        final Object value = item.getValue();
        final ObjectLocator itemValueLocator = itemLocator.field("Value"); //$NON-NLS-1$

        if (value instanceof DirectPositionType) {
          coordinates.add(coordinateConverter.createCoordinate(
              itemValueLocator,
              (DirectPositionType) value));
        }
        else if (value instanceof PointType) {
          coordinates.add(pointConverter
              .createGeometry(itemValueLocator, (PointType) value)
              .getCoordinate());
        }
        else if (value instanceof PointPropertyType) {
          coordinates.add(pointConverter
              .createGeometry(itemValueLocator, (PointPropertyType) value)
              .getCoordinate());
        }
        else if (value instanceof CoordType) {
          coordinates
              .add(coordinateConverter.createCoordinate(itemValueLocator, (CoordType) value));
        }
        else {
          throw new ConversionFailedException(itemValueLocator, "Unexpected type."); //$NON-NLS-1$
        }
      }
      final Coordinate[] coordinatesArray = coordinates.toArray(new Coordinate[coordinates.size()]);
      return getGeometryFactory().createLinearRing(coordinatesArray);

    }
    else if (linearRingType.isSetPosList()) {

      final Coordinate[] coordinates = coordinateConverter.createCoordinates(locator
          .field("PosList"), linearRingType //$NON-NLS-1$
          .getPosList());
      return getGeometryFactory().createLinearRing(coordinates);

    }
    else if (linearRingType.isSetCoordinates()) {
      final Coordinate[] coordinates = coordinateConverter.createCoordinates(locator
          .field("Coordinates"), //$NON-NLS-1$
          linearRingType.getCoordinates());
      return getGeometryFactory().createLinearRing(coordinates);

    }
    else {
      throw new ConversionFailedException(locator);
    }
  }

  public LinearRing createGeometry(
      ObjectLocator locator,
      LinearRingPropertyType linearRingPropertyType) throws ConversionFailedException {
    if (linearRingPropertyType.isSetLinearRing()) {
      return createGeometry(locator.field("LinearRing"), linearRingPropertyType.getLinearRing()); //$NON-NLS-1$
    }
    else {
      throw new ConversionFailedException(locator, "Expected [LinearRing] element."); //$NON-NLS-1$
    }
  }
}
