package net.opengis.gml.v_3_1_1;

import java.math.BigInteger;

import org.jvnet.jaxb2_commons.lang.CopyStrategy;
import org.jvnet.jaxb2_commons.lang.CopyTo;
import org.jvnet.jaxb2_commons.lang.JAXBCopyStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;

public class DefaultSRSReferenceGroup extends DefaultSRSInformationGroup
    implements
    SRSReferenceGroup,
    CopyTo {

  public DefaultSRSReferenceGroup() {
  }

  public DefaultSRSReferenceGroup(SRSReferenceGroup that) {
    super(that);
    if (that.isSetSrsDimension()) {
      this.setSrsDimension(that.getSrsDimension());
    }
    if (this.isSetSrsName()) {
      this.setSrsName(that.getSrsName());
    }
  }

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
    return copyTo(null, target, JAXBCopyStrategy.INSTANCE);
  }

  @Override
  public Object copyTo(ObjectLocator locator, Object target, CopyStrategy copyStrategy) {
    final Object draftCopy = ((target == null) ? createNewInstance() : target);
    super.copyTo(locator, draftCopy, copyStrategy);
    if (draftCopy instanceof SRSReferenceGroup) {
      final SRSReferenceGroup copy = ((SRSReferenceGroup) draftCopy);
      if (this.isSetSrsName()) {
        String sourceSrsName;
        sourceSrsName = this.getSrsName();
        String copySrsName = ((String) copyStrategy.copy(
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
        BigInteger copySrsDimension = ((BigInteger) copyStrategy.copy(LocatorUtils.field(
            locator,
            "srsDimension"), sourceSrsDimension));
        copy.setSrsDimension(copySrsDimension);
      }
      else {
        copy.setSrsDimension(null);
      }
    }
    return draftCopy;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((srsDimension == null) ? 0 : srsDimension.hashCode());
    result = prime * result + ((srsName == null) ? 0 : srsName.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    DefaultSRSReferenceGroup other = (DefaultSRSReferenceGroup) obj;
    if (getSrsDimension() == null) {
      if (other.getSrsDimension() != null) {
        return false;
      }
    }
    else if (!getSrsDimension().equals(other.getSrsDimension())) {
      return false;
    }
    if (getSrsName() == null) {
      if (other.getSrsName() != null) {
        return false;
      }
    }
    else if (!getSrsName().equals(other.getSrsName())) {
      return false;
    }

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
