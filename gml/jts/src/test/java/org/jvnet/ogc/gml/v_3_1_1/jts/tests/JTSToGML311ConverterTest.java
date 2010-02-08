package org.jvnet.ogc.gml.v_3_1_1.jts.tests;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;

import org.jvnet.ogc.gml.v_3_1_1.Constants;
import org.jvnet.ogc.gml.v_3_1_1.jts.JTSToGML311Converter;

import junit.framework.TestCase;
import net.opengis.gml.v_3_1_1.ObjectFactory;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

public class JTSToGML311ConverterTest extends TestCase {

  private final GeometryFactory geometryFactory = new GeometryFactory();

  private JAXBContext context;

  private JTSToGML311Converter converter;

  @Override
  protected void setUp() throws Exception {
    context = JAXBContext.newInstance(ObjectFactory.class.getPackage().getName());
    converter = new JTSToGML311Converter();
  }

  protected void marshal(Object object) throws JAXBException {
    final Marshaller marshaller = context.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", //$NON-NLS-1$
        Constants.NAMESPACE_PREFIX_MAPPER);
    marshaller.marshal(object, new StreamResult(System.out));
  }

  public void testPoint() throws Exception {

    final Point point = geometryFactory.createPoint(new Coordinate(10, 20));
    marshal(converter.createGeometry(point));

  }

}
