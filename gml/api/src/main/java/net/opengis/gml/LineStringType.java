package net.opengis.gml;

import java.util.List;

import javax.xml.bind.JAXBElement;

public interface LineStringType extends AbstractCurveType {
	public List<JAXBElement<?>> getPosOrPointPropertyOrPointRep();

	public boolean isSetPosOrPointPropertyOrPointRep();

	public void unsetPosOrPointPropertyOrPointRep();

	public void setPosOrPointPropertyOrPointRep(List<JAXBElement<?>> value);

	public DirectPositionListType getPosList();

	public void setPosList(DirectPositionListType value);

	public boolean isSetPosList();

	public CoordinatesType getCoordinates();

	public void setCoordinates(CoordinatesType value);

	public boolean isSetCoordinates();

}
