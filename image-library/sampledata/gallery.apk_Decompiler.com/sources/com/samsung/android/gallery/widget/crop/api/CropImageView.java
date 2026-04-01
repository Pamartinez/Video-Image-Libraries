package com.samsung.android.gallery.widget.crop.api;

import android.view.MotionEvent;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface CropImageView {
    float getScaleRatio();

    boolean isZoomed();

    boolean onTouch(MotionEvent motionEvent);

    void setIsZooming(boolean z);

    void setOnCropImageMatrixChangedListener(OnCropImageMatrixChangedListener onCropImageMatrixChangedListener);
}
