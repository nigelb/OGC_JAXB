package org.jvnet.ogc.gml.v_3_1_1.jts;

import net.opengis.gml.v_3_1_1.AbstractGeometryType;
import net.opengis.gml.v_3_1_1.ObjectFactory;

import com.vividsolutions.jts.geom.Geometry;

public abstract class AbstractJTSToGML311Converter<G extends AbstractGeometryType, P, J extends Geometry>
    implements
    JTSToGML311ConverterInterface<G, P, J> {

  private final ObjectFactory objectFactory;

  public AbstractJTSToGML311Converter(ObjectFactory objectFactory) {
    this.objectFactory = objectFactory;
  }

  public ObjectFactory getObjectFactory() {
    return objectFactory;
  }

}
