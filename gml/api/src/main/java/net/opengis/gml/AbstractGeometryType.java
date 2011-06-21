package net.opengis.gml;


public interface AbstractGeometryType extends AbstractGMLType,
		SRSReferenceGroup {
	public String getGid();

	public void setGid(String value);

	public boolean isSetGid();

}
