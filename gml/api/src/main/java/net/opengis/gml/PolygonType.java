package net.opengis.gml;

import java.util.List;

import javax.xml.bind.JAXBElement;

public interface PolygonType extends AbstractSurfaceType {

	public JAXBElement<? extends AbstractRingPropertyType> getExterior();

	public boolean isSetExterior();

	public List getInterior();

	public boolean isSetInterior();

	public void unsetInterior();

}
