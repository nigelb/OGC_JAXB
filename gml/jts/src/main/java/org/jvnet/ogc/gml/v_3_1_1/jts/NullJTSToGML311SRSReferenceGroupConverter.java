package org.jvnet.ogc.gml.v_3_1_1.jts;

import net.opengis.gml.v_3_1_1.SRSReferenceGroup;

import com.vividsolutions.jts.geom.Geometry;

public class NullJTSToGML311SRSReferenceGroupConverter implements JTSToGML311SRSReferenceGroupConverterInterface {

  @Override
  public void convert(Geometry source, SRSReferenceGroup target) {
  }

}
