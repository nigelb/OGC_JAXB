package org.jvnet.ogc.gml.v_3_1_1.jts;

import com.vividsolutions.jts.geom.GeometryFactory;

public abstract class AbstractGML311ToJTSConverter {
  private final GeometryFactory geometryFactory;

  public AbstractGML311ToJTSConverter(GeometryFactory geometryFactory) {
    super();
    this.geometryFactory = geometryFactory;
  }

  public GeometryFactory getGeometryFactory() {
    return geometryFactory;
  }
}
