/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.11
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package touchgl.core;

public class TouchGLView {
  private long swigCPtr;
  protected boolean swigCMemOwn;

  protected TouchGLView(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(TouchGLView obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        touchglJNI.delete_TouchGLView(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public TouchGLView(int widthPt, int heightPt, int pixelsPerPoint) {
    this(touchglJNI.new_TouchGLView__SWIG_0(widthPt, heightPt, pixelsPerPoint), true);
  }

  public TouchGLView(int widthPt, int heightPt) {
    this(touchglJNI.new_TouchGLView__SWIG_1(widthPt, heightPt), true);
  }

  public void resize(int widthPx, int heightPx) {
    touchglJNI.TouchGLView_resize(swigCPtr, this, widthPx, heightPx);
  }

  public int getWidthPx() {
    return touchglJNI.TouchGLView_getWidthPx(swigCPtr, this);
  }

  public int getHeightPx() {
    return touchglJNI.TouchGLView_getHeightPx(swigCPtr, this);
  }

  public void prepareToDraw() {
    touchglJNI.TouchGLView_prepareToDraw(swigCPtr, this);
  }

  public GiCanvas beginPaint(boolean pathCached) {
    long cPtr = touchglJNI.TouchGLView_beginPaint(swigCPtr, this, pathCached);
    return (cPtr == 0) ? null : new GiCanvas(cPtr, false);
  }

  public void endPaint() {
    touchglJNI.TouchGLView_endPaint(swigCPtr, this);
  }

}
