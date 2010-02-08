package org.jvnet.ogc.gml.v_3_1_1.jts.tests;

import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.transform.stream.StreamSource;

import junit.framework.Assert;
import junit.framework.TestCase;
import net.opengis.gml.v_3_1_1.ObjectFactory;

import org.jvnet.ogc.gml.v_3_1_1.jts.ConversionFailedException;
import org.jvnet.ogc.gml.v_3_1_1.jts.GML311ToJTSConverter;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Polygon;

public class GML311ToJTSConverterTest extends TestCase {

  private JAXBContext context;

  private GML311ToJTSConverter converter;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    context = JAXBContext.newInstance(ObjectFactory.class.getPackage().getName());
    converter = new GML311ToJTSConverter();
  }

  private Geometry unmarshal(String resourceName) throws JAXBException, ConversionFailedException {
    final URL url = getClass().getResource(resourceName);
    final Object object = context.createUnmarshaller().unmarshal(new StreamSource(url.toString()));
    return converter.createGeometry(JAXBIntrospector.getValue(object));

  }

  public void testLineString0() throws Exception {

    final LineString lineString = (LineString) unmarshal("LineString[0].xml"); //$NON-NLS-1$
    Assert.assertEquals(12, lineString.getCoordinates().length);

  }

  public void testPolygon0() throws Exception {

    final Polygon polygon = (Polygon) unmarshal("Polygon[0].xml"); //$NON-NLS-1$
    Assert.assertEquals(20, polygon.getCoordinates().length);

  }
}
