package net.opengis.wms.v_1_3_0.tests;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import junit.framework.TestCase;
import net.opengis.wms.v_1_3_0.Capability;
import net.opengis.wms.v_1_3_0.DCPType;
import net.opengis.wms.v_1_3_0.Get;
import net.opengis.wms.v_1_3_0.HTTP;
import net.opengis.wms.v_1_3_0.Layer;
import net.opengis.wms.v_1_3_0.ObjectFactory;
import net.opengis.wms.v_1_3_0.OnlineResource;
import net.opengis.wms.v_1_3_0.OperationType;
import net.opengis.wms.v_1_3_0.Request;
import net.opengis.wms.v_1_3_0.Service;
import net.opengis.wms.v_1_3_0.WMSCapabilities;

public class MarshallerTest extends TestCase {

	public void testMarshalWMSCapabilities0() throws Exception {

		WMSCapabilities wmsCapabilities = createWMSCapabilities();

		JAXBContext context = JAXBContext
				.newInstance("net.opengis.wms.v_1_3_0");

		Marshaller marshaller = context.createMarshaller();

		{
			SchemaFactory schemaFactory = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

			URL schemaURL = getClass().getClassLoader().getResource(
					"wms/1.3.0/capabilities_1_3_0.xsd");

			Schema schema = schemaFactory.newSchema(schemaURL);

			marshaller.setSchema(schema);
		}

		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(wmsCapabilities, System.out);

	}

	private ObjectFactory objectFactory = new ObjectFactory();

	private WMSCapabilities createWMSCapabilities() {
		final WMSCapabilities wmsCapabilities = objectFactory
				.createWMSCapabilities();

		wmsCapabilities.setService(createService());
		wmsCapabilities.setCapability(createCapability());

		return wmsCapabilities;
	}

	private Service createService() {
		final Service service = objectFactory.createService();
		service.setName("WMS");
		service.setTitle("Dummy WMS service");
		service.setOnlineResource(createOnlineResource("http://localhost"));
		return service;
	}

	private Capability createCapability() {
		final Capability capability = objectFactory.createCapability();
		capability.setRequest(createRequest());
		capability.setException(createException());
		capability.setLayer(createLayer());
		return capability;
	}

	private Request createRequest() {
		final Request request = objectFactory.createRequest();
		request.setGetCapabilities(createGetCapabilities());
		request.setGetMap(createGetMap());
		return request;
	}

	private OperationType createGetMap() {
		final OperationType getMap = objectFactory.createOperationType();
		getMap.getFormat().add("image/gif");
		getMap.getFormat().add("image/png");
		getMap.getFormat().add("image/jpeg");
		final DCPType dcp = objectFactory.createDCPType();
		getMap.getDCPType().add(dcp);
		final HTTP http = objectFactory.createHTTP();
		dcp.setHTTP(http);
		final Get get = objectFactory.createGet();
		http.setGet(get);
		get.setOnlineResource(createOnlineResource("http://localhost?"));
		return getMap;
	}

	private OperationType createGetCapabilities() {
		final OperationType getCapabilities;
		getCapabilities = objectFactory.createOperationType();
		getCapabilities.getFormat().add("text/xml");
		final DCPType dcp = objectFactory.createDCPType();
		dcp.setHTTP(objectFactory.createHTTP());
		dcp.getHTTP().setGet(objectFactory.createGet());
		dcp.getHTTP().getGet().setOnlineResource(
				createOnlineResource("http://localhost?"));
		getCapabilities.getDCPType().add(dcp);
		return getCapabilities;
	}

	private net.opengis.wms.v_1_3_0.Exception createException() {
		final net.opengis.wms.v_1_3_0.Exception exception = objectFactory
				.createException();
		exception.getFormat().add("XML");
		exception.getFormat().add("INIMAGE");
		exception.getFormat().add("BLANK");
		return exception;
	}

	private Layer createLayer() {
		return createLayer("Main layer", "mainLayer", Arrays
				.asList(createLayer("Sub layer", "subLayer", Collections
						.<Layer> emptyList())));
	}

	private OnlineResource createOnlineResource(final String href) {
		final OnlineResource onlineResource = objectFactory
				.createOnlineResource();
		onlineResource.setHref(href);
		return onlineResource;
	}

	private Layer createLayer(String title, String name, List<Layer> sublayers) {
		final Layer layer = objectFactory.createLayer();
		layer.setTitle(title);
		layer.setName(name);
		layer.getLayer().addAll(sublayers);
		return layer;
	}

}
