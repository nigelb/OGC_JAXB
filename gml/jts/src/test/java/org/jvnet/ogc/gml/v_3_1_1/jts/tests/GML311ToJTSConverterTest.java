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
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Point;
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

  public void testPoint0() throws Exception {

    final Point point = (Point) unmarshal("Point[0].xml"); //$NON-NLS-1$
    Assert.assertFalse(point.isEmpty());
    Assert.assertTrue(point.isValid());

  }

  public void testPoint1() throws Exception {
    final Point point = (Point) unmarshal("Point[1].xml"); //$NON-NLS-1$
    Assert.assertFalse(point.isEmpty());
    Assert.assertTrue(point.isValid());
  }

  public void testPoint2() throws Exception {
    final Point point = (Point) unmarshal("Point[2].xml"); //$NON-NLS-1$
    Assert.assertFalse(point.isEmpty());
    Assert.assertTrue(point.isValid());
  }

  public void testLineString0() throws Exception {

    final LineString lineString = (LineString) unmarshal("LineString[0].xml"); //$NON-NLS-1$
    Assert.assertFalse(lineString.isEmpty());
    Assert.assertTrue(lineString.isValid());
    Assert.assertEquals(12, lineString.getCoordinates().length);

  }

  public void testLineString1() throws Exception {

    final LineString lineString = (LineString) unmarshal("LineString[1].xml"); //$NON-NLS-1$
    Assert.assertFalse(lineString.isEmpty());
    Assert.assertTrue(lineString.isValid());
    Assert.assertEquals(12, lineString.getCoordinates().length);

  }

  public void testLineString2() throws Exception {

    final LineString lineString = (LineString) unmarshal("LineString[2].xml"); //$NON-NLS-1$
    Assert.assertFalse(lineString.isEmpty());
    Assert.assertTrue(lineString.isValid());
    Assert.assertEquals(12, lineString.getCoordinates().length);

  }
  
  public void testLineString3() throws Exception {

    final LineString lineString = (LineString) unmarshal("LineString[2].xml"); //$NON-NLS-1$
    Assert.assertFalse(lineString.isEmpty());
    Assert.assertTrue(lineString.isValid());
    Assert.assertEquals(12, lineString.getCoordinates().length);

  }

  public void testLineStrings() throws Exception {
    final LineString lineString0 = (LineString) unmarshal("LineString[0].xml"); //$NON-NLS-1$
    final LineString lineString1 = (LineString) unmarshal("LineString[1].xml"); //$NON-NLS-1$
    final LineString lineString2 = (LineString) unmarshal("LineString[2].xml"); //$NON-NLS-1$
    final LineString lineString3 = (LineString) unmarshal("LineString[2].xml"); //$NON-NLS-1$
    Assert.assertTrue(lineString0.equals(lineString1));
    Assert.assertTrue(lineString1.equals(lineString2));
    Assert.assertTrue(lineString2.equals(lineString3));
  }
  
  public void testLinearRing0() throws Exception {

    final LinearRing linearRing = (LinearRing) unmarshal("LinearRing[0].xml"); //$NON-NLS-1$
    Assert.assertFalse(linearRing.isEmpty());
    Assert.assertTrue(linearRing.isValid());
    Assert.assertEquals(13, linearRing.getCoordinates().length);

  }

  public void testLinearRing1() throws Exception {

    final LinearRing linearRing = (LinearRing) unmarshal("LinearRing[1].xml"); //$NON-NLS-1$
    Assert.assertFalse(linearRing.isEmpty());
    Assert.assertTrue(linearRing.isValid());
    Assert.assertEquals(13, linearRing.getCoordinates().length);

  }
  
  public void testLinearRings() throws Exception {
    final LinearRing linearRing0 = (LinearRing) unmarshal("LinearRing[0].xml"); //$NON-NLS-1$
    final LinearRing linearRing1 = (LinearRing) unmarshal("LinearRing[1].xml"); //$NON-NLS-1$
    Assert.assertTrue(linearRing0.equals(linearRing1));
  }
  
  
  

  public void testPolygon0() throws Exception {

    final Polygon polygon = (Polygon) unmarshal("Polygon[0].xml"); //$NON-NLS-1$
    Assert.assertEquals(20, polygon.getCoordinates().length);

  }

  public void testPolygon1() throws Exception {
    final Polygon polygon = (Polygon) unmarshal("Polygon[1].xml"); //$NON-NLS-1$
    Assert.assertEquals(20, polygon.getCoordinates().length);
  }
  
  public void testPolygon2() throws Exception {
    final Polygon polygon = (Polygon) unmarshal("Polygon[2].xml"); //$NON-NLS-1$
    final double area = polygon.getArea();
    Assert.assertEquals(3600, area, 1);;
  }

  public void testPolygons() throws Exception {
    final Polygon polygon0 = (Polygon) unmarshal("Polygon[0].xml"); //$NON-NLS-1$
    final Polygon polygon1 = (Polygon) unmarshal("Polygon[1].xml"); //$NON-NLS-1$
    Assert.assertTrue(polygon0.equals(polygon1));
  }
  
}
