package org.jvnet.ogc.gml.v_3_1_1.jts;

import javax.xml.bind.JAXBElement;

import net.opengis.gml.v_3_1_1.DirectPositionType;
import net.opengis.gml.v_3_1_1.LineStringPropertyType;
import net.opengis.gml.v_3_1_1.LineStringType;
import net.opengis.gml.v_3_1_1.ObjectFactory;

import com.vividsolutions.jts.geom.LineString;

public class JTSToGML311LineStringConverter
    extends
    AbstractJTSToGML311Converter<LineStringType, LineStringPropertyType, LineString> {
  private final JTSToGML311CoordinateConverter coordinateConverter;

  public JTSToGML311LineStringConverter(ObjectFactory objectFactory) {
    super(objectFactory);
    coordinateConverter = new JTSToGML311CoordinateConverter(objectFactory);
  }

  public JTSToGML311LineStringConverter() {
    this(new ObjectFactory());
  }

  @Override
  public LineStringType createGeometryType(LineString lineString) {

    final LineStringType resultLineString = getObjectFactory().createLineStringType();

    for (DirectPositionType directPosition : coordinateConverter.convertCoordinates(lineString
        .getCoordinates())) {
      final JAXBElement<DirectPositionType> pos = getObjectFactory().createPos(directPosition);
      resultLineString.getPosOrPointPropertyOrPointRep().add(pos);

    }
    return resultLineString;
  }

  @Override
  public LineStringPropertyType createPropertyType(LineString lineString) {
    final LineStringPropertyType lineStringPropertyType = getObjectFactory()
        .createLineStringPropertyType();
    lineStringPropertyType.setLineString(createGeometryType(lineString));
    return lineStringPropertyType;
  }

  @Override
  public JAXBElement<LineStringType> createElement(LineString linearString) {
    return getObjectFactory().createLineString(createGeometryType(linearString));
  }
}
