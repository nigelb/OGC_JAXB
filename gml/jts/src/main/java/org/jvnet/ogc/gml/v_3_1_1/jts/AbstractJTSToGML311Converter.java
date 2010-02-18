package org.jvnet.ogc.gml.v_3_1_1.jts;

import javax.xml.bind.JAXBElement;

import net.opengis.gml.v_3_1_1.AbstractGeometryType;
import net.opengis.gml.v_3_1_1.ObjectFactory;

import com.vividsolutions.jts.geom.Geometry;

public abstract class AbstractJTSToGML311Converter<G extends AbstractGeometryType, P, J extends Geometry> {

  private final ObjectFactory objectFactory;

  public AbstractJTSToGML311Converter(ObjectFactory objectFactory) {
    this.objectFactory = objectFactory;
  }

  public ObjectFactory getObjectFactory() {
    return objectFactory;
  }

  public abstract G createGeometryType(J geometry);

  public abstract P createPropertyType(J geometry);

  public abstract JAXBElement<? extends G> createElement(J geometry);
}
