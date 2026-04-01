package com.samsung.android.gallery.widget.listview.pinch.v3;

import Fa.C0571z;
import H3.l;
import J.d;
import Jb.a;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.view.View;
import android.widget.ImageView;
import com.samsung.android.gallery.module.abstraction.GridValue;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.PinchImageView;
import com.samsung.android.gallery.widget.PinchMatrix;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.animator.ScaleAnimator;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.GridInfo;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DataItem extends PinchItem {
    private Integer mBaseGrid;
    private final BitmapCache mBitmapCache;
    private int mDensityDpi;
    private boolean mIsAttached;
    private ListViewHolder mMatrixMakeHolder;

    public DataItem(BitmapCache bitmapCache) {
        this.mBitmapCache = bitmapCache;
    }

    private PinchImageView.BackgroundDrawingMode calculateDrawableBackgroundMode(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem == null || !mediaItem.isTransparent()) {
            if (mediaItem2 == null || !mediaItem2.isTransparent()) {
                return PinchImageView.BackgroundDrawingMode.NEVER;
            }
            if (mediaItem == null || !mediaItem.isTransparent()) {
                return PinchImageView.BackgroundDrawingMode.FADE_IN;
            }
            return PinchImageView.BackgroundDrawingMode.NEVER;
        } else if (mediaItem2 == null || !mediaItem2.isTransparent()) {
            return PinchImageView.BackgroundDrawingMode.FADE_OUT;
        } else {
            return PinchImageView.BackgroundDrawingMode.ALWAYS;
        }
    }

    private PinchMatrix[] createMatrix(boolean z) {
        PinchMatrix pinchMatrix;
        PinchMatrix pinchMatrix2 = new PinchMatrix();
        if (z) {
            pinchMatrix = new PinchMatrix();
        } else {
            pinchMatrix = pinchMatrix2;
        }
        return new PinchMatrix[]{pinchMatrix2, pinchMatrix};
    }

    private RectF getBaseRect() {
        return getRect(this.mBaseGrid.intValue());
    }

    private ArrayList<PropertyAnimator> getMatrixAnimator(GridInfo gridInfo) {
        PinchImageView pinchImageView = (PinchImageView) this.mListViewHolder.getImage();
        ArrayList<PropertyAnimator> arrayList = new ArrayList<>();
        if (gridInfo.withRealRatio()) {
            PinchMatrix[] fromImageMatrix = pinchImageView.getFromImageMatrix();
            if (fromImageMatrix != null) {
                arrayList.add(new PinchMatrixAnimator(pinchImageView, fromImageMatrix[0], fromImageMatrix[1]));
            }
            PinchMatrix[] toImageMatrix = pinchImageView.getToImageMatrix();
            if (toImageMatrix != null) {
                arrayList.add(new PinchMatrixAnimator(pinchImageView, toImageMatrix[0], toImageMatrix[1]));
            }
        }
        return arrayList;
    }

    private PropertyAnimator getPinchCrossFadeAnimator(GridInfo gridInfo, PinchLayoutManager pinchLayoutManager) {
        PinchImageView pinchImageView = (PinchImageView) this.mListViewHolder.getImage();
        pinchImageView.setMultiMode(true);
        PinchCrossFadeAnimator pinchCrossFadeAnimator = new PinchCrossFadeAnimator(pinchImageView);
        requestBitmap(gridInfo, pinchImageView, pinchLayoutManager);
        return pinchCrossFadeAnimator;
    }

    private PropertyAnimator getScaleAnimator(GridInfo gridInfo) {
        float width;
        float height;
        prepareScaleAnimation(gridInfo);
        boolean withRealRatio = gridInfo.withRealRatio();
        RectF rect = getRect(gridInfo.from());
        RectF rect2 = getRect(gridInfo.to());
        if (rect == null || rect2 == null) {
            return null;
        }
        boolean isFromRealRatio = gridInfo.isFromRealRatio();
        if (isFromRealRatio) {
            width = rect2.width();
        } else {
            width = rect.width();
        }
        int i2 = (int) width;
        if (isFromRealRatio) {
            height = rect2.height();
        } else {
            height = rect.height();
        }
        int i7 = (int) height;
        ScaleAnimator scaleAnimator = new ScaleAnimator(getScalableView(), getBaseRect(), rect, rect2);
        scaleAnimator.enableUpdateLayoutParam(withRealRatio).setAnimationListener(new d(withRealRatio, scaleAnimator, i2, i7));
        return scaleAnimator;
    }

    private int getSpanWidth(PinchLayoutManager pinchLayoutManager, int i2) {
        return Math.max(1, (int) (((float) pinchLayoutManager.getHintWidthSpace(i2)) / ((float) pinchLayoutManager.getHintSpanCount(i2))));
    }

    private ThumbKind getThumbKind(PinchLayoutManager pinchLayoutManager, GridInfo gridInfo, boolean z) {
        return getThumbKind(pinchLayoutManager, z ? gridInfo.from() : gridInfo.to());
    }

    private void handleWrongItem(Runnable runnable) {
        Object obj;
        StringBuilder sb2 = new StringBuilder("Hide item.[");
        Integer num = this.mBaseGrid;
        if (num != null) {
            obj = getRect(num.intValue());
        } else {
            obj = "null";
        }
        sb2.append(obj);
        sb2.append("][");
        sb2.append(this.mListViewHolder.getImage());
        sb2.append("]");
        Log.w("DataItem", sb2.toString());
        setVisibility(false);
    }

    private void invalidateMatrix(GridInfo gridInfo, PinchMatrix[] pinchMatrixArr, MediaItem mediaItem, Bitmap bitmap) {
        int width;
        float height;
        RectF rect;
        RectF rect2 = getRect(gridInfo.from());
        if (!gridInfo.withRealRatio() || rect2 == null) {
            RectF baseRect = getBaseRect();
            width = (int) baseRect.width();
            height = baseRect.height();
        } else {
            width = (int) rect2.width();
            height = rect2.height();
        }
        updateMatrix(pinchMatrixArr[0], mediaItem, bitmap, width, (int) height);
        if (gridInfo.withRealRatio() && (rect = getRect(gridInfo.to())) != null) {
            PinchMatrix pinchMatrix = pinchMatrixArr[1];
            int width2 = (int) rect.width();
            int height2 = (int) rect.height();
            updateMatrix(pinchMatrix, mediaItem, bitmap, width2, height2);
        }
    }

    private boolean isFromOutside(GridInfo gridInfo) {
        if (isShowing(gridInfo.from()) || !isShowing(gridInfo.to())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getScaleAnimator$3(boolean z, ScaleAnimator scaleAnimator, int i2, int i7, View view) {
        if (z) {
            scaleAnimator.forceProgressDone();
            ViewUtils.setViewSize(view, Integer.valueOf(i2), Integer.valueOf(i7));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$requestBitmap$1(PinchImageView pinchImageView, MediaItem mediaItem, GridInfo gridInfo, PinchMatrix[] pinchMatrixArr, Bitmap bitmap) {
        if (bitmap != null) {
            bitmap.setDensity(this.mDensityDpi);
        }
        pinchImageView.setFromBitmap(mediaItem.getThumbCacheKey(), bitmap);
        invalidateMatrix(gridInfo, pinchMatrixArr, mediaItem, bitmap);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$requestBitmap$2(PinchImageView pinchImageView, MediaItem mediaItem, GridInfo gridInfo, PinchMatrix[] pinchMatrixArr, Bitmap bitmap) {
        if (bitmap != null) {
            bitmap.setDensity(this.mDensityDpi);
        }
        pinchImageView.setToBitmap(mediaItem.getThumbCacheKey(), bitmap);
        invalidateMatrix(gridInfo, pinchMatrixArr, mediaItem, bitmap);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateAlphaToPreventFlickering$0() {
        this.mListViewHolder.getRootView().setAlpha(1.0f);
    }

    private void prepareScaleAnimation(GridInfo gridInfo) {
        if (gridInfo.isToRealRatio()) {
            resetScale(getScalableView());
            this.mBaseGrid = Integer.valueOf(gridInfo.from());
        }
    }

    private void requestBitmap(GridInfo gridInfo, PinchImageView pinchImageView, PinchLayoutManager pinchLayoutManager) {
        MediaItem mediaItem;
        PinchLayoutManager pinchLayoutManager2 = pinchLayoutManager;
        boolean withRealRatio = gridInfo.withRealRatio();
        MediaItem mediaItem2 = getMediaItem(gridInfo.from(), pinchLayoutManager2);
        if (mediaItem2 != null) {
            PinchMatrix[] createMatrix = createMatrix(withRealRatio);
            pinchImageView.setFromImageMatrix(createMatrix);
            mediaItem = mediaItem2;
            this.mBitmapCache.requestBitmap(mediaItem, getThumbKind(pinchLayoutManager2, gridInfo, true), new a(this, pinchImageView, mediaItem2, gridInfo, createMatrix, 0), false);
        } else {
            mediaItem = mediaItem2;
        }
        MediaItem mediaItem3 = getMediaItem(gridInfo.to(), pinchLayoutManager2);
        if (mediaItem3 != null) {
            PinchMatrix[] createMatrix2 = createMatrix(withRealRatio);
            pinchImageView.setToImageMatrix(createMatrix2);
            this.mBitmapCache.requestBitmap(mediaItem3, getThumbKind(pinchLayoutManager2, gridInfo, false), new a(this, pinchImageView, mediaItem3, gridInfo, createMatrix2, 1), gridInfo.isFromRealRatio());
        }
        pinchImageView.enableDrawBackground(calculateDrawableBackgroundMode(mediaItem, mediaItem3));
    }

    private void resetScalableView(View view) {
        ViewUtils.setViewSize(view, -1, -1);
        resetScale(view);
        resetAlpha(view);
    }

    private void updateAlphaToPreventFlickering(GridInfo gridInfo, boolean z) {
        boolean z3;
        if (z || this.mIsAttached) {
            z3 = true;
        } else {
            z3 = false;
        }
        this.mIsAttached = z3;
        if (!z3 && isFromOutside(gridInfo)) {
            this.mListViewHolder.getRootView().setAlpha(0.0f);
            this.mListViewHolder.getRootView().post(new l(16, this));
            this.mIsAttached = true;
        }
    }

    private void updateMatrix(PinchMatrix pinchMatrix, MediaItem mediaItem, Bitmap bitmap, int i2, int i7) {
        this.mMatrixMakeHolder.setTempMode();
        this.mMatrixMakeHolder.bind(mediaItem);
        this.mMatrixMakeHolder.bindImage(bitmap);
        this.mMatrixMakeHolder.getImage().layout(0, 0, i2, i7);
        this.mMatrixMakeHolder.updateMatrix();
        pinchMatrix.set(this.mMatrixMakeHolder.getImage().getImageMatrix());
        pinchMatrix.setReady(true);
    }

    public ArrayList<PropertyAnimator> getAnimators(GridInfo gridInfo, PinchLayoutManager pinchLayoutManager, int i2, boolean z, Runnable runnable) {
        ArrayList<PropertyAnimator> arrayList = new ArrayList<>();
        if (getBaseGrid() == null || getBaseRect() == null || this.mListViewHolder.getImage() == null) {
            handleWrongItem(runnable);
            return arrayList;
        }
        arrayList.add(getTranslateAnimator(gridInfo, i2));
        arrayList.add(getPinchCrossFadeAnimator(gridInfo, pinchLayoutManager));
        arrayList.add(getScaleAnimator(gridInfo));
        arrayList.addAll(getMatrixAnimator(gridInfo));
        arrayList.removeIf(new C0571z(21));
        updateAlphaToPreventFlickering(gridInfo, z);
        return arrayList;
    }

    public Integer getBaseGrid() {
        return this.mBaseGrid;
    }

    public boolean isData() {
        return true;
    }

    public void onAnimationCompleted() {
        super.onAnimationCompleted();
        resetScalableView(getScalableView());
        ImageView image = this.mListViewHolder.getImage();
        if (image instanceof PinchImageView) {
            ((PinchImageView) image).setMultiMode(false);
        }
    }

    public void setMatrixMakeHolder(ListViewHolder listViewHolder) {
        this.mMatrixMakeHolder = listViewHolder;
    }

    public void setViewHolder(ListViewHolder listViewHolder) {
        super.setViewHolder(listViewHolder);
        this.mDensityDpi = listViewHolder.getRootView().getContext().getResources().getDisplayMetrics().densityDpi;
    }

    public void updateBaseRectGrid(int i2) {
        if (i2 > 1 && this.mBaseGrid == null) {
            this.mBaseGrid = Integer.valueOf(i2);
        }
    }

    public ThumbKind getThumbKind(PinchLayoutManager pinchLayoutManager, int i2) {
        if (GridValue.revert(i2) == 1) {
            return ThumbKind.FREE_KIND;
        }
        return ThumbKind.getOptimalThumbKind(this.mListViewHolder.getRootView().getContext(), getSpanWidth(pinchLayoutManager, i2));
    }

    public DataItem(BitmapCache bitmapCache, int i2) {
        this.mBitmapCache = bitmapCache;
        this.mBaseGrid = Integer.valueOf(i2);
    }
}
