package org.jvnet.ogc.gml.v_3_1_1.jts;

import javax.xml.bind.JAXBElement;

import net.opengis.gml.v_3_1_1.AbstractRingPropertyType;
import net.opengis.gml.v_3_1_1.DirectPositionType;
import net.opengis.gml.v_3_1_1.LinearRingType;
import net.opengis.gml.v_3_1_1.ObjectFactory;

import com.vividsolutions.jts.geom.LinearRing;

public class JTSToGML311LinearRingConverter
    extends
    AbstractJTSToGML311Converter<LinearRingType, AbstractRingPropertyType, LinearRing> {
  private final JTSToGML311CoordinateConverter coordinateConverter;

  public JTSToGML311LinearRingConverter(ObjectFactory objectFactory) {
    super(objectFactory);
    coordinateConverter = new JTSToGML311CoordinateConverter(objectFactory);
  }

  public JTSToGML311LinearRingConverter() {
    this(new ObjectFactory());
  }

  public LinearRingType createGeometryType(LinearRing linearRing) {
    final LinearRingType resultLinearRing = getObjectFactory().createLinearRingType();

    for (DirectPositionType directPosition : coordinateConverter.convertCoordinates(linearRing
        .getCoordinates())) {
      final JAXBElement<DirectPositionType> pos = getObjectFactory().createPos(directPosition);
      resultLinearRing.getPosOrPointPropertyOrPointRep().add(pos);

    }
    return resultLinearRing;
  }

  public AbstractRingPropertyType createPropertyType(final LinearRing ring) {
    final AbstractRingPropertyType abstractRingProperty = getObjectFactory()
        .createAbstractRingPropertyType();

    abstractRingProperty.setRing(getObjectFactory().createLinearRing(createGeometryType(ring)));
    return abstractRingProperty;
  }

  public JAXBElement<LinearRingType> createElement(LinearRing linearRing) {
    return getObjectFactory().createLinearRing(createGeometryType(linearRing));
  }
}
