package org.jvnet.ogc.gml.v_3_1_1.jts;

import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import net.opengis.gml.v_3_1_1.SRSReferenceGroup;

import com.vividsolutions.jts.geom.Geometry;

public interface GML311ToJTSSRIDConverterInterface {

  public void convert(ObjectLocator locator, SRSReferenceGroup source, Geometry target)
      throws ConversionFailedException;
}
