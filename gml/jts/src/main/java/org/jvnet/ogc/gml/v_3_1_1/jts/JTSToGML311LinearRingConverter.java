package org.jvnet.ogc.gml.v_3_1_1.jts;

import javax.xml.bind.JAXBElement;

import net.opengis.gml.v_3_1_1.AbstractRingPropertyType;
import net.opengis.gml.v_3_1_1.DirectPositionType;
import net.opengis.gml.v_3_1_1.LinearRingType;

import com.vividsolutions.jts.geom.LinearRing;

public class JTSToGML311LinearRingConverter
    extends
    AbstractJTSToGML311Converter<LinearRingType, AbstractRingPropertyType, LinearRing> {
  private final JTSToGML311CoordinateConverter coordinateConverter;

  public JTSToGML311LinearRingConverter(JTSToGML311CoordinateConverter coordinateConverter) {
    super(coordinateConverter.getObjectFactory(), coordinateConverter
        .getSrsReferenceGroupConverter());
    this.coordinateConverter = coordinateConverter;
  }

  public JTSToGML311LinearRingConverter() {
    this(new JTSToGML311CoordinateConverter(
        JTSToGML311Constants.DEFAULT_OBJECT_FACTORY,
        JTSToGML311Constants.DEFAULT_SRS_REFERENCE_GROUP_CONVERTER));
  }

  @Override
  protected LinearRingType doCreateGeometryType(LinearRing linearRing) {
    final LinearRingType resultLinearRing = getObjectFactory().createLinearRingType();

    for (DirectPositionType directPosition : coordinateConverter.convertCoordinates(linearRing
        .getCoordinates())) {
      final JAXBElement<DirectPositionType> pos = getObjectFactory().createPos(directPosition);
      resultLinearRing.getPosOrPointPropertyOrPointRep().add(pos);

    }
    return resultLinearRing;
  }

  @Override
  public AbstractRingPropertyType createPropertyType(final LinearRing ring) {
    final AbstractRingPropertyType abstractRingProperty = getObjectFactory()
        .createAbstractRingPropertyType();

    abstractRingProperty.setRing(getObjectFactory().createLinearRing(createGeometryType(ring)));
    return abstractRingProperty;
  }

  @Override
  public JAXBElement<LinearRingType> createElement(LinearRing linearRing) {
    return getObjectFactory().createLinearRing(createGeometryType(linearRing));
  }
}
