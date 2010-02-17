package org.jvnet.ogc.gml.v_3_1_1.jts;

import javax.xml.bind.JAXBElement;

import net.opengis.gml.v_3_1_1.DirectPositionType;
import net.opengis.gml.v_3_1_1.LineStringPropertyType;
import net.opengis.gml.v_3_1_1.LineStringType;
import net.opengis.gml.v_3_1_1.ObjectFactory;

import com.vividsolutions.jts.geom.LineString;

public class JTSToGML311LineStringConverter extends AbstractJTSToGML311Converter {
  private final JTSToGML311CoordinateConverter coordinateConverter;

  public JTSToGML311LineStringConverter(ObjectFactory objectFactory) {
    super(objectFactory);
    coordinateConverter = new JTSToGML311CoordinateConverter(objectFactory);
  }

  public JTSToGML311LineStringConverter() {
    this(new ObjectFactory());
  }

  public LineStringType createLineStringType(LineString lineString) {

    final LineStringType resultLineString = getObjectFactory().createLineStringType();

    for (DirectPositionType directPosition : coordinateConverter.convertCoordinates(lineString
        .getCoordinates())) {
      final JAXBElement<DirectPositionType> pos = getObjectFactory().createPos(directPosition);
      resultLineString.getPosOrPointPropertyOrPointRep().add(pos);

    }
    return resultLineString;
  }

  public LineStringPropertyType createLineStringPropertyType(LineString lineString) {
    final LineStringPropertyType lineStringPropertyType = getObjectFactory()
        .createLineStringPropertyType();
    lineStringPropertyType.setLineString(createLineStringType(lineString));
    return lineStringPropertyType;
  }

  public JAXBElement<LineStringType> createLineString(LineString linearString) {
    return getObjectFactory().createLineString(createLineStringType(linearString));
  }
}
