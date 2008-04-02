package net.opengis.cat.csw.test;

import javax.xml.bind.JAXBContext;

import junit.framework.Assert;
import junit.framework.TestCase;
import net.opengis.cat.csw.Constants;

public class TestMultipleContexts extends TestCase {

	public void testMultipleContexts() throws Exception {
		final JAXBContext context200 = JAXBContext
				.newInstance(Constants.CSW_2_0_0_CONTEXT_PATH);
		final JAXBContext context201 = JAXBContext
				.newInstance(Constants.CSW_2_0_1_CONTEXT_PATH);
		final JAXBContext context202 = JAXBContext
				.newInstance(Constants.CSW_2_0_2_CONTEXT_PATH);

		Assert.assertNotNull(context200.createUnmarshaller().unmarshal(
				getClass().getClassLoader().getResource(
						"net/opengis/cat/csw/v_2_0_0/test/test0.xml")));
		Assert.assertNotNull(context202.createUnmarshaller().unmarshal(
				getClass().getClassLoader().getResource(
						"net/opengis/cat/csw/v_2_0_2/test/test0.xml")));
	}
}
