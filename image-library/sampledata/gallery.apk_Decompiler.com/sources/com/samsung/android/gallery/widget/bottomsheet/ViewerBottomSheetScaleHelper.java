package com.samsung.android.gallery.widget.bottomsheet;

import android.util.Size;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.photoview.PhotoView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import i.C0212a;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewerBottomSheetScaleHelper {
    private final Supplier<Size> mBaseViewSize;
    private final Supplier<Size> mBitmapSize;
    private final Supplier<Size> mTargetViewSize;

    public ViewerBottomSheetScaleHelper(Supplier<Size> supplier, Supplier<Size> supplier2, Supplier<Size> supplier3) {
        this.mBaseViewSize = supplier;
        this.mTargetViewSize = supplier2;
        this.mBitmapSize = supplier3;
    }

    private float getMatrixFromScale() {
        return Math.min(5.0f, Math.min(((float) this.mBaseViewSize.get().getWidth()) / ((float) this.mBitmapSize.get().getWidth()), ((float) this.mBaseViewSize.get().getHeight()) / ((float) this.mBitmapSize.get().getHeight())));
    }

    private float getMatrixTargetScale(float f) {
        return getScaleInternal(getMatrixFromScale(), getMatrixToScale(), f);
    }

    private float getMatrixToScale() {
        return Math.max(((float) this.mTargetViewSize.get().getWidth()) / ((float) this.mBitmapSize.get().getWidth()), ((float) this.mTargetViewSize.get().getHeight()) / ((float) this.mBitmapSize.get().getHeight()));
    }

    private float getReducedScaleIfVideoViewBiggerThanTarget(View view, View view2, float f) {
        boolean z;
        int min;
        int height;
        int floor = (int) Math.floor((double) (((float) view.getWidth()) * f));
        int floor2 = (int) Math.floor((double) (((float) view.getHeight()) * f));
        if (floor <= this.mTargetViewSize.get().getWidth() || floor2 <= this.mTargetViewSize.get().getHeight()) {
            return f;
        }
        if (((float) floor) / ((float) this.mTargetViewSize.get().getWidth()) < ((float) floor2) / ((float) this.mTargetViewSize.get().getHeight())) {
            z = true;
        } else {
            z = false;
        }
        float width = ((float) this.mBitmapSize.get().getWidth()) / ((float) this.mBitmapSize.get().getHeight());
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view2.getLayoutParams();
        View rootView = view2.getRootView();
        if (z) {
            min = Math.round(((float) Math.min(view.getHeight(), (rootView.getHeight() - marginLayoutParams.bottomMargin) - marginLayoutParams.topMargin)) * width);
            height = view2.getWidth();
        } else {
            min = (int) (((float) Math.min(view.getWidth(), (rootView.getWidth() - marginLayoutParams.leftMargin) - marginLayoutParams.rightMargin)) / width);
            height = view2.getHeight();
        }
        return ((float) height) / ((float) min);
    }

    private float getScaleInternal(float f, float f5, float f8) {
        return C0212a.a(f5, f, f8, f);
    }

    private float getViewFromScale() {
        return 1.0f;
    }

    private float getViewTargetScale(float f) {
        return getScaleInternal(getViewFromScale(), getViewToScale(), f);
    }

    private float getViewToScale() {
        float width = (((float) this.mBitmapSize.get().getWidth()) * ((float) this.mTargetViewSize.get().getHeight())) / ((float) (this.mTargetViewSize.get().getWidth() * this.mBitmapSize.get().getHeight()));
        return Math.max(width, 1.0f / width);
    }

    private boolean isBlockRegionDecoding(int i2, int i7) {
        if (i7 <= 0 || i2 != i7) {
            return false;
        }
        return true;
    }

    private boolean isScalable() {
        if (getMatrixFromScale() == getMatrixToScale() && getViewFromScale() == getViewToScale()) {
            return false;
        }
        return true;
    }

    public void setPhotoScale(PhotoView photoView, float f, int i2) {
        boolean z;
        boolean z3;
        boolean z7 = false;
        if (0.0f >= f || f > 1.0f || !isScalable()) {
            z = false;
        } else {
            z = true;
        }
        float matrixTargetScale = getMatrixTargetScale(f);
        if (!Float.isNaN(matrixTargetScale) && !Float.isInfinite(matrixTargetScale)) {
            int sampleSize = photoView.getSampleSize(getMatrixTargetScale(0.0f));
            int sampleSize2 = photoView.getSampleSize(getMatrixTargetScale(1.0f));
            if (!z || !isBlockRegionDecoding(sampleSize, sampleSize2)) {
                z3 = false;
            } else {
                z3 = true;
            }
            photoView.blockRegionDecoding(z3);
            photoView.blockPendingScale(z);
            photoView.setMaxScale(Math.max((float) i2, matrixTargetScale));
            if (f > 0.0f) {
                z7 = true;
            }
            photoView.setSupportCustomCropRect(z7);
            if (f == 1.0f) {
                photoView.forceResetScaleAndCenter();
            }
            photoView.setScale(matrixTargetScale);
            photoView.invalidate();
        }
    }

    public void setVideoScale(IMediaPlayerView iMediaPlayerView, View view, float f) {
        if (iMediaPlayerView != null) {
            float viewTargetScale = getViewTargetScale(f);
            if (f == 0.0f) {
                iMediaPlayerView.setDefaultPosition(false);
            }
            if (f < 1.0f) {
                viewTargetScale = getReducedScaleIfVideoViewBiggerThanTarget(iMediaPlayerView.getView(), view, viewTargetScale);
            }
            if (Float.isNaN(viewTargetScale) || Float.isInfinite(viewTargetScale)) {
                Log.e((CharSequence) "ViewerBottomSheetScaleHelper", "setVideoScale invalid", Float.valueOf(viewTargetScale));
            } else {
                ViewUtils.setScale(iMediaPlayerView.getView(), viewTargetScale, viewTargetScale);
            }
        }
    }
}
