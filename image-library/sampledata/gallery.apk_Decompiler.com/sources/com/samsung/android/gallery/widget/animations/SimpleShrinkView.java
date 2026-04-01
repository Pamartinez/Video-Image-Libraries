package com.samsung.android.gallery.widget.animations;

import Bb.g;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import c0.C0086a;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.ImageMatrix;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ObjectDictionary;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.R$color;
import com.samsung.android.gallery.widget.abstraction.TransitionInfo;
import com.samsung.android.gallery.widget.animator.AlphaAnimator;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.samsung.android.ocr.MOCRLang;
import e5.C0451a;
import eb.C0689c;
import eb.d;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SimpleShrinkView {
    protected final String TAG;
    boolean clearBitmapRefCount;
    float fromHeight;
    float fromWidth;
    final Activity mActivity;
    Bitmap mBitmap;
    protected final Blackboard mBlackboard;
    private Runnable mErrorListener;
    protected final View.OnLayoutChangeListener mLayoutChangeListener = new g(9, this);
    boolean mMasking = false;
    float mMaskingRate = 0.1f;
    MediaItem mMediaItem;
    private Runnable mReadyListener;
    private int mRetryCount;
    ImageView mShrinkBackgroundView;
    ImageView mShrinkView;
    private final int mTopMargin;
    View mTopView;
    TransitionInfo mTransitionInfo;
    public double scaleRatio = 1.0d;

    public SimpleShrinkView(Blackboard blackboard) {
        long j2;
        String str;
        String str2;
        String simpleName = getClass().getSimpleName();
        this.TAG = simpleName;
        this.mBlackboard = blackboard;
        Activity readActivity = BlackboardUtils.readActivity(blackboard);
        this.mActivity = readActivity;
        int multiWindowToolbarHeight = WindowUtils.getMultiWindowToolbarHeight(readActivity);
        this.mTopMargin = multiWindowToolbarHeight;
        TransitionInfo transitionInfo = (TransitionInfo) blackboard.pop("data://shared_original_bitmap", null);
        transitionInfo = transitionInfo == null ? new TransitionInfo() : transitionInfo;
        this.mTransitionInfo = transitionInfo;
        this.mMediaItem = transitionInfo.item;
        Bitmap bitmap = transitionInfo.bitmap;
        this.mBitmap = bitmap;
        if (bitmap == null || ObjectDictionary.getRefCounter(bitmap) <= 0) {
            Bitmap bitmap2 = this.mBitmap;
            if (bitmap2 == null || bitmap2.isRecycled()) {
                transitionInfo.bitmap = null;
                Log.e(simpleName, "bitmap is recycled..");
            } else {
                Bitmap bitmap3 = transitionInfo.bitmap;
                transitionInfo.bitmap = bitmap3.copy(bitmap3.getConfig(), true);
                Log.e(simpleName, "bitmap will be recycled..");
            }
            this.mBitmap = transitionInfo.bitmap;
        } else {
            ObjectDictionary.increaseRefCounter(this.mBitmap);
            this.clearBitmapRefCount = true;
        }
        StringBuilder sb2 = new StringBuilder("construct");
        MediaItem mediaItem = transitionInfo.item;
        if (mediaItem != null) {
            j2 = mediaItem.getFileId();
        } else {
            j2 = 0;
        }
        Long valueOf = Long.valueOf(j2);
        String simpleString = Logger.toSimpleString(transitionInfo.bitmap);
        RectF rectF = transitionInfo.displayRect;
        if (transitionInfo.top != null) {
            str = "T";
        } else {
            str = "t";
        }
        if (transitionInfo.bottom != null) {
            str2 = "B";
        } else {
            str2 = "b";
        }
        String concat = str.concat(str2);
        String i2 = C0086a.i(multiWindowToolbarHeight, "top=");
        sb2.append(Logger.v(valueOf, simpleString, rectF, concat, i2, "padding=" + transitionInfo.paddingStart));
        Log.st(simpleName, sb2.toString());
    }

    private Rect getCropRect(RectF rectF, ImageView imageView, int i2) {
        if (RectUtils.isValidRect(rectF)) {
            return RectUtils.getSmartCropRect(imageView.getDrawable(), rectF, i2, false);
        }
        return null;
    }

    private void initViewMatrix(MediaItem mediaItem, ImageView imageView) {
        if (usePresetMatrix()) {
            imageView.setImageMatrix(new Matrix(this.mTransitionInfo.presetMatrix));
        } else {
            setViewMatrix(mediaItem, imageView);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$prepareTopView$0(int i2, Bitmap bitmap, Bitmap bitmap2, int i7, int i8, ImageView imageView, ViewGroup.MarginLayoutParams marginLayoutParams) {
        marginLayoutParams.topMargin = (((i2 - bitmap.getHeight()) - bitmap2.getHeight()) - i7) - i8;
        imageView.setLayoutParams(marginLayoutParams);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$recycle$1(ImageView imageView) {
        ViewUtils.removeSelf(imageView);
        clearBitmapReference();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$recycle$2(ImageView imageView) {
        new AlphaAnimator((View) imageView, 1.0f, 0.0f).setDuration(120).withEndAction(new C0689c(this, imageView, 1)).start();
    }

    private void onError() {
        String str = this.TAG;
        Log.ste(str, "onError {" + this.mRetryCount + "}, " + ViewUtils.getViewRect(this.mShrinkView));
        ViewUtils.removeSelf(this.mShrinkView);
        this.mShrinkView = null;
        removeExtraShrinkViews();
        clearShrinkData();
        clearBitmapReference();
        Runnable runnable = this.mErrorListener;
        if (runnable != null) {
            runnable.run();
        }
    }

    /* access modifiers changed from: private */
    public void onLayoutChange(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
        if ((i8 - i2 == i13 - i11 && i10 - i7 == i14 - i12) || usePresetMatrix()) {
            return;
        }
        if (this.mMasking) {
            setViewMatrixMasking(this.mMediaItem, (ImageView) view);
        } else {
            setViewMatrix(this.mMediaItem, (ImageView) view);
        }
    }

    /* access modifiers changed from: private */
    public void onViewReady() {
        ImageView imageView = this.mShrinkView;
        if (imageView != null) {
            int i2 = this.mRetryCount + 1;
            this.mRetryCount = i2;
            if (i2 <= 10) {
                if (imageView.getWidth() == 0 || this.mShrinkView.getHeight() == 0) {
                    String str = this.TAG;
                    Log.ste(str, "onViewReady failed. " + ViewUtils.getViewRect(this.mShrinkView));
                    this.mShrinkView.post(new C0451a(8, this));
                    return;
                }
                String str2 = this.TAG;
                Log.st(str2, "onViewReady " + ViewUtils.getViewRect(this.mShrinkView));
                initViewMatrix(this.mMediaItem, this.mShrinkView);
                this.mShrinkView.setVisibility(0);
                this.mShrinkView.addOnLayoutChangeListener(this.mLayoutChangeListener);
                ViewUtils.setVisibleOrGone(this.mShrinkBackgroundView, true);
                ViewUtils.setVisibleOrGone(this.mTopView, true);
                Runnable runnable = this.mReadyListener;
                if (runnable != null) {
                    runnable.run();
                }
                this.fromWidth = (float) this.mShrinkView.getWidth();
                this.fromHeight = (float) this.mShrinkView.getHeight();
                return;
            }
        }
        Log.ste(this.TAG, "onViewReady failed. null view or max retry");
        onError();
    }

    private void prepareBackgroundView(ViewGroup viewGroup, boolean z) {
        int i2;
        ImageView createShrinkBackgroundImageView = createShrinkBackgroundImageView(viewGroup.getContext());
        this.mShrinkBackgroundView = createShrinkBackgroundImageView;
        createShrinkBackgroundImageView.setVisibility(4);
        this.mShrinkBackgroundView.setY((float) this.mTopMargin);
        Bitmap bitmap = this.mTransitionInfo.background;
        if (bitmap != null) {
            this.mShrinkBackgroundView.setImageBitmap(bitmap);
        } else {
            ImageView imageView = this.mShrinkBackgroundView;
            if (z) {
                i2 = R$color.black_color;
            } else {
                i2 = R$color.daynight_default_background;
            }
            imageView.setBackgroundResource(i2);
        }
        viewGroup.addView(this.mShrinkBackgroundView, new ViewGroup.LayoutParams(viewGroup.getWidth(), viewGroup.getHeight() - this.mTopMargin));
    }

    private void prepareShrinkView(ViewGroup viewGroup) {
        float f;
        TransitionInfo transitionInfo = this.mTransitionInfo;
        Bitmap bitmap = transitionInfo.bitmap;
        RectF rectF = transitionInfo.displayRect;
        if (rectF == null || rectF.width() == 0.0f || rectF.height() == 0.0f || bitmap == null) {
            Log.ste(this.TAG, "prepareShrinkView failed. wrong display-rect " + rectF);
            onError();
            return;
        }
        float f5 = rectF.top;
        float f8 = (float) this.mTopMargin;
        rectF.top = f5 + f8;
        rectF.bottom += f8;
        ImageView createShrinkImageView = createShrinkImageView(viewGroup.getContext());
        this.mShrinkView = createShrinkImageView;
        createShrinkImageView.setLayoutParams(new ViewGroup.LayoutParams((int) rectF.width(), (int) rectF.height()));
        if (Features.isEnabled(Features.IS_RTL)) {
            f = -rectF.left;
        } else {
            f = rectF.left;
        }
        createShrinkImageView.setX(f);
        createShrinkImageView.setY(rectF.top);
        createShrinkImageView.setVisibility(4);
        viewGroup.addView(createShrinkImageView, new ViewGroup.LayoutParams((int) rectF.width(), (int) rectF.height()));
        createShrinkImageView.setImageBitmap(bitmap);
        createShrinkImageView.setScaleType(ImageView.ScaleType.MATRIX);
        createShrinkImageView.post(new C0451a(8, this));
    }

    private void prepareTopView(ViewGroup viewGroup, boolean z) {
        TransitionInfo transitionInfo = this.mTransitionInfo;
        Bitmap bitmap = transitionInfo.top;
        Bitmap bitmap2 = transitionInfo.bottom;
        if (bitmap != null && bitmap2 != null) {
            int height = viewGroup.getHeight() - this.mTopMargin;
            LinearLayout linearLayout = new LinearLayout(viewGroup.getContext());
            linearLayout.setVisibility(4);
            linearLayout.setY((float) this.mTopMargin);
            linearLayout.setOrientation(1);
            int i2 = this.mTransitionInfo.paddingStart;
            if (i2 > 0) {
                linearLayout.setPaddingRelative(i2, 0, 0, 0);
            }
            viewGroup.addView(linearLayout, new ViewGroup.LayoutParams(viewGroup.getWidth(), height));
            this.mTopView = linearLayout;
            int statusBarHeightIfExists = SystemUi.getStatusBarHeightIfExists(this.mActivity);
            if (statusBarHeightIfExists > 0) {
                LinearLayout linearLayout2 = new LinearLayout(viewGroup.getContext());
                linearLayout2.setBackgroundColor(getWindow().getStatusBarColor());
                linearLayout.addView(linearLayout2, new ViewGroup.LayoutParams(viewGroup.getWidth(), statusBarHeightIfExists));
            }
            ImageView imageView = new ImageView(viewGroup.getContext());
            imageView.setImageBitmap(bitmap);
            linearLayout.addView(imageView, new ViewGroup.LayoutParams(bitmap.getWidth(), bitmap.getHeight()));
            ImageView imageView2 = new ImageView(viewGroup.getContext());
            imageView2.setImageBitmap(bitmap2);
            linearLayout.addView(imageView2, new ViewGroup.LayoutParams(bitmap.getWidth(), bitmap2.getHeight()));
            int navigationBarHeight = SystemUi.getNavigationBarHeight(this.mActivity.getWindow().getDecorView().getRootWindowInsets());
            if (navigationBarHeight > 0) {
                LinearLayout linearLayout3 = new LinearLayout(viewGroup.getContext());
                linearLayout3.setBackgroundColor(getWindow().getNavigationBarColor());
                linearLayout.addView(linearLayout3, new ViewGroup.LayoutParams(viewGroup.getWidth(), navigationBarHeight));
            }
            Optional.ofNullable((ViewGroup.MarginLayoutParams) imageView2.getLayoutParams()).ifPresent(new d(height, bitmap, bitmap2, navigationBarHeight, statusBarHeightIfExists, imageView2));
        }
    }

    private void setViewMatrixMasking(MediaItem mediaItem, ImageView imageView) {
        int i2;
        Rect rect;
        if (mediaItem != null && imageView.getScaleType() == ImageView.ScaleType.MATRIX) {
            if (mediaItem.isVideo() || mediaItem.isBroken() || mediaItem.isFolder()) {
                i2 = 0;
            } else {
                i2 = mediaItem.getOrientation();
            }
            Drawable drawable = imageView.getDrawable();
            double d = 1.0d - (this.scaleRatio * ((double) this.mMaskingRate));
            if (i2 % MOCRLang.KHMER == 0) {
                int intrinsicWidth = ((int) (((double) drawable.getIntrinsicWidth()) - (((double) drawable.getIntrinsicWidth()) * d))) / 2;
                int intrinsicHeight = ((int) (((double) drawable.getIntrinsicHeight()) - (((double) drawable.getIntrinsicHeight()) * d))) / 2;
                rect = new Rect(intrinsicWidth, intrinsicHeight, drawable.getIntrinsicWidth() - intrinsicWidth, drawable.getIntrinsicHeight() - intrinsicHeight);
            } else {
                int intrinsicHeight2 = ((int) (((double) drawable.getIntrinsicHeight()) - (((double) drawable.getIntrinsicHeight()) * d))) / 2;
                int intrinsicWidth2 = ((int) (((double) drawable.getIntrinsicWidth()) - (((double) drawable.getIntrinsicWidth()) * d))) / 2;
                rect = new Rect(intrinsicHeight2, intrinsicWidth2, drawable.getIntrinsicHeight() - intrinsicHeight2, drawable.getIntrinsicWidth() - intrinsicWidth2);
            }
            imageView.setImageMatrix(ImageMatrix.create(ImageMatrix.MatrixParam.create(imageView, false).withCropRect(rect).withOrientation(i2).withOrientationTag(mediaItem.getOrientationTag())));
        }
    }

    public void cancel() {
        ViewUtils.setVisibility(this.mShrinkView, 4);
        ViewUtils.setVisibility(this.mShrinkBackgroundView, 4);
    }

    public void clearBitmapReference() {
        Bitmap bitmap;
        if (this.clearBitmapRefCount && (bitmap = this.mBitmap) != null) {
            this.mBitmap = null;
            this.clearBitmapRefCount = false;
            ThreadUtil.postOnUiThread(new C0451a(9, bitmap));
        }
    }

    public void clearShrinkData() {
        this.mBlackboard.erase("data://shrink_active");
    }

    public ImageView createShrinkBackgroundImageView(Context context) {
        ImageView imageView = new ImageView(context);
        imageView.setClickable(true);
        return imageView;
    }

    public ImageView createShrinkImageView(Context context) {
        ImageView imageView = new ImageView(context);
        imageView.setClickable(true);
        return imageView;
    }

    public void disableDragMasking() {
        this.mMasking = false;
        this.fromWidth = (float) this.mShrinkView.getWidth();
        this.fromHeight = (float) this.mShrinkView.getHeight();
    }

    public void enableDragMasking() {
        this.mMasking = true;
    }

    public View getDecorView() {
        return this.mActivity.getWindow().getDecorView();
    }

    public Window getWindow() {
        return this.mActivity.getWindow();
    }

    public void recycle() {
        ImageView imageView = this.mShrinkView;
        if (imageView != null) {
            this.mShrinkView = null;
            imageView.removeOnLayoutChangeListener(this.mLayoutChangeListener);
            imageView.post(new C0689c(this, imageView, 0));
        }
        removeExtraShrinkViews();
    }

    public void removeExtraShrinkViews() {
        ViewUtils.removeSelf(this.mShrinkBackgroundView);
        this.mShrinkBackgroundView = null;
        ViewUtils.removeSelf(this.mTopView);
        this.mTopView = null;
    }

    public void setViewMatrix(MediaItem mediaItem, ImageView imageView) {
        int i2;
        if (mediaItem != null && imageView.getScaleType() == ImageView.ScaleType.MATRIX) {
            if (mediaItem.isVideo() || mediaItem.isBroken() || mediaItem.isFolder()) {
                i2 = 0;
            } else {
                i2 = mediaItem.getOrientation();
            }
            imageView.setImageMatrix(ImageMatrix.create(ImageMatrix.MatrixParam.create(imageView, true).withCropRect(getCropRect(mediaItem.getCropRectRatio(), imageView, i2)).withOrientation(i2).withOrientationTag(mediaItem.getOrientationTag())));
        }
    }

    public SimpleShrinkView show(boolean z) {
        ViewGroup viewGroup = (ViewGroup) getDecorView();
        prepareBackgroundView(viewGroup, z);
        prepareShrinkView(viewGroup);
        prepareTopView(viewGroup, z);
        return this;
    }

    public boolean usePresetMatrix() {
        if (this.mTransitionInfo.presetMatrix != null) {
            return true;
        }
        return false;
    }

    public SimpleShrinkView withErrorAction(Runnable runnable) {
        this.mErrorListener = runnable;
        return this;
    }

    public SimpleShrinkView withReadyAction(Runnable runnable) {
        this.mReadyListener = runnable;
        return this;
    }
}
