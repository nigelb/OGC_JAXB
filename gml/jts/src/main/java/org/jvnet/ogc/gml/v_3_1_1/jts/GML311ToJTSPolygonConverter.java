package org.jvnet.ogc.gml.v_3_1_1.jts;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import net.opengis.gml.v_3_1_1.AbstractRingPropertyType;
import net.opengis.gml.v_3_1_1.AbstractRingType;
import net.opengis.gml.v_3_1_1.LinearRingType;
import net.opengis.gml.v_3_1_1.PolygonPropertyType;
import net.opengis.gml.v_3_1_1.PolygonType;

import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Polygon;

public class GML311ToJTSPolygonConverter
    extends
    AbstractGML311ToJTSConverter<PolygonType, PolygonPropertyType, Polygon> {

  // + Polygon

  private final GML311ToJTSLinearRingConverter linearRingConverter;

  public GML311ToJTSPolygonConverter(GeometryFactory geometryFactory) {
    super(geometryFactory);
    linearRingConverter = new GML311ToJTSLinearRingConverter(geometryFactory);
  }

  public GML311ToJTSPolygonConverter() {
    this(new GeometryFactory());
  }

  public Polygon createGeometry(ObjectLocator locator, PolygonType polygonType)
      throws ConversionFailedException {
    final LinearRing shell;
    if (polygonType.isSetExterior()) {
      final AbstractRingType abstractRingType = ((JAXBElement<? extends AbstractRingType>) polygonType
          .getExterior()
          .getValue()
          .getRing()).getValue();
      if (abstractRingType instanceof LinearRingType) {
        shell = linearRingConverter.createGeometry(locator
            .field("Exterior").field("Value").field("Ring").field( //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                "Value"), (LinearRingType) abstractRingType); //$NON-NLS-1$
      }
      else {
        throw new ConversionFailedException(locator.field("Exterior").field("Value").field("Ring"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            "Only linear rings are supported."); //$NON-NLS-1$
      }
    }
    else {
      shell = null;
    }

    final LinearRing[] holes;
    if (polygonType.isSetInterior()) {
      final ObjectLocator interiorObjectLocator = locator.field("Interior"); //$NON-NLS-1$
      final List<LinearRing> holesList = new ArrayList<LinearRing>(polygonType.getInterior().size());
      for (int index = 0; index < polygonType.getInterior().size(); index++) {
        final ObjectLocator entryLocator = interiorObjectLocator.entry(index);
        final JAXBElement<AbstractRingPropertyType> ringElement = polygonType.getInterior().get(
            index);

        final AbstractRingType abstractRingType = ringElement.getValue().getRing().getValue();
        if (abstractRingType instanceof LinearRingType) {
          holesList.add(linearRingConverter.createGeometry(entryLocator
              .field("Value").field("Ring").field("Value"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

              (LinearRingType) abstractRingType));
        }
        else {
          throw new ConversionFailedException(entryLocator, "Only linear rings are supported."); //$NON-NLS-1$
        }
      }

      holes = holesList.toArray(new LinearRing[holesList.size()]);
    }
    else {
      holes = null;
    }
    return getGeometryFactory().createPolygon(shell, holes);
  }

  public Polygon createGeometry(ObjectLocator locator, PolygonPropertyType polygonPropertyType)
      throws ConversionFailedException {
    if (polygonPropertyType.isSetPolygon()) {
      return createGeometry(locator.field("Polygon"), polygonPropertyType.getPolygon()); //$NON-NLS-1$
    }
    else {
      throw new ConversionFailedException(locator, "Expected [Polygon] element."); //$NON-NLS-1$
    }
  }

}
