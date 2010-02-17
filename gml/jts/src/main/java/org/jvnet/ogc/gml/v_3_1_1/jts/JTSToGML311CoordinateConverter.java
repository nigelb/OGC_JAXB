package org.jvnet.ogc.gml.v_3_1_1.jts;

import net.opengis.gml.v_3_1_1.DirectPositionType;
import net.opengis.gml.v_3_1_1.ObjectFactory;

import com.vividsolutions.jts.geom.Coordinate;

public class JTSToGML311CoordinateConverter {
  private final ObjectFactory objectFactory;

  public JTSToGML311CoordinateConverter(ObjectFactory objectFactory) {
    super();
    this.objectFactory = objectFactory;
  }

  public JTSToGML311CoordinateConverter() {
    this(new ObjectFactory());
  }

  public DirectPositionType convertCoordinate(Coordinate coordinate) {
    final DirectPositionType directPosition = objectFactory.createDirectPositionType();

    directPosition.getValue().add(coordinate.x);
    directPosition.getValue().add(coordinate.y);
    if (!Double.isNaN(coordinate.z)) {
      directPosition.getValue().add(coordinate.z);
    }
    return directPosition;

  }

  public DirectPositionType[] convertCoordinates(Coordinate[] coordinates) {
    if (coordinates == null) {
      return null;
    }
    else {
      final DirectPositionType[] directPositions = new DirectPositionType[coordinates.length];
      for (int index = 0; index < coordinates.length; index++) {

        directPositions[index] = convertCoordinate(coordinates[index]);
      }
      return directPositions;
    }
  }
}
