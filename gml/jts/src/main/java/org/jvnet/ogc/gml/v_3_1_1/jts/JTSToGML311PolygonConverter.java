package org.jvnet.ogc.gml.v_3_1_1.jts;

import javax.xml.bind.JAXBElement;

import net.opengis.gml.v_3_1_1.AbstractRingPropertyType;
import net.opengis.gml.v_3_1_1.ObjectFactory;
import net.opengis.gml.v_3_1_1.PolygonPropertyType;
import net.opengis.gml.v_3_1_1.PolygonType;

import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Polygon;

public class JTSToGML311PolygonConverter extends AbstractJTSToGML311Converter {
  private final JTSToGML311LinearRingConverter linearRingConverter;

  public JTSToGML311PolygonConverter(ObjectFactory objectFactory) {
    super(objectFactory);
    linearRingConverter = new JTSToGML311LinearRingConverter(objectFactory);
  }

  public JTSToGML311PolygonConverter() {
    this(new ObjectFactory());
  }

  public PolygonType createPolygonType(Polygon polygon) {
    final PolygonType resultPolygon = getObjectFactory().createPolygonType();
    {
      final LinearRing exteriorRing = (LinearRing) polygon.getExteriorRing();
      final AbstractRingPropertyType abstractRingProperty = linearRingConverter
          .createAbstractRingPropertyType(exteriorRing);
      resultPolygon.setExterior(getObjectFactory().createExterior(abstractRingProperty));
    }
    for (int index = 0; index < polygon.getNumInteriorRing(); index++) {
      final LinearRing interiorRing = (LinearRing) polygon.getInteriorRingN(index);
      resultPolygon.getInterior().add(
          getObjectFactory().createInterior(
              linearRingConverter.createAbstractRingPropertyType(interiorRing)));
    }
    return resultPolygon;

  }

  public PolygonPropertyType createPolygonPropertyType(Polygon polygon) {
    final PolygonPropertyType polygonPropertyType = getObjectFactory().createPolygonPropertyType();
    polygonPropertyType.setPolygon(createPolygonType(polygon));
    return polygonPropertyType;
  }

  public JAXBElement<PolygonType> createPolygon(Polygon polygon) {
    return getObjectFactory().createPolygon(createPolygonType(polygon));
  }
}
