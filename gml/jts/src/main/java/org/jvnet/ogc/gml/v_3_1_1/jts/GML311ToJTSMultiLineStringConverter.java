package org.jvnet.ogc.gml.v_3_1_1.jts;

import java.util.ArrayList;
import java.util.List;

import net.opengis.gml.v_3_1_1.LineStringPropertyType;
import net.opengis.gml.v_3_1_1.LineStringType;
import net.opengis.gml.v_3_1_1.MultiLineStringPropertyType;
import net.opengis.gml.v_3_1_1.MultiLineStringType;

import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiLineString;

public class GML311ToJTSMultiLineStringConverter
    extends
    AbstractGML311ToJTSConverter<MultiLineStringType, MultiLineStringPropertyType, MultiLineString> {

  // + MultiLineString

  private final GML311ToJTSLineStringConverter lineStringConverter;

  public GML311ToJTSMultiLineStringConverter(GeometryFactory geometryFactory) {
    super(geometryFactory);
    lineStringConverter = new GML311ToJTSLineStringConverter(geometryFactory);
  }

  public GML311ToJTSMultiLineStringConverter() {
    this(new GeometryFactory());
  }

  @Override
  public MultiLineString createGeometry(
      ObjectLocator locator,
      MultiLineStringType multiLineStringType) throws ConversionFailedException {
    final List<LineStringPropertyType> lineStringMembers = multiLineStringType
        .getLineStringMember();
    final List<LineString> lineStrings = new ArrayList<LineString>(lineStringMembers.size());
    for (int index = 0; index < lineStringMembers.size(); index++) {
      final LineStringPropertyType lineStringPropertyType = lineStringMembers.get(index);

      final LineStringType lineStringType = lineStringPropertyType.getLineString();
      lineStrings.add(lineStringConverter.createGeometry(locator.property(
          "lineStringMember", lineStringMembers).item(index, lineStringPropertyType).property( //$NON-NLS-1$
          "lineString",
          lineStringType), lineStringType));
    }
    return getGeometryFactory().createMultiLineString(
        lineStrings.toArray(new LineString[lineStrings.size()]));
  }

  @Override
  public MultiLineString createGeometry(
      ObjectLocator locator,
      MultiLineStringPropertyType multiLineStringPropertyType) throws ConversionFailedException {
    if (multiLineStringPropertyType.isSetMultiLineString()) {
      return createGeometry(locator.property("multiLineString", multiLineStringPropertyType
          .getMultiLineString()), multiLineStringPropertyType.getMultiLineString());
    }
    else {
      throw new ConversionFailedException(locator, "Expected [MultiLineString] element."); //$NON-NLS-1$
    }
  }
}
