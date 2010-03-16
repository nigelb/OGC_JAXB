package org.jvnet.ogc.gml.v_3_1_1.jts;

import net.opengis.gml.v_3_1_1.SRSReferenceGroup;

import com.vividsolutions.jts.geom.Geometry;

public interface GML311ToJTSSRIDConverterInterface {

  public void convert(SRSReferenceGroup source, Geometry target);
}
