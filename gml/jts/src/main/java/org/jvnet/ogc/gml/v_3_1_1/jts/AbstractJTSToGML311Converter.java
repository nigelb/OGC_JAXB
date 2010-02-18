package org.jvnet.ogc.gml.v_3_1_1.jts;

import net.opengis.gml.v_3_1_1.ObjectFactory;

public abstract class AbstractJTSToGML311Converter {

  private final ObjectFactory objectFactory;

  public AbstractJTSToGML311Converter(ObjectFactory objectFactory) {
    this.objectFactory = objectFactory;
  }

  public ObjectFactory getObjectFactory() {
    return objectFactory;
  }
}
