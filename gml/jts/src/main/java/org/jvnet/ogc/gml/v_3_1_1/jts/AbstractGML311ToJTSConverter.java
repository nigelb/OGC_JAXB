package org.jvnet.ogc.gml.v_3_1_1.jts;

import net.opengis.gml.v_3_1_1.AbstractGeometryType;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;

public abstract class AbstractGML311ToJTSConverter<G extends AbstractGeometryType, P, J extends Geometry>
    implements
    GML311ToJTSConverterInterface<G, P, J> {
  private final GeometryFactory geometryFactory;

  public AbstractGML311ToJTSConverter(GeometryFactory geometryFactory) {
    super();
    this.geometryFactory = geometryFactory;
  }

  public GeometryFactory getGeometryFactory() {
    return geometryFactory;
  }
}
