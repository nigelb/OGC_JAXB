package org.jvnet.ogc.gml.v_3_1_1.jts;

import javax.xml.bind.JAXBElement;

import net.opengis.gml.v_3_1_1.MultiLineStringPropertyType;
import net.opengis.gml.v_3_1_1.MultiLineStringType;
import net.opengis.gml.v_3_1_1.ObjectFactory;

import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiLineString;

public class JTSToGML311MultiLineStringConverter extends AbstractJTSToGML311Converter {
  private final JTSToGML311LineStringConverter lineStringConverter;

  public JTSToGML311MultiLineStringConverter(ObjectFactory objectFactory) {
    super(objectFactory);
    lineStringConverter = new JTSToGML311LineStringConverter(objectFactory);
  }

  public JTSToGML311MultiLineStringConverter() {
    this(new ObjectFactory());
  }

  public MultiLineStringType createMultiLineStringType(MultiLineString multiLineString) {
    final MultiLineStringType multiLineStringType = getObjectFactory().createMultiLineStringType();
    for (int index = 0; index < multiLineString.getNumGeometries(); index++) {
      final LineString lineString = (LineString) multiLineString.getGeometryN(index);
      multiLineStringType.getLineStringMember().add(
          lineStringConverter.createLineStringPropertyType(lineString));
    }

    return multiLineStringType;
  }

  public MultiLineStringPropertyType createMultiLineStringPropertyType(
      MultiLineString multiLineString) {
    final MultiLineStringPropertyType multiLineStringPropertyType = getObjectFactory()
        .createMultiLineStringPropertyType();
    multiLineStringPropertyType.setMultiLineString(createMultiLineStringType(multiLineString));
    return multiLineStringPropertyType;
  }

  public JAXBElement<MultiLineStringType> createMultiLineString(MultiLineString multiLineString) {
    return getObjectFactory().createMultiLineString(createMultiLineStringType(multiLineString));
  }
}
