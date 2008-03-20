package net.opengis.sld.v_1_1_0;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import junit.framework.Assert;
import junit.framework.TestCase;

public class JAXBContextTest extends TestCase {

	public void testContext() throws Exception {

		final JAXBContext context = JAXBContext
				.newInstance("net.opengis.gml.v_3_1_1:"
						+ "net.opengis.ows.v_1_0_0:"
						+ "net.opengis.filter.v_1_1_0:"
						+ "net.opengis.wfs.v_1_1_0:"
						+ "net.opengis.se.v_1_1_0:"
						+ "net.opengis.wms.v_1_3_0:"
						+ "net.opengis.sld.v_1_1_0");

		Assert.assertNotNull(unmarshalResource(context,
				"example_capabilities.xml"));
		Assert.assertNotNull(unmarshalResource(context,
				"example_describelayer.xml"));
		Assert.assertNotNull(unmarshalResource(context, "example_getmap.xml"));
		Assert.assertNotNull(unmarshalResource(context, "example-sld.xml"));

	}

	private Object unmarshalResource(final JAXBContext context,
			final String resourceName) throws JAXBException {
		return context.createUnmarshaller().unmarshal(
				getClass().getResource(resourceName));
	}

}
