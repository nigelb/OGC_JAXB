package org.jvnet.ogcl.bind.marshaller;

import java.util.HashMap;
import java.util.Map;

public class DefaultNamespacePrefixMapper extends
		com.sun.xml.bind.marshaller.NamespacePrefixMapper {

	private Map<String, String> preferredPrefixMap = new HashMap<String, String>();

	@Override
	public String getPreferredPrefix(String namespaceUri, String suggestion,
			boolean requirePrefix) {
		return preferredPrefixMap.get(namespaceUri);
	}

	public DefaultNamespacePrefixMapper declare(String namespaceURI,
			String prefix) {
		final DefaultNamespacePrefixMapper mapper = new DefaultNamespacePrefixMapper();
		mapper.preferredPrefixMap.putAll(this.preferredPrefixMap);
		mapper.preferredPrefixMap.put(namespaceURI, prefix);
		return mapper;
	}

}
