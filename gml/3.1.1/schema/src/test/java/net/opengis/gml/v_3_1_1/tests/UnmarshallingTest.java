package net.opengis.gml.v_3_1_1.tests;

import java.net.URL;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;

import junit.framework.TestCase;
import net.opengis.gml.v_3_1_1.DirectPositionType;
import net.opengis.gml.v_3_1_1.LineStringType;
import net.opengis.gml.v_3_1_1.LinearRingType;
import net.opengis.gml.v_3_1_1.PointType;
import net.opengis.gml.v_3_1_1.PolygonType;

public class UnmarshallingTest extends TestCase {

  private JAXBContext context;

  @Override
  protected void setUp() throws Exception {

    context = JAXBContext
        .newInstance("net.opengis.gml.v_3_1_1:org.w3.smil.v_2_0:org.w3.smil.v_2_0.language");
  }

  public void testPoint0() throws Exception {

    final URL url = getClass().getResource("Point[0].xml");
    final Unmarshaller unmarshaller = context.createUnmarshaller();
    final PointType point = ((JAXBElement<PointType>) unmarshaller.unmarshal(url)).getValue();
    assertFalse(point.getPos().getValue().isEmpty());
  }

  public void testLineString0() throws Exception {

    final URL url = getClass().getResource("LineString[0].xml");
    final Unmarshaller unmarshaller = context.createUnmarshaller();
    final LineStringType lineString = ((JAXBElement<LineStringType>) unmarshaller.unmarshal(url))
        .getValue();
    List<JAXBElement<?>> pos = lineString.getPosOrPointPropertyOrPointRep();
    assertFalse(pos.isEmpty());
    final DirectPositionType value = ((JAXBElement<DirectPositionType>) pos.get(pos.size() - 1))
        .getValue();
    assertEquals(9513.7, value.getValue().get(0), 0.000001);
    assertEquals(4343.6, value.getValue().get(1), 0.000001);
  }

  public void testPolygon0() throws Exception {

    final URL url = getClass().getResource("Polygon[0].xml");
    final Unmarshaller unmarshaller = context.createUnmarshaller();
    final PolygonType polygon = ((JAXBElement<PolygonType>) unmarshaller.unmarshal(url)).getValue();
    final LinearRingType ringType = (LinearRingType) polygon
        .getExterior()
        .getValue()
        .getRing()
        .getValue();
    assertNotNull(ringType.getCoordinates().getValue());

  }

  public void testPolygon1() throws Exception {

    final URL url = getClass().getResource("Polygon[1].xml");
    final Unmarshaller unmarshaller = context.createUnmarshaller();
    final PolygonType polygon = ((JAXBElement<PolygonType>) unmarshaller.unmarshal(url)).getValue();
    final LinearRingType ringType = (LinearRingType) polygon
        .getExterior()
        .getValue()
        .getRing()
        .getValue();
    assertFalse(ringType.getPosOrPointPropertyOrPointRep().isEmpty());

  }
}
