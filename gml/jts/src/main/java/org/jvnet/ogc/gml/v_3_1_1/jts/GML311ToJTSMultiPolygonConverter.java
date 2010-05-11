package org.jvnet.ogc.gml.v_3_1_1.jts;

import java.util.ArrayList;
import java.util.List;

import net.opengis.gml.v_3_1_1.MultiPolygonPropertyType;
import net.opengis.gml.v_3_1_1.MultiPolygonType;
import net.opengis.gml.v_3_1_1.PolygonPropertyType;
import net.opengis.gml.v_3_1_1.PolygonType;

import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Polygon;

public class GML311ToJTSMultiPolygonConverter
    extends
    AbstractGML311ToJTSConverter<MultiPolygonType, MultiPolygonPropertyType, MultiPolygon> {

  // + MultiPolygon

  private final GML311ToJTSPolygonConverter polygonConverter;

  public GML311ToJTSMultiPolygonConverter(GeometryFactory geometryFactory) {
    super(geometryFactory);
    polygonConverter = new GML311ToJTSPolygonConverter(geometryFactory);
  }

  public GML311ToJTSMultiPolygonConverter() {
    this(new GeometryFactory());
  }

  @Override
  public MultiPolygon createGeometry(ObjectLocator locator, MultiPolygonType multiPolygonType)
      throws ConversionFailedException {
    final List<PolygonPropertyType> polygonMembers = multiPolygonType.getPolygonMember();
    final List<Polygon> polygons = new ArrayList<Polygon>(polygonMembers.size());
    for (int index = 0; index < polygonMembers.size(); index++) {
      final PolygonPropertyType polygonPropertyType = polygonMembers.get(index);
      final PolygonType polygonType = polygonPropertyType.getPolygon();
      polygons
          .add(polygonConverter
              .createGeometry(
                  locator
                      .field("polygonMember", polygonMembers).entry(index, polygonPropertyType).field("polygon", polygonType), //$NON-NLS-1$ //$NON-NLS-2$
                  polygonType));

    }
    return getGeometryFactory().createMultiPolygon(polygons.toArray(new Polygon[polygons.size()]));
  }

  @Override
  public MultiPolygon createGeometry(
      ObjectLocator locator,
      MultiPolygonPropertyType multiPolygonPropertyType) throws ConversionFailedException {
    if (multiPolygonPropertyType.isSetMultiPolygon()) {
      return createGeometry(locator.field("multiPolygon", multiPolygonPropertyType
          .getMultiPolygon()), multiPolygonPropertyType.getMultiPolygon());
    }
    else {
      throw new ConversionFailedException(locator, "Expected [MultiPolygon] element."); //$NON-NLS-1$
    }
  }
}
