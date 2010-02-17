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

public class GML311ToJTSMultiLineStringConverter extends AbstractGML311ToJTSConverter {

  // + MultiLineString

  private final GML311ToJTSLineStringConverter lineStringConverter;

  public GML311ToJTSMultiLineStringConverter(GeometryFactory geometryFactory) {
    super(geometryFactory);
    lineStringConverter = new GML311ToJTSLineStringConverter(geometryFactory);
  }

  public GML311ToJTSMultiLineStringConverter() {
    this(new GeometryFactory());
  }

  public MultiLineString createMultiLineString(
      ObjectLocator locator,
      MultiLineStringType multiLineStringType) throws ConversionFailedException {
    final List<LineStringPropertyType> lineStringMembers = multiLineStringType
        .getLineStringMember();
    final List<LineString> lineStrings = new ArrayList<LineString>(lineStringMembers.size());
    for (int index = 0; index < lineStringMembers.size(); index++) {
      final LineStringPropertyType lineStringPropertyType = lineStringMembers.get(index);

      final LineStringType lineStringType = lineStringPropertyType.getLineString();
      lineStrings.add(lineStringConverter.createLineString(locator
          .field("LineStringMember").entry(index).field( //$NON-NLS-1$
              "LineString"), lineStringType)); //$NON-NLS-1$
    }
    return getGeometryFactory().createMultiLineString(
        lineStrings.toArray(new LineString[lineStrings.size()]));
  }

  public MultiLineString createMultiLineString(
      ObjectLocator locator,
      MultiLineStringPropertyType multiLineStringPropertyType) throws ConversionFailedException {
    if (multiLineStringPropertyType.isSetMultiLineString()) {
      return createMultiLineString(locator.field("MultiLineString"), multiLineStringPropertyType //$NON-NLS-1$
          .getMultiLineString());
    }
    else {
      throw new ConversionFailedException(locator, "Expected [MultiLineString] element."); //$NON-NLS-1$
    }
  }
}
