package org.jvnet.ogc.gml.v_3_1_1.jts;

import net.opengis.gml.v_3_1_1.SRSReferenceGroup;

import com.vividsolutions.jts.geom.Geometry;

public interface JTSToGML311SRSReferenceGroupConverterInterface {

  public void convert(Geometry source, SRSReferenceGroup target);
}
