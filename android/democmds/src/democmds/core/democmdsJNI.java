/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.11
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package democmds.core;

public class democmdsJNI {
  public final static native void delete_Floats(long jarg1);
  public final static native long new_Floats__SWIG_0(int jarg1);
  public final static native long new_Floats__SWIG_1();
  public final static native void Floats_setSize(long jarg1, Floats jarg1_, int jarg2);
  public final static native int Floats_count(long jarg1, Floats jarg1_);
  public final static native float Floats_get(long jarg1, Floats jarg1_, int jarg2);
  public final static native void Floats_set__SWIG_0(long jarg1, Floats jarg1_, int jarg2, float jarg3);
  public final static native void Floats_set__SWIG_1(long jarg1, Floats jarg1_, int jarg2, float jarg3, float jarg4);
  public final static native void delete_Chars(long jarg1);
  public final static native long new_Chars__SWIG_0(int jarg1);
  public final static native long new_Chars__SWIG_1();
  public final static native void Chars_setSize(long jarg1, Chars jarg1_, int jarg2);
  public final static native int Chars_count(long jarg1, Chars jarg1_);
  public final static native char Chars_get(long jarg1, Chars jarg1_, int jarg2);
  public final static native void Chars_set__SWIG_0(long jarg1, Chars jarg1_, int jarg2, char jarg3);
  public final static native void Chars_set__SWIG_1(long jarg1, Chars jarg1_, int jarg2, char jarg3, char jarg4);
  public final static native int DemoCmdsGate_registerCmds(int jarg1);
  public final static native int DemoCmdsGate_getDimensions(int jarg1, long jarg2, Floats jarg2_, long jarg3, Chars jarg3_);
  public final static native long new_DemoCmdsGate();
  public final static native void delete_DemoCmdsGate(long jarg1);
  public final static native void delete_MgCoreView(long jarg1);
  public final static native long MgCoreView_fromHandle(int jarg1);
  public final static native int MgCoreView_toHandle(long jarg1, MgCoreView jarg1_);
  public final static native int MgCoreView_viewAdapterHandle(long jarg1, MgCoreView jarg1_);
  public final static native int MgCoreView_backDoc(long jarg1, MgCoreView jarg1_);
  public final static native int MgCoreView_backShapes(long jarg1, MgCoreView jarg1_);
  public final static native int MgCoreView_acquireFrontDoc(long jarg1, MgCoreView jarg1_);
  public final static native void MgCoreView_releaseDoc(long jarg1, MgCoreView jarg1_, int jarg2);
  public final static native int MgCoreView_acquireDynamicShapes(long jarg1, MgCoreView jarg1_);
  public final static native void MgCoreView_releaseShapes(long jarg1, MgCoreView jarg1_, int jarg2);
  public final static native boolean MgCoreView_loadDynamicShapes(long jarg1, MgCoreView jarg1_, long jarg2);
  public final static native void MgCoreView_applyDynamicShapes(long jarg1, MgCoreView jarg1_);
  public final static native boolean MgCoreView_isPressDragging(long jarg1, MgCoreView jarg1_);
  public final static native String MgCoreView_getCommand(long jarg1, MgCoreView jarg1_);
  public final static native boolean MgCoreView_setCommand__SWIG_0(long jarg1, MgCoreView jarg1_, String jarg2, String jarg3);
  public final static native boolean MgCoreView_setCommand__SWIG_1(long jarg1, MgCoreView jarg1_, String jarg2);
  public final static native boolean MgCoreView_doContextAction(long jarg1, MgCoreView jarg1_, int jarg2);
  public final static native void MgCoreView_clearCachedData(long jarg1, MgCoreView jarg1_);
  public final static native int MgCoreView_addShapesForTest(long jarg1, MgCoreView jarg1_);
  public final static native int MgCoreView_getShapeCount(long jarg1, MgCoreView jarg1_);
  public final static native int MgCoreView_getChangeCount(long jarg1, MgCoreView jarg1_);
  public final static native int MgCoreView_getDrawCount(long jarg1, MgCoreView jarg1_);
  public final static native int MgCoreView_getSelectedShapeCount(long jarg1, MgCoreView jarg1_);
  public final static native int MgCoreView_getSelectedShapeType(long jarg1, MgCoreView jarg1_);
  public final static native int MgCoreView_getSelectedShapeID(long jarg1, MgCoreView jarg1_);
  public final static native void MgCoreView_clear(long jarg1, MgCoreView jarg1_);
  public final static native boolean MgCoreView_loadFromFile__SWIG_0(long jarg1, MgCoreView jarg1_, String jarg2, boolean jarg3);
  public final static native boolean MgCoreView_loadFromFile__SWIG_1(long jarg1, MgCoreView jarg1_, String jarg2);
  public final static native boolean MgCoreView_saveToFile__SWIG_0(long jarg1, MgCoreView jarg1_, int jarg2, String jarg3, boolean jarg4);
  public final static native boolean MgCoreView_saveToFile__SWIG_1(long jarg1, MgCoreView jarg1_, int jarg2, String jarg3);
  public final static native boolean MgCoreView_saveToFile__SWIG_2(long jarg1, MgCoreView jarg1_, String jarg2, boolean jarg3);
  public final static native boolean MgCoreView_saveToFile__SWIG_3(long jarg1, MgCoreView jarg1_, String jarg2);
  public final static native boolean MgCoreView_loadShapes__SWIG_0(long jarg1, MgCoreView jarg1_, long jarg2, boolean jarg3);
  public final static native boolean MgCoreView_loadShapes__SWIG_1(long jarg1, MgCoreView jarg1_, long jarg2);
  public final static native boolean MgCoreView_saveShapes__SWIG_0(long jarg1, MgCoreView jarg1_, int jarg2, long jarg3);
  public final static native boolean MgCoreView_saveShapes__SWIG_1(long jarg1, MgCoreView jarg1_, long jarg2);
  public final static native String MgCoreView_getContent__SWIG_0(long jarg1, MgCoreView jarg1_, int jarg2);
  public final static native void MgCoreView_freeContent(long jarg1, MgCoreView jarg1_);
  public final static native boolean MgCoreView_setContent(long jarg1, MgCoreView jarg1_, String jarg2);
  public final static native String MgCoreView_getContent__SWIG_1(long jarg1, MgCoreView jarg1_);
  public final static native boolean MgCoreView_zoomToExtent(long jarg1, MgCoreView jarg1_);
  public final static native boolean MgCoreView_zoomToModel(long jarg1, MgCoreView jarg1_, float jarg2, float jarg3, float jarg4, float jarg5);
  public final static native long MgCoreView_getContext(long jarg1, MgCoreView jarg1_, boolean jarg2);
  public final static native void MgCoreView_setContext__SWIG_0(long jarg1, MgCoreView jarg1_, int jarg2);
  public final static native void MgCoreView_setContext__SWIG_1(long jarg1, MgCoreView jarg1_, long jarg2, int jarg3, int jarg4);
  public final static native void MgCoreView_setContextEditing(long jarg1, MgCoreView jarg1_, boolean jarg2);
  public final static native int MgCoreView_addImageShape__SWIG_0(long jarg1, MgCoreView jarg1_, String jarg2, float jarg3, float jarg4);
  public final static native int MgCoreView_addImageShape__SWIG_1(long jarg1, MgCoreView jarg1_, String jarg2, float jarg3, float jarg4, float jarg5, float jarg6);
  public final static native boolean MgCoreView_getBoundingBox__SWIG_0(long jarg1, MgCoreView jarg1_, long jarg2, Floats jarg2_);
  public final static native boolean MgCoreView_getBoundingBox__SWIG_1(long jarg1, MgCoreView jarg1_, long jarg2, Floats jarg2_, int jarg3);
}
