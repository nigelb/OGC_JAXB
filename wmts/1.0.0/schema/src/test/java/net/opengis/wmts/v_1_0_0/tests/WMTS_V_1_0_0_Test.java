package net.opengis.wmts.v_1_0_0.tests;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import net.opengis.wmts.v_1_0_0.Capabilities;
import net.opengis.wmts.v_1_0_0.ObjectFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WMTS_V_1_0_0_Test {

	private JAXBContext context;

	@Before
	public void initializeContext() throws JAXBException {
		this.context = JAXBContext.newInstance(ObjectFactory.class.getPackage()
				.getName());
	}

	@Test
	public void unmarshalsGetCapabiltities() throws JAXBException {
		Unmarshaller unmarshaller = this.context.createUnmarshaller();

		JAXBElement<Capabilities> capablitiesElement = unmarshaller.unmarshal(
				new StreamSource(getClass().getResourceAsStream(
						"wmtsGetCapabilities_response.xml")),
				Capabilities.class);

		Capabilities capabilities = capablitiesElement.getValue();

		Assert.assertEquals("Web Map Tile Service", capabilities
				.getServiceIdentification().getTitle().get(0).getValue());

	}

}
