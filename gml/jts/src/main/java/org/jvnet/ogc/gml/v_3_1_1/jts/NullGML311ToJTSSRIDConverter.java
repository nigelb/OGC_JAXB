package org.jvnet.ogc.gml.v_3_1_1.jts;

import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import net.opengis.gml.v_3_1_1.SRSReferenceGroup;

import com.vividsolutions.jts.geom.Geometry;

public class NullGML311ToJTSSRIDConverter implements GML311ToJTSSRIDConverterInterface {

  @Override
  public void convert(ObjectLocator locator, SRSReferenceGroup source, Geometry target) {
  }

}
