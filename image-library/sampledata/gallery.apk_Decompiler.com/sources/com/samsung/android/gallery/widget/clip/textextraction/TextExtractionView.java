package com.samsung.android.gallery.widget.clip.textextraction;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import com.samsung.android.gallery.module.clip.textextraction.TextExtractionHelper;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.R$color;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.clip.ClipView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import y7.f;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TextExtractionView extends ClipView {
    private static final Paint PAINT_FILL_CLEAR = new Paint(1);
    private final StringCompat TAG = new StringCompat(getClass().getSimpleName());
    private long mDownTime = -1;
    private final Path mHighlightPaths = new Path();
    private boolean mSelected = false;
    private TextExtractionHelper mTextHelper;
    private OnViewClickListener mViewClickListener;
    private OnViewReadyListener mViewReadyListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnViewClickListener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnViewReadyListener {
    }

    public TextExtractionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void clearSelection() {
        TextExtractionHelper textExtractionHelper = this.mTextHelper;
        if (textExtractionHelper != null) {
            textExtractionHelper.clearSelection();
        }
    }

    private Path getPathFromPoint(PointF[] pointFArr) {
        Path path = new Path();
        PointF pointF = pointFArr[0];
        path.moveTo(pointF.x, pointF.y);
        PointF pointF2 = pointFArr[1];
        path.lineTo(pointF2.x, pointF2.y);
        PointF pointF3 = pointFArr[2];
        path.lineTo(pointF3.x, pointF3.y);
        PointF pointF4 = pointFArr[3];
        path.lineTo(pointF4.x, pointF4.y);
        PointF pointF5 = pointFArr[0];
        path.lineTo(pointF5.x, pointF5.y);
        PointF pointF6 = pointFArr[1];
        path.lineTo(pointF6.x, pointF6.y);
        return path;
    }

    private void init() {
        Paint paint = PAINT_FILL_CLEAR;
        paint.setAntiAlias(true);
        paint.setColor(getContext().getResources().getColor(R$color.textextraction_clear_color, (Resources.Theme) null));
        paint.setPathEffect(new CornerPathEffect((float) getContext().getResources().getDimensionPixelSize(R$dimen.textextraction_bg_corner_radius)));
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth((float) getContext().getResources().getDimensionPixelSize(R$dimen.textextraction_bg_stroke_width));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    private void notifyViewClick(boolean z, boolean z3, float f, float f5) {
        if (this.mViewClickListener != null) {
            PreferenceCache.TextExtractionHint.setBoolean(false);
            ((f) this.mViewClickListener).f2757a.onTextExtractionViewClick(z, z3, f, f5);
        }
    }

    private void notifyViewReady(boolean z, boolean z3, float f, float f5) {
        OnViewReadyListener onViewReadyListener = this.mViewReadyListener;
        if (onViewReadyListener != null) {
            ((f) onViewReadyListener).f2757a.onTextExtractionViewReady(z, z3, f, f5);
            this.mViewReadyListener = null;
        }
    }

    private void onTouchPreHandle(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        long j2 = -1;
        if (actionMasked == 0) {
            this.mScaling = false;
            this.mTranslating = false;
            this.mSelected = isSelectedDirectCheck();
            if (motionEvent.getPointerCount() == 1) {
                j2 = System.currentTimeMillis();
            }
            this.mDownTime = j2;
        } else if (actionMasked == 1) {
            if (motionEvent.getPointerCount() == 1 && this.mDownTime != -1 && System.currentTimeMillis() - this.mDownTime < 200) {
                clearSelection();
            }
            this.mTextHelper.setTranslationState(false);
            this.mDownTime = -1;
        } else if (actionMasked != 2) {
            if (actionMasked == 3) {
                clearSelection();
                this.mTextHelper.setScaleState(false);
                this.mTextHelper.setTranslationState(false);
                this.mDownTime = -1;
            } else if (actionMasked == 6) {
                this.mTextHelper.setScaleState(false);
            }
        } else if (this.mScaling && motionEvent.getPointerCount() > 1) {
            this.mTextHelper.setScaleState(true);
        } else if (this.mTranslating) {
            this.mTextHelper.setTranslationState(true);
        }
    }

    private void prepareHighlightPaths() {
        RectF rectF;
        this.mHighlightPaths.reset();
        if (this.mTextHelper.isFullFilterState() && (rectF = this.mDefaultRect) != null) {
            ArrayList<PointF[]> highlightPoints = this.mTextHelper.getHighlightPoints(rectF);
            this.mHighlightPaths.reset();
            if (!highlightPoints.isEmpty()) {
                Iterator<PointF[]> it = highlightPoints.iterator();
                while (it.hasNext()) {
                    this.mHighlightPaths.addPath(getPathFromPoint(it.next()));
                }
            }
        }
    }

    public void destroy() {
        super.destroy();
        this.mViewClickListener = null;
        this.mViewReadyListener = null;
    }

    public boolean hasData() {
        TextExtractionHelper textExtractionHelper = this.mTextHelper;
        if (textExtractionHelper == null) {
            return false;
        }
        if (textExtractionHelper.hasData() || this.mTextHelper.hasBarcodeData()) {
            return true;
        }
        return false;
    }

    public boolean isFullState() {
        TextExtractionHelper textExtractionHelper = this.mTextHelper;
        if (textExtractionHelper == null || !textExtractionHelper.isFullState()) {
            return false;
        }
        return true;
    }

    public boolean isFullStateExceptFilter() {
        TextExtractionHelper textExtractionHelper = this.mTextHelper;
        if (textExtractionHelper == null || !textExtractionHelper.isFullStateExceptFilter()) {
            return false;
        }
        return true;
    }

    public boolean isSelectedDirectCheck() {
        TextExtractionHelper textExtractionHelper = this.mTextHelper;
        if (textExtractionHelper == null) {
            return false;
        }
        if (textExtractionHelper.isTextSelected() || this.mTextHelper.isBarcodeSelected()) {
            return true;
        }
        return false;
    }

    public boolean isToggleConsumable() {
        if (!ViewUtils.isVisible(this)) {
            return false;
        }
        if (this.mSelected || this.mTextHelper.isTranslationOn()) {
            return true;
        }
        return false;
    }

    public boolean onClick(float f, float f5, boolean z) {
        StringCompat stringCompat = this.TAG;
        StringBuilder sb2 = new StringBuilder("onClick");
        sb2.append(Logger.v("(" + f + GlobalPostProcInternalPPInterface.SPLIT_REGEX + f5 + ")", Boolean.valueOf(z), this.mTextHelper));
        Log.d(stringCompat, sb2.toString());
        if (this.mTextHelper == null || !hasData()) {
            return false;
        }
        boolean start = this.mTextHelper.start(getCoordinatedX(f), getCoordinatedY(f5), z, false);
        notifyViewClick(z, start, f, f5);
        return start;
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (hasData() && this.mTextHelper != null) {
            setScaleX(this.mScale);
            setScaleY(this.mScale);
            setTranslationX(this.mTransX);
            setTranslationY(this.mTransY);
            updateViewLocation();
            setClipBounds(getBounds());
            if (!this.mTextHelper.isFullFilterState() || this.mHighlightPaths.isEmpty()) {
                this.mTextHelper.draw(canvas);
                return;
            }
            canvas.drawColor(getContext().getResources().getColor(R$color.textextraction_dim_color, (Resources.Theme) null));
            Paint paint = PAINT_FILL_CLEAR;
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            canvas.drawPath(this.mHighlightPaths, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
            canvas.drawPath(this.mHighlightPaths, paint);
        }
    }

    public void onReady(RectF rectF, int[] iArr, int i2, int i7, boolean z) {
        boolean z3;
        setDisplayRect(rectF, iArr, i2, i7);
        if (this.mTextHelper == null) {
            Log.e(this.TAG, "onReady failed. no helper");
            notifyViewReady(true, false, -1.0f, -1.0f);
            return;
        }
        Log.d(this.TAG, "onReady " + this);
        TextExtractionHelper textExtractionHelper = this.mTextHelper;
        RectF rectF2 = this.mDefaultRect;
        if (rectF2 != null) {
            rectF = rectF2;
        }
        textExtractionHelper.ready(rectF);
        prepareHighlightPaths();
        if (hasData()) {
            z3 = this.mTextHelper.start(this.mLocation, this.mTopMargin, this.mScale, z);
        } else {
            z3 = false;
        }
        notifyViewReady(false, z3, this.mTextHelper.getInitX(), this.mTextHelper.getInitY());
    }

    public void onRefresh(RectF rectF, int[] iArr, int i2, int i7, boolean z, boolean z3) {
        boolean z7;
        setDisplayRect(rectF, iArr, i2, i7);
        if (this.mTextHelper == null) {
            Log.e(this.TAG, "onRefresh failed, null textHelper");
            return;
        }
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "onRefresh " + this);
        TextExtractionHelper textExtractionHelper = this.mTextHelper;
        RectF rectF2 = this.mDefaultRect;
        if (rectF2 != null) {
            rectF = rectF2;
        }
        textExtractionHelper.refresh(rectF);
        prepareHighlightPaths();
        if (!hasData() || !z3) {
            z7 = false;
        } else {
            z7 = this.mTextHelper.start(this.mLocation, this.mTopMargin, this.mScale, z);
        }
        notifyViewReady(true, z7, this.mTextHelper.getInitX(), this.mTextHelper.getInitY());
    }

    public boolean onTouch(MotionEvent motionEvent) {
        boolean z = false;
        if (hasData() && this.mDisplayRect != null) {
            onTouchPreHandle(motionEvent);
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            obtain.setLocation(getCoordinatedX(motionEvent.getX()), getCoordinatedY(motionEvent.getY()));
            if (this.mTextHelper.touch(obtain) && !this.mTextHelper.isTranslationOn()) {
                z = true;
            }
            obtain.recycle();
            if (z) {
                ViewParent parent = getParent();
                ((ViewParent) Objects.requireNonNullElse(parent.getParent(), parent)).requestDisallowInterceptTouchEvent(true);
            }
        }
        return z;
    }

    public void setDisplayRect(RectF rectF, int[] iArr, int i2, int i7) {
        super.setDisplayRect(rectF, iArr, i2, i7);
        TextExtractionHelper textExtractionHelper = this.mTextHelper;
        if (textExtractionHelper != null) {
            textExtractionHelper.setScale(this.mScale);
        }
    }

    public void setLogTag(String str) {
        this.TAG.setTag(str);
    }

    public void setViewClickListener(OnViewClickListener onViewClickListener) {
        this.mViewClickListener = onViewClickListener;
    }

    public void setViewReadyListener(OnViewReadyListener onViewReadyListener) {
        this.mViewReadyListener = onViewReadyListener;
    }

    public void setVisionTextHelper(TextExtractionHelper textExtractionHelper) {
        this.mTextHelper = textExtractionHelper;
        if (textExtractionHelper != null) {
            textExtractionHelper.init(this, (ViewGroup) ((View) getParent()).findViewById(R$id.capsule_layout), (LinearLayout) ((View) getParent()).findViewById(R$id.lang_manage_layout));
        }
    }

    public String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder("LTV[");
        sb2.append(Logger.toVisibilityValue(getVisibility()));
        sb2.append(',');
        sb2.append(this.mTextHelper);
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
