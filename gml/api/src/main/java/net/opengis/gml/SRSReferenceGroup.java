package net.opengis.gml;

import java.math.BigInteger;

public interface SRSReferenceGroup extends SRSInformationGroup {

  public String getSrsName();

  public void setSrsName(String value);

  public boolean isSetSrsName();

  public BigInteger getSrsDimension();

  public void setSrsDimension(BigInteger value);

  public boolean isSetSrsDimension();

}
