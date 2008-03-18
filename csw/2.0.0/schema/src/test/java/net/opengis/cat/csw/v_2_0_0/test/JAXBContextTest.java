package net.opengis.cat.csw.v_2_0_0.test;

import java.net.URL;

import javax.xml.bind.JAXBContext;

import junit.framework.Assert;
import junit.framework.TestCase;

public class JAXBContextTest extends TestCase {

	public void testContext() throws Exception {

		final JAXBContext jaxbContext2 = JAXBContext
				.newInstance("net.opengis.gml.v_2_1_2:org.purl.dc.elements.v_1_1:org.purl.dc.terms:net.opengis.filter.v_1_0_20:net.opengis.cat.csw.v_2_0_0:net.opengis.ows.v_0_3_0");

		final URL resource = getClass().getResource("test0.xml");

		final Object result = jaxbContext2.createUnmarshaller().unmarshal(
				resource);

		Assert.assertNotNull(result);

	}

}
