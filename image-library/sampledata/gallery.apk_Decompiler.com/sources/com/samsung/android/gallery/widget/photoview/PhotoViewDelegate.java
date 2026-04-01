package com.samsung.android.gallery.widget.photoview;

import Nb.c;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.samsung.android.gallery.module.graphics.BitmapOptions;
import com.samsung.android.gallery.module.graphics.BitmapSizeHolder;
import com.samsung.android.gallery.support.utils.ExifUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.R$styleable;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimatorListener;
import com.samsung.android.gallery.widget.photoview.PhotoViewMotionControl;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class PhotoViewDelegate {
    final StringCompat TAG;
    PhotoView mView;

    public PhotoViewDelegate(PhotoView photoView) {
        this.mView = photoView;
        this.TAG = photoView.TAG;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startBlendingAni$0(ValueAnimator valueAnimator) {
        this.mView.mImageProcessor.mAlphaBlendingValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.mView.invalidate();
    }

    public static void setMatrixArray(float[] fArr, float f, float f5, float f8, float f10, float f11, float f12, float f13, float f14) {
        fArr[0] = f;
        fArr[1] = f5;
        fArr[2] = f8;
        fArr[3] = f10;
        fArr[4] = f11;
        fArr[5] = f12;
        fArr[6] = f13;
        fArr[7] = f14;
    }

    public static void updateDstArrayWithRotation(float[] fArr, Tile tile, int i2) {
        Tile tile2 = tile;
        int i7 = i2;
        if (i7 == 0) {
            Rect rect = tile2.vRect;
            int i8 = rect.left;
            int i10 = rect.top;
            int i11 = rect.right;
            int i12 = rect.bottom;
            float f = (float) i10;
            float[] fArr2 = fArr;
            setMatrixArray(fArr2, (float) i8, (float) i10, (float) i11, f, (float) i11, (float) i12, (float) i8, (float) i12);
        } else if (i7 == 90) {
            Rect rect2 = tile2.vRect;
            int i13 = rect2.right;
            int i14 = rect2.top;
            float f5 = (float) i13;
            int i15 = rect2.bottom;
            int i16 = rect2.left;
            float[] fArr3 = fArr;
            float f8 = (float) i16;
            setMatrixArray(fArr3, (float) i13, (float) i14, f5, (float) i15, f8, (float) i15, (float) i16, (float) i14);
        } else if (i7 == 180) {
            Rect rect3 = tile2.vRect;
            int i17 = rect3.right;
            int i18 = rect3.bottom;
            float f10 = (float) i18;
            int i19 = rect3.left;
            float f11 = (float) i19;
            float f12 = (float) i18;
            float f13 = (float) i19;
            int i20 = rect3.top;
            float f14 = f13;
            float f15 = (float) i20;
            setMatrixArray(fArr, (float) i17, f10, f11, f12, f14, f15, (float) i17, (float) i20);
        } else if (i7 == 270) {
            Rect rect4 = tile2.vRect;
            int i21 = rect4.left;
            int i22 = rect4.bottom;
            float f16 = (float) i21;
            int i23 = rect4.top;
            int i24 = rect4.right;
            float[] fArr4 = fArr;
            float f17 = (float) i24;
            setMatrixArray(fArr4, (float) i21, (float) i22, f16, (float) i23, f17, (float) i23, (float) i24, (float) i22);
        }
    }

    public View.AccessibilityDelegate buildAccessibilityDelegate() {
        return new View.AccessibilityDelegate() {
            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$dispatchPopulateAccessibilityEvent$0() {
                Supplier<String> supplier = PhotoViewDelegate.this.mView.mContentDescriptionSupplier;
                if (supplier != null) {
                    String str = supplier.get();
                    if (!TextUtils.isEmpty(str)) {
                        PhotoViewDelegate.this.mView.setContentDescription(str);
                        sendAccessibilityEvent(PhotoViewDelegate.this.mView, 32768);
                    }
                }
            }

            public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
                if (accessibilityEvent.getEventType() != 32768 || !TextUtils.isEmpty(PhotoViewDelegate.this.mView.getContentDescription()) || PhotoViewDelegate.this.mView.mContentDescriptionSupplier == null) {
                    return super.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
                }
                ThreadUtil.postOnBgThread(new p(this));
                return true;
            }

            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                String str;
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                Supplier<String> supplier = PhotoViewDelegate.this.mView.mAccessibilityUsageHintSupplier;
                if (supplier != null) {
                    str = supplier.get();
                } else {
                    str = null;
                }
                if (TextUtils.isEmpty(str)) {
                    accessibilityNodeInfo.removeAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_CLICK);
                    accessibilityNodeInfo.setClickable(false);
                    return;
                }
                accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(16, str));
            }
        };
    }

    public PhotoViewMotionControl.SourceInfoGetter buildSourceInfoGetter() {
        return new PhotoViewMotionControl.SourceInfoGetter() {
            public int getHeight(boolean z) {
                return PhotoViewDelegate.this.mView.mImageProcessor.getHeight(z);
            }

            public int getOrientationTag() {
                return PhotoViewDelegate.this.mView.mImageProcessor.getOrientationTag();
            }

            public int getWidth(boolean z) {
                return PhotoViewDelegate.this.mView.mImageProcessor.getWidth(z);
            }
        };
    }

    public int calculateInSampleSize(float f) {
        PhotoView photoView = this.mView;
        if (photoView.mConfig.mMinimumTileDpi > 0) {
            DisplayMetrics displayMetrics = photoView.getResources().getDisplayMetrics();
            f *= ((float) this.mView.mConfig.mMinimumTileDpi) / ((displayMetrics.xdpi + displayMetrics.ydpi) / 2.0f);
        }
        return this.mView.mImageProcessor.calculateInSampleSize(f);
    }

    public boolean checkZoomState(float f) {
        boolean z;
        if (f > minScale() * 1.1f) {
            z = true;
        } else {
            z = false;
        }
        if (this.mView.mConfig.mZoomState != z) {
            String stringCompat = this.TAG.toString();
            Log.majorEvent(stringCompat, "zoomStateChanged : " + z);
            if (z) {
                this.mView.tryRegionDecoding();
            }
            OnZoomDetectListener onZoomDetectListener = this.mView.mOnZoomDetectListener;
            if (onZoomDetectListener != null) {
                onZoomDetectListener.isZoomDetected(z);
            }
            this.mView.mConfig.mZoomState = z;
        }
        return z;
    }

    public void clearBitmap() {
        Bitmap bitmap = this.mView.mImageProcessor.getBitmap();
        if (bitmap == null || !bitmap.isRecycled()) {
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "clearBitmap " + Logger.toSimpleString(bitmap));
        } else {
            StringCompat stringCompat2 = this.TAG;
            Log.e(stringCompat2, "clearBitmap recycled " + bitmap);
        }
        this.mView.reset(true);
        this.mView.setImageDrawable((Drawable) null);
    }

    public void clearListeners() {
        PhotoView photoView = this.mView;
        photoView.mAccessibilityUsageHintSupplier = null;
        photoView.mContentDescriptionSupplier = null;
        photoView.mOnMatrixChangeListener = null;
        photoView.mOnZoomDetectListener = null;
        photoView.mOnSecondaryClickListener = null;
        photoView.mOnFlingListener = null;
        photoView.mOnLongPressListener = null;
        photoView.mOnViewerExitGestureListener = null;
        photoView.mOnTranslationListener = null;
        photoView.mProxyViewLayoutParams = null;
        photoView.mMotionControl = null;
        photoView.mOnTileTaskDoneCallback = null;
        photoView.setOnClickListener((View.OnClickListener) null);
        this.mView.addContentDescription((Supplier<String>) null);
        this.mView.addUsageHint((Supplier<String>) null);
        this.mView.setScaleChangeListener((Consumer<Float>) null);
    }

    public Paint createPaints() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        return paint;
    }

    public void debugVisibility(int i2) {
        if (i2 == 8 || i2 == 4) {
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "setVisibility {" + Logger.toVisibilityValue(i2) + '}');
        }
    }

    public void fitToBounds(boolean z, ScaleAndTranslate scaleAndTranslate) {
        float f;
        float f5;
        float f8;
        PointF pointF = scaleAndTranslate.vTranslate;
        float limitedScale = this.mView.limitedScale(scaleAndTranslate.scale);
        float width = ((float) this.mView.mImageProcessor.getWidth(true)) * limitedScale;
        float height = ((float) this.mView.mImageProcessor.getHeight(true)) * limitedScale;
        pointF.x = Math.max(pointF.x, z ? ((float) this.mView.getViewWidth()) - width : -width);
        pointF.y = Math.max(pointF.y, z ? ((float) this.mView.getViewHeight()) - height : -height);
        float f10 = 0.5f;
        if (this.mView.getPaddingLeft() > 0 || this.mView.getPaddingRight() > 0) {
            f = ((float) this.mView.getPaddingLeft()) / ((float) (this.mView.getPaddingRight() + this.mView.getPaddingLeft()));
        } else {
            f = 0.5f;
        }
        if (this.mView.getPaddingTop() > 0 || this.mView.getPaddingBottom() > 0) {
            f10 = ((float) this.mView.getPaddingTop()) / ((float) (this.mView.getPaddingBottom() + this.mView.getPaddingTop()));
        }
        if (z) {
            float viewWidth = ((float) this.mView.getViewWidth()) - width;
            float f11 = (float) 0;
            f5 = Math.max(0.0f, (viewWidth + f11) * f);
            f8 = Math.max(0.0f, ((((float) this.mView.getViewHeight()) - height) + f11) * f10);
        } else {
            f5 = (float) Math.max(0, this.mView.getViewWidth());
            f8 = (float) Math.max(0, this.mView.getViewHeight());
        }
        pointF.x = Math.min(pointF.x, f5);
        pointF.y = Math.min(pointF.y, f8);
        scaleAndTranslate.scale = limitedScale;
    }

    public void fitToParentCenterBounds(ScaleAndTranslate scaleAndTranslate) {
        float f;
        View view = (View) this.mView.getParent().getParent();
        if (view != null) {
            int width = view.getWidth();
            int height = view.getHeight();
            int paddingLeft = this.mView.getPaddingLeft();
            int paddingRight = this.mView.getPaddingRight();
            int paddingTop = this.mView.getPaddingTop();
            int i2 = paddingRight + paddingLeft;
            int paddingBottom = this.mView.getPaddingBottom() + paddingTop;
            float min = Math.min(((float) (width - i2)) / ((float) this.mView.mImageProcessor.getWidth(true)), ((float) (height - paddingBottom)) / ((float) this.mView.mImageProcessor.getHeight(true)));
            float width2 = ((float) this.mView.mImageProcessor.getWidth(true)) * min;
            float height2 = ((float) this.mView.mImageProcessor.getHeight(true)) * min;
            float f5 = 0.5f;
            if (i2 > 0) {
                f = ((float) paddingLeft) / ((float) i2);
            } else {
                f = 0.5f;
            }
            if (paddingBottom > 0) {
                f5 = ((float) paddingTop) / ((float) paddingBottom);
            }
            float f8 = ((float) width) - width2;
            float max = Math.max(0.0f, f * f8);
            float f10 = ((float) height) - height2;
            float max2 = Math.max(0.0f, f5 * f10);
            PointF pointF = scaleAndTranslate.vTranslate;
            pointF.x = Math.min(Math.max(pointF.x, f8), max);
            pointF.y = Math.min(Math.max(pointF.y, f10), max2);
            scaleAndTranslate.scale = min;
        }
    }

    public float getDefaultMinScale() {
        int paddingTop = this.mView.getPaddingTop() + this.mView.getPaddingBottom();
        return Math.min(((float) (this.mView.getViewWidth() - (this.mView.getPaddingRight() + this.mView.getPaddingLeft()))) / ((float) this.mView.mImageProcessor.getWidth(true)), ((float) (this.mView.getViewHeight() - paddingTop)) / ((float) this.mView.mImageProcessor.getHeight(true)));
    }

    public RectF getDisplayMinRect() {
        ScaleAndTranslate scaleAndTranslate = new ScaleAndTranslate(1.0f, new PointF(0.0f, 0.0f));
        fitToParentCenterBounds(scaleAndTranslate);
        PointF pointF = scaleAndTranslate.vTranslate;
        float f = pointF.x;
        return new RectF(f, pointF.y, (((float) this.mView.mImageProcessor.getWidth(true)) * scaleAndTranslate.scale) + f, (((float) this.mView.mImageProcessor.getHeight(true)) * scaleAndTranslate.scale) + scaleAndTranslate.vTranslate.y);
    }

    public RectF getDisplayRect() {
        PhotoView photoView = this.mView;
        RectF displayRect = photoView.mPosCtrl.getDisplayRect(photoView.getContext(), this.mView.mImageProcessor.getWidth(true), this.mView.mImageProcessor.getHeight(true));
        if (displayRect != null && ExifUtils.isHorizontalMirror(this.mView.mImageProcessor.getOrientationTag())) {
            float width = displayRect.width();
            float viewWidth = ((float) this.mView.getViewWidth()) - displayRect.left;
            displayRect.right = viewWidth;
            displayRect.left = viewWidth - width;
        }
        return displayRect;
    }

    public int getSampleSize(float f) {
        return Math.min(BitmapOptions.computeInSampleSizeLowerBound(this.mView.mImageProcessor.getWidth(false), this.mView.mImageProcessor.getHeight(false), BitmapSizeHolder.size()), calculateInSampleSize(f));
    }

    public float getScaleForMinSize() {
        PhotoView photoView = this.mView;
        if (photoView.mConfig.mMinSize <= photoView.getViewWidth()) {
            PhotoView photoView2 = this.mView;
            if (photoView2.mConfig.mMinSize <= photoView2.getViewHeight()) {
                float width = (float) this.mView.mImageProcessor.getWidth(true);
                float height = (float) this.mView.mImageProcessor.getHeight(true);
                int i2 = this.mView.mConfig.mMinSize;
                if (i2 > 0) {
                    return Math.max(((float) i2) / width, ((float) i2) / height);
                }
                return 0.0f;
            }
        }
        return this.mView.centerCropScale();
    }

    public void makeRoundCorner(Canvas canvas) {
        try {
            PhotoView photoView = this.mView;
            int i2 = photoView.mConfig.mRoundedCornerRadius;
            if (i2 > 0) {
                RectF displayRect = photoView.getDisplayRect();
                if (displayRect == null) {
                    Log.e(this.TAG, "makeCornerRound fail. rect is null");
                    return;
                }
                Path path = new Path();
                float f = (float) i2;
                path.addRoundRect(displayRect, f, f, Path.Direction.CW);
                canvas.clipPath(path);
            }
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "makeCornerRound failed", (Throwable) e);
        }
    }

    public float minScale() {
        float f;
        PhotoView photoView = this.mView;
        PhotoViewConfig photoViewConfig = photoView.mConfig;
        if (!photoViewConfig.mIsViewInitialCenterCrop || (photoViewConfig.mInitializedBound && !photoViewConfig.mIsKeepCenterCrop)) {
            f = Math.max(getDefaultMinScale(), getScaleForMinSize());
        } else {
            f = photoView.centerCropScale();
        }
        return Math.min(this.mView.getMaxScale(), f);
    }

    public void reset(boolean z) {
        PhotoViewMotionControl photoViewMotionControl = this.mView.mMotionControl;
        if (photoViewMotionControl != null) {
            photoViewMotionControl.reset();
        }
        this.mView.mPosCtrl.reset();
        PhotoViewConfig photoViewConfig = this.mView.mConfig;
        photoViewConfig.mPendingScale = null;
        photoViewConfig.mSPendingCenter = null;
        photoViewConfig.mSatTemp = null;
        photoViewConfig.mMatrix.reset();
        if (z) {
            this.mView.mImageProcessor.close();
            this.mView.mImageProcessor = new ImageProcessor(this.TAG.getTag());
        } else {
            PhotoView photoView = this.mView;
            Objects.requireNonNull(photoView);
            ThreadUtil.runOnUiThread(new c(photoView, 1));
        }
        PhotoView photoView2 = this.mView;
        PhotoViewMotionControl photoViewMotionControl2 = photoView2.mMotionControl;
        if (photoViewMotionControl2 != null) {
            photoViewMotionControl2.setGestureDetector(photoView2.getContext());
        }
    }

    public void resetScaleAndCenterInternal() {
        float f;
        PhotoViewMotionControl photoViewMotionControl = this.mView.mMotionControl;
        if (photoViewMotionControl != null) {
            photoViewMotionControl.setScaleAnimation((ScaleAnimation) null);
        }
        PhotoView photoView = this.mView;
        if (!photoView.mConfig.mBlockPendingScale) {
            OnZoomDetectListener onZoomDetectListener = photoView.mOnZoomDetectListener;
            if (onZoomDetectListener != null) {
                onZoomDetectListener.isZoomDetected(false);
            }
            this.mView.mConfig.mZoomState = false;
        }
        PhotoView photoView2 = this.mView;
        PhotoViewConfig photoViewConfig = photoView2.mConfig;
        if (photoViewConfig.mIsViewInitialCenterCrop) {
            f = photoView2.centerCropScale();
        } else {
            f = photoView2.limitedScale(0.0f);
        }
        photoViewConfig.mPendingScale = Float.valueOf(f);
        if (this.mView.isViewAndBitmapReady()) {
            PhotoView photoView3 = this.mView;
            PhotoViewConfig photoViewConfig2 = photoView3.mConfig;
            photoViewConfig2.mSPendingCenter = photoView3.mImageProcessor.getCenter(photoViewConfig2.mSupportCustomCrop);
        } else {
            this.mView.mConfig.mSPendingCenter = new PointF(0.0f, 0.0f);
        }
        this.mView.invalidate();
    }

    public void setMinimumTileDpi(DisplayMetrics displayMetrics, int i2) {
        this.mView.mConfig.mMinimumTileDpi = (int) Math.min((displayMetrics.xdpi + displayMetrics.ydpi) / 2.0f, (float) i2);
    }

    public void startBlendingAni(int i2) {
        ValueAnimator duration = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f}).setDuration((long) i2);
        duration.addUpdateListener(new o(this));
        duration.addListener(new SimpleAnimatorListener() {
            public void onAnimationCancel(Animator animator) {
                onAnimationEnd(animator);
            }

            public void onAnimationEnd(Animator animator) {
                PhotoViewDelegate photoViewDelegate = PhotoViewDelegate.this;
                ImageProcessor imageProcessor = photoViewDelegate.mView.mImageProcessor;
                imageProcessor.mAlphaBlendingValue = 1.0f;
                imageProcessor.mLastBitmap = null;
                Log.p(photoViewDelegate.TAG, "ppp completed animation END");
            }
        });
        duration.start();
    }

    public String toDebugString() {
        return "{mBitmap=" + Logger.toString(this.mView.mImageProcessor.getBitmap()) + ", mIsPreview=" + this.mView.mImageProcessor.isPreviewMode() + ", mScale=" + this.mView.mPosCtrl.getScale() + ", size=(" + this.mView.getWidth() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mView.getHeight() + "), bound=" + this.mView.getDisplayRect() + ", mMatrix=" + this.mView.mConfig.mMatrix + ", mLastDrawnPixels=" + this.mView.mConfig.mLastDrawnPixels + '}';
    }

    public void updateAttrs(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.PhotoView);
            this.mView.mConfig.mViewBlockMotionControl = obtainStyledAttributes.getBoolean(R$styleable.PhotoView_blockMotion, false);
            this.mView.mConfig.mBlockPinchShrink = obtainStyledAttributes.getBoolean(R$styleable.PhotoView_blockPinchShrink, false);
            obtainStyledAttributes.recycle();
            TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, R$styleable.RemasterViewer);
            this.mView.mConfig.mIsUnlimitedDoubleTapZoom = "unlimited".equals(obtainStyledAttributes2.getString(R$styleable.RemasterViewer_doubleTapZoomType));
            this.mView.mConfig.mMinSize = obtainStyledAttributes2.getDimensionPixelSize(R$styleable.RemasterViewer_minSize, 0);
            obtainStyledAttributes2.recycle();
        }
    }

    public void updateMatrixForBitmap(Bitmap bitmap) {
        float f;
        float f5;
        if (this.mView.mImageProcessor.isPreviewMode()) {
            f5 = (((float) this.mView.mImageProcessor.getWidth(false)) / ((float) bitmap.getWidth())) * this.mView.mPosCtrl.getScale();
            f = (((float) this.mView.mImageProcessor.getHeight(false)) / ((float) bitmap.getHeight())) * this.mView.mPosCtrl.getScale();
        } else {
            f5 = this.mView.mPosCtrl.getScale();
            f = this.mView.mPosCtrl.getScale();
        }
        this.mView.mConfig.mMatrix.reset();
        this.mView.mConfig.mMatrix.postScale(f5, f);
        PhotoView photoView = this.mView;
        photoView.mConfig.mMatrix.postRotate((float) photoView.mImageProcessor.getOrientation());
        PhotoView photoView2 = this.mView;
        photoView2.mConfig.mMatrix.postTranslate(photoView2.mPosCtrl.getX(), this.mView.mPosCtrl.getY());
        if (this.mView.mImageProcessor.is180Degree()) {
            PhotoView photoView3 = this.mView;
            photoView3.mConfig.mMatrix.postTranslate(photoView3.mPosCtrl.getScale() * ((float) this.mView.mImageProcessor.getWidth(false)), this.mView.mPosCtrl.getScale() * ((float) this.mView.mImageProcessor.getHeight(false)));
        } else if (this.mView.mImageProcessor.is90Degree()) {
            PhotoView photoView4 = this.mView;
            photoView4.mConfig.mMatrix.postTranslate(photoView4.mPosCtrl.getScale() * ((float) this.mView.mImageProcessor.getHeight(false)), 0.0f);
        } else if (this.mView.mImageProcessor.is270Degree()) {
            PhotoView photoView5 = this.mView;
            photoView5.mConfig.mMatrix.postTranslate(0.0f, photoView5.mPosCtrl.getScale() * ((float) this.mView.mImageProcessor.getWidth(false)));
        }
    }

    public void updateMatrixForTile(Tile tile) {
        Rect rect;
        this.mView.mConfig.mMatrix.reset();
        float[] fArr = new float[8];
        if (tile.sampleSize != 0) {
            Rect rect2 = tile.borders;
            int i2 = rect2.left;
            int i7 = tile.sampleSize;
            rect = new Rect(i2 / i7, rect2.top / i7, rect2.right / i7, rect2.bottom / i7);
        } else {
            Rect rect3 = tile.borders;
            rect = new Rect(rect3.left, rect3.top, rect3.right, rect3.bottom);
        }
        setMatrixArray(fArr, (float) rect.left, (float) rect.top, (float) (tile.bitmap.getWidth() - rect.right), (float) rect.top, (float) (tile.bitmap.getWidth() - rect.right), (float) (tile.bitmap.getHeight() - rect.bottom), (float) rect.left, (float) (tile.bitmap.getHeight() - rect.bottom));
        float[] fArr2 = new float[8];
        updateDstArrayWithRotation(fArr2, tile, this.mView.mImageProcessor.getOrientation());
        this.mView.mConfig.mMatrix.setPolyToPoly(fArr, 0, fArr2, 0, 4);
    }

    public PointF vTranslateForSCenter(float f, float f5, float f8) {
        PointF viewCenter = ViewUtils.getViewCenter(this.mView);
        PhotoViewConfig photoViewConfig = this.mView.mConfig;
        if (photoViewConfig.mSatTemp == null) {
            photoViewConfig.mSatTemp = new ScaleAndTranslate(0.0f, new PointF(0.0f, 0.0f));
        }
        ScaleAndTranslate scaleAndTranslate = this.mView.mConfig.mSatTemp;
        scaleAndTranslate.scale = f8;
        scaleAndTranslate.vTranslate.set(viewCenter.x - (f * f8), viewCenter.y - (f5 * f8));
        PhotoView photoView = this.mView;
        photoView.fitToBounds(true, photoView.mConfig.mSatTemp);
        return this.mView.mConfig.mSatTemp.vTranslate;
    }

    public void fitToBounds(boolean z) {
        boolean z3;
        if (this.mView.mPosCtrl.getPosition() == null) {
            this.mView.mPosCtrl.init();
            z3 = true;
        } else {
            z3 = false;
        }
        PhotoViewConfig photoViewConfig = this.mView.mConfig;
        if (photoViewConfig.mSatTemp == null) {
            photoViewConfig.mSatTemp = new ScaleAndTranslate(0.0f, new PointF(0.0f, 0.0f));
        }
        PhotoView photoView = this.mView;
        photoView.mConfig.mSatTemp.scale = photoView.mPosCtrl.getScale();
        PhotoView photoView2 = this.mView;
        photoView2.mConfig.mSatTemp.vTranslate.set(photoView2.mPosCtrl.getPosition());
        PhotoView photoView3 = this.mView;
        photoView3.fitToBounds(z, photoView3.mConfig.mSatTemp);
        PhotoView photoView4 = this.mView;
        photoView4.mPosCtrl.setScale(photoView4.mConfig.mSatTemp.scale);
        PhotoView photoView5 = this.mView;
        photoView5.mPosCtrl.setPosition(photoView5.mConfig.mSatTemp.vTranslate);
        if (z3) {
            PhotoView photoView6 = this.mView;
            PointF initialPosition = photoView6.mImageProcessor.getInitialPosition(photoView6.mConfig.mSupportCustomCrop);
            PhotoView photoView7 = this.mView;
            PhotoViewPositionControl photoViewPositionControl = photoView7.mPosCtrl;
            photoViewPositionControl.setPosition(photoView7.vTranslateForSCenter(initialPosition.x, initialPosition.y, photoViewPositionControl.getScale()));
        }
        this.mView.mConfig.mInitializedBound = true;
    }
}
