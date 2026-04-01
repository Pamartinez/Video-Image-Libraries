package com.samsung.android.gallery.widget.clip.objectcapture;

import C4.i;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;
import com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureHelper;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.clip.ClipView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.ArrayList;
import java.util.Objects;
import x7.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ObjectCaptureView extends ClipView {
    private static final boolean DEBUG = PocFeatures.isEnabled(PocFeatures.ObjectCaptureDebug);
    private static final Paint DEBUG_PAINT;
    private final StringCompat TAG = new StringCompat(getClass().getSimpleName());
    private ObjectCaptureHelper mObjectHelper;
    private boolean mObjectSelected = false;
    private OnViewLongClickListener mViewLongClickListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnViewLongClickListener {
    }

    static {
        Paint paint = new Paint();
        DEBUG_PAINT = paint;
        paint.setColor(-16711936);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2.0f);
    }

    public ObjectCaptureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void drawDebugView(Canvas canvas) {
        int sourceWidth = this.mObjectHelper.getSourceWidth();
        ArrayList<Rect> boundList = this.mObjectHelper.getBoundList();
        if (boundList != null && !boundList.isEmpty() && this.mDefaultRect != null && sourceWidth > 0) {
            boundList.forEach(new i((Object) this, sourceWidth, (Object) canvas, 8));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$drawDebugView$0(int i2, Canvas canvas, Rect rect) {
        float width = this.mDefaultRect.width() / ((float) i2);
        RectF rectF = this.mDefaultRect;
        float f = rectF.left;
        float f5 = rectF.top;
        canvas.drawRect(new RectF((((float) rect.left) * width) + f, (((float) rect.top) * width) + f5, (((float) rect.right) * width) + f, (((float) rect.bottom) * width) + f5), DEBUG_PAINT);
    }

    private void notifyViewLongClick(boolean z) {
        OnViewLongClickListener onViewLongClickListener = this.mViewLongClickListener;
        if (onViewLongClickListener != null) {
            ((c) onViewLongClickListener).f2745a.lambda$onViewReady$7(z);
        }
    }

    private void onTouchPostHandle(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1) {
            this.mObjectHelper.setScaleState(false);
            this.mObjectHelper.setTranslationState(false);
        } else if (actionMasked == 3) {
            this.mObjectHelper.setScaleState(false);
            this.mObjectHelper.setTranslationState(false);
            notifyToggleConsumed();
        } else if ((actionMasked == 5 || actionMasked == 261) && motionEvent.getPointerCount() >= 2) {
            this.mObjectHelper.setScaleState(false);
            this.mObjectHelper.setTranslationState(false);
            notifyToggleConsumed();
        }
    }

    private void onTouchPreHandle(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.mScaling = false;
            this.mTranslating = false;
            this.mObjectSelected = this.mObjectHelper.isObjectSelected();
        } else if (actionMasked != 2) {
            if (actionMasked == 6) {
                this.mObjectHelper.setScaleState(false);
            }
        } else if (this.mScaling && motionEvent.getPointerCount() > 1) {
            this.mObjectHelper.setScaleState(true);
        } else if (this.mTranslating) {
            this.mObjectHelper.setTranslationState(true);
        }
    }

    public void destroy() {
        super.destroy();
        this.mViewLongClickListener = null;
    }

    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        ObjectCaptureHelper objectCaptureHelper = this.mObjectHelper;
        if (objectCaptureHelper != null) {
            objectCaptureHelper.dispatchDraw(canvas);
        }
    }

    public boolean hasData() {
        ObjectCaptureHelper objectCaptureHelper = this.mObjectHelper;
        if (objectCaptureHelper == null || !objectCaptureHelper.hasData()) {
            return false;
        }
        return true;
    }

    public boolean isObjectSelected() {
        if (!ViewUtils.isVisible(this) || !this.mObjectSelected) {
            return false;
        }
        return true;
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mObjectHelper != null) {
            setScaleX(this.mScale);
            setScaleY(this.mScale);
            setTranslationX(this.mTransX);
            setTranslationY(this.mTransY);
            updateViewLocation();
            setClipBounds(getBounds());
            if (DEBUG) {
                drawDebugView(canvas);
            }
        }
    }

    public boolean onLongClick(float f, float f5) {
        StringCompat stringCompat = this.TAG;
        StringBuilder sb2 = new StringBuilder("onLongClick");
        sb2.append(Logger.v("(" + f + GlobalPostProcInternalPPInterface.SPLIT_REGEX + f5 + ")", this.mObjectHelper));
        Log.d(stringCompat, sb2.toString());
        ObjectCaptureHelper objectCaptureHelper = this.mObjectHelper;
        if (!(objectCaptureHelper == null || this.mDisplayRect == null)) {
            objectCaptureHelper.clearMaskedObjectResult();
            float width = this.mDisplayRect.width() / ((float) this.mObjectHelper.getSourceWidth());
            RectF rectF = this.mDisplayRect;
            int i2 = (int) (((f5 - rectF.top) - ((float) this.mTopMargin)) / width);
            if (this.mObjectHelper.isObjectAt((int) ((f - rectF.left) / width), i2)) {
                this.mObjectHelper.start(getCoordinatedX(f), getCoordinatedY(f5), false, false);
                notifyViewLongClick(true);
                return true;
            }
            notifyViewLongClick(false);
        }
        return false;
    }

    public void onReady(RectF rectF, int[] iArr, int i2, int i7, boolean z) {
        setDisplayRect(rectF, iArr, i2, i7);
        if (this.mObjectHelper == null) {
            Log.e(this.TAG, "onReady failed. null helper");
            return;
        }
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "onReady " + this);
        ObjectCaptureHelper objectCaptureHelper = this.mObjectHelper;
        RectF rectF2 = this.mDefaultRect;
        if (rectF2 != null) {
            rectF = rectF2;
        }
        objectCaptureHelper.setBitmapRect(rectF);
        if (this.mObjectHelper.hasData()) {
            ObjectCaptureHelper objectCaptureHelper2 = this.mObjectHelper;
            objectCaptureHelper2.start(getCoordinatedX(objectCaptureHelper2.getInitX()), getCoordinatedY(this.mObjectHelper.getInitY()), true, z);
        }
    }

    public void onRefresh(RectF rectF, int[] iArr, int i2, int i7, boolean z, boolean z3) {
        setDisplayRect(rectF, iArr, i2, i7);
        if (this.mObjectHelper == null) {
            Log.e(this.TAG, "onRefresh failed, null helper");
            return;
        }
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "onRefresh " + this);
        ObjectCaptureHelper objectCaptureHelper = this.mObjectHelper;
        RectF rectF2 = this.mDefaultRect;
        if (rectF2 != null) {
            rectF = rectF2;
        }
        objectCaptureHelper.refresh(rectF);
        if (hasData() && z3) {
            ObjectCaptureHelper objectCaptureHelper2 = this.mObjectHelper;
            objectCaptureHelper2.start(getCoordinatedX(objectCaptureHelper2.getInitX()), getCoordinatedY(this.mObjectHelper.getInitY()), true, z);
        }
    }

    public boolean onTouch(MotionEvent motionEvent) {
        if (this.mObjectHelper == null || this.mDisplayRect == null) {
            return false;
        }
        onTouchPreHandle(motionEvent);
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        obtain.setLocation(getCoordinatedX(motionEvent.getX()), getCoordinatedY(motionEvent.getY()));
        boolean z = this.mObjectHelper.touch(obtain);
        obtain.recycle();
        if (z) {
            ViewParent parent = getParent();
            ((ViewParent) Objects.requireNonNullElse(parent.getParent(), parent)).requestDisallowInterceptTouchEvent(true);
        }
        onTouchPostHandle(motionEvent);
        return z;
    }

    public void setDisplayRect(RectF rectF, int[] iArr, int i2, int i7) {
        super.setDisplayRect(rectF, iArr, i2, i7);
        ObjectCaptureHelper objectCaptureHelper = this.mObjectHelper;
        if (objectCaptureHelper != null) {
            objectCaptureHelper.setScale(this.mScale);
        }
    }

    public void setObjectCaptureHelper(ObjectCaptureHelper objectCaptureHelper) {
        this.mObjectHelper = objectCaptureHelper;
        if (objectCaptureHelper != null) {
            objectCaptureHelper.init(this);
        }
    }

    public void setOnViewLongClickListener(OnViewLongClickListener onViewLongClickListener) {
        this.mViewLongClickListener = onViewLongClickListener;
    }

    public String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder("OCV[");
        sb2.append(Logger.toVisibilityValue(getVisibility()));
        sb2.append(',');
        sb2.append(this.mObjectHelper);
        sb2.append(",(");
        sb2.append(this.mLocation[0]);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mLocation[1]);
        sb2.append(")");
        String str2 = "";
        if (this.mDisplayRect != null) {
            str = ",dis=" + this.mDisplayRect;
        } else {
            str = str2;
        }
        sb2.append(str);
        if (this.mDefaultRect != null) {
            str2 = ",def=" + this.mDefaultRect;
        }
        return C0212a.p(sb2, str2, "]");
    }
}
