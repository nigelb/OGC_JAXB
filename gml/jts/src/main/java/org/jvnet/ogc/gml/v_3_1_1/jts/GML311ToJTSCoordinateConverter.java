package org.jvnet.ogc.gml.v_3_1_1.jts;

import java.util.List;

import net.opengis.gml.v_3_1_1.CoordType;
import net.opengis.gml.v_3_1_1.CoordinatesType;
import net.opengis.gml.v_3_1_1.DirectPositionListType;
import net.opengis.gml.v_3_1_1.DirectPositionType;

import org.apache.commons.lang.StringUtils;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import com.vividsolutions.jts.geom.Coordinate;

public class GML311ToJTSCoordinateConverter {
  public Coordinate createCoordinate(ObjectLocator locator, DirectPositionType directPositionType)
      throws ConversionFailedException {
    final List<Double> value = directPositionType.getValue();
    final int count = value.size();
    if (count == 2) {
      double x = value.get(0).doubleValue();
      double y = value.get(1).doubleValue();
      return new Coordinate(x, y);
    }
    else if (count == 3) {
      double x = value.get(0).doubleValue();
      double y = value.get(1).doubleValue();
      double z = value.get(2).doubleValue();
      return new Coordinate(x, y, z);

    }
    else {
      throw new ConversionFailedException(locator.field("Value"), //$NON-NLS-1$
          "Direct position type is expected to have 2 or 3 items."); //$NON-NLS-1$
    }

  }

  public Coordinate[] createCoordinates(
      ObjectLocator locator,
      DirectPositionListType directPositionListType) throws ConversionFailedException {
    final int dimensions = directPositionListType.isSetSrsDimension() ? directPositionListType
        .getSrsDimension()
        .intValue() : 2;

    if (dimensions < 2 || dimensions > 3) {
      throw new ConversionFailedException(locator.field("SrsDimension"), //$NON-NLS-1$
          "Only two- or three-dimensional coordinates are supported."); //$NON-NLS-1$
    }

    final List<Double> values = directPositionListType.getValue();
    if (values.size() % dimensions != 0) {
      throw new ConversionFailedException(locator.field("Value"), //$NON-NLS-1$
          "Wrong number of entries in the list."); //$NON-NLS-1$
    }

    final Coordinate[] coordinates = new Coordinate[values.size() / dimensions];
    for (int index = 0; index < values.size() / dimensions; index++) {
      if (dimensions == 2) {
        coordinates[index] = new Coordinate(values.get(index * dimensions), values.get(index
            * dimensions
            + 1));
      }
      else if (dimensions == 3) {
        coordinates[index] = new Coordinate(values.get(index * dimensions), values.get(index
            * dimensions
            + 1), values.get(index * dimensions + 2));
      }
    }
    return coordinates;

  }

  public Coordinate createCoordinate(ObjectLocator locator, CoordType coordType)
      throws ConversionFailedException {
    if (coordType.isSetX() && coordType.isSetY() && !coordType.isSetZ()) {
      return new Coordinate(coordType.getX().doubleValue(), coordType.getY().doubleValue());
    }
    else if (coordType.isSetX() && coordType.isSetY() && !coordType.isSetZ()) {
      return new Coordinate(
          coordType.getX().doubleValue(),
          coordType.getY().doubleValue(),
          coordType.getZ().doubleValue());

    }
    else {
      throw new ConversionFailedException(locator, "Either X, Y or X, Y, Z values are required."); //$NON-NLS-1$
    }

  }

  public Coordinate[] createCoordinates(ObjectLocator locator, final CoordinatesType coordinates)
      throws ConversionFailedException {
    final Coordinate[] coords = createCoordinates(locator, coordinates.getValue(), coordinates
        .getDecimal(), coordinates.getCs(), coordinates.getTs());
    return coords;
  }

  public Coordinate[] createCoordinates(
      ObjectLocator locator,
      String value,
      String ds,
      String cs,
      String ts) throws ConversionFailedException {
    final String coordinateSeparator = cs == null ? " " : cs; //$NON-NLS-1$

    final String[] coordinates = StringUtils.split(value, coordinateSeparator);
    final Coordinate[] coordinatesArray = new Coordinate[coordinates.length];
    for (int index = 0; index < coordinates.length; index++) {
      coordinatesArray[index] = createCoordinate(locator.entry(index), coordinates[index], ds, ts);
    }
    return coordinatesArray;
  }

  public Coordinate createCoordinate(ObjectLocator locator, String value, String ds, String ts)
      throws ConversionFailedException {
    final String tupleSeparator = ts == null ? "," : ts; //$NON-NLS-1$

    final String[] tuples = StringUtils.split(value, tupleSeparator);

    final double[] coordinateComponents = new double[tuples.length];
    for (int index = 0; index < tuples.length; index++) {
      coordinateComponents[index] = createCoordinateComponent(
          locator.entry(index),
          tuples[index],
          ds);
    }
    if (coordinateComponents.length == 2) {
      return new Coordinate(coordinateComponents[0], coordinateComponents[1]);
    }
    else if (coordinateComponents.length == 3) {
      return new Coordinate(
          coordinateComponents[0],
          coordinateComponents[1],
          coordinateComponents[2]);

    }
    else {
      throw new ConversionFailedException(locator, "Expected two or three coordinates."); //$NON-NLS-1$
    }
  }

  public double createCoordinateComponent(ObjectLocator locator, String value, String ds)
      throws ConversionFailedException {
    final String decimalSeparator = ds == null ? "." : ds; //$NON-NLS-1$
    try {
      return Double.parseDouble(value.replace(decimalSeparator, ".")); //$NON-NLS-1$
    }
    catch (NumberFormatException nfex) {
      throw new ConversionFailedException(locator, nfex);
    }
  }
}
