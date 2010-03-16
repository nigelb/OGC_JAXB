package org.jvnet.ogc.gml.v_3_1_1.jts;

import net.opengis.gml.v_3_1_1.SRSReferenceGroup;

import com.vividsolutions.jts.geom.Geometry;

public class NullGML311ToJTSSRIDConverter implements GML311ToJTSSRIDConverterInterface {

  @Override
  public void convert(SRSReferenceGroup source, Geometry target) {
  }

}
