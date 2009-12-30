package org.jvnet.ogc.gml.v_3_1_1;

import org.jvnet.ogcl.bind.marshaller.DefaultNamespacePrefixMapper;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

public class Constants {

	public static final String GML_NAMESPACE_URI = "http://www.opengis.net/gml";
	public static final String XLINK_NAMESPACE_URI = "http://www.w3.org/1999/xlink";
	public static final String SMIL20_NAMESPACE_URI = "http://www.w3.org/2001/SMIL20/";
	public static final String SMIL20_LANGUAGE_NAMESPACE_URI = "http://www.w3.org/2001/SMIL20/Language";

	public static final NamespacePrefixMapper NAMESPACE_PREFIX_MAPPER = new DefaultNamespacePrefixMapper()
			.declare(GML_NAMESPACE_URI, "gml").declare(XLINK_NAMESPACE_URI,
					"xlink").declare(SMIL20_NAMESPACE_URI, "smil20").declare(
					SMIL20_LANGUAGE_NAMESPACE_URI, "smil20lang");

}
