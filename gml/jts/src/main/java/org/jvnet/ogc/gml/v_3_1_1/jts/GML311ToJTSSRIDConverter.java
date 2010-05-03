//package org.jvnet.ogc.gml.v_3_1_1.jts;
//
//import java.text.MessageFormat;
//import java.text.ParseException;
//
//import org.apache.commons.lang.Validate;
//import org.jvnet.jaxb2_commons.locator.ObjectLocator;
//
//import net.opengis.gml.v_3_1_1.SRSReferenceGroup;
//
//import com.vividsolutions.jts.geom.Geometry;
//
//public class GML311ToJTSSRIDConverter implements GML311ToJTSSRIDConverterInterface {
//
//  String[] patterns = {
//      "EPSG:{0,number,integer}",
//      "urn:ogc:def:crs:EPSG::{0,number,integer}",
//      "urn:ogc:def:crs:EPSG:{1}:{0,number,integer}",
//      "urn:x-ogc:def:crs:EPSG::{0,number,integer}",
//      "urn:x-ogc:def:crs:EPSG:{1}:{0,number,integer}",
//      "http://www.opengis.net/gml/srs/epsg.xml#{0,number,integer}" };
//
//  @Override
//  public void convert(ObjectLocator locator, SRSReferenceGroup source, Geometry target)
//      throws ConversionFailedException
//  {
//    Validate.notNull(source);
//    Validate.notNull(target);
//
//    String srsName = source.getSrsName();
//    for (String pattern : patterns) {
//      try {
//        MessageFormat format = new MessageFormat(pattern);
//        Object[] codearray = format.parse(srsName);
//        if (codearray.length > 0) {
//          target.setSRID(((Number) codearray[0]).intValue());
//          if (target.getUserData() == null) {
//            target.setUserData(srsName);
//            return;
//          }
//        }
//      }
//      catch (ParseException e) {
//        // this pattern was not correct
//        continue;
//      }
//    }
//    if (target.getUserData()!=null){
//      throw new ConversionFailedException(locator, MessageFormat.format(
//        "Could not parse SRS name [{0}].",
//        srsName));
//    }else{
//      target.setUserData(srsName);
//    }
//  }
//
//}
