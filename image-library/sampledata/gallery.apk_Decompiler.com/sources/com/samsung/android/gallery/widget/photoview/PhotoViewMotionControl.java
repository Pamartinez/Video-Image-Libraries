package com.samsung.android.gallery.widget.photoview;

import A.a;
import android.content.Context;
import android.graphics.PointF;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import c0.C0086a;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.abstraction.BoosterCompat;
import com.samsung.android.gallery.support.utils.ExifUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.gesture.PinchGestureDetector;
import com.samsung.android.gallery.widget.photoview.PhotoViewGestureDetector;
import com.samsung.android.gallery.widget.photoview.ScaleAnimation;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PhotoViewMotionControl implements View.OnTouchListener {
    private boolean mAllowPinchShrink;
    private boolean mBlockPinchShrink;
    private final BoosterCompat mBooster;
    /* access modifiers changed from: private */
    public PhotoViewGestureDetector mDetector;
    private float mDistBtwTwoFingerStart;
    private float mDoubleTapZoomScale = 1.0f;
    /* access modifiers changed from: private */
    public int mFingerCntOnScreen;
    private ArrayList<PhotoView> mImageViewList = new ArrayList<>();
    /* access modifiers changed from: private */
    public boolean mIsPanning;
    private boolean mIsSingleTakenBottomSheetMovable = false;
    private boolean mIsUnlimitedDoubleTapZoom = false;
    /* access modifiers changed from: private */
    public boolean mIsZooming;
    private int mMaxTouchCount;
    private float mOffset;
    /* access modifiers changed from: private */
    public OnMotionZoomListener mOnMotionZoomListener;
    private float mPinchFocusX = 0.0f;
    private float mPinchFocusY = 0.0f;
    private PinchGestureDetector mPinchGestureDetector;
    private PinchStatus mPinchStatus;
    protected final PhotoViewPositionControl mPosCtrl;
    /* access modifiers changed from: private */
    public ScaleAnimation mScaleAnim;
    private ScaleGestureDetector mScaleGestureDetector;
    protected float mScaleRatio = 1.0f;
    private float mScaleStart;
    private PhotoViewMotionControl mSecondaryMotionControl;
    private GestureDetector mSingleDetector;
    /* access modifiers changed from: private */
    public SourceInfoGetter mSourceInfo;
    boolean mSupportExitGesture = true;
    private boolean mTouchDown;
    private float mTouchSlop;
    private PointF mVCenterStart;
    private PointF mVTranslateStart;
    private ViewParent mViewParent;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class MotionControlGestureListener extends PhotoViewGestureDetector.PhotoViewGestureDetectorListener {
        public MotionControlGestureListener() {
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onFling$0() {
            PhotoViewMotionControl.this.getMainPhotoView().handleAnimation();
        }

        public boolean onDoubleTap(MotionEvent motionEvent) {
            char c5;
            char c6;
            char c8;
            PhotoView mainPhotoView = PhotoViewMotionControl.this.getMainPhotoView();
            if (!mainPhotoView.isViewAndBitmapReady() || PhotoViewMotionControl.this.mPosCtrl.getPosition() == null || !mainPhotoView.isZoomEnabled()) {
                StringBuilder sb2 = new StringBuilder("onDoubleTap skip {");
                if (mainPhotoView.isViewAndBitmapReady()) {
                    c5 = 'R';
                } else {
                    c5 = 'r';
                }
                sb2.append(c5);
                if (mainPhotoView.isZoomed()) {
                    c6 = 'Z';
                } else {
                    c6 = 'z';
                }
                sb2.append(c6);
                if (mainPhotoView.isZoomEnabled()) {
                    c8 = 'E';
                } else {
                    c8 = 'e';
                }
                sb2.append(c8);
                sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                sb2.append(PhotoViewMotionControl.this.mPosCtrl.getPosition());
                Log.w("PhotoViewMotionControl", sb2.toString());
                return super.onDoubleTapEvent(motionEvent);
            }
            if (PhotoViewMotionControl.this.mDetector != null) {
                PhotoViewMotionControl.this.mDetector.makeDisable();
            }
            PhotoViewMotionControl.this.setGestureDetector(mainPhotoView.getContext());
            PhotoViewMotionControl photoViewMotionControl = PhotoViewMotionControl.this;
            float p6 = photoViewMotionControl.zoomByDoubleTap(mainPhotoView, photoViewMotionControl.mPosCtrl.viewToSourceCoord(new PointF(PhotoViewMotionControl.this.getX(motionEvent), PhotoViewMotionControl.this.getY(motionEvent))));
            Log.d("PhotoViewMotionControl", "onDoubleTap {" + PhotoViewMotionControl.this.mPosCtrl.getScale() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + p6 + "}");
            if (PhotoViewMotionControl.this.mOnMotionZoomListener != null) {
                PhotoViewMotionControl.this.mOnMotionZoomListener.onDoubleTapZoomStateChanged(!mainPhotoView.isZoomed());
            }
            return true;
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f5) {
            boolean z;
            if (ExifUtils.isHorizontalMirror(PhotoViewMotionControl.this.mSourceInfo.getOrientationTag())) {
                f *= -1.0f;
            }
            if (!PhotoViewMotionControl.this.getMainPhotoView().isViewAndBitmapReady() || PhotoViewMotionControl.this.mPosCtrl.getPosition() == null || motionEvent == null || motionEvent2 == null || ((Math.abs(motionEvent.getX() - motionEvent2.getX()) <= 50.0f && Math.abs(motionEvent.getY() - motionEvent2.getY()) <= 50.0f) || ((Math.abs(f) <= 500.0f && Math.abs(f5) <= 500.0f) || PhotoViewMotionControl.this.mIsZooming || PhotoViewMotionControl.this.mFingerCntOnScreen != 0))) {
                return super.onFling(motionEvent, motionEvent2, f, f5);
            }
            PhotoViewMotionControl photoViewMotionControl = PhotoViewMotionControl.this;
            if (photoViewMotionControl.canFling(photoViewMotionControl.getMainPhotoView()) && Math.abs(f) > 900.0f && Math.abs(motionEvent.getX() - motionEvent2.getX()) > Math.abs(motionEvent.getY() - motionEvent2.getY()) && !PhotoViewMotionControl.this.mIsPanning) {
                PhotoView mainPhotoView = PhotoViewMotionControl.this.getMainPhotoView();
                if (motionEvent.getX() - motionEvent2.getX() > 0.0f) {
                    z = true;
                } else {
                    z = false;
                }
                mainPhotoView.onFling(z);
            }
            PointF vTranslateEnd = PhotoViewMotionControl.this.getVTranslateEnd(f, f5);
            float k = ((((float) PhotoViewMotionControl.this.getViewWidth()) / 2.0f) - vTranslateEnd.x) / PhotoViewMotionControl.this.mPosCtrl.getScale();
            float j2 = ((((float) PhotoViewMotionControl.this.getViewHeight()) / 2.0f) - vTranslateEnd.y) / PhotoViewMotionControl.this.mPosCtrl.getScale();
            float scale = PhotoViewMotionControl.this.mPosCtrl.getScale();
            float limitedScale = PhotoViewMotionControl.this.getMainPhotoView().limitedScale(scale);
            if (!PhotoViewMotionControl.this.validSCenterValue(k, j2)) {
                StringBuilder sb2 = new StringBuilder("value is invalid, vTranslateEnd.x: ");
                sb2.append(vTranslateEnd.x);
                sb2.append(", vTranslateEnd.y: ");
                C0086a.y(sb2, vTranslateEnd.y, ", sCenterXEnd: ", k, ", sCenterYEnd: ");
                sb2.append(j2);
                Log.d("PhotoViewMotionControl", sb2.toString());
                return false;
            }
            PointF pointF = new PointF(k, j2);
            PointF sourceToViewCoord = PhotoViewMotionControl.this.mPosCtrl.sourceToViewCoord(pointF);
            if (sourceToViewCoord == null) {
                Log.d("PhotoViewMotionControl", "sourceToViewCoord return null");
                return false;
            }
            PhotoViewMotionControl.this.mScaleAnim = new ScaleAnimation.Builder(scale, limitedScale).setTargetSCenter(pointF).setStartPointOfView(sourceToViewCoord).setEndPointOfView(ViewUtils.getViewCenter(PhotoViewMotionControl.this.getMainPhotoView())).withEasing(1).withOrigin(3).withDuration(500).withInterruptible().setAnimationListener(new n(this)).build();
            PhotoViewMotionControl.this.invalidateView();
            return true;
        }

        public void onLongPress(MotionEvent motionEvent) {
            char c5;
            char c6;
            if (!PhotoViewMotionControl.this.getMainPhotoView().isViewAndBitmapReady() || PhotoViewMotionControl.this.mPosCtrl.getPosition() == null || !isEnabled()) {
                StringBuilder sb2 = new StringBuilder("onLongPress skip {");
                if (PhotoViewMotionControl.this.getMainPhotoView().isViewAndBitmapReady()) {
                    c5 = 'R';
                } else {
                    c5 = 'r';
                }
                sb2.append(c5);
                sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                if (isEnabled()) {
                    c6 = 'E';
                } else {
                    c6 = 'e';
                }
                sb2.append(c6);
                sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                sb2.append(PhotoViewMotionControl.this.mPosCtrl.getPosition());
                sb2.append("}");
                Log.w("PhotoViewMotionControl", sb2.toString());
                return;
            }
            Log.majorEvent("PhotoViewMotionControl", "LongPress");
            PhotoViewMotionControl.this.getMainPhotoView().onLongPress(motionEvent.getX(), motionEvent.getY());
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            PhotoView mainPhotoView = PhotoViewMotionControl.this.getMainPhotoView();
            mainPhotoView.mLastTapX = motionEvent.getX();
            mainPhotoView.mLastTapY = motionEvent.getY();
            mainPhotoView.performClick();
            return true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnMotionZoomListener {
        void onDoubleTapZoomStateChanged(boolean z);

        void onPinchZoomStateChanged(boolean z);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnTouchMirroringListener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum PinchStatus {
        OUT,
        IN,
        NONE
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SourceInfoGetter {
        int getHeight(boolean z);

        int getOrientationTag();

        int getWidth(boolean z);
    }

    public PhotoViewMotionControl(PhotoView photoView, SourceInfoGetter sourceInfoGetter, PhotoViewPositionControl photoViewPositionControl) {
        this.mSourceInfo = sourceInfoGetter;
        this.mPosCtrl = photoViewPositionControl;
        addPhotoView(photoView);
        this.mTouchSlop = ((float) ViewConfiguration.get(photoView.getContext()).getScaledTouchSlop()) / 2.0f;
        this.mBooster = SeApiCompat.getBoosterCompat(photoView.getContext().getApplicationContext());
        this.mScaleGestureDetector = new ScaleGestureDetector(photoView.getContext(), new ScaleGestureDetector.SimpleOnScaleGestureListener());
    }

    private void addPhotoView(PhotoView photoView) {
        if (this.mImageViewList.size() == 0) {
            this.mImageViewList.add(photoView);
        }
    }

    /* access modifiers changed from: private */
    public boolean canFling(PhotoView photoView) {
        if (photoView == null) {
            return false;
        }
        if (photoView.isZoomed() || photoView.isZoomedByMinSize()) {
            return true;
        }
        return false;
    }

    private float distance(float f, float f5, float f8, float f10) {
        float f11 = f - f5;
        float f12 = f8 - f10;
        return (float) Math.sqrt((double) ((f12 * f12) + (f11 * f11)));
    }

    private void fitToBoundsView() {
        Iterator<PhotoView> it = this.mImageViewList.iterator();
        while (it.hasNext()) {
            it.next().fitToBounds(true);
        }
    }

    private float getAdjustedScale() {
        float f;
        float f5;
        float f8;
        int width = this.mSourceInfo.getWidth(true);
        int height = this.mSourceInfo.getHeight(true);
        if (!(width == 0 || height == 0)) {
            if (width > height) {
                f = (float) width;
                f5 = (float) height;
            } else {
                f = (float) height;
                f5 = (float) width;
            }
            if (f / f5 >= 6.0f) {
                int viewWidth = getViewWidth();
                int viewHeight = getViewHeight();
                if (!(viewWidth == 0 || viewHeight == 0)) {
                    if (width > height && viewWidth > viewHeight) {
                        f8 = (((float) width) / ((float) viewWidth)) / (((float) height) / ((float) viewHeight));
                    } else if (width >= height || viewWidth >= viewHeight) {
                        f8 = 2.31f;
                    } else {
                        f8 = (((float) height) / ((float) viewHeight)) / (((float) width) / ((float) viewWidth));
                    }
                    if (f8 != 1.0f) {
                        return f8;
                    }
                }
            }
        }
        return 2.31f;
    }

    private float getDoubleTapZoomScale() {
        float minScale = getMainPhotoView().minScale();
        float adjustedScale = getAdjustedScale();
        if (this.mIsUnlimitedDoubleTapZoom || minScale >= 0.5f || adjustedScale != 2.31f) {
            return minScale * adjustedScale;
        }
        return Math.max(0.5f, minScale * adjustedScale);
    }

    private float getNewScale(float f) {
        return this.mScaleGestureDetector.getScaleFactor() * this.mScaleStart;
    }

    private PointF getTargetSCenter(float f, float f5, float f8) {
        PointF vTranslateForSCenter = getMainPhotoView().vTranslateForSCenter(f, f5, f8);
        PointF viewCenter = ViewUtils.getViewCenter(getMainPhotoView());
        return new PointF((viewCenter.x - vTranslateForSCenter.x) / f8, (viewCenter.y - vTranslateForSCenter.y) / f8);
    }

    /* access modifiers changed from: private */
    public int getViewHeight() {
        return getMainPhotoView().getHeight();
    }

    /* access modifiers changed from: private */
    public int getViewWidth() {
        return getMainPhotoView().getWidth();
    }

    private float getX(MotionEvent motionEvent, int i2) {
        float x9 = motionEvent.getX(i2);
        if (Float.isNaN(x9)) {
            a.B(i2, "MotionEvent.getX(pointerIndex) is NaN : ", "PhotoViewMotionControl");
            x9 = 0.0f;
        }
        return getX(x9);
    }

    private float getY(MotionEvent motionEvent, int i2) {
        float y = motionEvent.getY(i2);
        if (Float.isNaN(y)) {
            a.B(i2, "MotionEvent.getY(pointerIndex) is NaN : ", "PhotoViewMotionControl");
            y = 0.0f;
        }
        return getY(y);
    }

    private void handleBoost(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mBooster.acquireFlick();
        } else if (action == 1 || action == 3) {
            this.mBooster.releaseFlick(200);
        }
    }

    /* access modifiers changed from: private */
    public void invalidateView() {
        Iterator<PhotoView> it = this.mImageViewList.iterator();
        while (it.hasNext()) {
            it.next().invalidate();
        }
    }

    private boolean isZoomEnabled() {
        if (!isZoomInProgress() || !getMainPhotoView().isZoomEnabled()) {
            return false;
        }
        return true;
    }

    private boolean isZoomInProgress() {
        return this.mScaleGestureDetector.isInProgress();
    }

    private boolean isZoomed() {
        ScaleAnimation scaleAnimation = this.mScaleAnim;
        if (scaleAnimation != null) {
            if (scaleAnimation.getStartScale() < this.mScaleAnim.getTargetScale()) {
                return true;
            }
            return false;
        } else if (getScale() != getMainPhotoView().minScale()) {
            return true;
        } else {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$zoom$1() {
        getMainPhotoView().handleAnimation();
    }

    private void notifyMatrixChanged() {
        Iterator<PhotoView> it = this.mImageViewList.iterator();
        while (it.hasNext()) {
            it.next().notifyMatrixChanged();
        }
    }

    private boolean onCancelEvent(MotionEvent motionEvent, int i2) {
        if (i2 == 1) {
            this.mMaxTouchCount = 0;
        }
        this.mTouchDown = false;
        return false;
    }

    private boolean onDownEvent(MotionEvent motionEvent, int i2) {
        this.mScaleAnim = null;
        this.mTouchDown = true;
        if (ViewUtils.isVisible(getMainPhotoView()) && (canFling(getMainPhotoView()) || i2 > 1)) {
            requestDisallowInterceptTouchEvent(!this.mIsSingleTakenBottomSheetMovable);
        }
        this.mMaxTouchCount = Math.max(this.mMaxTouchCount, i2);
        if (i2 >= 2) {
            this.mDistBtwTwoFingerStart = distance(getX(motionEvent, 0), getX(motionEvent, 1), getY(motionEvent, 0), getY(motionEvent, 1));
            this.mScaleStart = this.mPosCtrl.getScale();
            this.mVTranslateStart.set(this.mPosCtrl.getX(), this.mPosCtrl.getY());
            this.mVCenterStart.set((getX(motionEvent, 0) + getX(motionEvent, 1)) / 2.0f, (getY(motionEvent, 0) + getY(motionEvent, 1)) / 2.0f);
            PointF pointF = this.mVCenterStart;
            this.mPinchFocusX = pointF.x;
            this.mPinchFocusY = pointF.y;
        } else {
            this.mVTranslateStart.set(this.mPosCtrl.getX(), this.mPosCtrl.getY());
            this.mVCenterStart.set(getX(motionEvent), getY(motionEvent));
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0019  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x001e A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean onMoveEvent(android.view.MotionEvent r3, int r4) {
        /*
            r2 = this;
            int r0 = r2.mMaxTouchCount
            r1 = 0
            if (r0 <= 0) goto L_0x0016
            r0 = 2
            if (r4 < r0) goto L_0x000d
            boolean r3 = r2.onMultiTouch(r3, r1)
            goto L_0x0017
        L_0x000d:
            boolean r4 = r2.mIsZooming
            if (r4 != 0) goto L_0x0016
            boolean r3 = r2.onOneFingerPan(r3, r1)
            goto L_0x0017
        L_0x0016:
            r3 = r1
        L_0x0017:
            if (r3 == 0) goto L_0x001e
            r2.invalidateView()
            r2 = 1
            return r2
        L_0x001e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.photoview.PhotoViewMotionControl.onMoveEvent(android.view.MotionEvent, int):boolean");
    }

    private boolean onMultiTouch(MotionEvent motionEvent, boolean z) {
        PinchStatus pinchStatus;
        PhotoViewMotionControl photoViewMotionControl;
        boolean z3 = false;
        float distance = distance(getX(motionEvent, 0), getX(motionEvent, 1), getY(motionEvent, 0), getY(motionEvent, 1));
        float x9 = getX((motionEvent.getX(1) + motionEvent.getX(0)) / 2.0f);
        float y = getY((motionEvent.getY(1) + motionEvent.getY(0)) / 2.0f);
        if (!isZoomEnabled() || (!this.mIsPanning && !pinchEnoughToZoom(distance, x9, y))) {
            return z;
        }
        setIsZooming(true);
        this.mIsPanning = true;
        float scale = this.mPosCtrl.getScale();
        float newScale = getNewScale(distance);
        if (newScale > scale) {
            pinchStatus = PinchStatus.OUT;
        } else {
            pinchStatus = PinchStatus.IN;
        }
        setPinchStatus(pinchStatus);
        PhotoView mainPhotoView = getMainPhotoView();
        float adjustedNewScale = getAdjustedNewScale(newScale, scale);
        float min = Math.min(mainPhotoView.getMaxScale(), adjustedNewScale);
        if (!(this.mOnMotionZoomListener == null || mainPhotoView.getMaxScale() == mainPhotoView.minScale())) {
            OnMotionZoomListener onMotionZoomListener = this.mOnMotionZoomListener;
            if (adjustedNewScale > scale) {
                z3 = true;
            }
            onMotionZoomListener.onPinchZoomStateChanged(z3);
        }
        if (min <= mainPhotoView.minScale()) {
            this.mPosCtrl.setScale(min);
            this.mDistBtwTwoFingerStart = distance;
            this.mScaleStart = mainPhotoView.minScale();
            this.mVCenterStart.set(x9, y);
            this.mVTranslateStart.set(this.mPosCtrl.getPosition());
            photoViewMotionControl = this;
        } else {
            this.mPosCtrl.setScale(min);
            photoViewMotionControl = this;
            photoViewMotionControl.setPositionOnMultiTouch(motionEvent, x9, (this.mPosCtrl.getScale() / scale) * (x9 - this.mPosCtrl.getX()), y, (this.mPosCtrl.getScale() / scale) * (y - this.mPosCtrl.getY()));
            if ((((float) photoViewMotionControl.mSourceInfo.getHeight(true)) * scale < ((float) photoViewMotionControl.getViewHeight()) && photoViewMotionControl.mPosCtrl.getScale() * ((float) photoViewMotionControl.mSourceInfo.getHeight(true)) >= ((float) photoViewMotionControl.getViewHeight())) || (scale * ((float) photoViewMotionControl.mSourceInfo.getWidth(true)) < ((float) photoViewMotionControl.getViewWidth()) && photoViewMotionControl.mPosCtrl.getScale() * ((float) photoViewMotionControl.mSourceInfo.getWidth(true)) >= ((float) photoViewMotionControl.getViewWidth()))) {
                photoViewMotionControl.fitToBoundsView();
                photoViewMotionControl.mVCenterStart.set(x9, y);
                photoViewMotionControl.mVTranslateStart.set(photoViewMotionControl.mPosCtrl.getPosition());
                photoViewMotionControl.mDistBtwTwoFingerStart = distance;
            }
        }
        photoViewMotionControl.fitToBoundsView();
        photoViewMotionControl.refreshRequiredTilesView();
        return true;
    }

    private boolean onOneFingerPan(MotionEvent motionEvent, boolean z) {
        boolean z3;
        boolean z7;
        boolean z9;
        boolean z10;
        boolean z11;
        float x9 = getX(motionEvent);
        float y = getY(motionEvent);
        float abs = Math.abs(x9 - this.mVCenterStart.x);
        float abs2 = Math.abs(y - this.mVCenterStart.y);
        float f = this.mTouchSlop;
        if (abs <= f && abs2 <= f && !this.mIsPanning) {
            return z;
        }
        PhotoViewPositionControl photoViewPositionControl = this.mPosCtrl;
        PointF pointF = this.mVTranslateStart;
        float f5 = pointF.x;
        PointF pointF2 = this.mVCenterStart;
        photoViewPositionControl.setPosition((x9 - pointF2.x) + f5, (y - pointF2.y) + pointF.y, false);
        float x10 = this.mPosCtrl.getX();
        float y8 = this.mPosCtrl.getY();
        fitToBoundsView();
        if (x10 != this.mPosCtrl.getX() || (Float.isNaN(0.0f / x10) && Float.isNaN(0.0f / this.mPosCtrl.getX()))) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (y8 != this.mPosCtrl.getY()) {
            z7 = true;
        } else {
            z7 = false;
        }
        if (!z3 || abs <= abs2 || this.mIsPanning) {
            z9 = false;
        } else {
            z9 = true;
        }
        if (!z7 || abs2 <= abs || this.mIsPanning) {
            z10 = false;
        } else {
            z10 = true;
        }
        PhotoView mainPhotoView = getMainPhotoView();
        if (y8 != this.mPosCtrl.getY() || abs2 <= this.mOffset * 3.0f) {
            z11 = false;
        } else {
            z11 = true;
        }
        if (z9 || (z3 && z7 && !z11 && !this.mIsPanning)) {
            if (!canFling(mainPhotoView) && (abs > 0.0f || abs2 > 0.0f)) {
                this.mMaxTouchCount = 0;
                if (ViewUtils.isVisible(mainPhotoView)) {
                    requestDisallowInterceptTouchEvent(false);
                }
            }
        } else if (z10) {
            this.mVCenterStart.set(x9, y);
        } else {
            this.mIsPanning = true;
        }
        if (canFling(mainPhotoView)) {
            refreshRequiredTilesView();
        }
        return true;
    }

    private boolean onTouchEventInternal(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action == 2) {
                    return onMoveEvent(motionEvent, pointerCount);
                }
                if (action == 3) {
                    return onCancelEvent(motionEvent, pointerCount);
                }
                if (action != 5) {
                    if (action != 6) {
                        if (action != 261) {
                            if (action != 262) {
                                return false;
                            }
                        }
                    }
                }
            }
            return onUpEvent(motionEvent, pointerCount);
        }
        return onDownEvent(motionEvent, pointerCount);
    }

    private boolean onUpEvent(MotionEvent motionEvent, int i2) {
        boolean z;
        boolean z3;
        int i7 = i2 - 1;
        this.mFingerCntOnScreen = i7;
        if (i7 > 0) {
            z = true;
        } else {
            z = false;
        }
        this.mTouchDown = z;
        setPinchStatus(PinchStatus.NONE);
        if (this.mMaxTouchCount <= 0 || (!(z3 = this.mIsZooming) && !this.mIsPanning)) {
            if (i2 == 1) {
                setIsZooming(false);
                this.mIsPanning = false;
                this.mMaxTouchCount = 0;
            }
            return false;
        }
        if (z3 && i2 == 2) {
            this.mIsPanning = true;
            this.mVTranslateStart.set(this.mPosCtrl.getX(), this.mPosCtrl.getY());
            if (motionEvent.getActionIndex() == 1) {
                this.mVCenterStart.set(getX(motionEvent, 0), getY(motionEvent, 0));
            } else {
                this.mVCenterStart.set(getX(motionEvent, 1), getY(motionEvent, 1));
            }
            if (this.mMaxTouchCount == 2) {
                setIsZooming(false);
            }
        }
        if (i2 < 2) {
            setIsZooming(false);
            this.mIsPanning = false;
            this.mMaxTouchCount = 0;
            notifyMatrixChanged();
        }
        refreshRequiredTilesView();
        return true;
    }

    private boolean pinchEnoughToZoom(float f, float f5, float f8) {
        if (Math.abs(this.mScaleGestureDetector.getScaleFactor() - 1.0f) > 0.01f) {
            return true;
        }
        return false;
    }

    private void refreshRequiredTilesView() {
        Iterator<PhotoView> it = this.mImageViewList.iterator();
        while (it.hasNext()) {
            PhotoView next = it.next();
            next.refreshRequiredTiles(true, next.mPosCtrl);
        }
    }

    private void requestDisallowInterceptTouchEvent(boolean z) {
        ViewParent viewParent = this.mViewParent;
        if (viewParent != null) {
            viewParent.requestDisallowInterceptTouchEvent(z);
        }
    }

    private void setPinchStatus(PinchStatus pinchStatus) {
        if (this.mPinchStatus != pinchStatus) {
            this.mPinchStatus = pinchStatus;
        }
    }

    /* access modifiers changed from: private */
    public boolean validSCenterValue(float f, float f5) {
        if (Float.isNaN(f) || Float.isNaN(f5)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public float zoomByDoubleTap(PhotoView photoView, PointF pointF) {
        boolean z;
        if (this.mPosCtrl.getScale() == photoView.minScale()) {
            z = true;
        } else {
            z = false;
        }
        PhotoViewMotionControl photoViewMotionControl = this.mSecondaryMotionControl;
        if (photoViewMotionControl != null) {
            z = photoViewMotionControl.isZoomed();
        }
        float min = Math.min(photoView.getMaxScale(), getDoubleTapZoomScale());
        if (!z) {
            min = photoView.minScale();
        }
        zoom(min, pointF);
        return min;
    }

    public PhotoView getMainPhotoView() {
        return this.mImageViewList.get(0);
    }

    public int getMaxTouchCount() {
        return this.mMaxTouchCount;
    }

    public float getScale() {
        return this.mPosCtrl.getScale();
    }

    public ScaleAnimation getScaleAnimation() {
        return this.mScaleAnim;
    }

    public float getSourceX(float f) {
        return this.mPosCtrl.viewToSourceX(f);
    }

    public float getSourceY(float f) {
        return this.mPosCtrl.viewToSourceY(f);
    }

    public PointF getVCenterStart() {
        return this.mVCenterStart;
    }

    public PointF getVTranslateEnd(float f, float f5) {
        return new PointF((f * 0.25f * this.mScaleRatio) + this.mPosCtrl.getX(), (f5 * 0.25f * this.mScaleRatio) + this.mPosCtrl.getY());
    }

    public boolean isAllFingerUp() {
        return !this.mTouchDown;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z;
        if (motionEvent.getButtonState() == 2 && motionEvent.getAction() == 0) {
            Log.d("PhotoViewMotionControl", "onTouch : BUTTON_SECONDARY");
            getMainPhotoView().onSecondaryClicked(motionEvent);
            return true;
        }
        PhotoView mainPhotoView = getMainPhotoView();
        if (mainPhotoView.mConfig.mViewBlockMotionControl || mainPhotoView.isTranslated()) {
            return false;
        }
        if (this.mPinchGestureDetector == null) {
            this.mPinchGestureDetector = new PinchGestureDetector(mainPhotoView.getContext());
        }
        if (mainPhotoView.isZoomed() || this.mBlockPinchShrink) {
            this.mAllowPinchShrink = false;
        } else if (motionEvent.getAction() == 0) {
            this.mAllowPinchShrink = true;
        }
        if (!this.mAllowPinchShrink || !this.mPinchGestureDetector.onTouchEvent(motionEvent) || !this.mPinchGestureDetector.isPinchDirectionIn()) {
            handleBoost(motionEvent);
            this.mScaleGestureDetector.onTouchEvent(motionEvent);
            PhotoViewMotionControl photoViewMotionControl = this.mSecondaryMotionControl;
            if (photoViewMotionControl != null) {
                photoViewMotionControl.onTouch(photoViewMotionControl.getMainPhotoView(), motionEvent);
            }
            if (this.mPosCtrl.getPosition() == null) {
                Log.d("PhotoViewMotionControl", "onTouch: null position");
                GestureDetector gestureDetector = this.mSingleDetector;
                if (gestureDetector != null) {
                    gestureDetector.onTouchEvent(motionEvent);
                }
                return true;
            }
            PhotoViewGestureDetector photoViewGestureDetector = this.mDetector;
            if (photoViewGestureDetector == null || photoViewGestureDetector.onTouchEvent(motionEvent)) {
                setIsZooming(false);
                this.mIsPanning = false;
                this.mMaxTouchCount = 0;
                if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
                    mainPhotoView.onTouchCapture(motionEvent);
                }
                return true;
            }
            ScaleAnimation scaleAnimation = this.mScaleAnim;
            if (scaleAnimation == null || scaleAnimation.isInterruptible()) {
                this.mScaleAnim = null;
                if (mainPhotoView.onTouchCapture(motionEvent)) {
                    return true;
                }
                if (this.mVTranslateStart == null) {
                    this.mVTranslateStart = new PointF(0.0f, 0.0f);
                }
                if (this.mVCenterStart == null) {
                    this.mVCenterStart = new PointF(0.0f, 0.0f);
                }
                if (onTouchEventInternal(motionEvent) || getMainPhotoView().onTouchEvent(motionEvent)) {
                    return true;
                }
                return false;
            }
            requestDisallowInterceptTouchEvent(true);
            return true;
        }
        Log.d("PhotoViewMotionControl", "onTouch: consumed by pinch");
        if (motionEvent.getPointerCount() >= 2) {
            z = true;
        } else {
            z = false;
        }
        mainPhotoView.onExitGesture(z, false);
        return true;
    }

    public void reset() {
        this.mScaleStart = 0.0f;
        setPinchStatus(PinchStatus.NONE);
        setIsZooming(false);
        this.mMaxTouchCount = 0;
        this.mScaleAnim = null;
        this.mDistBtwTwoFingerStart = 0.0f;
        this.mVCenterStart = null;
        this.mVTranslateStart = null;
    }

    public void setBlockPinchShrink(boolean z) {
        this.mBlockPinchShrink = z;
    }

    public void setGestureDetector(Context context) {
        this.mDetector = new PhotoViewGestureDetector(context, new MotionControlGestureListener());
        this.mSingleDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                PhotoViewMotionControl.this.getMainPhotoView().performClick();
                return true;
            }
        });
    }

    public void setIsZooming(boolean z) {
        this.mIsZooming = z;
    }

    public void setMotionZoomListener(OnMotionZoomListener onMotionZoomListener) {
        this.mOnMotionZoomListener = onMotionZoomListener;
    }

    public void setOffset(float f) {
        this.mOffset = f * 5.0f;
    }

    public void setPositionOnMultiTouch(MotionEvent motionEvent, float f, float f5, float f8, float f10) {
        float x9 = (getX(motionEvent.getX(0)) + getX(motionEvent.getX(1))) / 2.0f;
        float y = (motionEvent.getY(1) + motionEvent.getY(0)) / 2.0f;
        this.mPinchFocusX = x9;
        this.mPinchFocusY = y;
        this.mPosCtrl.setPosition((f - f5) + (x9 - this.mPinchFocusX), (f8 - f10) + (y - this.mPinchFocusY), true);
    }

    public void setScaleAnimation(ScaleAnimation scaleAnimation) {
        this.mScaleAnim = scaleAnimation;
    }

    public void setSecondaryControl(PhotoViewMotionControl photoViewMotionControl) {
        this.mSecondaryMotionControl = photoViewMotionControl;
    }

    public void setUnlimitedDoubleTapZoom(boolean z) {
        this.mIsUnlimitedDoubleTapZoom = z;
    }

    public void setViewParent(ViewParent viewParent) {
        this.mViewParent = viewParent;
    }

    public void supportExitGesture(boolean z) {
        this.mSupportExitGesture = z;
    }

    public void zoom(float f, PointF pointF) {
        float scale = this.mPosCtrl.getScale();
        float limitedScale = getMainPhotoView().limitedScale(f);
        PointF targetSCenter = getTargetSCenter(pointF.x, pointF.y, limitedScale);
        PointF sourceToViewCoord = this.mPosCtrl.sourceToViewCoord(targetSCenter);
        ScaleAnimation.Builder targetSCenter2 = new ScaleAnimation.Builder(scale, limitedScale).setTargetSCenter(targetSCenter);
        if (sourceToViewCoord == null) {
            sourceToViewCoord = ViewUtils.getViewCenter(getMainPhotoView());
        }
        this.mScaleAnim = targetSCenter2.setStartPointOfView(sourceToViewCoord).setEndPointOfView(ViewUtils.getViewCenter(getMainPhotoView())).withOrigin(4).withDuration(160).setAnimationListener(new K4.a(16, this)).build();
        invalidateView();
    }

    public void zoomToMinScale() {
        if (this.mPosCtrl.getScale() != getMainPhotoView().minScale()) {
            zoom(getMainPhotoView().minScale(), new PointF(0.0f, 0.0f));
        }
    }

    /* access modifiers changed from: private */
    public float getX(MotionEvent motionEvent) {
        float x9 = motionEvent.getX();
        if (Float.isNaN(x9)) {
            Log.e("PhotoViewMotionControl", "MotionEvent.getX() is NaN");
            x9 = 0.0f;
        }
        return getX(x9);
    }

    /* access modifiers changed from: private */
    public float getY(MotionEvent motionEvent) {
        float y = motionEvent.getY();
        if (Float.isNaN(y)) {
            Log.e("PhotoViewMotionControl", "MotionEvent.getY() is NaN");
            y = 0.0f;
        }
        return getY(y);
    }

    private float getX(float f) {
        float sourceX = getSourceX(f);
        if (ExifUtils.isHorizontalMirror(this.mSourceInfo.getOrientationTag())) {
            return ((float) DeviceInfo.getDisplayWidth(getMainPhotoView().getContext())) - this.mPosCtrl.sourceToViewX(sourceX);
        }
        return this.mPosCtrl.sourceToViewX(sourceX);
    }

    private float getY(float f) {
        return this.mPosCtrl.sourceToViewY(getSourceY(f));
    }

    public void setTouchMirroringListener(OnTouchMirroringListener onTouchMirroringListener) {
    }

    public float getAdjustedNewScale(float f, float f5) {
        return f;
    }
}
