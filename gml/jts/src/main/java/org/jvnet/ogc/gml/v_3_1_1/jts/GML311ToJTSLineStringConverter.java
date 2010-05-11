package org.jvnet.ogc.gml.v_3_1_1.jts;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import net.opengis.gml.v_3_1_1.CoordType;
import net.opengis.gml.v_3_1_1.DirectPositionType;
import net.opengis.gml.v_3_1_1.LineStringPropertyType;
import net.opengis.gml.v_3_1_1.LineStringType;
import net.opengis.gml.v_3_1_1.PointPropertyType;
import net.opengis.gml.v_3_1_1.PointType;

import org.jvnet.jaxb2_commons.locator.FieldObjectLocator;
import org.jvnet.jaxb2_commons.locator.ListEntryObjectLocator;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;

public class GML311ToJTSLineStringConverter
    extends
    AbstractGML311ToJTSConverter<LineStringType, LineStringPropertyType, LineString> {

  // + LineString

  private final GML311ToJTSCoordinateConverter coordinateConverter;
  private final GML311ToJTSPointConverter pointConverter;

  public GML311ToJTSLineStringConverter(GeometryFactory geometryFactory) {
    super(geometryFactory);
    coordinateConverter = new GML311ToJTSCoordinateConverter();
    pointConverter = new GML311ToJTSPointConverter(geometryFactory);
  }

  public GML311ToJTSLineStringConverter() {
    this(new GeometryFactory());
  }

  @Override
  public LineString createGeometry(ObjectLocator locator, LineStringType lineStringType)
      throws ConversionFailedException {
    if (lineStringType.isSetPosOrPointPropertyOrPointRep()) {

      final List<Coordinate> coordinates = new LinkedList<Coordinate>();
      final FieldObjectLocator fieldLocator = locator.field(
          "PosOrPointPropertyOrPointRep", lineStringType.getPosOrPointPropertyOrPointRep()); //$NON-NLS-1$
      for (int index = 0; index < lineStringType.getPosOrPointPropertyOrPointRep().size(); index++) {
        final JAXBElement<?> item = lineStringType.getPosOrPointPropertyOrPointRep().get(index);
        final ListEntryObjectLocator itemLocator = fieldLocator.entry(index, item);
        final Object value = item.getValue();
        final ObjectLocator itemValueLocator = itemLocator.field("value", value); //$NON-NLS-1$

        if (value instanceof PointType) {
          coordinates.add(pointConverter.createGeometry(

          itemValueLocator, (PointType) value).getCoordinate());
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
        else if (value instanceof DirectPositionType) {
          coordinates.add(coordinateConverter.createCoordinate(
              itemValueLocator,
              (DirectPositionType) value));
        }
        else {
          throw new ConversionFailedException(itemLocator, "Unexpected type."); //$NON-NLS-1$
        }
      }
      final Coordinate[] coordinatesArray = coordinates.toArray(new Coordinate[coordinates.size()]);
      return getGeometryFactory().createLineString(coordinatesArray);

    }
    else if (lineStringType.isSetPosList()) {

      final Coordinate[] coordinates = coordinateConverter.createCoordinates(locator.field(
          "posList",//$NON-NLS-1$
          lineStringType.getPosList()), lineStringType.getPosList());
      return getGeometryFactory().createLineString(coordinates);

    }
    else if (lineStringType.isSetCoordinates()) {
      final Coordinate[] coordinates = coordinateConverter.createCoordinates(locator.field(
          "coordinates", lineStringType.getCoordinates()), //$NON-NLS-1$
          lineStringType.getCoordinates());
      return getGeometryFactory().createLineString(coordinates);

    }
    else {
      throw new ConversionFailedException(locator);
    }
  }

  @Override
  public LineString createGeometry(
      ObjectLocator locator,
      LineStringPropertyType lineStringPropertyType) throws ConversionFailedException {
    if (lineStringPropertyType.isSetLineString()) {
      return createGeometry(
          locator.field("lineString", lineStringPropertyType.getLineString()), lineStringPropertyType.getLineString()); //$NON-NLS-1$
    }
    else {
      throw new ConversionFailedException(locator, "Expected [LineString] element."); //$NON-NLS-1$
    }
  }

}
