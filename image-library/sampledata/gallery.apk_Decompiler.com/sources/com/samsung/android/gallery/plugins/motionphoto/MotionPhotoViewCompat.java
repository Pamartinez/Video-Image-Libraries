package com.samsung.android.gallery.plugins.motionphoto;

import Ba.i;
import Ba.j;
import Ba.k;
import Ba.l;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.VideoView;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FlingAnimation;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.OnSecondaryClickListener;
import com.samsung.android.gallery.widget.OnTranslationListener;
import com.samsung.android.gallery.widget.animator.PathInterpolator;
import com.samsung.android.gallery.widget.animator.ValueAnimatorIgnoreSetting;
import com.samsung.android.gallery.widget.capture.ClipViewDelegate;
import com.samsung.android.gallery.widget.capture.IClipRootView;
import com.samsung.android.gallery.widget.debug.DebugDecodingInfo;
import com.samsung.android.gallery.widget.gesture.GestureIdentifier;
import com.samsung.android.gallery.widget.gesture.PinchGestureDetector;
import com.samsung.android.gallery.widget.photoview.OnFlingListener;
import com.samsung.android.gallery.widget.photoview.OnViewerExitGestureListener;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.videoview.DoubleTapSeekListener;
import com.samsung.android.gallery.widget.videoview.ScaleEndListener;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;
import u7.C0523d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MotionPhotoViewCompat extends VideoView implements IClipRootView {
    private static final boolean IS_RTL = Features.isEnabled(Features.IS_RTL);
    private static final boolean SUPPORT_DEBUG_VIEW = PocFeatures.isEnabled(PocFeatures.RegionDecodingInfo);
    /* access modifiers changed from: private */
    public static final boolean SUPPORT_MOTION_PHOTO_ZOOM = PreferenceFeatures.OneUi30.MOTION_PHOTO_ZOOM;
    protected final StringCompat TAG;
    /* access modifiers changed from: private */
    public Supplier<String> mAccessibilityUsageHint;
    private boolean mCaptureViewHandleEvent;
    /* access modifiers changed from: private */
    public final ClipViewDelegate mClipViewDelegate;
    /* access modifiers changed from: private */
    public Supplier<String> mContentDescription;
    /* access modifiers changed from: private */
    public float mCurrentScale;
    private DebugDecodingInfo mDebugDecodingInfo;
    private DoubleTapSeekListener mDoubleTapSeekListener;
    /* access modifiers changed from: private */
    public OnViewerExitGestureListener mExitListener;
    /* access modifiers changed from: private */
    public boolean mFixedFocusX;
    /* access modifiers changed from: private */
    public boolean mFixedFocusY;
    /* access modifiers changed from: private */
    public FlingAnimation mFlingAnimX;
    /* access modifiers changed from: private */
    public FlingAnimation mFlingAnimY;
    private final GestureIdentifier mGestureIdentifier;
    private OnFlingListener mOnFlingListener;
    /* access modifiers changed from: private */
    public View.OnLongClickListener mOnLongClickListener;
    private OnSecondaryClickListener mOnSecondaryClickListener;
    private OnTranslationListener mOnTranslationListener;
    /* access modifiers changed from: private */
    public boolean mOnceScaledUp;
    private int mOrientationForDebug;
    /* access modifiers changed from: private */
    public final RectF mParentRect;
    private final PinchGestureDetector mPinchGestureDetector;
    private boolean mResetScaleOnLayout;
    /* access modifiers changed from: private */
    public ScaleEndListener mScaleEndListener;
    private final ViewTreeObserver.OnScrollChangedListener mScrollChangedListener;
    private boolean mSetDefaultPosition;
    /* access modifiers changed from: private */
    public float mStartFocusX;
    /* access modifiers changed from: private */
    public float mStartFocusY;
    /* access modifiers changed from: private */
    public float mStartH;
    /* access modifiers changed from: private */
    public float mStartScale;
    /* access modifiers changed from: private */
    public float mStartW;
    /* access modifiers changed from: private */
    public float mStartX;
    /* access modifiers changed from: private */
    public float mStartY;
    /* access modifiers changed from: private */
    public SurfaceHolder.Callback mSurfaceHolderCallback;
    protected final AtomicBoolean mSurfaceReady;
    private boolean mTouchDown;
    protected final MotionPhotoViewImpl mVideoViewImpl;
    /* access modifiers changed from: private */
    public ViewParent mViewParent;
    private final Animator.AnimatorListener mZoomAnimatorListener;

    public MotionPhotoViewCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* access modifiers changed from: private */
    public float calculateX(float f, float f5) {
        float f8 = this.mStartX;
        float f10 = this.mStartFocusX;
        float f11 = this.mStartScale;
        return (f - f10) + (f8 - ((f5 - f11) * (((f10 - f8) - (this.mStartW / 2.0f)) / f11)));
    }

    /* access modifiers changed from: private */
    public float calculateY(float f, float f5) {
        float f8 = this.mStartY;
        float f10 = this.mStartFocusY;
        float f11 = this.mStartScale;
        return (f - f10) + (f8 - ((f5 - f11) * (((f10 - f8) - (this.mStartH / 2.0f)) / f11)));
    }

    /* access modifiers changed from: private */
    public boolean doubleTapSeek(MotionEvent motionEvent) {
        if (this.mDoubleTapSeekListener == null || !isSurfaceReady()) {
            return false;
        }
        if (motionEvent.getX() < ((float) getWidth()) * 0.2f) {
            if (IS_RTL) {
                this.mDoubleTapSeekListener.onDoubleTapFf();
            } else {
                this.mDoubleTapSeekListener.onDoubleTapRew();
            }
            return true;
        } else if (motionEvent.getX() <= ((float) getWidth()) * 0.8f) {
            return false;
        } else {
            if (IS_RTL) {
                this.mDoubleTapSeekListener.onDoubleTapRew();
            } else {
                this.mDoubleTapSeekListener.onDoubleTapFf();
            }
            return true;
        }
    }

    private void drawDebugInfo(DebugDecodingInfo debugDecodingInfo, Canvas canvas) {
        if (debugDecodingInfo != null) {
            debugDecodingInfo.createPaints();
            debugDecodingInfo.init();
            DebugDecodingInfo debugDecodingInfo2 = debugDecodingInfo;
            Canvas canvas2 = canvas;
            debugDecodingInfo2.drawDebugText(canvas2, getScaleX(), this.mStartScale, 5.0f, new PointF(getX(), getY()), new PointF(((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f), this.mOrientationForDebug, "V");
            debugDecodingInfo2.drawDebug(canvas2, " DataPos=" + this.TAG.getTag());
            Rect rect = new Rect();
            getDrawingRect(rect);
            debugDecodingInfo2.drawDebugLine(canvas2, rect);
        }
    }

    /* access modifiers changed from: private */
    public PointF ensureBoundary(float f, float f5, float f8) {
        Pair<Float, Float> xRange = getXRange(f8);
        float max = Math.max(((Float) xRange.first).floatValue(), Math.min(((Float) xRange.second).floatValue(), f));
        Pair<Float, Float> yRange = getYRange(f8);
        return new PointF(max, Math.max(((Float) yRange.first).floatValue(), Math.min(((Float) yRange.second).floatValue(), f5)));
    }

    /* access modifiers changed from: private */
    public float ensureScale(float f) {
        return Math.max(minScale(), Math.min(5.0f, f));
    }

    /* access modifiers changed from: private */
    public void flingTo(float f, float f5) {
        try {
            if (!isFlickToNext(f, f5)) {
                Pair<Float, Float> xRange = getXRange(getScaleX());
                FlingAnimation flingAnimation = this.mFlingAnimX;
                if (flingAnimation == null) {
                    this.mFlingAnimX = new FlingAnimation(this, DynamicAnimation.f986X);
                } else {
                    flingAnimation.cancel();
                }
                ((FlingAnimation) this.mFlingAnimX.setStartVelocity(f).setMinValue(((Float) xRange.first).floatValue()).setMaxValue(((Float) xRange.second).floatValue()).setFriction(2.0f).addUpdateListener(new l(this, 0))).start();
                Pair<Float, Float> yRange = getYRange(getScaleY());
                FlingAnimation flingAnimation2 = this.mFlingAnimY;
                if (flingAnimation2 == null) {
                    this.mFlingAnimY = new FlingAnimation(this, DynamicAnimation.Y);
                } else {
                    flingAnimation2.cancel();
                }
                ((FlingAnimation) this.mFlingAnimY.setStartVelocity(f5).setMinValue(((Float) yRange.first).floatValue()).setMaxValue(((Float) yRange.second).floatValue()).setFriction(2.0f).addUpdateListener(new l(this, 1))).start();
            }
        } catch (IllegalArgumentException e) {
            Log.e(this.TAG, e.toString());
        }
    }

    private PointF getDefaultPosition() {
        return new PointF((this.mParentRect.width() - ((float) getWidth())) / 2.0f, (this.mParentRect.height() - ((float) getHeight())) / 2.0f);
    }

    private float getExtraXForZoomToPaddingAreaFirst(float f) {
        return Math.max(0.0f, getGapRatio(this.mParentRect.width() - ((float) ((int) (((float) this.mVideoViewImpl.getSurfaceWidth()) * f))), this.mParentRect.width() - ((float) ((int) (((float) this.mVideoViewImpl.getSurfaceWidth()) * minScale()))))) * ((float) (getPaddingLeft() - getPaddingRight()));
    }

    private float getExtraYForZoomToPaddingAreaFirst(float f) {
        return Math.max(0.0f, getGapRatio(this.mParentRect.height() - ((float) ((int) (((float) this.mVideoViewImpl.getSurfaceHeight()) * f))), this.mParentRect.height() - ((float) ((int) (((float) this.mVideoViewImpl.getSurfaceHeight()) * minScale()))))) * ((float) (getPaddingTop() - getPaddingBottom()));
    }

    private float getGapRatio(float f, float f5) {
        if (f5 != 0.0f) {
            return f / f5;
        }
        return 1.0f;
    }

    private int getHorizontalPadding() {
        return getPaddingRight() + getPaddingLeft();
    }

    private Pair<Integer, Integer> getImageXRange(int i2) {
        float floatValue = ((Float) getXRange(getScaleX()).second).floatValue();
        float scaleX = ((float) i2) / (getScaleX() * ((float) getWidth()));
        float f = -(getX() - floatValue);
        float width = this.mParentRect.width() + f;
        return new Pair<>(Integer.valueOf(Math.max(0, Math.min(i2, Math.round(f * scaleX)))), Integer.valueOf(Math.max(0, Math.min(i2, Math.round(scaleX * width)))));
    }

    private Pair<Integer, Integer> getImageYRange(int i2) {
        float floatValue = ((Float) getYRange(getScaleY()).second).floatValue();
        float scaleY = ((float) i2) / (getScaleY() * ((float) getHeight()));
        float f = -(getY() - floatValue);
        float height = this.mParentRect.height() + f;
        return new Pair<>(Integer.valueOf(Math.max(0, Math.min(i2, Math.round(f * scaleY)))), Integer.valueOf(Math.max(0, Math.min(i2, Math.round(scaleY * height)))));
    }

    private int getVerticalPadding() {
        return getPaddingTop() + getPaddingBottom();
    }

    private Pair<Float, Float> getXRange(float f) {
        float width = ((float) getWidth()) * f;
        float width2 = ((this.mParentRect.width() - ((float) getWidth())) + getExtraXForZoomToPaddingAreaFirst(f)) / 2.0f;
        float max = Math.max(0.0f, (width - this.mParentRect.width()) / 2.0f);
        return new Pair<>(Float.valueOf(width2 - max), Float.valueOf(width2 + max));
    }

    private Pair<Float, Float> getYRange(float f) {
        float height = ((float) getHeight()) * f;
        float height2 = ((this.mParentRect.height() - ((float) getHeight())) + getExtraYForZoomToPaddingAreaFirst(f)) / 2.0f;
        float max = Math.max(0.0f, (height - this.mParentRect.height()) / 2.0f);
        return new Pair<>(Float.valueOf(height2 - max), Float.valueOf(height2 + max));
    }

    private boolean isFlickToNext(float f, float f5) {
        Pair<Float, Float> xRange = getXRange(getScaleX());
        boolean z = false;
        if ((getX() != ((Float) xRange.first).floatValue() && getX() != ((Float) xRange.second).floatValue()) || Math.abs(f) <= 900.0f || Math.abs(f) <= Math.abs(f5)) {
            return false;
        }
        if (f < 0.0f) {
            z = true;
        }
        onFling(z);
        return true;
    }

    /* access modifiers changed from: private */
    public boolean isPinchShrinkExitCondition() {
        if (this.mOnceScaledUp || this.mStartScale > minScale() || this.mCurrentScale >= 0.95f) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public boolean isScaleAvailable() {
        OnTranslationListener onTranslationListener = this.mOnTranslationListener;
        if (onTranslationListener == null || onTranslationListener.isTranslated()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$flingTo$2(DynamicAnimation dynamicAnimation, float f, float f5) {
        this.mClipViewDelegate.onDraw();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$flingTo$3(DynamicAnimation dynamicAnimation, float f, float f5) {
        this.mClipViewDelegate.onDraw();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onLayout$0() {
        setDefaultPosition(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$zoomTo$1(float f, float f5, float f8, PointF pointF, float f10, ValueAnimator valueAnimator) {
        Float f11 = (Float) valueAnimator.getAnimatedValue();
        float floatValue = f - (f11.floatValue() * (f - f5));
        setScaleX(floatValue);
        setScaleY(floatValue);
        float floatValue2 = f8 - (f11.floatValue() * (f8 - pointF.x));
        float floatValue3 = f11.floatValue();
        setX(floatValue2);
        setY(f10 - (floatValue3 * (f10 - pointF.y)));
        this.mClipViewDelegate.onDraw();
    }

    /* access modifiers changed from: private */
    public float minScale() {
        boolean z;
        float width = (float) getParentView().getWidth();
        float height = (float) getParentView().getHeight();
        float surfaceWidth = (float) this.mVideoViewImpl.getSurfaceWidth();
        float surfaceHeight = (float) this.mVideoViewImpl.getSurfaceHeight();
        boolean z3 = false;
        if (getVerticalPadding() == 0 && getHorizontalPadding() == 0) {
            z = true;
        } else {
            z = false;
        }
        if (surfaceWidth == 0.0f || surfaceHeight == 0.0f) {
            z3 = true;
        }
        if (z || z3) {
            return 1.0f;
        }
        return Math.min((width - ((float) getHorizontalPadding())) / surfaceWidth, (height - ((float) getVerticalPadding())) / surfaceHeight);
    }

    private void onTouchEventInternal(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        boolean z = true;
        if (action != 0) {
            if (action != 1) {
                if (action != 5) {
                    if (action != 6) {
                        if (action != 261) {
                            if (action != 262) {
                                return;
                            }
                        }
                    }
                }
            }
            if (motionEvent.getPointerCount() - 1 <= 0) {
                z = false;
            }
            this.mTouchDown = z;
            return;
        }
        this.mTouchDown = true;
    }

    private void setStartValueForScale() {
        this.mStartFocusX = this.mParentRect.width() / 2.0f;
        this.mStartFocusY = this.mParentRect.height() / 2.0f;
        this.mStartX = getX();
        this.mStartY = getY();
        this.mStartW = (float) getWidth();
        this.mStartH = (float) getHeight();
        this.mStartScale = getScaleX();
    }

    /* access modifiers changed from: private */
    public void zoomTo(float f, PointF pointF) {
        float scaleX = getScaleX();
        float x9 = getX();
        float y = getY();
        PointF ensureBoundary = ensureBoundary(pointF.x, pointF.y, f);
        ValueAnimator ofFloat = ValueAnimatorIgnoreSetting.ofFloat(0.0f, 1.0f);
        ofFloat.setDuration(160);
        ofFloat.setInterpolator(PathInterpolator.create(PathInterpolator.Type.TYPE_SINE_IN_OUT_70));
        ofFloat.addUpdateListener(new k(this, scaleX, f, x9, ensureBoundary, y));
        ofFloat.addListener(this.mZoomAnimatorListener);
        ofFloat.start();
    }

    public int getBottomMarginFromSupplier() {
        ViewGroup parentView = getParentView();
        if (parentView != null) {
            return ((ViewGroup.MarginLayoutParams) parentView.getLayoutParams()).bottomMargin;
        }
        return ((ViewGroup.MarginLayoutParams) getLayoutParams()).bottomMargin;
    }

    public Rect getCropRectOnImage(int i2, int i7) {
        Pair<Integer, Integer> imageXRange = getImageXRange(i2);
        Pair<Integer, Integer> imageYRange = getImageYRange(i7);
        return new Rect(((Integer) imageXRange.first).intValue(), ((Integer) imageYRange.first).intValue(), ((Integer) imageXRange.second).intValue(), ((Integer) imageYRange.second).intValue());
    }

    public RectF getDisplayMinRect() {
        float f;
        float f5;
        View view = (View) this.mViewParent;
        if (view == null) {
            return new RectF((float) getLeft(), (float) getTop(), (float) getRight(), (float) getBottom());
        }
        float width = (float) view.getWidth();
        float height = (float) view.getHeight();
        float width2 = (float) getWidth();
        float height2 = (float) getHeight();
        if (width / height > width2 / height2) {
            f5 = (width2 * height) / height2;
            f = height;
        } else {
            f = (height2 * width) / width2;
            f5 = width;
        }
        float f8 = (width / 2.0f) - (f5 / 2.0f);
        float f10 = (height / 2.0f) - (f / 2.0f);
        return new RectF(f8, f10, f5 + f8, f + f10);
    }

    public RectF getDisplayRect() {
        float right = ((float) (getRight() - getLeft())) / 2.0f;
        float bottom = ((float) (getBottom() - getTop())) / 2.0f;
        float scaleX = (getScaleX() * ((float) getWidth())) / 2.0f;
        float scaleX2 = (getScaleX() * ((float) getHeight())) / 2.0f;
        return new RectF(getX() + (right - scaleX), getY() + (bottom - scaleX2), getX() + right + scaleX, getY() + bottom + scaleX2);
    }

    public void getLocation(int[] iArr) {
        ViewGroup parentView = getParentView();
        if (parentView != null) {
            parentView.getLocationInWindow(iArr);
        }
    }

    public ViewGroup getParentView() {
        return (ViewGroup) getParent();
    }

    public int getTopMarginFromSupplier() {
        ViewGroup parentView = getParentView();
        if (parentView != null) {
            return ((ViewGroup.MarginLayoutParams) parentView.getLayoutParams()).topMargin;
        }
        return ((ViewGroup.MarginLayoutParams) getLayoutParams()).topMargin;
    }

    public int[] getVideoSize() {
        return this.mVideoViewImpl.getVideoSize();
    }

    public boolean isAlreadyUp() {
        return !this.mTouchDown;
    }

    public boolean isMinScale() {
        if (getScaleX() <= minScale()) {
            return true;
        }
        return false;
    }

    public boolean isSurfaceReady() {
        return this.mSurfaceReady.get();
    }

    public boolean isZoomed() {
        if (getScaleX() > minScale()) {
            return true;
        }
        return false;
    }

    public void onAttachedToWindow() {
        getViewTreeObserver().addOnScrollChangedListener(this.mScrollChangedListener);
        super.onAttachedToWindow();
    }

    public void onDetachedFromWindow() {
        getViewTreeObserver().removeOnScrollChangedListener(this.mScrollChangedListener);
        super.onDetachedFromWindow();
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (SUPPORT_DEBUG_VIEW) {
            drawDebugInfo(this.mDebugDecodingInfo, canvas);
        }
        this.mClipViewDelegate.onDraw();
    }

    public void onFling(boolean z) {
        if (this.mOnFlingListener != null && ViewUtils.isVisible(this)) {
            this.mOnFlingListener.onFling(z);
        }
    }

    public void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        super.onLayout(z, i2, i7, i8, i10);
        if (z) {
            if (this.mResetScaleOnLayout) {
                this.mResetScaleOnLayout = false;
                resetScale();
            }
            ViewGroup parentView = getParentView();
            this.mParentRect.set(parentView.getX(), parentView.getY(), parentView.getX() + ((float) parentView.getWidth()), parentView.getY() + ((float) parentView.getHeight()));
            PointF ensureBoundary = ensureBoundary(getX(), getY(), getScaleX());
            setX(ensureBoundary.x);
            setY(ensureBoundary.y);
            this.mClipViewDelegate.onDraw();
            if (this.mSetDefaultPosition) {
                post(new j(this, 0));
                this.mSetDefaultPosition = false;
            }
        }
    }

    public void onMeasure(int i2, int i7) {
        if (this.mVideoViewImpl.onMeasure(i2, i7)) {
            super.setMeasuredDimension(this.mVideoViewImpl.getSurfaceWidth(), this.mVideoViewImpl.getSurfaceHeight());
        } else {
            super.onMeasure(i2, i7);
        }
    }

    public boolean onTouchCapture(MotionEvent motionEvent) {
        boolean z = false;
        if (!this.mClipViewDelegate.hasData()) {
            return false;
        }
        boolean onTouch = this.mClipViewDelegate.onTouch(motionEvent);
        this.mCaptureViewHandleEvent = onTouch;
        ViewParent viewParent = this.mViewParent;
        if (viewParent != null) {
            if (onTouch || isZoomed() || motionEvent.getPointerCount() > 1) {
                z = true;
            }
            viewParent.requestDisallowInterceptTouchEvent(z);
        }
        return this.mCaptureViewHandleEvent;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getButtonState() == 2 && motionEvent.getAction() == 0 && this.mOnSecondaryClickListener != null) {
            Log.d(this.TAG, "onTouchEvent : BUTTON_SECONDARY");
            ((C0523d) this.mOnSecondaryClickListener).a(motionEvent);
            return true;
        }
        if (SUPPORT_DEBUG_VIEW) {
            invalidate();
        }
        onTouchEventInternal(motionEvent);
        if (onTouchCapture(motionEvent)) {
            return true;
        }
        return false;
    }

    public void resetScale() {
        setScaleX(minScale());
        setScaleY(minScale());
        setScale(minScale());
    }

    public void setAccessibility() {
        setAccessibilityDelegate(new View.AccessibilityDelegate() {
            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                CharSequence charSequence;
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                if (MotionPhotoViewCompat.this.mAccessibilityUsageHint != null) {
                    charSequence = (CharSequence) MotionPhotoViewCompat.this.mAccessibilityUsageHint.get();
                } else {
                    charSequence = "";
                }
                accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(16, charSequence));
            }

            public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
                CharSequence charSequence;
                super.onPopulateAccessibilityEvent(view, accessibilityEvent);
                if (accessibilityEvent.getEventType() == 32768) {
                    MotionPhotoViewCompat motionPhotoViewCompat = MotionPhotoViewCompat.this;
                    if (motionPhotoViewCompat.mContentDescription != null) {
                        charSequence = (CharSequence) MotionPhotoViewCompat.this.mContentDescription.get();
                    } else {
                        charSequence = "";
                    }
                    motionPhotoViewCompat.setContentDescription(charSequence);
                }
            }
        });
    }

    public void setDebugText(int i2) {
        this.mOrientationForDebug = i2;
    }

    public void setDefaultPosition() {
        setDefaultPosition(true);
    }

    public void setDoubleTapSeekListener(DoubleTapSeekListener doubleTapSeekListener) {
        this.mDoubleTapSeekListener = doubleTapSeekListener;
    }

    public void setLogTag(Object obj) {
        this.TAG.setTag(obj);
    }

    public void setOnFlingListener(OnFlingListener onFlingListener) {
        this.mOnFlingListener = onFlingListener;
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.mOnLongClickListener = onLongClickListener;
    }

    public void setOnSecondaryClickListener(OnSecondaryClickListener onSecondaryClickListener) {
        this.mOnSecondaryClickListener = onSecondaryClickListener;
    }

    public void setPadding(int i2, int i7, int i8, int i10) {
        this.mResetScaleOnLayout = isMinScale();
        super.setPadding(i2, i7, i8, i10);
    }

    public void setPinchShrinkSupport(OnViewerExitGestureListener onViewerExitGestureListener) {
        this.mExitListener = onViewerExitGestureListener;
    }

    public void setScale(float f) {
        this.mVideoViewImpl.setScale(f);
    }

    public void setScaleEndListener(ScaleEndListener scaleEndListener) {
        this.mScaleEndListener = scaleEndListener;
    }

    public void setScaleRelative(float f) {
        setStartValueForScale();
        float ensureScale = ensureScale(getScaleX() * f);
        zoomTo(ensureScale, new PointF(calculateX(this.mStartFocusX, ensureScale), calculateY(this.mStartFocusY, ensureScale)));
    }

    public void setSurfaceHolderCallback(SurfaceHolder.Callback callback) {
        this.mSurfaceHolderCallback = callback;
    }

    public void setTranslationListener(OnTranslationListener onTranslationListener) {
        this.mOnTranslationListener = onTranslationListener;
    }

    public void setVideoSize(int i2, int i7) {
        this.mVideoViewImpl.setVideoSize(i2, i7);
    }

    public void setViewParent(ViewParent viewParent) {
        this.mViewParent = viewParent;
    }

    public void zoomToWithoutAnimation(float f, PointF pointF) {
        PointF ensureBoundary = ensureBoundary(pointF.x, pointF.y, f);
        setScaleX(f);
        setScaleY(f);
        setX(ensureBoundary.x);
        setY(ensureBoundary.y);
        this.mClipViewDelegate.onDraw();
    }

    public MotionPhotoViewCompat(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public void setDefaultPosition(boolean z) {
        if (this.mParentRect.height() <= 0.0f || getHeight() <= 0) {
            setScaleX(minScale());
            setScaleY(minScale());
            this.mClipViewDelegate.onDraw();
        } else if (z) {
            zoomTo(minScale(), getDefaultPosition());
        } else {
            zoomToWithoutAnimation(minScale(), getDefaultPosition());
        }
    }

    public MotionPhotoViewCompat(Context context, AttributeSet attributeSet, int i2, int i7) {
        super(context, attributeSet, i2, i7);
        this.TAG = new StringCompat(getClass().getSimpleName());
        this.mVideoViewImpl = new MotionPhotoViewImpl();
        this.mOrientationForDebug = 0;
        this.mScrollChangedListener = new i(this);
        this.mStartScale = 1.0f;
        this.mParentRect = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        this.mSurfaceReady = new AtomicBoolean(false);
        this.mClipViewDelegate = new ClipViewDelegate(this);
        this.mZoomAnimatorListener = new Animator.AnimatorListener() {
            public void onAnimationEnd(Animator animator) {
                if (MotionPhotoViewCompat.this.mScaleEndListener != null) {
                    ((C0523d) MotionPhotoViewCompat.this.mScaleEndListener).b();
                }
            }

            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
            }
        };
        this.mCaptureViewHandleEvent = false;
        if (SUPPORT_DEBUG_VIEW) {
            this.mDebugDecodingInfo = new DebugDecodingInfo(context);
            setWillNotDraw(false);
        }
        GestureIdentifier gestureIdentifier = new GestureIdentifier(context, new GestureIdentifier.SimpleGestureListener() {
            public boolean onDoubleTap(MotionEvent motionEvent) {
                if (MotionPhotoViewCompat.this.doubleTapSeek(motionEvent) || (!MotionPhotoViewCompat.this.isSurfaceReady() && !MotionPhotoViewCompat.SUPPORT_MOTION_PHOTO_ZOOM)) {
                    return true;
                }
                if (!MotionPhotoViewCompat.this.isScaleAvailable()) {
                    return false;
                }
                if (MotionPhotoViewCompat.this.getScaleX() == MotionPhotoViewCompat.this.minScale()) {
                    MotionPhotoViewCompat.this.zoomTo(2.31f, new PointF(((MotionPhotoViewCompat.this.mParentRect.width() - (((float) MotionPhotoViewCompat.this.getWidth()) / 2.0f)) - motionEvent.getX()) * 1.31f, ((MotionPhotoViewCompat.this.mParentRect.height() - (((float) MotionPhotoViewCompat.this.getHeight()) / 2.0f)) - motionEvent.getY()) * 1.31f));
                    return true;
                }
                MotionPhotoViewCompat.this.setDefaultPosition();
                return true;
            }

            public boolean onDown(MotionEvent motionEvent) {
                if (MotionPhotoViewCompat.this.mFlingAnimX != null) {
                    MotionPhotoViewCompat.this.mFlingAnimX.cancel();
                }
                if (MotionPhotoViewCompat.this.mFlingAnimY != null) {
                    MotionPhotoViewCompat.this.mFlingAnimY.cancel();
                }
                if (MotionPhotoViewCompat.this.mViewParent == null) {
                    return true;
                }
                MotionPhotoViewCompat.this.mViewParent.requestDisallowInterceptTouchEvent(MotionPhotoViewCompat.this.isZoomed());
                return true;
            }

            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f5) {
                if (!MotionPhotoViewCompat.this.isZoomed()) {
                    return false;
                }
                MotionPhotoViewCompat.this.flingTo(f, f5);
                return true;
            }

            public void onLongPress(MotionEvent motionEvent) {
                if (MotionPhotoViewCompat.this.mClipViewDelegate.onLongPress(motionEvent.getX(), motionEvent.getY())) {
                    MotionPhotoViewCompat.this.invalidate();
                } else if (MotionPhotoViewCompat.this.mOnLongClickListener != null) {
                    MotionPhotoViewCompat.this.mOnLongClickListener.onLongClick(this);
                }
            }

            public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
                float f;
                float f5;
                if (!MotionPhotoViewCompat.this.isSurfaceReady() && !MotionPhotoViewCompat.SUPPORT_MOTION_PHOTO_ZOOM) {
                    return true;
                }
                if (!MotionPhotoViewCompat.this.isScaleAvailable()) {
                    return false;
                }
                float scaleFactor = scaleGestureDetector.getScaleFactor();
                if (!Float.isInfinite(scaleFactor) && !Float.isNaN(scaleFactor)) {
                    MotionPhotoViewCompat motionPhotoViewCompat = MotionPhotoViewCompat.this;
                    motionPhotoViewCompat.mCurrentScale = motionPhotoViewCompat.mCurrentScale * scaleFactor;
                }
                if (!MotionPhotoViewCompat.this.mOnceScaledUp && MotionPhotoViewCompat.this.mStartScale <= MotionPhotoViewCompat.this.minScale() && MotionPhotoViewCompat.this.mCurrentScale > MotionPhotoViewCompat.this.mStartScale) {
                    MotionPhotoViewCompat.this.mOnceScaledUp = true;
                }
                if (MotionPhotoViewCompat.this.mExitListener == null || !MotionPhotoViewCompat.this.isPinchShrinkExitCondition() || !ViewUtils.isVisible(this)) {
                    MotionPhotoViewCompat motionPhotoViewCompat2 = MotionPhotoViewCompat.this;
                    float L = motionPhotoViewCompat2.ensureScale(motionPhotoViewCompat2.mCurrentScale);
                    MotionPhotoViewCompat.this.setScaleX(L);
                    MotionPhotoViewCompat.this.setScaleY(L);
                    if (MotionPhotoViewCompat.this.mViewParent != null && MotionPhotoViewCompat.this.isZoomed()) {
                        MotionPhotoViewCompat.this.mViewParent.requestDisallowInterceptTouchEvent(true);
                    }
                    MotionPhotoViewCompat motionPhotoViewCompat3 = MotionPhotoViewCompat.this;
                    if (motionPhotoViewCompat3.mFixedFocusX) {
                        f = MotionPhotoViewCompat.this.mStartFocusX;
                    } else {
                        f = scaleGestureDetector.getFocusX();
                    }
                    float H5 = motionPhotoViewCompat3.calculateX(f, L);
                    MotionPhotoViewCompat motionPhotoViewCompat4 = MotionPhotoViewCompat.this;
                    if (motionPhotoViewCompat4.mFixedFocusY) {
                        f5 = MotionPhotoViewCompat.this.mStartFocusY;
                    } else {
                        f5 = scaleGestureDetector.getFocusY();
                    }
                    PointF K6 = MotionPhotoViewCompat.this.ensureBoundary(H5, motionPhotoViewCompat4.calculateY(f5, L), L);
                    MotionPhotoViewCompat.this.setX(K6.x);
                    MotionPhotoViewCompat.this.setY(K6.y);
                    MotionPhotoViewCompat.this.mClipViewDelegate.onDraw();
                    return true;
                }
                MotionPhotoViewCompat.this.mExitListener.onExitGesture(true, false);
                return true;
            }

            public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
                boolean z;
                float f;
                float f5;
                MotionPhotoViewCompat motionPhotoViewCompat = MotionPhotoViewCompat.this;
                float scaleX = motionPhotoViewCompat.getScaleX();
                motionPhotoViewCompat.mCurrentScale = scaleX;
                motionPhotoViewCompat.mStartScale = scaleX;
                boolean z3 = false;
                if (MotionPhotoViewCompat.this.mStartScale <= MotionPhotoViewCompat.this.minScale()) {
                    MotionPhotoViewCompat.this.mOnceScaledUp = false;
                }
                MotionPhotoViewCompat motionPhotoViewCompat2 = MotionPhotoViewCompat.this;
                if (MotionPhotoViewCompat.this.getScaleX() * ((float) motionPhotoViewCompat2.getWidth()) < MotionPhotoViewCompat.this.mParentRect.width()) {
                    z = true;
                } else {
                    z = false;
                }
                motionPhotoViewCompat2.mFixedFocusX = z;
                MotionPhotoViewCompat motionPhotoViewCompat3 = MotionPhotoViewCompat.this;
                if (MotionPhotoViewCompat.this.getScaleY() * ((float) motionPhotoViewCompat3.getHeight()) < MotionPhotoViewCompat.this.mParentRect.height()) {
                    z3 = true;
                }
                motionPhotoViewCompat3.mFixedFocusY = z3;
                MotionPhotoViewCompat motionPhotoViewCompat4 = MotionPhotoViewCompat.this;
                if (motionPhotoViewCompat4.mFixedFocusX) {
                    f = MotionPhotoViewCompat.this.mParentRect.width() / 2.0f;
                } else {
                    f = scaleGestureDetector.getFocusX();
                }
                motionPhotoViewCompat4.mStartFocusX = f;
                MotionPhotoViewCompat motionPhotoViewCompat5 = MotionPhotoViewCompat.this;
                if (motionPhotoViewCompat5.mFixedFocusY) {
                    f5 = MotionPhotoViewCompat.this.mParentRect.height() / 2.0f;
                } else {
                    f5 = scaleGestureDetector.getFocusY();
                }
                motionPhotoViewCompat5.mStartFocusY = f5;
                MotionPhotoViewCompat motionPhotoViewCompat6 = MotionPhotoViewCompat.this;
                motionPhotoViewCompat6.mStartX = motionPhotoViewCompat6.getX();
                MotionPhotoViewCompat motionPhotoViewCompat7 = MotionPhotoViewCompat.this;
                motionPhotoViewCompat7.mStartY = motionPhotoViewCompat7.getY();
                MotionPhotoViewCompat motionPhotoViewCompat8 = MotionPhotoViewCompat.this;
                motionPhotoViewCompat8.mStartW = (float) motionPhotoViewCompat8.getWidth();
                MotionPhotoViewCompat motionPhotoViewCompat9 = MotionPhotoViewCompat.this;
                motionPhotoViewCompat9.mStartH = (float) motionPhotoViewCompat9.getHeight();
                return true;
            }

            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f5) {
                if (!MotionPhotoViewCompat.this.isZoomed()) {
                    return false;
                }
                MotionPhotoViewCompat motionPhotoViewCompat = MotionPhotoViewCompat.this;
                PointF K6 = motionPhotoViewCompat.ensureBoundary(MotionPhotoViewCompat.this.getX() - f, MotionPhotoViewCompat.this.getY() - f5, motionPhotoViewCompat.getScaleX());
                MotionPhotoViewCompat.this.setX(K6.x);
                MotionPhotoViewCompat.this.setY(K6.y);
                MotionPhotoViewCompat.this.mClipViewDelegate.onDraw();
                return true;
            }

            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                MotionPhotoViewCompat.this.performClick();
                return true;
            }
        });
        this.mGestureIdentifier = gestureIdentifier;
        gestureIdentifier.setQuickScaleEnabled(false);
        this.mPinchGestureDetector = null;
        setAccessibility();
        getHolder().addCallback(new SurfaceHolder.Callback() {
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                MotionPhotoViewCompat.this.mSurfaceReady.set(true);
                if (MotionPhotoViewCompat.this.mSurfaceHolderCallback != null) {
                    MotionPhotoViewCompat.this.mSurfaceHolderCallback.surfaceCreated(surfaceHolder);
                }
            }

            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                MotionPhotoViewCompat.this.mSurfaceReady.set(false);
                if (MotionPhotoViewCompat.this.mSurfaceHolderCallback != null) {
                    MotionPhotoViewCompat.this.mSurfaceHolderCallback.surfaceDestroyed(surfaceHolder);
                }
            }

            public void surfaceChanged(SurfaceHolder surfaceHolder, int i2, int i7, int i8) {
            }
        });
    }
}
