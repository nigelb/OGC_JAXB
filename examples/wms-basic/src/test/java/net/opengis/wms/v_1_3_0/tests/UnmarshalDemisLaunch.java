package net.opengis.wms.v_1_3_0.tests;

import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import junit.framework.TestCase;
import net.opengis.wms.v_1_3_0.Layer;
import net.opengis.wms.v_1_3_0.WMSCapabilities;

public class UnmarshalDemisLaunch extends TestCase {

	public void testUnmarshalDemis() throws Exception {

		// Create JAXB context for WMS 1.3.0
		JAXBContext context = JAXBContext
				.newInstance("net.opengis.wms.v_1_3_0");

		// Use the created JAXB context to construct an unmarshaller
		Unmarshaller unmarshaller = context.createUnmarshaller();

		// GetCapabilities URL of the Demis WorldMap WMS Server
		String url = "http://www2.demis.nl/wms/wms.asp?REQUEST=GetCapabilities&VERSION=1.3.0&wms=WorldMap";

		// Unmarshal the given URL, retrieve WMSCapabilities element
		JAXBElement<WMSCapabilities> wmsCapabilitiesElement = unmarshaller
				.unmarshal(new StreamSource(url), WMSCapabilities.class);

		// Retrieve WMSCapabilities instance
		WMSCapabilities wmsCapabilities = wmsCapabilitiesElement.getValue();

		// Iterate over layers, print out layer names
		for (Layer layer : wmsCapabilities.getCapability().getLayer()
				.getLayer()) {
			System.out.println(layer.getName());
		}

	}

	public void testUnmarshalAndValidateDemis() throws Exception {

		// Create JAXB context for WMS 1.3.0
		JAXBContext context = JAXBContext
				.newInstance("net.opengis.wms.v_1_3_0");

		// Use the created JAXB context to construct an unmarshaller
		Unmarshaller unmarshaller = context.createUnmarshaller();

		// Create an XML schema factory
		SchemaFactory schemaFactory = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

		// Get the WMS 1.3.0 classpath resource URL
		URL schemaURL = getClass().getClassLoader().getResource(
				"wms/1.3.0/capabilities_1_3_0.xsd");

		// Create an XML schema instance
		Schema schema = schemaFactory.newSchema(schemaURL);

		// Use the created XML schema instance for validation during
		// unmarshalling
		unmarshaller.setSchema(schema);

		// GetCapabilities URL of the Demis WorldMap WMS Server
		String url = "http://www2.demis.nl/wms/wms.asp?REQUEST=GetCapabilities&VERSION=1.3.0&wms=WorldMap";

		// Unmarshal the given URL, retrieve WMSCapabilities element
		JAXBElement<WMSCapabilities> wmsCapabilitiesElement = unmarshaller
				.unmarshal(new StreamSource(url), WMSCapabilities.class);

		// Retrieve WMSCapabilities instance
		WMSCapabilities wmsCapabilities = wmsCapabilitiesElement.getValue();

		// Iterate over layers, print out layer names
		for (Layer layer : wmsCapabilities.getCapability().getLayer()
				.getLayer()) {
			System.out.println(layer.getName());
		}

	}
}
