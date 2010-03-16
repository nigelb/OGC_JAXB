package net.opengis.gml.v_3_1_1;

import java.util.ArrayList;
import java.util.List;

public class DefaultSRSInformationGroup implements SRSInformationGroup {

  private List<String> axisLabels;

  private List<String> uomLabels;

  public List<String> getAxisLabels() {
    if (axisLabels == null) {
      axisLabels = new ArrayList<String>();
    }
    return this.axisLabels;
  }

  public boolean isSetAxisLabels() {
    return ((this.axisLabels != null) && (!this.axisLabels.isEmpty()));
  }

  public void unsetAxisLabels() {
    this.axisLabels = null;
  }

  public List<String> getUomLabels() {
    if (uomLabels == null) {
      uomLabels = new ArrayList<String>();
    }
    return this.uomLabels;
  }

  public boolean isSetUomLabels() {
    return ((this.uomLabels != null) && (!this.uomLabels.isEmpty()));
  }

  public void setAxisLabels(List<String> value) {
    List<String> draftl = this.getAxisLabels();
    draftl.addAll(value);
  }

  public void setUomLabels(List<String> value) {
    List<String> draftl = this.getUomLabels();
    draftl.addAll(value);
  }

  public void unsetUomLabels() {
    this.uomLabels = null;
  }

}
