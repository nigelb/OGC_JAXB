//package org.jvnet.ogc.gml.v_3_1_1.jts.tests;
//
//import junit.framework.TestCase;
//import net.opengis.gml.v_3_1_1.PointType;
//
//import org.jvnet.jaxb2_commons.locator.DefaultRootObjectLocator;
//import org.jvnet.ogc.gml.v_3_1_1.jts.ConversionFailedException;
//import org.jvnet.ogc.gml.v_3_1_1.jts.GML311ToJTSSRIDConverter;
//
//import com.vividsolutions.jts.geom.Coordinate;
//import com.vividsolutions.jts.geom.Geometry;
//import com.vividsolutions.jts.geom.GeometryFactory;
//
//public class GML311ToJTSSRIDConverterTest extends TestCase {
//
//  String[][] examples = { { "http://www.opengis.net/gml/srs/epsg.xml#63266405", "63266405" } };
//
//  String[] counterexamples = {
//
//  };
//
//  private GeometryFactory geometryFactory;
//  private GML311ToJTSSRIDConverter converter;
//
//  @Override
//  protected void setUp() throws Exception {
//    geometryFactory = new GeometryFactory();
//    converter = new GML311ToJTSSRIDConverter();
//
//  }
//
//  public void testConvertWithCorrectPoint() throws ConversionFailedException {
//
//    Geometry point = geometryFactory.createPoint(new Coordinate(0, 0));
//
//    final PointType source = new PointType();
//    int srid = 63266405;
//
//    source.setSrsName("http://www.opengis.net/gml/srs/epsg.xml#63266405");
//
//    converter.convert(new DefaultRootObjectLocator(source), source, point);
//    assertEquals(srid, point.getSRID());
//
//  }
//
//  public void testConvertWithWrongFormatAndTargetUserDataNull() throws ConversionFailedException {
//    Geometry point = geometryFactory.createPoint(new Coordinate(0, 0));
//
//    final PointType source = new PointType();
//
//    source.setSrsName("foo");
//
//    converter.convert(new DefaultRootObjectLocator(source), source, point);
//    assertEquals("foo", point.getUserData());
//
//  }
//
//  public void testConvertWithWrongFormatAndTargetUserDataNotNull() throws ConversionFailedException {
//    Geometry point = geometryFactory.createPoint(new Coordinate(0, 0));
//    point.setUserData("");
//    final PointType source = new PointType();
//
//    source.setSrsName("foo");
//
//    try {
//      converter.convert(new DefaultRootObjectLocator(source), source, point);
//      fail();
//    }
//    catch (ConversionFailedException e) {
//      assertTrue(true);
//    }
//
//  }
//
//}
