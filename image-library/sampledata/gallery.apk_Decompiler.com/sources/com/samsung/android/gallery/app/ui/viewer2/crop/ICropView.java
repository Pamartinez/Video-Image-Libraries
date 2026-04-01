package com.samsung.android.gallery.app.ui.viewer2.crop;

import android.graphics.Bitmap;
import android.graphics.RectF;
import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ICropView extends IMvpBaseView {
    void finishCropViewer(Object obj);

    RectF getRectToBeSaved();

    boolean isReadyCropView();

    void onAnimationFrameUpdated(Bitmap bitmap);

    void setDefaultImage(Bitmap bitmap);

    void setProgressCircleVisibility(boolean z);

    void onAnimationFrameStarted() {
    }
}
