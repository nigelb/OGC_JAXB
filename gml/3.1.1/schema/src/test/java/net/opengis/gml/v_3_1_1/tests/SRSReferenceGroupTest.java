package net.opengis.gml.v_3_1_1.tests;

import junit.framework.Assert;
import junit.framework.TestCase;
import net.opengis.gml.v_3_1_1.AbstractGeometryType;
import net.opengis.gml.v_3_1_1.SRSInformationGroup;
import net.opengis.gml.v_3_1_1.SRSReferenceGroup;

public class SRSReferenceGroupTest extends TestCase {

  public void testAbstractGeometryTypeIsSRSReferenceGroup() {
    Assert.assertTrue(SRSReferenceGroup.class.isAssignableFrom(AbstractGeometryType.class));
    Assert.assertTrue(SRSInformationGroup.class.isAssignableFrom(AbstractGeometryType.class));
  }
}
