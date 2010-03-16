package net.opengis.gml.v_3_1_1;

import java.math.BigInteger;
import java.util.List;

import org.jvnet.jaxb2_commons.lang.CopyTo;
import org.jvnet.jaxb2_commons.lang.Copyable;
import org.jvnet.jaxb2_commons.lang.builder.CopyBuilder;
import org.jvnet.jaxb2_commons.lang.builder.JAXBCopyBuilder;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;

public class DefaultSRSReferenceGroup extends DefaultSRSInformationGroup
    implements
    SRSReferenceGroup,
    Copyable,
    CopyTo {

  protected String srsName;
  protected BigInteger srsDimension;

  public String getSrsName() {
    return srsName;
  }

  public void setSrsName(String value) {
    this.srsName = value;
  }

  public boolean isSetSrsName() {
    return (this.srsName != null);
  }

  public BigInteger getSrsDimension() {
    return srsDimension;
  }

  public void setSrsDimension(BigInteger value) {
    this.srsDimension = value;
  }

  public boolean isSetSrsDimension() {
    return (this.srsDimension != null);
  }

  @Override
  public Object createNewInstance() {
    return new DefaultSRSReferenceGroup();
  }

  @Override
  public Object copyTo(Object target) {
    return copyTo(null, target);
  }

  @Override
  public Object copyTo(ObjectLocator locator, Object target) {
    return copyTo(locator, target, JAXBCopyBuilder.INSTANCE);
  }

  @Override
  public Object copyTo(Object target, CopyBuilder copyBuilder) {
    return copyTo(null, target, JAXBCopyBuilder.INSTANCE);
  }

  @Override
  public Object copyTo(ObjectLocator locator, Object target, CopyBuilder copyBuilder) {
    final Object draftCopy = ((target == null) ? createNewInstance() : target);
    if (draftCopy instanceof SRSReferenceGroup) {
      final SRSReferenceGroup copy = ((SRSReferenceGroup) draftCopy);
      if (this.isSetAxisLabels()) {
        List<String> sourceAxisLabels;
        sourceAxisLabels = this.getAxisLabels();
        @SuppressWarnings("unchecked")
        List<String> copyAxisLabels = ((List<String>) copyBuilder.copy(LocatorUtils.field(
            locator,
            "axisLabels"), sourceAxisLabels));
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
        List<String> copyUomLabels = ((List<String>) copyBuilder.copy(LocatorUtils.field(
            locator,
            "uomLabels"), sourceUomLabels));
        copy.unsetUomLabels();
        List<String> uniqueUomLabelsl = copy.getUomLabels();
        uniqueUomLabelsl.addAll(copyUomLabels);
      }
      else {
        copy.unsetUomLabels();
      }

      if (this.isSetSrsName()) {
        String sourceSrsName;
        sourceSrsName = this.getSrsName();
        String copySrsName = ((String) copyBuilder.copy(
            LocatorUtils.field(locator, "srsName"),
            sourceSrsName));
        copy.setSrsName(copySrsName);
      }
      else {
        copy.setSrsName(null);
      }

      if (this.isSetSrsDimension()) {
        BigInteger sourceSrsDimension;
        sourceSrsDimension = this.getSrsDimension();
        BigInteger copySrsDimension = ((BigInteger) copyBuilder.copy(LocatorUtils.field(
            locator,
            "srsName"), sourceSrsDimension));
        copy.setSrsDimension(copySrsDimension);
      }
      else {
        copy.setSrsDimension(null);
      }
    }
    return draftCopy;
  }

}
