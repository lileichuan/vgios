/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.11
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package touchgl.core;

public final class GiLineStyle {
  public final static GiLineStyle kGiLineSolid = new GiLineStyle("kGiLineSolid", touchglJNI.kGiLineSolid_get());
  public final static GiLineStyle kGiLineDash = new GiLineStyle("kGiLineDash");
  public final static GiLineStyle kGiLineDot = new GiLineStyle("kGiLineDot");
  public final static GiLineStyle kGiLineDashDot = new GiLineStyle("kGiLineDashDot");
  public final static GiLineStyle kGiLineDashDotdot = new GiLineStyle("kGiLineDashDotdot");
  public final static GiLineStyle kGiLineNull = new GiLineStyle("kGiLineNull");

  public final int swigValue() {
    return swigValue;
  }

  public String toString() {
    return swigName;
  }

  public static GiLineStyle swigToEnum(int swigValue) {
    if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
      return swigValues[swigValue];
    for (int i = 0; i < swigValues.length; i++)
      if (swigValues[i].swigValue == swigValue)
        return swigValues[i];
    throw new IllegalArgumentException("No enum " + GiLineStyle.class + " with value " + swigValue);
  }

  private GiLineStyle(String swigName) {
    this.swigName = swigName;
    this.swigValue = swigNext++;
  }

  private GiLineStyle(String swigName, int swigValue) {
    this.swigName = swigName;
    this.swigValue = swigValue;
    swigNext = swigValue+1;
  }

  private GiLineStyle(String swigName, GiLineStyle swigEnum) {
    this.swigName = swigName;
    this.swigValue = swigEnum.swigValue;
    swigNext = this.swigValue+1;
  }

  private static GiLineStyle[] swigValues = { kGiLineSolid, kGiLineDash, kGiLineDot, kGiLineDashDot, kGiLineDashDotdot, kGiLineNull };
  private static int swigNext = 0;
  private final int swigValue;
  private final String swigName;
}

