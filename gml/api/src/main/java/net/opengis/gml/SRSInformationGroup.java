package net.opengis.gml;

import java.util.List;

public interface SRSInformationGroup {

  public List<String> getAxisLabels();

  public boolean isSetAxisLabels();

  public void setAxisLabels(List<String> value);

  public void unsetAxisLabels();

  public List<String> getUomLabels();

  public boolean isSetUomLabels();

  public void setUomLabels(List<String> value);
  
  public void unsetUomLabels();

}
