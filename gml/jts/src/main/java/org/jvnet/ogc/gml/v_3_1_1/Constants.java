package org.jvnet.ogc.gml.v_3_1_1;

import org.jvnet.ogc.bind.marshaller.DefaultNamespacePrefixMapper;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

public class Constants {

  public static final String GML_NAMESPACE_URI = "http://www.opengis.net/gml"; //$NON-NLS-1$
  public static final String XLINK_NAMESPACE_URI = "http://www.w3.org/1999/xlink"; //$NON-NLS-1$
  public static final String SMIL20_NAMESPACE_URI = "http://www.w3.org/2001/SMIL20/"; //$NON-NLS-1$
  public static final String SMIL20_LANGUAGE_NAMESPACE_URI = "http://www.w3.org/2001/SMIL20/Language"; //$NON-NLS-1$

  public static final NamespacePrefixMapper NAMESPACE_PREFIX_MAPPER = new DefaultNamespacePrefixMapper()
      .declare(GML_NAMESPACE_URI, "gml") //$NON-NLS-1$
      .declare(XLINK_NAMESPACE_URI, "xlink") //$NON-NLS-1$
      .declare(SMIL20_NAMESPACE_URI, "smil20") //$NON-NLS-1$
      .declare(SMIL20_LANGUAGE_NAMESPACE_URI, "smil20lang"); //$NON-NLS-1$

}
