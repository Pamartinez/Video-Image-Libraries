package com.samsung.android.gallery.widget.photoview;

import L7.k;
import Nb.c;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.appcompat.widget.AppCompatImageView;
import c0.C0086a;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.cache.BytesBuffer;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.ExifUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MathUtils;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.OnSecondaryClickListener;
import com.samsung.android.gallery.widget.OnTranslationListener;
import com.samsung.android.gallery.widget.R$string;
import com.samsung.android.gallery.widget.capture.ClipViewDelegate;
import com.samsung.android.gallery.widget.capture.ICaptureActionMode;
import com.samsung.android.gallery.widget.capture.IClipRootView;
import com.samsung.android.gallery.widget.photoview.PhotoViewMotionControl;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.function.Consumer;
import java.util.function.Supplier;
import u7.C0523d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PhotoView extends AppCompatImageView implements IClipRootView {
    protected final StringCompat TAG = new StringCompat(getClass().getSimpleName());
    Supplier<String> mAccessibilityUsageHintSupplier;
    final ClipViewDelegate mClipViewDelegate = new ClipViewDelegate(this);
    final PhotoViewConfig mConfig;
    Supplier<String> mContentDescriptionSupplier;
    DebugDelegate mDebugDelegate;
    final PhotoViewDelegate mDelegate;
    boolean mIgnoreSetAlpha;
    public ImageProcessor mImageProcessor = ImageProcessor.EMPTY;
    private boolean mIsPostSetAlphaOnce;
    final Point mLastMaxDimensions;
    protected float mLastTapX;
    protected float mLastTapY;
    protected PhotoViewMotionControl mMotionControl;
    OnFlingListener mOnFlingListener;
    OnLongPressListener mOnLongPressListener;
    OnMatrixChangeListener mOnMatrixChangeListener;
    OnSecondaryClickListener mOnSecondaryClickListener;
    Runnable mOnSizeChangedListener;
    Runnable mOnTileTaskDoneCallback;
    OnTranslationListener mOnTranslationListener;
    OnViewerExitGestureListener mOnViewerExitGestureListener;
    OnZoomDetectListener mOnZoomDetectListener;
    final PhotoViewPositionControl mPosCtrl;
    ViewGroup.MarginLayoutParams mProxyViewLayoutParams;
    Consumer<Float> mScaleChangeListener;

    public PhotoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        PhotoViewPositionControl photoViewPositionControl = new PhotoViewPositionControl();
        this.mPosCtrl = photoViewPositionControl;
        PhotoViewConfig photoViewConfig = new PhotoViewConfig();
        this.mConfig = photoViewConfig;
        PhotoViewDelegate photoViewDelegate = new PhotoViewDelegate(this);
        this.mDelegate = photoViewDelegate;
        this.mLastMaxDimensions = new Point(2048, 2048);
        this.mIgnoreSetAlpha = false;
        this.mIsPostSetAlphaOnce = false;
        photoViewDelegate.updateAttrs(context, attributeSet);
        photoViewConfig.mMaxScale = 5.0f;
        setAccessibilityDelegate(photoViewDelegate.buildAccessibilityDelegate());
        photoViewPositionControl.setOnScaleChangeListener(new n(this));
        if (PhotoViewConfig.DEBUG_REGION_DECODING) {
            this.mDebugDelegate = new DebugDelegate(this);
        }
    }

    private void applyMirrorHorizontal(Canvas canvas) {
        if (ExifUtils.isHorizontalMirror(this.mImageProcessor.getOrientationTag())) {
            canvas.translate((float) getViewWidth(), 0.0f);
            canvas.scale(-1.0f, 1.0f);
        }
    }

    private void computeIfDifferent(int i2, int i7) {
        if (this.mImageProcessor.hasDifferentSize(true, i2, i7)) {
            this.mImageProcessor.resize(i2, i7);
            resetScaleAndCenter();
        }
    }

    private void createPaints() {
        PhotoViewConfig photoViewConfig = this.mConfig;
        if (photoViewConfig.mBitmapPaint == null) {
            photoViewConfig.mBitmapPaint = this.mDelegate.createPaints();
        }
    }

    private boolean drawTiles(Canvas canvas, int i2, boolean z) {
        Bitmap bitmap;
        long j2 = 0;
        int i7 = 0;
        for (Map.Entry next : this.mImageProcessor.getTileMap().entrySet()) {
            if (((Integer) next.getKey()).intValue() == i2 || z) {
                for (Tile tile : (List) next.getValue()) {
                    this.mPosCtrl.sourceToViewRect(tile.sRect, tile.vRect);
                    if (!tile.loading && (bitmap = tile.bitmap) != null && !bitmap.isRecycled()) {
                        canvas.save();
                        canvas.clipRect(tile.vRect);
                        this.mDelegate.updateMatrixForTile(tile);
                        Bitmap bitmap2 = tile.bitmap;
                        PhotoViewConfig photoViewConfig = this.mConfig;
                        canvas.drawBitmap(bitmap2, photoViewConfig.mMatrix, photoViewConfig.mBitmapPaint);
                        canvas.restore();
                        i7++;
                        j2 = (((long) tile.bitmap.getWidth()) * ((long) tile.bitmap.getHeight())) + j2;
                    } else if (z && this.mImageProcessor.getFullImageSampleSize() == tile.sampleSize) {
                        Log.e((CharSequence) this.TAG, "base layer draw failed : " + tile, Integer.valueOf(i2), Integer.valueOf(tile.sampleSize));
                    }
                    Bitmap bitmap3 = tile.bitmap;
                    if (bitmap3 == null || !bitmap3.isRecycled()) {
                        DebugDelegate debugDelegate = this.mDebugDelegate;
                        if (debugDelegate != null) {
                            debugDelegate.renderTileOnDraw(canvas, tile);
                        }
                    } else {
                        Log.e((CharSequence) this.TAG, "recycled bitmap : ", tile.bitmap);
                        throw new IllegalStateException("try draw recycled bitmap : " + tile.bitmap);
                    }
                }
                if (i7 > 0 && j2 > 10000000) {
                    Log.i(this.TAG, "DRAW done : " + i2 + Logger.v(Boolean.valueOf(z), Integer.valueOf(i7), Long.valueOf(j2)));
                }
            }
        }
        this.mConfig.mLastDrawnPixels = j2;
        if (i7 > 0) {
            return true;
        }
        return false;
    }

    private boolean forceResetScale() {
        PhotoViewConfig photoViewConfig = this.mConfig;
        if (!photoViewConfig.mIsViewInitialCenterCrop || !photoViewConfig.mInitializedBound || centerCropScale() == this.mPosCtrl.getScale() || !isViewAndBitmapReady()) {
            return false;
        }
        return true;
    }

    private boolean isAnimating() {
        PhotoViewMotionControl photoViewMotionControl = this.mMotionControl;
        if (photoViewMotionControl == null) {
            return false;
        }
        if ((photoViewMotionControl.getMaxTouchCount() <= 0 || isMinScale()) && this.mMotionControl.getScaleAnimation() == null) {
            return false;
        }
        return true;
    }

    private boolean isSourceAndViewReady() {
        if (getViewWidth() <= 0 || getViewHeight() <= 0 || !this.mImageProcessor.isSourceReady()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$resetRegionDecodingInfo$0() {
        this.mImageProcessor.lambda$close$9();
        ThreadUtil.runOnUiThread(new c(this, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setAlpha$1(float f) {
        super.setAlpha(f);
        this.mIsPostSetAlphaOnce = false;
    }

    /* access modifiers changed from: private */
    public void onScaleChanged(float f) {
        Consumer<Float> consumer = this.mScaleChangeListener;
        if (consumer != null) {
            consumer.accept(Float.valueOf(f));
        }
        boolean checkZoomState = this.mDelegate.checkZoomState(f);
        if (this.mOnZoomDetectListener != null && getContext() != null && checkZoomState && isMaxScale()) {
            announceForAccessibility(getContext().getString(R$string.cant_zoom_in));
        }
    }

    private void preDraw() {
        Float f;
        if (isSourceAndViewReady()) {
            PhotoViewConfig photoViewConfig = this.mConfig;
            if (!(photoViewConfig.mSPendingCenter == null || (f = photoViewConfig.mPendingScale) == null)) {
                if (!photoViewConfig.mBlockPendingScale) {
                    this.mPosCtrl.setScale(f.floatValue());
                }
                if (this.mPosCtrl.getPosition() == null) {
                    this.mPosCtrl.init();
                }
                this.mPosCtrl.setPosition((((float) getViewWidth()) / 2.0f) - (this.mPosCtrl.getScale() * this.mConfig.mSPendingCenter.x), (((float) getViewHeight()) / 2.0f) - (this.mPosCtrl.getScale() * this.mConfig.mSPendingCenter.y), true);
                PhotoViewConfig photoViewConfig2 = this.mConfig;
                photoViewConfig2.mSPendingCenter = null;
                photoViewConfig2.mPendingScale = null;
                fitToBounds(true);
                refreshRequiredTiles(true, this.mPosCtrl);
            }
            fitToBounds(this.mConfig.mHorizontalDisplayChanged);
            this.mConfig.mHorizontalDisplayChanged = false;
        }
    }

    private void renderBitmapOnDraw(Canvas canvas, Bitmap bitmap) {
        if (!bitmap.isRecycled()) {
            updateMatrixForBitmap(bitmap);
            PhotoViewConfig photoViewConfig = this.mConfig;
            canvas.drawBitmap(bitmap, photoViewConfig.mMatrix, photoViewConfig.mBitmapPaint);
            return;
        }
        StringCompat stringCompat = this.TAG;
        Log.e((CharSequence) stringCompat, "recycled bitmap : " + bitmap, bitmap);
        throw new IllegalStateException("try draw recycled bitmap : " + bitmap);
    }

    private void resetScaleAndCenterInternal() {
        this.mDelegate.resetScaleAndCenterInternal();
    }

    private void updateImage(Bitmap bitmap) {
        if (this.mImageProcessor == ImageProcessor.EMPTY) {
            this.mImageProcessor = new ImageProcessor(this.TAG.getTag());
        }
        this.mImageProcessor.setBitmap(bitmap);
        invalidate();
    }

    public void addContentDescription(Supplier<String> supplier) {
        this.mContentDescriptionSupplier = supplier;
    }

    public void addUsageHint(Supplier<String> supplier) {
        this.mAccessibilityUsageHintSupplier = supplier;
    }

    public void bindCaptureView(View view, boolean z, boolean z3) {
        this.mClipViewDelegate.bindCaptureView(view, z, z3, false);
        invalidate();
    }

    public void blockMotionControl(boolean z) {
        this.mConfig.mViewBlockMotionControl = z;
    }

    public void blockPendingScale(boolean z) {
        this.mConfig.mBlockPendingScale = z;
    }

    public void blockRegionDecoding(boolean z) {
        this.mConfig.mBlockRegionDecoding = z;
        DebugDelegate debugDelegate = this.mDebugDelegate;
        if (debugDelegate != null) {
            debugDelegate.disable(z);
        }
    }

    public final int calculateInSampleSize(float f) {
        return this.mDelegate.calculateInSampleSize(f);
    }

    public float centerCropScale() {
        return Math.max(((float) getViewWidth()) / ((float) this.mImageProcessor.getWidth(true)), ((float) getViewHeight()) / ((float) this.mImageProcessor.getHeight(true)));
    }

    public void clearBitmap() {
        this.mDelegate.clearBitmap();
        this.mClipViewDelegate.clear();
    }

    public void clearListeners() {
        this.mDelegate.clearListeners();
    }

    public PhotoViewMotionControl createDefaultMotionControl(ViewParent viewParent) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.mDelegate.setMinimumTileDpi(displayMetrics, ThumbKind.MEDIUM_KIND_SIZE);
        PhotoViewMotionControl createMotionControlInstance = createMotionControlInstance(this.mDelegate.buildSourceInfoGetter());
        createMotionControlInstance.setViewParent(viewParent);
        createMotionControlInstance.setOffset(displayMetrics.density);
        createMotionControlInstance.setGestureDetector(getContext());
        createMotionControlInstance.setBlockPinchShrink(this.mConfig.mBlockPinchShrink);
        createMotionControlInstance.setUnlimitedDoubleTapZoom(this.mConfig.mIsUnlimitedDoubleTapZoom);
        return createMotionControlInstance;
    }

    public PhotoViewMotionControl createMotionControlInstance(PhotoViewMotionControl.SourceInfoGetter sourceInfoGetter) {
        return new PhotoViewMotionControl(this, sourceInfoGetter, this.mPosCtrl);
    }

    public void fitToBounds(boolean z, ScaleAndTranslate scaleAndTranslate) {
        this.mDelegate.fitToBounds(z, scaleAndTranslate);
    }

    public final void forceResetScaleAndCenter() {
        resetScaleAndCenterInternal();
    }

    public Bitmap getBitmap() {
        return this.mImageProcessor.getBitmap();
    }

    public int getBottomMargin() {
        return ((ViewGroup.MarginLayoutParams) getLayoutParams()).bottomMargin;
    }

    public int getBottomMarginFromSupplier() {
        ViewGroup.MarginLayoutParams marginLayoutParams = this.mProxyViewLayoutParams;
        if (marginLayoutParams != null) {
            return marginLayoutParams.bottomMargin;
        }
        return getBottomMargin();
    }

    public PointF getCenter() {
        return this.mPosCtrl.viewToSourceCoord(((float) getViewWidth()) / 2.0f, ((float) getViewHeight()) / 2.0f);
    }

    public Rect getCropRectOnImage() {
        return this.mPosCtrl.getCropRectOnImage(getViewWidth(), getViewHeight(), this.mImageProcessor.getWidth(true), this.mImageProcessor.getHeight(true));
    }

    public Rect getCropRectOnView() {
        PhotoViewPositionControl photoViewPositionControl = this.mPosCtrl;
        return photoViewPositionControl.getCropRectOnView(limitedScale(photoViewPositionControl.getScale()), getViewWidth(), getViewHeight(), this.mImageProcessor.getWidth(true), this.mImageProcessor.getHeight(true));
    }

    public float getCurrentScale() {
        return this.mPosCtrl.getScale();
    }

    public Semaphore getDecoderLock() {
        return this.mImageProcessor.mDecoderLock;
    }

    public Matrix getDisplayMatrix() {
        return this.mConfig.mMatrix;
    }

    public RectF getDisplayMinRect() {
        return this.mDelegate.getDisplayMinRect();
    }

    public final RectF getDisplayRect() {
        return this.mDelegate.getDisplayRect();
    }

    public float getLastTapX() {
        return this.mLastTapX;
    }

    public float getLastTapY() {
        return this.mLastTapY;
    }

    public void getLocation(int[] iArr) {
        getLocationInWindow(iArr);
    }

    public float getMaxScale() {
        PhotoViewConfig photoViewConfig = this.mConfig;
        if (photoViewConfig.mIsViewInitialCenterCrop || photoViewConfig.mIsUnlimitedDoubleTapZoom) {
            return Math.max(photoViewConfig.mMaxScale, centerCropScale());
        }
        return photoViewConfig.mMaxScale;
    }

    public PhotoViewMotionControl getMotionControl() {
        return this.mMotionControl;
    }

    public final int getSampleSize() {
        return Math.min(this.mImageProcessor.getFullImageSampleSize(), calculateInSampleSize(this.mPosCtrl.getScale()));
    }

    public float getScaleRatio() {
        return minScale() / this.mPosCtrl.getScale();
    }

    public PointF getScaledPosition() {
        return this.mPosCtrl.getPosition();
    }

    public int getSceneType() {
        return -1;
    }

    public int getSourceHash() {
        return this.mImageProcessor.getHash();
    }

    public int getSourceHeight() {
        return this.mImageProcessor.getHeight(true);
    }

    public int getSourceWidth() {
        return this.mImageProcessor.getWidth(true);
    }

    public int getTopMargin() {
        return ((ViewGroup.MarginLayoutParams) getLayoutParams()).topMargin;
    }

    public int getTopMarginFromSupplier() {
        ViewGroup.MarginLayoutParams marginLayoutParams = this.mProxyViewLayoutParams;
        if (marginLayoutParams != null) {
            return marginLayoutParams.topMargin;
        }
        return getTopMargin();
    }

    public int getViewHeight() {
        return getHeight();
    }

    public int getViewWidth() {
        return getWidth();
    }

    public void handleAnimation() {
        PhotoViewMotionControl photoViewMotionControl = this.mMotionControl;
        if (photoViewMotionControl != null) {
            ScaleAnimation scaleAnimation = photoViewMotionControl.getScaleAnimation();
            if (scaleAnimation != null) {
                scaleOnDraw(scaleAnimation);
            } else if (this.mImageProcessor.hasUnloadedTile(getSampleSize())) {
                Log.i(this.TAG, "refresh unloaded tiles");
                refreshRequiredTiles(true, this.mPosCtrl);
            }
        }
    }

    public boolean hasSource() {
        return this.mImageProcessor.hasSource();
    }

    public void ignoreSetAlphaOnce() {
        this.mIgnoreSetAlpha = true;
    }

    public synchronized void initBaseLayer(Point point) {
        this.mConfig.mSatTemp = new ScaleAndTranslate(0.0f, new PointF(0.0f, 0.0f));
        fitToBounds(true, this.mConfig.mSatTemp);
        this.mImageProcessor.initBaseLayer(this, point, this.mPosCtrl);
    }

    public void initTileMap(Point point) {
        this.mImageProcessor.initTileMap(this, point);
    }

    public final void invalidatePreview() {
        invalidate();
        requestLayout();
    }

    public boolean isAlreadyUp() {
        PhotoViewMotionControl photoViewMotionControl = this.mMotionControl;
        if (photoViewMotionControl == null || !photoViewMotionControl.isAllFingerUp()) {
            return false;
        }
        return true;
    }

    public boolean isContentChanged(MediaItem mediaItem, Bitmap bitmap) {
        return this.mImageProcessor.isSourceChanged(mediaItem, bitmap);
    }

    public boolean isKeepCenterCrop() {
        return this.mConfig.mIsKeepCenterCrop;
    }

    public boolean isMaxScale() {
        if (this.mPosCtrl.getScale() >= getMaxScale()) {
            return true;
        }
        return false;
    }

    public boolean isMinScale() {
        if (this.mPosCtrl.getScale() <= minScale()) {
            return true;
        }
        return false;
    }

    public boolean isMovable() {
        return this.mClipViewDelegate.isMovable();
    }

    public boolean isSameSource(MediaItem mediaItem) {
        return this.mImageProcessor.isSameSource(mediaItem);
    }

    public boolean isToggleConsumable(float f, float f5) {
        if (this.mClipViewDelegate.onSinglePress(f, f5) || this.mClipViewDelegate.isToggleConsumable()) {
            return true;
        }
        return false;
    }

    public boolean isTranslated() {
        OnTranslationListener onTranslationListener = this.mOnTranslationListener;
        if (onTranslationListener == null || !onTranslationListener.isTranslated()) {
            return false;
        }
        return true;
    }

    public final boolean isViewAndBitmapReady() {
        if (!isSourceAndViewReady()) {
            return false;
        }
        if (this.mImageProcessor.hasBitmap() || this.mImageProcessor.isBaseLayerReady()) {
            return true;
        }
        return false;
    }

    public boolean isZoomEnabled() {
        return this.mConfig.mIsZoomEnabled;
    }

    public boolean isZoomed() {
        if (MathUtils.compare(this.mPosCtrl.getScale(), minScale()) > 0) {
            return true;
        }
        return false;
    }

    public boolean isZoomedByMinSize() {
        if (this.mDelegate.getScaleForMinSize() > this.mDelegate.getDefaultMinScale()) {
            return true;
        }
        return false;
    }

    public float limitedScale(float f) {
        return Math.min(getMaxScale(), Math.max(minScale(), f));
    }

    public final float minScale() {
        return this.mDelegate.minScale();
    }

    public void notifyMatrixChanged() {
        OnMatrixChangeListener onMatrixChangeListener = this.mOnMatrixChangeListener;
        if (onMatrixChangeListener != null) {
            ((C0523d) onMatrixChangeListener).e.onPhotoViewMatrixChanged(isAnimating());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0094, code lost:
        r0 = r5.mImageProcessor;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onDraw(android.graphics.Canvas r6) {
        /*
            r5 = this;
            r5.applyMirrorHorizontal(r6)
            super.onDraw(r6)
            r5.createPaints()
            boolean r0 = r5.isSourceAndViewReady()
            if (r0 != 0) goto L_0x002a
            com.samsung.android.gallery.support.utils.StringCompat r6 = r5.TAG
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "isSourceAndViewReady failed : "
            r0.<init>(r1)
            com.samsung.android.gallery.widget.photoview.ImageProcessor r5 = r5.mImageProcessor
            r1 = 0
            int r5 = r5.getWidth(r1)
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            com.samsung.android.gallery.support.utils.Log.w(r6, r5)
            return
        L_0x002a:
            com.samsung.android.gallery.widget.photoview.ImageProcessor r0 = r5.mImageProcessor
            java.util.Map r0 = r0.getTileMap()
            if (r0 != 0) goto L_0x003f
            com.samsung.android.gallery.widget.photoview.ImageProcessor r0 = r5.mImageProcessor
            boolean r0 = r0.isRunRegionDecoding()
            if (r0 == 0) goto L_0x003f
            android.graphics.Point r0 = r5.mLastMaxDimensions
            r5.initBaseLayer(r0)
        L_0x003f:
            boolean r0 = r5.isViewAndBitmapReady()
            if (r0 != 0) goto L_0x0057
            com.samsung.android.gallery.support.utils.StringCompat r6 = r5.TAG
            com.samsung.android.gallery.widget.photoview.ImageProcessor r5 = r5.mImageProcessor
            android.graphics.Bitmap r5 = r5.getBitmap()
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            java.lang.String r0 = "checkReady failed : "
            com.samsung.android.gallery.support.utils.Log.w((java.lang.CharSequence) r6, (java.lang.String) r0, (java.lang.Object[]) r5)
            return
        L_0x0057:
            r5.preDraw()
            com.samsung.android.gallery.widget.photoview.PhotoViewDelegate r0 = r5.mDelegate
            r0.makeRoundCorner(r6)
            r5.handleAnimation()
            com.samsung.android.gallery.widget.photoview.ImageProcessor r0 = r5.mImageProcessor
            java.util.Map r0 = r0.getTileMap()
            if (r0 == 0) goto L_0x007f
            com.samsung.android.gallery.widget.photoview.ImageProcessor r0 = r5.mImageProcessor
            boolean r0 = r0.isBaseLayerReady()
            if (r0 == 0) goto L_0x007f
            r5.renderTileOnDraw(r6)
            boolean r0 = com.samsung.android.gallery.widget.photoview.PhotoViewConfig.SUPPORT_PPP_BLENDING
            if (r0 == 0) goto L_0x00e8
            com.samsung.android.gallery.widget.photoview.ImageProcessor r0 = r5.mImageProcessor
            r1 = 0
            r0.mLastBitmap = r1
            goto L_0x00e8
        L_0x007f:
            com.samsung.android.gallery.widget.photoview.ImageProcessor r0 = r5.mImageProcessor
            boolean r0 = r0.hasBitmap()
            if (r0 == 0) goto L_0x00db
            com.samsung.android.gallery.widget.photoview.ImageProcessor r0 = r5.mImageProcessor
            android.graphics.Bitmap r0 = r0.getBitmap()
            r5.renderBitmapOnDraw(r6, r0)
            boolean r0 = com.samsung.android.gallery.widget.photoview.PhotoViewConfig.SUPPORT_PPP_BLENDING
            if (r0 == 0) goto L_0x00bf
            com.samsung.android.gallery.widget.photoview.ImageProcessor r0 = r5.mImageProcessor
            android.graphics.Bitmap r1 = r0.mLastBitmap
            if (r1 == 0) goto L_0x00bf
            android.graphics.Bitmap r0 = r0.getBitmap()
            if (r1 == r0) goto L_0x00bf
            com.samsung.android.gallery.widget.photoview.ImageProcessor r0 = r5.mImageProcessor
            float r0 = r0.mAlphaBlendingValue
            r1 = 1132396544(0x437f0000, float:255.0)
            float r0 = r0 * r1
            int r0 = (int) r0
            com.samsung.android.gallery.widget.photoview.PhotoViewConfig r1 = r5.mConfig
            android.graphics.Paint r1 = r1.mBitmapPaint
            r1.setAlpha(r0)
            com.samsung.android.gallery.widget.photoview.ImageProcessor r0 = r5.mImageProcessor
            android.graphics.Bitmap r0 = r0.mLastBitmap
            r5.renderBitmapOnDraw(r6, r0)
            com.samsung.android.gallery.widget.photoview.PhotoViewConfig r0 = r5.mConfig
            android.graphics.Paint r0 = r0.mBitmapPaint
            r1 = 255(0xff, float:3.57E-43)
            r0.setAlpha(r1)
        L_0x00bf:
            com.samsung.android.gallery.widget.photoview.PhotoViewConfig r0 = r5.mConfig
            com.samsung.android.gallery.widget.photoview.ImageProcessor r1 = r5.mImageProcessor
            android.graphics.Bitmap r1 = r1.getBitmap()
            int r1 = r1.getHeight()
            long r1 = (long) r1
            com.samsung.android.gallery.widget.photoview.ImageProcessor r3 = r5.mImageProcessor
            android.graphics.Bitmap r3 = r3.getBitmap()
            int r3 = r3.getWidth()
            long r3 = (long) r3
            long r1 = r1 * r3
            r0.mLastDrawnPixels = r1
            goto L_0x00e8
        L_0x00db:
            com.samsung.android.gallery.support.utils.StringCompat r0 = r5.TAG
            java.lang.String r1 = "draw nothing"
            com.samsung.android.gallery.support.utils.Log.w(r0, r1)
            com.samsung.android.gallery.widget.photoview.PhotoViewConfig r0 = r5.mConfig
            r1 = 0
            r0.mLastDrawnPixels = r1
        L_0x00e8:
            r5.updateCropImageMatrix()
            r5.notifyMatrixChanged()
            com.samsung.android.gallery.widget.photoview.DebugDelegate r0 = r5.mDebugDelegate
            if (r0 == 0) goto L_0x00f5
            r0.draw(r5, r6)
        L_0x00f5:
            com.samsung.android.gallery.widget.capture.ClipViewDelegate r5 = r5.mClipViewDelegate
            r5.onDraw()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.photoview.PhotoView.onDraw(android.graphics.Canvas):void");
    }

    public void onExitGesture(boolean z, boolean z3) {
        OnViewerExitGestureListener onViewerExitGestureListener = this.mOnViewerExitGestureListener;
        if (onViewerExitGestureListener != null) {
            onViewerExitGestureListener.onExitGesture(z, z3);
        }
    }

    public void onFling(boolean z) {
        OnFlingListener onFlingListener = this.mOnFlingListener;
        if (onFlingListener != null) {
            onFlingListener.onFling(z);
        }
    }

    public void onLongPress(float f, float f5) {
        if (this.mClipViewDelegate.onLongPress(f, f5)) {
            invalidate();
            return;
        }
        OnLongPressListener onLongPressListener = this.mOnLongPressListener;
        if (onLongPressListener != null) {
            ((C0523d) onLongPressListener).e.onLongPressed(f, f5);
        }
    }

    public void onMeasure(int i2, int i7) {
        boolean z;
        int mode = View.MeasureSpec.getMode(i2);
        int mode2 = View.MeasureSpec.getMode(i7);
        int size = View.MeasureSpec.getSize(i2);
        int size2 = View.MeasureSpec.getSize(i7);
        boolean z3 = false;
        if (mode != 1073741824) {
            z = true;
        } else {
            z = false;
        }
        if (mode2 != 1073741824) {
            z3 = true;
        }
        if (this.mImageProcessor.isSourceReady()) {
            if (z && z3) {
                size = this.mImageProcessor.getWidth(true);
                size2 = this.mImageProcessor.getHeight(true);
            } else if (z3) {
                size2 = (int) ((((double) this.mImageProcessor.getHeight(true)) / ((double) this.mImageProcessor.getWidth(true))) * ((double) size));
            } else if (z) {
                size = (int) ((((double) this.mImageProcessor.getWidth(true)) / ((double) this.mImageProcessor.getHeight(true))) * ((double) size2));
            }
        }
        setMeasuredDimension(Math.max(size, getSuggestedMinimumWidth()), Math.max(size2, getSuggestedMinimumHeight()));
    }

    public void onPreviewLoaded(Bitmap bitmap) {
        if (this.mImageProcessor.hasBitmap() || this.mImageProcessor.isBaseLayerReady()) {
            StringCompat stringCompat = this.TAG;
            Log.e(stringCompat, "onPreviewLoaded skip {" + this.mImageProcessor.hasBitmap() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mImageProcessor.isBaseLayerReady() + "}");
            return;
        }
        this.mImageProcessor.setBitmap(bitmap);
        if (isViewAndBitmapReady()) {
            invalidate();
            requestLayout();
        }
    }

    public void onSecondaryClicked(MotionEvent motionEvent) {
        OnSecondaryClickListener onSecondaryClickListener = this.mOnSecondaryClickListener;
        if (onSecondaryClickListener != null) {
            ((C0523d) onSecondaryClickListener).a(motionEvent);
        }
    }

    public void onSizeChanged(int i2, int i7, int i8, int i10) {
        this.mDelegate.checkZoomState(this.mPosCtrl.getScale());
        PointF center = getCenter();
        if (isViewAndBitmapReady() && center != null) {
            PhotoViewMotionControl photoViewMotionControl = this.mMotionControl;
            if (photoViewMotionControl != null) {
                photoViewMotionControl.setScaleAnimation((ScaleAnimation) null);
            }
            if (i7 <= 0) {
                Log.w(this.TAG, "onSizeChanged : height = 0, reset scale as minimum!");
                this.mPosCtrl.setScale(1.0E-4f);
            }
            this.mConfig.mPendingScale = Float.valueOf(this.mPosCtrl.getScale());
            this.mConfig.mSPendingCenter = center;
        }
        Runnable runnable = this.mOnSizeChangedListener;
        if (runnable != null) {
            runnable.run();
        }
    }

    public synchronized void onTileLoaded(int i2, Tile tile) {
        boolean z;
        String str;
        long j2;
        try {
            int sampleSize = getSampleSize();
            if (i2 == sampleSize) {
                z = true;
            } else {
                z = false;
            }
            boolean hasMissingTile = this.mImageProcessor.hasMissingTile(i2, this.mPosCtrl, getViewWidth(), getViewHeight());
            StringCompat stringCompat = this.TAG;
            StringBuilder sb2 = new StringBuilder("onTileLoaded :");
            sb2.append(i2);
            sb2.append("/");
            sb2.append(sampleSize);
            if (!hasMissingTile) {
                StringBuilder sb3 = new StringBuilder(" DONE +");
                if (this.mImageProcessor.mTileTaskElapsed == 0) {
                    j2 = 0;
                } else {
                    j2 = System.currentTimeMillis() - this.mImageProcessor.mTileTaskElapsed;
                }
                sb3.append(j2);
                str = sb3.toString();
            } else {
                str = "";
            }
            sb2.append(str);
            Log.v(stringCompat, sb2.toString(), tile);
            if (z) {
                tile.bitmap.prepareToDraw();
                if (!hasMissingTile) {
                    this.mImageProcessor.invisibleTiles(i2);
                    this.mImageProcessor.mTileTaskElapsed = 0;
                }
            }
            if (!hasMissingTile) {
                invalidate();
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public void onTileTaskDone(LoadTileTask loadTileTask) {
        this.mImageProcessor.removeTileTask(loadTileTask);
        Runnable runnable = this.mOnTileTaskDoneCallback;
        if (runnable != null) {
            runnable.run();
        }
    }

    public boolean onTouch(MotionEvent motionEvent) {
        PhotoViewMotionControl photoViewMotionControl = this.mMotionControl;
        if (photoViewMotionControl == null || !photoViewMotionControl.onTouch(this, motionEvent)) {
            return false;
        }
        return true;
    }

    public boolean onTouchCapture(MotionEvent motionEvent) {
        return this.mClipViewDelegate.onTouch(motionEvent);
    }

    public void postSetAlphaOnce(boolean z) {
        this.mIsPostSetAlphaOnce = z;
    }

    public void recycle() {
        PhotoViewConfig photoViewConfig = this.mConfig;
        photoViewConfig.mBlockPendingScale = false;
        photoViewConfig.mInitializedBound = false;
        photoViewConfig.mRoundedCornerRadius = 0;
        clearBitmap();
        setMaxScale(5.0f);
    }

    public void recycleTileMap() {
        this.mImageProcessor.recycleTileMap();
    }

    public void refreshCaptureView() {
        this.mClipViewDelegate.refreshView();
    }

    public void refreshRequiredTiles(boolean z, PhotoViewPositionControl photoViewPositionControl) {
        this.mImageProcessor.refreshRequiredTiles(this, z, photoViewPositionControl);
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void renderTileOnDraw(android.graphics.Canvas r9) {
        /*
            r8 = this;
            com.samsung.android.gallery.widget.photoview.ImageProcessor r0 = r8.mImageProcessor
            java.util.Map r0 = r0.getTileMap()
            if (r0 != 0) goto L_0x0010
            com.samsung.android.gallery.support.utils.StringCompat r8 = r8.TAG
            java.lang.String r9 = "fail render tile"
            com.samsung.android.gallery.support.utils.Log.w(r8, r9)
            return
        L_0x0010:
            int r1 = r8.getSampleSize()
            com.samsung.android.gallery.widget.photoview.ImageProcessor r2 = r8.mImageProcessor
            boolean r2 = r2.hasMissingTile(r1)
            if (r2 == 0) goto L_0x0036
            com.samsung.android.gallery.widget.photoview.ImageProcessor r3 = r8.mImageProcessor
            int r4 = r1 * 2
            com.samsung.android.gallery.widget.photoview.PhotoViewPositionControl r5 = r8.mPosCtrl
            int r6 = r8.getViewWidth()
            int r7 = r8.getViewHeight()
            boolean r3 = r3.hasMissingTile(r4, r5, r6, r7)
            if (r3 != 0) goto L_0x0036
            r3 = 0
            boolean r9 = r8.drawTiles(r9, r4, r3)
            goto L_0x003b
        L_0x0036:
            boolean r9 = r8.drawTiles(r9, r1, r2)
            r4 = r1
        L_0x003b:
            if (r9 != 0) goto L_0x006e
            com.samsung.android.gallery.support.utils.StringCompat r9 = r8.TAG
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r5 = "NOTHING TO DRAW : "
            r3.<init>(r5)
            com.samsung.android.gallery.widget.photoview.ImageProcessor r8 = r8.mImageProcessor
            int r8 = r8.getFullImageSampleSize()
            r3.append(r8)
            java.lang.String r8 = r3.toString()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r4)
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)
            int r4 = r0.size()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            java.lang.Object[] r0 = new java.lang.Object[]{r1, r3, r2, r0, r4}
            com.samsung.android.gallery.support.utils.Log.e((java.lang.CharSequence) r9, (java.lang.String) r8, (java.lang.Object[]) r0)
        L_0x006e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.photoview.PhotoView.renderTileOnDraw(android.graphics.Canvas):void");
    }

    public void reset(boolean z) {
        this.mDelegate.reset(z);
    }

    public void resetBeforeCenterCrop() {
        reset(false);
    }

    public void resetRegionDecodingInfo() {
        if (this.mImageProcessor.isSupportRegionDecoding() && this.mImageProcessor.isBaseLayerReady()) {
            StringCompat stringCompat = this.TAG;
            Log.v(stringCompat, "resetRegionDecodingInfo {" + this.mImageProcessor.isBaseLayerReady() + "}");
            ThreadPool.getInstance().submitUrgent(new c(this, 0));
        }
    }

    public void resetRegionDecodingInfoDirectly() {
        if (this.mImageProcessor.isSupportRegionDecoding() && this.mImageProcessor.isBaseLayerReady()) {
            this.mImageProcessor.lambda$close$9();
            recycleTileMap();
        }
    }

    public final void resetScaleAndCenter() {
        if (isZoomed() || forceResetScale()) {
            resetScaleAndCenterInternal();
        }
    }

    public void retryRegionDecoding() {
        if (this.mConfig.mZoomState) {
            tryRegionDecoding();
        }
    }

    public void scaleOnDraw(ScaleAnimation scaleAnimation) {
        boolean z;
        if (scaleAnimation.getStartPointOfView() != null) {
            long currentTimeMillis = System.currentTimeMillis();
            this.mPosCtrl.setScale(scaleAnimation.getEaseScale(currentTimeMillis));
            float easeFocusX = scaleAnimation.getEaseFocusX(currentTimeMillis);
            float easeFocusY = scaleAnimation.getEaseFocusY(currentTimeMillis);
            PhotoViewPositionControl photoViewPositionControl = this.mPosCtrl;
            photoViewPositionControl.offset((-photoViewPositionControl.sourceToViewX(scaleAnimation.getTargetSCenter().x)) + easeFocusX, (-this.mPosCtrl.sourceToViewY(scaleAnimation.getTargetSCenter().y)) + easeFocusY);
            boolean isFinished = scaleAnimation.isFinished(currentTimeMillis);
            if (isFinished || scaleAnimation.isFixedScale()) {
                z = true;
            } else {
                z = false;
            }
            fitToBounds(z);
            refreshRequiredTiles(isFinished, this.mPosCtrl);
            if (isFinished) {
                this.mPosCtrl.setScale(scaleAnimation.getTargetScale());
                PhotoViewMotionControl photoViewMotionControl = this.mMotionControl;
                if (photoViewMotionControl != null) {
                    photoViewMotionControl.setScaleAnimation((ScaleAnimation) null);
                }
            }
            invalidate();
        }
    }

    public void setAlpha(float f) {
        if (this.mIgnoreSetAlpha || this.mIsPostSetAlphaOnce) {
            if (this.mIsPostSetAlphaOnce) {
                postDelayed(new k(this, f, 1), 30);
            }
            this.mIgnoreSetAlpha = false;
            return;
        }
        super.setAlpha(f);
    }

    public void setAndKeepCenterCrop(boolean z) {
        PhotoViewConfig photoViewConfig = this.mConfig;
        photoViewConfig.mIsViewInitialCenterCrop = z;
        photoViewConfig.mIsKeepCenterCrop = z;
    }

    public void setCaptureActionMode(ICaptureActionMode iCaptureActionMode) {
        this.mClipViewDelegate.setCaptureActionMode(iCaptureActionMode);
    }

    public boolean setFrame(int i2, int i7, int i8, int i10) {
        boolean z;
        if (isTranslated() || (!this.mConfig.mIsViewInitialCenterCrop && (isMinScale() || !this.mConfig.mZoomState))) {
            z = true;
        } else {
            z = false;
        }
        boolean frame = super.setFrame(i2, i7, i8, i10);
        if (z && (frame || this.mConfig.mBlockPendingScale)) {
            resetScaleAndCenter();
        }
        return frame;
    }

    public void setImage(MediaItem mediaItem, Bitmap bitmap) {
        setImage(mediaItem, bitmap, (BytesBuffer) null);
    }

    public void setInitialCenterCrop(boolean z) {
        this.mConfig.mIsViewInitialCenterCrop = z;
    }

    public void setIsZooming(boolean z) {
        PhotoViewMotionControl photoViewMotionControl = this.mMotionControl;
        if (photoViewMotionControl != null) {
            photoViewMotionControl.setIsZooming(z);
        }
    }

    public void setLogTag(String str) {
        this.TAG.setTag(str);
        this.mImageProcessor.setTag(str);
    }

    public void setMaxScale(float f) {
        this.mConfig.mMaxScale = f;
    }

    public void setMaxScaleRelative(float f) {
        this.mConfig.mMaxScale = getMaxScale() * f;
    }

    public void setMotionControl(PhotoViewMotionControl photoViewMotionControl, OnViewerExitGestureListener onViewerExitGestureListener) {
        this.mMotionControl = photoViewMotionControl;
        this.mOnViewerExitGestureListener = onViewerExitGestureListener;
        setOnTouchListener(photoViewMotionControl);
    }

    public void setMotionZoomListener(PhotoViewMotionControl.OnMotionZoomListener onMotionZoomListener) {
        PhotoViewMotionControl photoViewMotionControl = this.mMotionControl;
        if (photoViewMotionControl != null) {
            photoViewMotionControl.setMotionZoomListener(onMotionZoomListener);
        }
    }

    public void setOnFlingListener(OnFlingListener onFlingListener) {
        this.mOnFlingListener = onFlingListener;
    }

    public void setOnLongPressListener(OnLongPressListener onLongPressListener) {
        this.mOnLongPressListener = onLongPressListener;
    }

    public void setOnMatrixChangeListener(OnMatrixChangeListener onMatrixChangeListener) {
        this.mOnMatrixChangeListener = onMatrixChangeListener;
    }

    public void setOnSecondaryClickListener(OnSecondaryClickListener onSecondaryClickListener) {
        this.mOnSecondaryClickListener = onSecondaryClickListener;
    }

    public void setOnSizeChangeListener(Runnable runnable) {
        this.mOnSizeChangedListener = runnable;
    }

    public void setOnTileTaskDoneCallback(Runnable runnable) {
        this.mOnTileTaskDoneCallback = runnable;
    }

    public void setPadding(int i2, int i7, int i8, int i10) {
        boolean isMinScale = isMinScale();
        super.setPadding(i2, i7, i8, i10);
        if (isMinScale && isViewAndBitmapReady()) {
            setScale(minScale());
        }
    }

    public void setPositionChangeListener(Consumer<PointF> consumer) {
        this.mPosCtrl.setOnPositionChangeListener(consumer);
    }

    public void setProxyViewLayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
        this.mProxyViewLayoutParams = marginLayoutParams;
    }

    public void setRoundedCorner(int i2) {
        this.mConfig.mRoundedCornerRadius = i2;
    }

    public void setScale(float f) {
        this.mPosCtrl.setScale(Math.min(getMaxScale(), f));
        fitToBounds(true);
        refreshRequiredTiles(true, this.mPosCtrl);
    }

    public void setScaleChangeListener(Consumer<Float> consumer) {
        this.mScaleChangeListener = consumer;
    }

    public void setScalePosition(PointF pointF) {
        this.mPosCtrl.setPosition(pointF);
    }

    public void setScaleRelative(float f) {
        PointF center;
        if (this.mMotionControl != null && (center = getCenter()) != null) {
            this.mMotionControl.zoom(f * this.mPosCtrl.getScale(), center);
        }
    }

    public void setSourceInfo(MediaItem mediaItem, Bitmap bitmap) {
        this.mImageProcessor.setSourceInfo(mediaItem, bitmap, (BytesBuffer) null);
    }

    public void setSupportCustomCropRect(boolean z) {
        this.mConfig.mSupportCustomCrop = z;
    }

    public void setTouchMirroringListener(PhotoViewMotionControl.OnTouchMirroringListener onTouchMirroringListener) {
        PhotoViewMotionControl photoViewMotionControl = this.mMotionControl;
        if (photoViewMotionControl != null) {
            photoViewMotionControl.setTouchMirroringListener(onTouchMirroringListener);
        }
    }

    public void setTranslationListener(OnTranslationListener onTranslationListener) {
        this.mOnTranslationListener = onTranslationListener;
    }

    public void setVisibility(int i2) {
        this.mDelegate.debugVisibility(i2);
        super.setVisibility(i2);
    }

    public void setZoomDetectListener(OnZoomDetectListener onZoomDetectListener) {
        this.mOnZoomDetectListener = onZoomDetectListener;
    }

    public void setZoomEnabled(boolean z) {
        this.mConfig.mIsZoomEnabled = z;
    }

    public boolean supportZoomCompat() {
        return false;
    }

    public String toString() {
        return Logger.v(this) + this.mDelegate.toDebugString();
    }

    public void tryRegionDecoding() {
        if (!this.mConfig.mBlockRegionDecoding) {
            this.mImageProcessor.tryRegionDecoding(this);
        }
    }

    public void updateAnim(Bitmap bitmap) {
        if (this.mImageProcessor == ImageProcessor.EMPTY) {
            this.mImageProcessor = new ImageProcessor(this.TAG.getTag());
        }
        computeIfDifferent(bitmap.getWidth(), bitmap.getHeight());
        this.mImageProcessor.setBitmap(bitmap);
        invalidate();
    }

    public void updateMatrixForBitmap(Bitmap bitmap) {
        this.mDelegate.updateMatrixForBitmap(bitmap);
    }

    public PointF vTranslateForSCenter(float f, float f5, float f8) {
        return this.mDelegate.vTranslateForSCenter(f, f5, f8);
    }

    public void fitToBounds(boolean z) {
        this.mDelegate.fitToBounds(z);
    }

    public final int getSampleSize(float f) {
        return this.mDelegate.getSampleSize(f);
    }

    public void setImage(MediaItem mediaItem, Bitmap bitmap, BytesBuffer bytesBuffer) {
        String str;
        int width;
        int height;
        if (getVisibility() != 0) {
            setVisibility(0);
        }
        if (bitmap == null || bitmap.isRecycled()) {
            Log.e(this.TAG, "setImage skip");
            return;
        }
        this.mConfig.mPhotoHdrSupported = mediaItem.isPhotoHdrSupported();
        PhotoViewConfig photoViewConfig = this.mConfig;
        photoViewConfig.mPhotoHdrCandidate = photoViewConfig.mPhotoHdrSupported && mediaItem.isPhotoHdrCandidate();
        boolean isPathChanged = this.mImageProcessor.isPathChanged(mediaItem.getPath(), bytesBuffer);
        boolean z = !isPathChanged && isContentChanged(mediaItem, bitmap);
        boolean isPostProcessing = this.mImageProcessor.isPostProcessing();
        boolean z3 = !mediaItem.isBroken() && !this.mImageProcessor.isSourceReady();
        boolean z7 = isPostProcessing && !mediaItem.isPostProcessing();
        boolean z9 = !isPostProcessing && (isPathChanged || z || z3);
        if (z7 && this.mImageProcessor.hasDifferentSize(false, mediaItem.getWidth(), mediaItem.getHeight())) {
            Log.e((CharSequence) this.TAG, "ppp resolution changed!!", Integer.valueOf(this.mImageProcessor.getWidth(false)), Integer.valueOf(this.mImageProcessor.getHeight(false)), Integer.valueOf(mediaItem.getWidth()), Integer.valueOf(mediaItem.getHeight()));
            z9 = true;
        }
        Bitmap bitmap2 = this.mImageProcessor.getBitmap();
        if (bitmap == bitmap2 && bitmap.getGenerationId() == this.mImageProcessor.getBitmapGenerationId() && !z9) {
            Log.e(this.TAG, "setImage duplicated");
            return;
        }
        if (z9) {
            reset(true);
            this.mImageProcessor.setSourceInfo(mediaItem, bitmap, bytesBuffer);
            resetScaleAndCenter();
        } else {
            this.mImageProcessor.close();
            ImageProcessor imageProcessor = new ImageProcessor(this.TAG.getTag());
            this.mImageProcessor = imageProcessor;
            imageProcessor.setSourceInfo(mediaItem, bitmap, bytesBuffer);
            if (this.mConfig.mZoomState) {
                tryRegionDecoding();
            }
            if (z7 && PhotoViewConfig.SUPPORT_PPP_BLENDING) {
                if ((width = mediaItem.getWidth()) > (height = mediaItem.getHeight())) {
                    Log.d(this.TAG, "ppp blending skip", C0086a.i(width, "w="), C0086a.i(height, "h="));
                } else {
                    Log.d(this.TAG, "ppp blending skip", C0086a.i(width, "w="), C0086a.i(height, "h="));
                }
                this.mDelegate.startBlendingAni(250);
                this.mImageProcessor.backupLastBitmap(bitmap2);
            }
        }
        updateImage(bitmap);
        if (bitmap2 == null) {
            requestLayout();
        }
        StringCompat stringCompat = this.TAG;
        StringBuilder sb2 = new StringBuilder("setImage");
        StringBuilder sb3 = new StringBuilder();
        sb3.append(mediaItem.getWidth());
        String str2 = "x";
        sb3.append(str2);
        sb3.append(mediaItem.getHeight());
        sb3.append(com.samsung.android.sdk.scs.base.utils.Log.TAG_SEPARATOR);
        sb3.append(mediaItem.getOrientation());
        String sb4 = sb3.toString();
        String str3 = "S";
        if (z9) {
            StringBuilder sb5 = new StringBuilder();
            sb5.append(z ? "C" : "c");
            sb5.append(z3 ? str3 : "s");
            sb5.append(isPathChanged ? "P" : "p");
            str = sb5.toString();
        } else {
            str = "csp";
        }
        StringBuilder sb6 = new StringBuilder();
        String str4 = "";
        if (isPostProcessing) {
            if (z7) {
                str4 = "(done)";
            }
            str4 = "PPP".concat(str4);
        }
        sb6.append(str4);
        if (bytesBuffer == null) {
            str3 = "F";
        }
        sb6.append(str3);
        if (mediaItem.isBroken()) {
            str2 = "X";
        }
        sb6.append(str2);
        sb2.append(Logger.v(sb4, str, sb6.toString(), "(" + getLeft() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + getTop() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + getRight() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + getBottom() + ")", Float.valueOf(this.mPosCtrl.getScale())));
        sb2.append(" ");
        sb2.append(Logger.toString(bitmap));
        Log.p(stringCompat, sb2.toString());
    }

    public void updateCropImageMatrix() {
    }

    public void setImage(Bitmap bitmap, int i2, int i7) {
        if (this.mImageProcessor == ImageProcessor.EMPTY) {
            this.mImageProcessor = new ImageProcessor(this.TAG.getTag());
        }
        computeIfDifferent(i2, i7);
        updateImage(bitmap);
    }

    public void setImage(Bitmap bitmap) {
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "setImage " + Logger.toString(bitmap));
        updateImage(bitmap);
    }
}
