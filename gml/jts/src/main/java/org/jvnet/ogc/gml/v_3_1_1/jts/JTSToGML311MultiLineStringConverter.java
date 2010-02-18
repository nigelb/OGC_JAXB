package org.jvnet.ogc.gml.v_3_1_1.jts;

import javax.xml.bind.JAXBElement;

import net.opengis.gml.v_3_1_1.MultiLineStringPropertyType;
import net.opengis.gml.v_3_1_1.MultiLineStringType;
import net.opengis.gml.v_3_1_1.ObjectFactory;

import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiLineString;

public class JTSToGML311MultiLineStringConverter
    extends
    AbstractJTSToGML311Converter<MultiLineStringType, MultiLineStringPropertyType, MultiLineString> {
  private final JTSToGML311LineStringConverter lineStringConverter;

  public JTSToGML311MultiLineStringConverter(ObjectFactory objectFactory) {
    super(objectFactory);
    lineStringConverter = new JTSToGML311LineStringConverter(objectFactory);
  }

  public JTSToGML311MultiLineStringConverter() {
    this(new ObjectFactory());
  }

  public MultiLineStringType createGeometryType(MultiLineString multiLineString) {
    final MultiLineStringType multiLineStringType = getObjectFactory().createMultiLineStringType();
    for (int index = 0; index < multiLineString.getNumGeometries(); index++) {
      final LineString lineString = (LineString) multiLineString.getGeometryN(index);
      multiLineStringType.getLineStringMember().add(
          lineStringConverter.createPropertyType(lineString));
    }

    return multiLineStringType;
  }

  public MultiLineStringPropertyType createPropertyType(
      MultiLineString multiLineString) {
    final MultiLineStringPropertyType multiLineStringPropertyType = getObjectFactory()
        .createMultiLineStringPropertyType();
    multiLineStringPropertyType.setMultiLineString(createGeometryType(multiLineString));
    return multiLineStringPropertyType;
  }

  public JAXBElement<MultiLineStringType> createElement(MultiLineString multiLineString) {
    return getObjectFactory().createMultiLineString(createGeometryType(multiLineString));
  }
}
