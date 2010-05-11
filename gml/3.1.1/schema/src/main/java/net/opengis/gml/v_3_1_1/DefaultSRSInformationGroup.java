package net.opengis.gml.v_3_1_1;

import java.util.ArrayList;
import java.util.List;

import org.jvnet.jaxb2_commons.lang.CopyStrategy;
import org.jvnet.jaxb2_commons.lang.CopyTo;
import org.jvnet.jaxb2_commons.lang.JAXBCopyStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;

public class DefaultSRSInformationGroup implements SRSInformationGroup, CopyTo {

  public DefaultSRSInformationGroup() {
  }

  public DefaultSRSInformationGroup(SRSInformationGroup that) {
    if (that.isSetAxisLabels()) {
      this.setAxisLabels(that.getAxisLabels());
    }
    if (that.isSetUomLabels()) {
      this.setUomLabels(that.getUomLabels());
    }
  }

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

  @Override
  public Object createNewInstance() {
    return new DefaultSRSInformationGroup();
  }

  @Override
  public Object copyTo(Object target) {
    return copyTo(null, target, JAXBCopyStrategy.INSTANCE);
  }

  @Override
  public Object copyTo(ObjectLocator locator, Object target, CopyStrategy copyStrategy) {
    final Object draftCopy = ((target == null) ? createNewInstance() : target);
    if (draftCopy instanceof SRSInformationGroup) {
      final SRSInformationGroup copy = ((SRSInformationGroup) draftCopy);
      if (this.isSetAxisLabels()) {
        List<String> sourceAxisLabels;
        sourceAxisLabels = this.getAxisLabels();
        @SuppressWarnings("unchecked")
        List<String> copyAxisLabels = ((List<String>) copyStrategy.copy(LocatorUtils.field(
            locator,
            "axisLabels",
            sourceAxisLabels), sourceAxisLabels));
        copy.unsetAxisLabels();
        List<String> uniqueAxisLabelsl = copy.getAxisLabels();
        uniqueAxisLabelsl.addAll(copyAxisLabels);
      }
      else {
        copy.unsetAxisLabels();
      }

      if (this.isSetUomLabels()) {
        List<String> sourceUomLabels;
        sourceUomLabels = this.getUomLabels();
        @SuppressWarnings("unchecked")
        List<String> copyUomLabels = ((List<String>) copyStrategy.copy(LocatorUtils.field(
            locator,
            "uomLabels",
            sourceUomLabels), sourceUomLabels));
        copy.unsetUomLabels();
        List<String> uniqueUomLabelsl = copy.getUomLabels();
        uniqueUomLabelsl.addAll(copyUomLabels);
      }
      else {
        copy.unsetUomLabels();
      }
    }
    return draftCopy;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((axisLabels == null) ? 0 : axisLabels.hashCode());
    result = prime * result + ((uomLabels == null) ? 0 : uomLabels.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    DefaultSRSInformationGroup other = (DefaultSRSInformationGroup) obj;
    if (getAxisLabels() == null) {
      if (other.getAxisLabels() != null) {
        return false;
      }
    }
    else if (!getAxisLabels().equals(other.getAxisLabels())) {
      return false;
    }

    if (getUomLabels() == null) {
      if (other.getUomLabels() != null) {
        return false;
      }
    }
    else if (!getUomLabels().equals(other.getUomLabels())) {
      return false;
    }
    return true;
  }

}
