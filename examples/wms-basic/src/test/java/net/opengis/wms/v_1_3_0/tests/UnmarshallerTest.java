package net.opengis.wms.v_1_3_0.tests;

import java.net.URL;
import java.util.Collections;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import junit.framework.Assert;
import junit.framework.TestCase;
import net.opengis.wms.v_1_3_0.WMSCapabilities;

import com.sun.xml.bind.api.JAXBRIContext;

public class UnmarshallerTest extends TestCase {

	public void testUnmarshalWMSCapabilities0() throws Exception {

		final JAXBContext context = JAXBContext
				.newInstance("net.opengis.wms.v_1_3_0");

		final Unmarshaller unmarshaller = context.createUnmarshaller();

		final URL resourceURL = getClass().getResource(
				"WMS_Capabilities[0].xml");

		Assert.assertNotNull(resourceURL);

		final JAXBElement<WMSCapabilities> wmsCapabilitiesElement = unmarshaller
				.unmarshal(new StreamSource(resourceURL.toString()),
						WMSCapabilities.class);

		final WMSCapabilities wmsCapabilities = wmsCapabilitiesElement
				.getValue();

		Assert.assertEquals(4, wmsCapabilities.getCapability().getLayer()
				.getLayer().size());

	}

	public void testUnmarshalAndValidateWMSCapabilities0() throws Exception {

		final JAXBContext context = JAXBContext
				.newInstance("net.opengis.wms.v_1_3_0");

		final Unmarshaller unmarshaller = context.createUnmarshaller();

		final SchemaFactory schemaFactory = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

		final URL schemaURL = getClass().getClassLoader().getResource(
				"wms/1.3.0/capabilities_1_3_0.xsd");

		final Schema schema = schemaFactory.newSchema(schemaURL);

		unmarshaller.setSchema(schema);

		final URL resourceURL = getClass().getResource(
				"WMS_Capabilities[0].xml");

		Assert.assertNotNull(resourceURL);

		final JAXBElement<WMSCapabilities> wmsCapabilitiesElement = unmarshaller
				.unmarshal(new StreamSource(resourceURL.toString()),
						WMSCapabilities.class);

		final WMSCapabilities wmsCapabilities = wmsCapabilitiesElement
				.getValue();

		Assert.assertEquals(4, wmsCapabilities.getCapability().getLayer()
				.getLayer().size());

	}

}
