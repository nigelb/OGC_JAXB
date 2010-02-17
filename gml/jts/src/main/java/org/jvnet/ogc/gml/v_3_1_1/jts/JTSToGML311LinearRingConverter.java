package org.jvnet.ogc.gml.v_3_1_1.jts;

import javax.xml.bind.JAXBElement;

import net.opengis.gml.v_3_1_1.AbstractRingPropertyType;
import net.opengis.gml.v_3_1_1.DirectPositionType;
import net.opengis.gml.v_3_1_1.LinearRingType;
import net.opengis.gml.v_3_1_1.ObjectFactory;

import com.vividsolutions.jts.geom.LinearRing;

public class JTSToGML311LinearRingConverter extends AbstractJTSToGML311Converter {
  private final JTSToGML311CoordinateConverter coordinateConverter;

  public JTSToGML311LinearRingConverter(ObjectFactory objectFactory) {
    super(objectFactory);
    coordinateConverter = new JTSToGML311CoordinateConverter(objectFactory);
  }

  public JTSToGML311LinearRingConverter() {
    this(new ObjectFactory());
  }

  public LinearRingType createLinearRingType(LinearRing linearRing) {
    final LinearRingType resultLinearRing = getObjectFactory().createLinearRingType();

    for (DirectPositionType directPosition : coordinateConverter.convertCoordinates(linearRing
        .getCoordinates())) {
      final JAXBElement<DirectPositionType> pos = getObjectFactory().createPos(directPosition);
      resultLinearRing.getPosOrPointPropertyOrPointRep().add(pos);

    }
    return resultLinearRing;
  }

  public AbstractRingPropertyType createAbstractRingPropertyType(final LinearRing ring) {
    final AbstractRingPropertyType abstractRingProperty = getObjectFactory()
        .createAbstractRingPropertyType();

    abstractRingProperty.setRing(getObjectFactory().createLinearRing(createLinearRingType(ring)));
    return abstractRingProperty;
  }

  public JAXBElement<LinearRingType> createLinearRing(LinearRing linearRing) {
    return getObjectFactory().createLinearRing(createLinearRingType(linearRing));
  }
}
